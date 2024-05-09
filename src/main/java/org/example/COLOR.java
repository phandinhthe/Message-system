package org.example;

public enum COLOR {
	BLACK("\u001B[0m"),
	ORANGE("\u001B[38;5;208m"),
	RED("\u001B[31m"),
	GREEN("\u001B[32m");

	private final String encodedColor;

	COLOR(String encodedColor) {
		this.encodedColor = encodedColor;
	}

	public String encodedColor() {
		return encodedColor;
	}
}
