package com.matthieu.epi;

public class SpreadSheetDecoding implements Solution {
    public static int decodeColID(String col) {
        int res=0;
        for (int i=0; i<col.length(); i++) {
            char nextChar = col.charAt(i);
            res *= 26;
            res += nextChar - 'A' + 1;
        }
        return res;
    }

    @Override
    public void solveProblem() {
        System.out.println("Index for column B is "+decodeColID("B"));
        System.out.println("Index for column AA is "+decodeColID("AA"));
    }
}
