package es.keensoft.bean;

import java.util.HashSet;

public class Photo {
	
	Integer id;
	Boolean vertical;
	Integer tagsCount;
	HashSet<String> tags;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getVertical() {
		return vertical;
	}
	public void setVertical(Boolean vertical) {
		this.vertical = vertical;
	}
	public Integer getTagsCount() {
		return tagsCount;
	}
	public void setTagsCount(Integer tagsCount) {
		this.tagsCount = tagsCount;
	}
	public HashSet<String> getTags() {
		return tags;
	}
	public void setTags(HashSet<String> tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "Photo [id=" + id + ", vertical=" + vertical + ", tagsCount=" + tagsCount + ", tags=" + tags + "]";
	}
	
	public static Photo mixPhotos(Photo photo1, Photo photo2) {
		Photo photo = new Photo();
		photo.setId(photo1.getId());
		photo.setVertical(photo1.getVertical());
		photo.setTagsCount(photo1.getTagsCount() + photo2.getTagsCount());
		HashSet<String> tags = new HashSet<String>();
		tags.addAll(photo1.getTags());
		tags.addAll(photo2.getTags());
		photo.setTags(tags);
		return photo;
	}
	
}
