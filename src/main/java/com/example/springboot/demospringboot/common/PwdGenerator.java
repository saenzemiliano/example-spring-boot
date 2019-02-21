package com.example.springboot.demospringboot.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PwdGenerator {

	private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String DIGITS = "0123456789";
	private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";
	private boolean useLower;
	private Integer useLowerCant = 0;
	private boolean useUpper;
	private Integer useUpperCant = 0;
	private boolean useDigits;
	private Integer useDigitsCant = 0;
	private boolean usePunctuation;
	private Integer usePunctuationCant = 0;

	private static final Map<String, String> RADIO_ALPHABETIC_CODE;

	static {
		RADIO_ALPHABETIC_CODE = new HashMap<String, String>();
		RADIO_ALPHABETIC_CODE.put("a", "a Minuscula");
		RADIO_ALPHABETIC_CODE.put("b", "b Minuscula");
		RADIO_ALPHABETIC_CODE.put("c", "c Minuscula");
		RADIO_ALPHABETIC_CODE.put("d", "d Minuscula");
		RADIO_ALPHABETIC_CODE.put("e", "e Minuscula");
		RADIO_ALPHABETIC_CODE.put("f", "f Minuscula");
		RADIO_ALPHABETIC_CODE.put("g", "g Minuscula");
		RADIO_ALPHABETIC_CODE.put("h", "h Minuscula");
		RADIO_ALPHABETIC_CODE.put("i", "i Minuscula");
		RADIO_ALPHABETIC_CODE.put("j", "j Minuscula");
		RADIO_ALPHABETIC_CODE.put("k", "k Minuscula");
		RADIO_ALPHABETIC_CODE.put("l", "l Minuscula");
		RADIO_ALPHABETIC_CODE.put("m", "m Minuscula");
		RADIO_ALPHABETIC_CODE.put("n", "n Minuscula");
		RADIO_ALPHABETIC_CODE.put("o", "o Minuscula");
		RADIO_ALPHABETIC_CODE.put("p", "p Minuscula");
		RADIO_ALPHABETIC_CODE.put("q", "q Minuscula");
		RADIO_ALPHABETIC_CODE.put("r", "r Minuscula");
		RADIO_ALPHABETIC_CODE.put("s", "s Minuscula");
		RADIO_ALPHABETIC_CODE.put("t", "t Minuscula");
		RADIO_ALPHABETIC_CODE.put("u", "u Minuscula");
		RADIO_ALPHABETIC_CODE.put("v", "v Minuscula");
		RADIO_ALPHABETIC_CODE.put("w", "w Minuscula");
		RADIO_ALPHABETIC_CODE.put("x", "x Minuscula");
		RADIO_ALPHABETIC_CODE.put("y", "y Minuscula");
		RADIO_ALPHABETIC_CODE.put("z", "z Minuscula");

		RADIO_ALPHABETIC_CODE.put("A", "A Mayuscula");
		RADIO_ALPHABETIC_CODE.put("B", "B Mayuscula");
		RADIO_ALPHABETIC_CODE.put("C", "C Mayuscula");
		RADIO_ALPHABETIC_CODE.put("D", "D Mayuscula");
		RADIO_ALPHABETIC_CODE.put("E", "E Mayuscula");
		RADIO_ALPHABETIC_CODE.put("F", "F Mayuscula");
		RADIO_ALPHABETIC_CODE.put("G", "G Mayuscula");
		RADIO_ALPHABETIC_CODE.put("H", "H Mayuscula");
		RADIO_ALPHABETIC_CODE.put("I", "I Mayuscula");
		RADIO_ALPHABETIC_CODE.put("J", "J Mayuscula");
		RADIO_ALPHABETIC_CODE.put("K", "K Mayuscula");
		RADIO_ALPHABETIC_CODE.put("L", "L Mayuscula");
		RADIO_ALPHABETIC_CODE.put("M", "M Mayuscula");
		RADIO_ALPHABETIC_CODE.put("N", "N Mayuscula");
		RADIO_ALPHABETIC_CODE.put("O", "O Mayuscula");
		RADIO_ALPHABETIC_CODE.put("P", "P Mayuscula");
		RADIO_ALPHABETIC_CODE.put("Q", "Q Mayuscula");
		RADIO_ALPHABETIC_CODE.put("R", "R Mayuscula");
		RADIO_ALPHABETIC_CODE.put("S", "S Mayuscula");
		RADIO_ALPHABETIC_CODE.put("T", "T Mayuscula");
		RADIO_ALPHABETIC_CODE.put("U", "U Mayuscula");
		RADIO_ALPHABETIC_CODE.put("V", "V Mayuscula");
		RADIO_ALPHABETIC_CODE.put("W", "W Mayuscula");
		RADIO_ALPHABETIC_CODE.put("X", "X Mayuscula");
		RADIO_ALPHABETIC_CODE.put("Y", "Y Mayuscula");
		RADIO_ALPHABETIC_CODE.put("Z", "Z Mayuscula");

		RADIO_ALPHABETIC_CODE.put("0", "Zero");
		RADIO_ALPHABETIC_CODE.put("1", "Uno");
		RADIO_ALPHABETIC_CODE.put("2", "Dos");
		RADIO_ALPHABETIC_CODE.put("3", "Tres");
		RADIO_ALPHABETIC_CODE.put("4", "Cuatro");
		RADIO_ALPHABETIC_CODE.put("5", "Cinco");
		RADIO_ALPHABETIC_CODE.put("6", "Seis");
		RADIO_ALPHABETIC_CODE.put("7", "Siete");
		RADIO_ALPHABETIC_CODE.put("8", "Ocho");
		RADIO_ALPHABETIC_CODE.put("9", "Nueve");
	}

	private PwdGenerator() {
		throw new UnsupportedOperationException("Empty constructor is not supported.");
	}

	private PwdGenerator(PwdGeneratorBuilder builder) {
		this.useLower = builder.useLower;
		this.useLowerCant = builder.useLowerCant;
		this.useUpper = builder.useUpper;
		this.useUpperCant = builder.useUpperCant;
		this.useDigits = builder.useDigits;
		this.useDigitsCant = builder.useDigitsCant;
		this.usePunctuation = builder.usePunctuation;
		this.usePunctuationCant = builder.usePunctuationCant;
	}

	public static class PwdGeneratorBuilder {

		private boolean useLower;
		private Integer useLowerCant;
		private boolean useUpper;
		private Integer useUpperCant;
		private boolean useDigits;
		private Integer useDigitsCant;
		private boolean usePunctuation;
		private Integer usePunctuationCant;

		public PwdGeneratorBuilder() {
			this.useLower = false;
			this.useLowerCant = 0;
			this.useUpper = false;
			this.useUpperCant = 0;
			this.useDigits = false;
			this.useDigitsCant = 0;
			this.usePunctuation = false;
			this.usePunctuationCant = 0;
		}

		public PwdGeneratorBuilder useLower(boolean useLower, int cant) {
			this.useLower = useLower;
			this.useLowerCant = cant;
			return this;
		}

		public PwdGeneratorBuilder useUpper(boolean useUpper, int cant) {
			this.useUpper = useUpper;
			this.useUpperCant = cant;
			return this;
		}

		public PwdGeneratorBuilder useDigits(boolean useDigits, int cant) {
			this.useDigits = useDigits;
			this.useDigitsCant = cant;
			return this;
		}

		public PwdGeneratorBuilder usePunctuation(boolean usePunctuation, int cant) {
			this.usePunctuation = usePunctuation;
			this.usePunctuationCant = cant;
			return this;
		}

		public PwdGenerator build() {
			return new PwdGenerator(this);
		}
	}

	public String generate(int length) {
		// Argument Validation.
		if (length <= 0
				|| this.useLowerCant + this.useUpperCant + this.useDigitsCant + this.usePunctuationCant > length) {
			throw new UnsupportedOperationException("Unsupported length!");
		}

		// Variables.
		StringBuilder password = new StringBuilder(length);
		Random random = new Random(System.nanoTime());

		// Collect the categories to use.
		List<String> allCategories = new ArrayList<>(4);
		
		if (useLower) {
			allCategories.add(LOWER);
			password.append(random(random, LOWER, this.useLowerCant));
		}
		if (useUpper) {
			allCategories.add(UPPER);
			password.append(random(random, UPPER, this.useUpperCant));
		}
		if (useDigits) {
			allCategories.add(DIGITS);
			password.append(random(random, DIGITS, this.useDigitsCant));
		}
		if (usePunctuation) {
			allCategories.add(PUNCTUATION);
			password.append(random(random, PUNCTUATION, this.usePunctuationCant));
		}

		// Complete build the password.
		for (int i = 0; i < Math.abs(length - (this.useLowerCant + this.useUpperCant + this.useDigitsCant + this.usePunctuationCant)); i++) {
			String charCategory = allCategories.get(random.nextInt(allCategories.size()));
			password.append(random(random, charCategory, 1));
		}
		return shuffle(new String(password));
	}

	private char[] random(Random random, final String category, Integer cant) {
		char[] result = new char[cant];

		for (int i = 0; i < cant; i++) {
			int position = random.nextInt(category.length());
			result[i] = category.charAt(position);
		}

		return result;
	}

	public static String shuffle(String input) {
		List<Character> characters = new ArrayList<Character>();
		for (char c : input.toCharArray()) {
			characters.add(c);
		}
		StringBuilder output = new StringBuilder(input.length());
		while (characters.size() != 0) {
			int randPicker = (int) (Math.random() * characters.size());
			output.append(characters.remove(randPicker));
		}
		return output.toString();
	}

	public static String descrirptor(String pwd) {
		String descriptor = "";
		for (int i = 0; i < pwd.length(); i++) {
			String desc = pwd.charAt(i) + "";
			if (RADIO_ALPHABETIC_CODE.containsKey(pwd.charAt(i) + "")) {
				desc = RADIO_ALPHABETIC_CODE.get(pwd.charAt(i) + "");
			}
			descriptor = (i == 0 ? desc : descriptor + ", " + desc);
		}
		return descriptor;
	}
}
