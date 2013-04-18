package com.matthieu.epi;

import java.util.ArrayList;

public class DefectiveJugs implements Solution {
    private static class LowHigh {
        int low;
        int high;
        @Override
        public String toString() {
            return "["+low+","+high+"]";
        }
        public LowHigh(int l, int h) {
            low=l; high=h;
        }
        public boolean contains(LowHigh other) {
            return low<=other.low && high>=other.high;
        }
    }

    static ArrayList<LowHigh> cache = new ArrayList<LowHigh>();

    public static boolean isPossible(ArrayList<LowHigh> jugs, LowHigh target) {
        if ((target.low<0) || (target.high<0) || (target.low>target.high))
            return false;
        if (cache.contains(target))
            return false;

        for (LowHigh j : jugs) {
            if ((target.contains(j)) || (isPossible(jugs, new LowHigh(target.low-j.low, target.high-j.high)))) {
                System.out.println("Using jug "+j);
                return true;
            }
        }
        cache.add(target);
        return false;
    }


    @Override
    public void solveProblem() {
        ArrayList<LowHigh> jugs = new ArrayList<LowHigh>();
        jugs.add(new LowHigh(230,240));
        jugs.add(new LowHigh(290,310));
        jugs.add(new LowHigh(500,515));

        System.out.println("Can make the recipe 1 ? "+isPossible(jugs, new LowHigh(2100,2300)));
    }
}
