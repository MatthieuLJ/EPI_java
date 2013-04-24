package com.matthieu.epi;

import java.util.Arrays;

public class CharacterOccurrences implements Solution {
    public static int[] countOccurrences(String str) {
        byte[] bytes = str.getBytes();
        int [] res = new int[256];
        Arrays.fill(res, 0);
        for (byte b : bytes) {
            res[b]++;
        }
        System.out.print("String "+str+" has ");
        for (int i=0; i<res.length; i++) {
            if (res[i]==0)
                continue;
            System.out.print("("+((char)i)+","+res[i]+")");
        }
        System.out.println();
        return res;
    }

    @Override
    public void solveProblem() {
        countOccurrences("abcfracadfca  &^adlfc716AFND");
    }
}
