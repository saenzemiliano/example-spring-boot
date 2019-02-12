/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.common;

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
        Iterable<String> it = EmailGenerator.generate("@company-enzo.com.uy", "Juàn", "", "Herñadez", "");
        for (String cand : it) {
            long startTime = System.currentTimeMillis();
            System.out.print("Validating....["+ cand + "]...");
            if(Tools.isEmailValidAlternaive(cand)) {
                System.out.print("It's valid: [" + cand + "]");
            } else {
                System.out.print("It's not valid: [" + cand + "]");
            }
            long estimatedTime = System.currentTimeMillis() - startTime;
            System.out.println("...Time.."+estimatedTime+".."+ estimatedTime/1000 + "s");
        }
        
    }

    public static Iterable<String> generate(String domain, String name1, String name2, String surname1, String surname2) {
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

        candidates = replaceDot(candidates, VALID_CHARACTERS, domain);

        return candidates;
    }

    private static List<String> replaceDot(List<String> candidates, String[] chracts, String domain) {
        List<String> cands = new LinkedList<>();

        for (String chract : chracts) {
            candidates.forEach((candidate) -> {
                String email = unaccent(candidate).replace("|", chract).toLowerCase() + domain;
                cands.add(email);
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
