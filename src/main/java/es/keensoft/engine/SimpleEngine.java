package es.keensoft.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.keensoft.bean.Photo;
import es.keensoft.bean.Slide;
import es.keensoft.bean.Taggable;

/**
 * Simple Engine to compile Slide Show Deck.
 * 
 * 1. Compile slides from Vertical Photos finding tags clouds 
 *    without common strings or with the minimal common strings
 * 2. Take first slide and find the first one returning 
 *    the max value for Google formula
 * 3. Continue iterating till no more slides left
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

	// Criteria to stop pairing vertical photos, this means that two vertical 
	// photos have only N tags in common.
	// Optimal is 0
	public static Integer STOP_PAIRING_V_VALUE = 0;
	
	// Criteria to stop pairing slides.
	// Optimal is "max(countTags) / 2" or upper
	public static Integer STOP_PAIRING_H_VALUE = Integer.MAX_VALUE;
	
	// Compile a Slide Deck from a photo list
	public static List<List<Integer>> getSlideShow(List<Photo> photos) {

		System.out.println("V CRITERIA : " + STOP_PAIRING_V_VALUE);
		System.out.println("H CRITERIA : " + STOP_PAIRING_H_VALUE);
		
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
								if (value <= STOP_PAIRING_V_VALUE) {
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

	// The more different the tag cloud, the better. As it will maximize results
	// in slide comparison.
	public static Integer compareVerticalPhoto(Taggable photo1, Taggable photo2) {
		List<String> common = new ArrayList<>(photo1.getTags());
		common.retainAll(photo2.getTags());
		return common.size();
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
			if (!usedIds[i]) {
				Integer value = compareSlide(slide, slides.get(i));
				if (value > maxValue) {
					maxPosition = i;
					maxValue = value;
				}
				if (value >= STOP_PAIRING_H_VALUE) {
					return maxPosition;
				}
			}
		}
		return maxPosition;
	}

	// Scoring algorithm from Google Problem Statement
	public static Integer compareSlide(Taggable slide1, Taggable slide2) {

		int countSlide1 = slide1.getTags().size();
		int countSlide2 = slide2.getTags().size();
		
		List<String> common = new ArrayList<>(slide1.getTags());
		common.retainAll(slide2.getTags());

		int countCommon = common.size();
		int countOnly1 = countSlide1 - countCommon;
		int countOnly2 = countSlide2 - countCommon;

		return Math.min(common.size(), Math.min(countOnly1, countOnly2));

	}
	
}
