package com.matthieu.epi;

public class BitReversal implements Solution {
    long known_reversed[] = null;

    {{
        known_reversed = new long[0x10000];
        for (int i=0; i<=0xffff; i++) {
            known_reversed[i] = reverse16(i);
        }
    }}

    private int reverse16(int input) {
        int res=0;

        for (int i=0; i<16; i++) {
            if ((input & (1 << i)) != 0)
                res |= (1 << (16-i));
        }

        return res;
    }

    private long reverse(long input) {
        long res=0;
        res = known_reversed[(int) (input & 0xffff)];
        res <<= 16;
        res |= known_reversed[(int) (input >> 16) & 0xffff];
        res <<= 16;
        res |= known_reversed[(int) (input >> 32) & 0xffff];
        res <<= 16;
        res |= known_reversed[(int) (input >> 48) & 0xffff];

        return res;
    }

    @Override
    public void solveProblem() {
        long number = (long) (Math.random() * Long.MAX_VALUE);

        System.out.println("Reversal of "+number+" is "+reverse(number));
        System.out.println(Long.toBinaryString(number)+" becomes "+Long.toBinaryString(reverse(number)));
    }
}
