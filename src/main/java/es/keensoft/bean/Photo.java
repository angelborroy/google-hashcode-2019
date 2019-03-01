package es.keensoft.bean;

import java.util.HashSet;

public class Photo implements Taggable {

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

	public Boolean isVertical() {
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

}
