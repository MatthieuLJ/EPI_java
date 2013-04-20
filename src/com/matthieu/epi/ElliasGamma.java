package com.matthieu.epi;

import java.util.ArrayList;
import java.util.Arrays;

public class ElliasGamma implements Solution {
    public static String encode(int input) {
        String binary = Integer.toBinaryString(input);
        StringBuilder prefix = new StringBuilder();
        for (int i=0; i<binary.length()-1; i++)
            prefix.append("0");
        prefix.append(binary);
        return prefix.toString();
    }
    public static String encode(int[] input) {
        StringBuilder res = new StringBuilder();
        for (int i:input) {
            res.append(encode(i));
        }
        return res.toString();
    }
    public static int[] decode(String input) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int index=0;
        int size;
        while (index < input.length()) {
            size=1;
            while (input.charAt(index)=='0') {
                index++;
                size++;
            }
            res.add(Integer.valueOf(input.substring(index, index+size),2));
            index += size;
        }
        int array[] = new int[res.size()];
        for (int i=0; i<res.size(); i++) {
            array[i] = res.get(i).intValue();
        }
        return array;
    }

    @Override
    public void solveProblem() {
        int [] input = new int[] {2,10,65,3};
        String encoded = encode(input);
        System.out.println("Array "+ Arrays.toString(input)+" gets encoded as "+encoded+" and reverted as "+Arrays.toString(decode(encoded)));
    }
}
