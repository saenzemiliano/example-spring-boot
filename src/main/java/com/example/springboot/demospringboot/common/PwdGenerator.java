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
    private boolean useUpper;
    private boolean useDigits;
    private boolean usePunctuation;
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
        this.useUpper = builder.useUpper;
        this.useDigits = builder.useDigits;
        this.usePunctuation = builder.usePunctuation;
    }

    public static class PwdGeneratorBuilder {

        private boolean useLower;
        private boolean useUpper;
        private boolean useDigits;
        private boolean usePunctuation;

        public PwdGeneratorBuilder() {
            this.useLower = false;
            this.useUpper = false;
            this.useDigits = false;
            this.usePunctuation = false;
        }

        /**
         * Set true in case you would like to include lower characters
         * (abc...xyz). Default false.
         *
         * @param useLower true in case you would like to include lower
         * characters (abc...xyz). Default false.
         * @return the builder for chaining.
         */
        public PwdGeneratorBuilder useLower(boolean useLower) {
            this.useLower = useLower;
            return this;
        }

        /**
         * Set true in case you would like to include upper characters
         * (ABC...XYZ). Default false.
         *
         * @param useUpper true in case you would like to include upper
         * characters (ABC...XYZ). Default false.
         * @return the builder for chaining.
         */
        public PwdGeneratorBuilder useUpper(boolean useUpper) {
            this.useUpper = useUpper;
            return this;
        }

        /**
         * Set true in case you would like to include digit characters (123..).
         * Default false.
         *
         * @param useDigits true in case you would like to include digit
         * characters (123..). Default false.
         * @return the builder for chaining.
         */
        public PwdGeneratorBuilder useDigits(boolean useDigits) {
            this.useDigits = useDigits;
            return this;
        }

        /**
         * Set true in case you would like to include punctuation characters
         * (!@#..). Default false.
         *
         * @param usePunctuation true in case you would like to include
         * punctuation characters (!@#..). Default false.
         * @return the builder for chaining.
         */
        public PwdGeneratorBuilder usePunctuation(boolean usePunctuation) {
            this.usePunctuation = usePunctuation;
            return this;
        }

        /**
         * Get an object to use.
         *
         * @return the {@link gr.idrymavmela.business.lib.PwdGenerator}
         * object.
         */
        public PwdGenerator build() {
            return new PwdGenerator(this);
        }
    }

    /**
     * This method will generate a password depending the use* properties you
     * define. It will use the categories with a probability. It is not sure
     * that all of the defined categories will be used.
     *
     * @param length the length of the password you would like to generate.
     * @return a password that uses the categories you define when constructing
     * the object with a probability.
     */
    public String generate(int length) {
        // Argument Validation.
        if (length <= 0) {
            return "";
        }

        // Variables.
        StringBuilder password = new StringBuilder(length);
        Random random = new Random(System.nanoTime());

        // Collect the categories to use.
        List<String> charCategories = new ArrayList<>(4);
        if (useLower) {
            charCategories.add(LOWER);
        }
        if (useUpper) {
            charCategories.add(UPPER);
        }
        if (useDigits) {
            charCategories.add(DIGITS);
        }
        if (usePunctuation) {
            charCategories.add(PUNCTUATION);
        }

        // Build the password.
        for (int i = 0; i < length; i++) {
            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
            int position = random.nextInt(charCategory.length());
            password.append(charCategory.charAt(position));
        }
        return new String(password);
    }

    public static String descrirptor(String pwd) {
		String descriptor = "";
		for (int i = 0; i < pwd.length(); i++) {
			String desc = pwd.charAt(i) + "";
			if(RADIO_ALPHABETIC_CODE.containsKey(pwd.charAt(i) + "")) {
				desc = RADIO_ALPHABETIC_CODE.get(pwd.charAt(i) + "");
			} 
			descriptor = ( i==0 ? desc : descriptor +", " + desc ) ;
		}
		return descriptor;
	}
}

