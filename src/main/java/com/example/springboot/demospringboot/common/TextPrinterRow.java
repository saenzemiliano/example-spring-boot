package com.example.springboot.demospringboot.common;
public abstract class TextPrinterRow<T> {


	public abstract String rowByIndex(T t, int i) throws Exception;
	
}