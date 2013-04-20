package com.matthieu.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnumeratingPrimes implements Solution {
    public static List<Integer> primesUpTo(int n) {
        boolean primes[] = new boolean[n+1];
        Arrays.fill(primes, true);
        primes[0]=false;
        primes[1]=false;
        int index=0;
        while (index <= n) {
            if (!primes[index]) {
                index++;
                continue;
            }
            int i=2;
            while (index*i <= n) {
                primes[index*i]=false;
                i++;
            }
            index++;
        }

        List<Integer> res = new ArrayList<Integer>();
        for (int i=0; i<=n; i++) {
            if (primes[i])
                res.add(i);
        }

        return res;
    }

    @Override
    public void solveProblem() {
        System.out.println("Primes to 100: "+primesUpTo(100));
    }
}
