package com.matthieu.epi;

import java.util.*;

public class PhoneNumberMnemonic implements Solution {
    public void mnemonic(String number, Map<String, ArrayList<String>> mapping, String prefix) {
        if (number.length()==0) {
            System.out.println(prefix);
            return;
        }

        for (String item : mapping.get(number.substring(0,1))) {
            mnemonic(number.substring(1), mapping, prefix+item);
        }
    }

    @Override
    public void solveProblem() {
        Map<String, ArrayList<String>> mapping = new HashMap<String, ArrayList<String>>();
        mapping.put("0", new ArrayList<String>(Arrays.asList("0")));
        mapping.put("1", new ArrayList<String>(Arrays.asList("A", "B", "C")));
        mapping.put("2", new ArrayList<String>(Arrays.asList("D", "E", "F")));
        mapping.put("3", new ArrayList<String>(Arrays.asList("G", "H", "I")));
        mapping.put("4", new ArrayList<String>(Arrays.asList("J", "K", "L")));
        mapping.put("5", new ArrayList<String>(Arrays.asList("M", "N", "O")));
        mapping.put("6", new ArrayList<String>(Arrays.asList("P", "Q", "R", "S")));
        mapping.put("7", new ArrayList<String>(Arrays.asList("T", "U", "V")));
        mapping.put("8", new ArrayList<String>(Arrays.asList("W", "X", "Y", "Z")));
        mapping.put("9", new ArrayList<String>(Arrays.asList("9")));

        int new_test = (int) (Math.random()*1000);
        System.out.println("Phone number "+new_test+" has the following mnemonics:");
        mnemonic(Integer.toString(new_test), mapping, "");
    }
}
