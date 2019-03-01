package es.keensoft.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import es.keensoft.bean.Photo;
import es.keensoft.bean.Slide;
import es.keensoft.bean.Taggable;

/**
 * Simple Engine to compile Slide Show Deck.
 * 
 * 1. Create slides from vertical photos using a Tag Difference criteria 
 * 2. Compile the deck by using Google Scoring Algorithm
 *
 */
public class SimpleEngine {

	// Control variables for used IDs
	static boolean[] usedIds;
	static Integer usedCount = 0;

	// Slides deck
	static List<Slide> slides;

	// Output format for the output file
	static List<List<Integer>> output = new ArrayList<List<Integer>>();

	// Criteria to stop searching vertical photos, this means that two vertical 
	// photos have only 1 tag in common.
	// Optimal would be 0
	static Integer STOP_VALUE = 1;

	// Compile a Slide Deck from a photo list
	public static List<List<Integer>> getSlideShow(List<Photo> photos) {

		System.out.println("Compiling slides...");
		initSlides(photos);
		usedIds = new boolean[slides.size()];

		System.out.println("Ordering slides...");
		int currentElement = 0;
		int count = 0;
		while (usedCount < slides.size()) {
			int nextElement = getNextSlide(currentElement);
			currentElement = nextElement;
			System.out.println(++count + "/" + slides.size());
		}
		return output;

	}

	// Build slides from horizontal and vertical photos
	private static void initSlides(List<Photo> photos) {
		slides = new ArrayList<Slide>();
		usedIds = new boolean[photos.size()];
		for (int i = 0; i < photos.size(); i++) {
			if (!usedIds[i]) {
				usedIds[i] = true;
				if (photos.get(i).isVertical()) {
					int j = i;
					Integer minValue = Integer.MAX_VALUE;
					Integer minPosition = -1;
					for (j = i + 1; j < photos.size(); j++) {
						if (!usedIds[j] && photos.get(j).isVertical()) {
							Integer value = compareVerticalPhoto(photos.get(i), photos.get(j));
							if (value < minValue) {
								minPosition = j;
								if (value <= STOP_VALUE) {
									break;
								}
							}
						}
					}
					Slide s = new Slide();
					s.setId(slides.size());
					s.setPhotos(Arrays.asList(photos.get(i), photos.get(minPosition)));
					usedIds[minPosition] = true;
					slides.add(s);
				} else {
					Slide s = new Slide();
					s.setId(slides.size());
					s.setPhotos(Arrays.asList(photos.get(i)));
					slides.add(s);
				}
			}
			System.out.println((i + 1) + "/" + photos.size());
		}
	}

	// Calculate next slide by getting best option scoring
	public static Integer getNextSlide(Integer currentElement) {

		Integer next = -1;
		usedIds[currentElement] = true;
		usedCount++;

		output.add(slides.get(currentElement).getIds());
		next = getBestOption(slides.get(currentElement));

		return next;
	}

	// Get best scoring option for next slide
	private static Integer getBestOption(Slide slide) {
		Integer maxValue = -1;
		Integer maxPosition = -1;
		for (int i = 0; i < slides.size(); i++) {
			if (slide.getId() != i && !usedIds[i]) {
				Integer value = compareSlide(slide, slides.get(i));
				if (value > maxValue) {
					maxPosition = i;
				}
			}
		}
		return maxPosition;
	}

	// Scoring algorithm from Google Problem Statement
	public static Integer compareSlide(Taggable slide1, Taggable slide2) {

		List<String> common = new ArrayList<>(slide1.getTags());
		common.retainAll(slide2.getTags());

		List<String> only1 = slide1.getTags().stream().filter(item -> !common.contains(item)).collect(Collectors.toList());
		List<String> only2 = slide2.getTags().stream().filter(item -> !common.contains(item)).collect(Collectors.toList());

		return Math.min(common.size(), Math.min(only1.size(), only2.size()));

	}

	// The more different the tag cloud, the better. As it will maximize results
	// in slide comparison.
	public static Integer compareVerticalPhoto(Taggable photo1, Taggable photo2) {
		List<String> common = new ArrayList<>(photo1.getTags());
		common.retainAll(photo2.getTags());
		return common.size();
	}

}
