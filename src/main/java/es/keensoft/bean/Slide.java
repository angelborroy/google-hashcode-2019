package es.keensoft.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Slide implements Taggable {

	Integer id;
	List<Photo> photos;
	HashSet<String> tags;

	public List<Photo> getPhotos() {
		return photos;
	}

	public List<Integer> getIds() {
		List<Integer> ids = new ArrayList<Integer>();
		for (Photo p : photos) {
			ids.add(p.getId());
		}
		return ids;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public HashSet<String> getTags() {
		if (tags == null) {
			tags = new HashSet<String>();
			for (Photo p : photos) {
				tags.addAll(p.getTags());
			}
		}
		return tags;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
