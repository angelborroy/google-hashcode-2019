package es.keensoft.bean;

import java.util.List;

public class InputLine {

	private List<String> numbers;

	public List<String> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "InputLine [numbers=" + numbers + "]";
	}

}
