package com.matthieu.epi;

public class StringIntegerConversions implements Solution {
    public String intToString(int x) {
        if (x==0)
            return "0";

        StringBuilder sb = new StringBuilder();
        boolean is_negative = (x<0);
        x = Math.abs(x);
        while (x > 0) {
            sb.insert(0,x % 10);
            x /= 10;
        }
        if (is_negative) sb.insert(0, "-");
        return sb.toString();
    }

    public int stringToInt(String s) {
        byte[] chars = s.getBytes();
        boolean is_negative = (chars[0]=='-');
        int offset = is_negative?1:0;
        int res=0;

        while (offset < chars.length) {
            res = (res*10) + (chars[offset]-'0');
            offset++;
        }
        if (is_negative) res = -res;

        return res;
    }

    @Override
    public void solveProblem() {
        int new_test = (int) ((Math.random()*100000) - 50000);
        System.out.println("Number "+new_test+" translates into "+intToString(new_test)+" and back to "+stringToInt(intToString(new_test)));
    }
}
