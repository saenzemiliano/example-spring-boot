/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.model;

import java.text.Normalizer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author emiliano
 */
public class EmailGenerator {

    private static final Random RAND = new Random();
    private static final String[] VALID_CHARACTERS = {".", "", "_"};
    private static final Integer RANDOM_LIMIT = 1000;

    public static void main(String[] args) {
        Iterable<String> it = EmailGenerator.generate("Juàn", "Migüel", "Herñadez", "Marquóz");
        for (String cand : it) {
            System.out.println(cand);
        }
    }

    public static Iterable<String> generate(String name1, String name2, String surname1, String surname2) {
        //Emiliano Jose Saenz Perdomo
        List<String> candidates = new LinkedList<>();

        //emiliano.saenz
        candidates.add(name1 + "|" + surname1);
        //saenz.emiliano
        candidates.add(surname1 + "|" + name1);
        //saenz.perdomo
        candidates.add(surname1 + "|" + surname2);
        //emiliano.jose
        candidates.add(name1 + "|" + name2);
        //jose.saenz
        candidates.add(name2 + "|" + surname1);
        //e.saenz
        candidates.add(initial(name1) + "|" + surname1);
        //j.saenz
        candidates.add(initial(name2) + "|" + surname1);
        //j.perdomo
        candidates.add(initial(name2) + "|" + surname2);
        //ejose.saenz
        candidates.add(initial(name1) + name2 + "|" + surname1);
        //saenz.ejose
        candidates.add(surname1 + "|" + initial(name1) + name2);
        //ejose.perdomo
        candidates.add(initial(name1) + name2 + "|" + surname2);
        //perdomo.ejose
        candidates.add(surname2 + "|" + initial(name1) + name2);
        //emiliano.saenz{nuemro}
        for (int i = 0; i < 10; i++) {
            candidates.add(name1 + "|" + surname1 + RAND.nextInt(RANDOM_LIMIT));
        }

        candidates = replaceDot(candidates, VALID_CHARACTERS);

        return candidates;
    }

    private static List<String> replaceDot(List<String> candidates, String[] chracts) {
        List<String> cands = new LinkedList<>();

        for (String chract : chracts) {
            candidates.forEach((candidate) -> {
                cands.add(unaccent(candidate).replace("|", chract).toLowerCase());
            });
        }

        return cands;
    }

    ; 
    
    
    private static String initial(String name) {
        if (name != null && name.length() > 0) {
            return name.charAt(0) + "";
        }
        return "";
    }

    public static String unaccent(String src) {
        return Normalizer
                .normalize(src, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

}
