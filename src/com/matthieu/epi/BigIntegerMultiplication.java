package com.matthieu.epi;

import java.util.Arrays;

public class BigIntegerMultiplication implements Solution {
    public String multiply(String a, String b) {
        byte solution[] = new byte[a.length()+b.length()];
        Arrays.fill(solution, (byte) '0');

        boolean is_negative = a.substring(0,1).equals("-") ^ b.substring(0,1).equals("-");
        if (a.substring(0,1).equals("-"))
            a=a.substring(1);
        if (b.substring(0,1).equals("-"))
            b=b.substring(1);

        byte []multiplicand, multiplier;
        if (a.length()> b.length()) {
            multiplicand = a.getBytes();
            multiplier = b.getBytes();
        } else {
            multiplicand = b.getBytes();
            multiplier = a.getBytes();
        }

        for (int i=0; i<multiplier.length; i++) {
            int multiplier_digit=multiplier[multiplier.length-1-i]-'0';
            if (multiplier_digit == 0)
                continue;
            int carry=0;
            int j;
            for (j=0; j<multiplicand.length; j++) {
                int multiplicand_digit = multiplicand[multiplicand.length-1-j]-'0';
                int current_result = solution[solution.length-i-j-1]-'0';
                current_result += carry + multiplicand_digit * multiplier_digit;
                carry = current_result/10;
                current_result %= 10;
                solution[solution.length-i-j-1] = (byte) (current_result + '0');
            }
            while (carry != 0) {
                solution[solution.length-i-j-1] = (byte) ((carry % 10)+'0');
                carry /= 10;
                j++;
            }

        }

        String res = new String(solution);
        if (is_negative)
            res = "-"+res;

        return res;
    }

    @Override
    public void solveProblem() {
        int mul_a = (int) (Math.random()* 10000);
        int mul_b = (int) (Math.random()* 10000);
        System.out.println("Multiplication of "+mul_a+" by "+mul_b+" is "+multiply(Integer.toString(mul_a), Integer.toString(mul_b))+" = "+ (mul_a*mul_b));
    }
}
