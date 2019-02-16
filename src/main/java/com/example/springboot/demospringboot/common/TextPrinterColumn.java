package com.example.springboot.demospringboot.common;
public class TextPrinterColumn {
	private final String name;
	private final Integer width;
	
	public TextPrinterColumn(String name, int width) {
		this.name = name;
		if(width < name.length()) {
			width = name.length();
		}
		this.width = width;
	}
	public Integer getWidth() {
		return width;
	}

	public String getName() {
		return name;
	}

	
	
}