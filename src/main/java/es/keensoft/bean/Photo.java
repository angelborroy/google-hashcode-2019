package es.keensoft.bean;

import java.util.List;

public class Photo {
	
	Boolean vertical;
	Integer tagsCount;
	List<String> tags;
	
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
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "Photo [vertical=" + vertical + ", tagsCount=" + tagsCount + ", tags=" + tags + "]";
	}

}
