package es.keensoft.bean;

import java.util.List;

public class Output {

	private Integer slidesCount;
	private List<List<Integer>> slidesLines;

	public Integer getSlidesCount() {
		return slidesCount;
	}

	public void setSlidesCount(Integer slidesCount) {
		this.slidesCount = slidesCount;
	}

	public List<List<Integer>> getSlidesLines() {
		return slidesLines;
	}

	public void setSlidesLines(List<List<Integer>> slidesLines) {
		this.slidesLines = slidesLines;
	}

	@Override
	public String toString() {
		return "Output [slidesCount=" + slidesCount + ", slidesLine=" + slidesLines + "]";
	}

}
