package com.matthieu.epi;

public class ClosestSameWeight implements Solution {
    @Override
    public void solveProblem() {
        long new_test = (long) (Math.random() * Long.MAX_VALUE);

        int i=0;
        while ((((new_test >> i) & 0x1) == ((new_test >> (i+1)) & 0x1)) && (i<63))
            i++;

        if (i == 63)
            System.out.println("All bits are 0 or 1 ("+new_test+")");

        System.out.println("Closest number with same weight to "+new_test+" is "+SwapBits.swap(new_test, i, i+1));
        System.out.println("Change at bit "+i+" from "+Long.toBinaryString(new_test)+" to "+Long.toBinaryString(SwapBits.swap(new_test, i, i+1)));
    }
}
