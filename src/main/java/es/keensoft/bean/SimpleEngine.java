package es.keensoft.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleEngine {
	
	static List<Integer> usedIds = new ArrayList<Integer>();
	static List<List<Integer>> output = new ArrayList<List<Integer>>();
	
	public static List<List<Integer>> scorePhotos(List<Photo> photos) {
		
		int currentElement = 0;
		while (usedIds.size() < photos.size()) {
			int nextElement = scorePhoto(currentElement, photos);
			currentElement = nextElement;
		}
		return output;
		
	}
	
	public static Integer scorePhoto(Integer currentElement, List<Photo> photos) {
		
		Integer next = -1;
		usedIds.add(currentElement);
		
		if (photos.get(currentElement).getVertical()) {
			
			// find next vertical
			int j = 0;
			for (j = 0; j < photos.size(); j++) {
				if (!usedIds.contains(photos.get(j).getId()) && photos.get(j).getVertical()) {
					break;
				}
			}
			
			if (j < photos.size()) {
				
				usedIds.add(j);
				Photo p = Photo.mixPhotos(photos.get(currentElement), photos.get(j));
				
				output.add(Arrays.asList(currentElement, j));
				next = getBestOption(p, photos);
				
			} else {
				
				throw new RuntimeException("No more vertical photos found!");
				
			}
			
			
		} else {
			// find best choice
			output.add(Arrays.asList(currentElement));
			next = getBestOption(photos.get(currentElement), photos);
		}
		
		return next;
	}
	
	private static Integer getBestOption(Photo photo1, List<Photo> photos) {
		Integer maxValue = 0;
		Integer maxPosition = -1;
		for (int i = 0; i < photos.size(); i++) {
			if (photo1.getId() != i && !usedIds.contains(i)) {
				Integer value = compare(photo1, photos.get(i));
				if (value > maxValue) {
					maxPosition = i;
				}
			}
		}
		return maxPosition;
	}
	
	public static Integer compare(Photo photo1, Photo photo2) {

        List<String> common = new ArrayList<>(photo1.getTags());
        common.retainAll(photo2.getTags());

        System.out.println(common);

        List<String> only1 = photo1.getTags().stream().filter(item -> !common.contains(item)).collect(Collectors.toList());
        List<String> only2 = photo2.getTags().stream().filter(item -> !common.contains(item)).collect(Collectors.toList());

        System.out.println(only1);
        System.out.println(only2);
        return Math.min(common.size(), Math.min(only1.size(), only2.size()));
        
    }
}
