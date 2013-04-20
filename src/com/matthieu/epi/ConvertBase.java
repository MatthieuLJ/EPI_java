package com.matthieu.epi;

public class ConvertBase implements Solution {
    public static long fromBase(String input, int b) {
        boolean isNegative=input.substring(0,1).equals("-");
        long res=0;
        int start_index = isNegative?1:0;
        for (int i=start_index; i<input.length(); i++) {
            res=res*b;
            byte nextChar = input.substring(i, i+1).toUpperCase().getBytes()[0];
            int nextValue;
            if ((nextChar>='A') && (nextChar<='F'))
                nextValue = nextChar-'A'+10;
            else
                nextValue = nextChar-'0';
            res+=nextValue;
        }
        res = res * (isNegative?-1:1);
        return res;
    }

    public static String toBase(long input, int b) {
        StringBuffer res = new StringBuffer();
        boolean isNegative = (input < 0);
        if (isNegative) input=-input;
        while (input != 0) {
            long nextValue = input % b;
            char nextChar;
            if (nextValue>=10)
                nextChar = (char) ('A'+nextValue-10);
            else
                nextChar = (char) ('0'+nextValue);
            res.insert(0,nextChar);
            input /= b;
        }
        if (isNegative)
            res.insert(0, '-');
        return res.toString();
    }
    public static String changeBase(String input, int b1, int b2) {
        return toBase(fromBase(input, b1), b2);
    }
    @Override
    public void solveProblem() {
        System.out.println("Convert 0xaf to binary gives "+changeBase("AF", 16, 2));
        System.out.println("Convert -123 to hex gives "+changeBase("-123", 10, 16));
    }
}
