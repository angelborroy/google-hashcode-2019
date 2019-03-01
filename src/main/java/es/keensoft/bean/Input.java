package es.keensoft.bean;

import java.util.List;

public class Input {

	private Integer photosCount;
	private List<Photo> photos;

	public Integer getPhotosCount() {
		return photosCount;
	}

	public void setPhotosCount(Integer photosCount) {
		this.photosCount = photosCount;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photo) {
		this.photos = photo;
	}

	@Override
	public String toString() {
		return "Input [photosCount=" + photosCount + ", photo=" + photos + "]";
	}

}
