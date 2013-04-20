package com.matthieu.epi;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ComputeXPowerN implements Solution {
    static HashMap<Integer, ArrayList<HashSet<Integer>>> cache = new HashMap<Integer, ArrayList<HashSet<Integer>>>();
             // cache.get(i) contains the list of possibilities to get to X^i

    public static boolean sameSet(HashSet<Integer> a, HashSet<Integer> b) {
        if (a.size() != b.size())
            return false;
        for (int aItem : a) {
            if (!b.contains(aItem))
                return false;
        }
        return true;
    }

    public static int fastestPower(int n) {
        cache.put(1, new ArrayList<HashSet<Integer>>());
        cache.get(1).add(new HashSet<Integer>());
            // nothing really needed to build X^1

        for (int i=2; i<=n; i++) {
            for (int j=1; j<=Math.floor(i/2); j++) {
                // we want to build the possibilities to build X^i based on X^j x X^(i-j)
                for (HashSet<Integer> Xj : cache.get(j)) {
                    for (HashSet<Integer> Xi_j : cache.get(i-j)) {
                        HashSet<Integer> merge = new HashSet<Integer>();
                        merge.addAll(Xj);
                        merge.addAll(Xi_j);
                        merge.add(i);
                        merge.add(j);
                        merge.remove(1);
                        if ((cache.get(i)!=null) && (cache.get(i).size() > 0) && (cache.get(i).get(0).size() < merge.size())) {
                            // we already have a better way
                            continue;
                        }
                        if ((cache.get(i)!=null) && (cache.get(i).size() > 0) && (cache.get(i).get(0).size() > merge.size())) {
                            // everything we had was worth
                            cache.remove(i);
                        }
                        if (cache.get(i) == null) {
                            cache.put(i, new ArrayList<HashSet<Integer>>());
                        }

                        // make sure we're not adding the same set several times
                        boolean already_exists=false;
                        for (HashSet<Integer> existing : cache.get(i)) {
                            if (sameSet(existing, merge)) {
                                already_exists=true;
                                break;
                            }
                        }
                        if (!already_exists)
                            cache.get(i).add(merge);
                    }
                }
            }
            //System.out.println("To build power "+i+" got "+cache.get(i));
        }
        return cache.get(n).get(0).size();
    }

    @Override
    public void solveProblem() {
        System.out.println("Fastest way to build exponent 26 is with "+fastestPower(26)+" operations");
    }
}
