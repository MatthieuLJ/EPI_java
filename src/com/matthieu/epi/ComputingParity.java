package com.matthieu.epi;

public class ComputingParity implements Solution {
    int known_parities[] = null;

    public ComputingParity() {
        known_parities = new int[0x100];
        for (int i=0; i<=0xff; i++) {
            known_parities[i] = parity2(i);
        }
    }

    private int parity1(long input) {
        int res=0;

        while(input != 0) {
            if ((input & 1) != 0)
                res ^= 1;
            input >>>= 1;
        }

        return res;
    }

    private int parity2(long input) {
        int res=0;

        while(input != 0) {
            input = input & (input-1);
            res ^= 1;
        }

        return res;
    }

    private int parity3(long input) {
        int res = 0;
        res ^= known_parities[(int) (input >>> 56) & 0xff];
        res ^= known_parities[(int) (input >>> 48) & 0xff];
        res ^= known_parities[(int) (input >>> 40) & 0xff];
        res ^= known_parities[(int) (input >>> 32) & 0xff];
        res ^= known_parities[(int) (input >>> 24) & 0xff];
        res ^= known_parities[(int) (input >>> 16) & 0xff];
        res ^= known_parities[(int) (input >>> 8) & 0xff];
        res ^= known_parities[(int) (input) & 0xff];

        return res;
    }

    @Override
    public void solve_problem() {
        final int num_tests=5;

        for (int i=0; i<num_tests; i++) {
            long new_test = (long) (Math.random() * Long.MAX_VALUE) / 2;
            System.out.println("Parity for "+new_test+" is "+parity1(new_test)+" or "+parity2(new_test)+" or "+parity3(new_test));
        }
    }
}
