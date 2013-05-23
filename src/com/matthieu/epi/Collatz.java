package com.matthieu.epi;

import java.util.*;

public class Collatz implements Solution {
    public static final int MAX_CHECK = 10000;
    private static final CollatzChecker checker = new CollatzChecker();

    public static class CollatzChecker {
        int minOK=1;
        Map<Long, Integer> checked = new HashMap<Long, Integer>();

        public void addCheck(long i) {
            if (i < MAX_CHECK)
                checked.put(i, 1);
        }
        public void flush() {
            List<Long> keys = new ArrayList<Long>(checked.keySet());
            Collections.sort(keys);
            for (long k : keys) {
                if (k==minOK+2) {
                    minOK+=2;   // this way of increasing minOK may not be sufficient...
                    checked.remove(k);
                    continue;
                }
                checked.put(k, 2);
            }
        }
        public int getStatus(long i) {
            if (i<=minOK)
                return 2; // it's good
            if (!checked.containsKey(i))
                return 0; // don't know
            else
                return checked.get(i);
        }

    }

    public static boolean checkWithSeed(long seed) {
        long start_seed = seed;

        while (true) {
            while (seed % 2 == 0) {
                seed /= 2;
            }
            if (seed < start_seed) {
                break;
            }

            int res = checker.getStatus(seed);
            switch (res) {
                case 1:
                    System.out.println("Got into a loop starting at "+start_seed);
                    return false;
                case 2:
                    checker.flush();
                    return true;
                case 0:
                    checker.addCheck(seed);
                    break;
            }

            if (seed >= Long.MAX_VALUE / 3) {
                System.out.println("Overflow starting from "+start_seed);
                return false;
            }
            seed = ((3*seed)+1)/2;
        }
        checker.flush();
        return true;
    }

    @Override
    public void solveProblem() {
        for (int i=3; i<MAX_CHECK; i+=2) {
            System.out.print("Checking " + i + '\r');
            if (!checkWithSeed(i))
                return;
        }
        System.out.println();

    }
}
