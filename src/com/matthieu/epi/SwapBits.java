package com.matthieu.epi;

public class SwapBits implements Solution {

    private long Swap(long input, int index1, int index2) {
        if (((input >> index1)&0x1) != ((input >> index2)&0x1)) {
            input ^= (1L << index1) | (1L << index2);
        }
        return input;
    }

    @Override
    public void solve_problem() {
        long number = (long) (Math.random() * Long.MAX_VALUE);
        int i = (int) (Math.random() * 64);
        int j = (int) (Math.random() * 64);

        System.out.println("Swapping bits "+i+" and "+j+" of "+Long.toHexString(number)+" gives "+Long.toHexString(Swap(number, i, j)));


    }
}
