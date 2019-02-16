package com.example.springboot.demospringboot.common;

import org.springframework.data.util.Pair;

public class TextPrinter<T> {
	private Iterable<T> iterable;
	private TextPrinterColumn[] columns;
	private TextPrinterRow<T> textPrinterRow;

	public TextPrinter(Iterable<T> it, TextPrinterColumn[] columns, TextPrinterRow<T> textPrinterRow) {
		this.iterable = it;
		this.columns = columns;
		this.textPrinterRow = textPrinterRow;
	}

	public String generate() throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		WriteHeader(stringBuilder);
		for (T t : iterable) {
			for (int i = 0; i < columns.length; i++) {
				Integer width = columns[i].getWidth() + (i < columns.length - 1 ? 1 : 0 );
				stringBuilder.append(PrinterRow(width, textPrinterRow.rowByIndex(t, i)));
			}
			stringBuilder.append("\n");
		}
		return new String(stringBuilder);
	}

	private String PrinterRow(Integer width, String value) {
		value = (Tools.isBlankOrNull(value) ? "": value);
		value = (value.length() > width ? value.substring(0, width) : value );
		return value + Repeat(" ", Math.abs(value.length() - width) );
	}

	private void WriteHeader(StringBuilder stringBuilder) {
		Pair<String, Integer> pair = Columns(columns);
		stringBuilder.append(pair.getFirst() + "\n");
		stringBuilder.append(Divider(pair.getSecond()) + "\n");
	}

	private Pair<String, Integer> Columns(TextPrinterColumn[] columns) {
		StringBuilder stringBuilder = new StringBuilder();
		Integer cant = 0;
		for (int i = 0; i < columns.length; i++) {
			TextPrinterColumn column = columns[i];
			stringBuilder.append(AddColumn(column.getName(), column.getWidth()));
			if (i < columns.length - 1) {
				stringBuilder.append("|");
			}
		}
		cant = stringBuilder.length();
		return Pair.of(new String(stringBuilder), cant);
	}

	private String AddColumn(String column, int i) {
		return column + Space(Math.abs(column.length() - i));
	}

	private String Space(int cant) {
		return Repeat(" ", cant);
	}

	private String Divider(Integer cant) {
		return Repeat("_", cant);
	}

	private String Repeat(String str0, int cant) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("");
		for (int i = 0; i < cant; i++) {
			stringBuilder.append(str0);
		}
		return new String(stringBuilder);
	}

}
