package com.matthieu.epi;

import java.util.ArrayList;
import java.util.HashMap;

public class PrettyPrinting implements Solution {
    public static class lineBreaking {
        ArrayList<Integer> breaks;
        int messiness;
        public lineBreaking(ArrayList<Integer> br, int mess) {
            messiness=mess;
            breaks=br;
        }
    }

    public static lineBreaking breakLines(int [] wordLengths, int offset, int lineLength, HashMap<Integer, lineBreaking> memory) {
        int i=1;                                         // we are going to break the next line at offset+i
        int blankSpace = lineLength-wordLengths[offset]; // blank space at the end of the line

        if (memory.containsKey(offset))
            return memory.get(offset);

        ArrayList<Integer> res = new ArrayList<Integer>();
        int leastMessiness = Integer.MAX_VALUE;

        if (offset + i >= wordLengths.length)
            return new lineBreaking(res, 0);

        while (offset+i < wordLengths.length) {
            lineBreaking tmp = breakLines(wordLengths, offset+i, lineLength, memory);

            if (tmp.messiness + Math.pow(2, blankSpace) < leastMessiness) {
                res = new ArrayList(tmp.breaks);
                res.add(0, offset+i);
                leastMessiness = tmp.messiness + (int) Math.pow(2, blankSpace);
            }

            blankSpace -= 1+wordLengths[offset+i];
            if (blankSpace<0)
                break;
            i++;
        }

        memory.put(offset, new lineBreaking(res, leastMessiness));
        return memory.get(offset);
    }

    @Override
    public void solveProblem() {
        int numWords=50;
        int [] words = new int[numWords];
        for (int i=0; i<words.length; i++) {
            words[i] = (int) (1+Math.random()*6);
        }
        int lineLength=30;

        lineBreaking res = breakLines(words, 0, lineLength, new HashMap<Integer, lineBreaking>());
        System.out.println("Optimum printing: "+res.breaks);
        int wordIndex=0;
        for (int i=0; i<res.breaks.size(); i++) {
            while (wordIndex < res.breaks.get(i)) {
                for (int j=0; j<words[wordIndex]; j++)
                    System.out.print("X");

                wordIndex++;
                if (wordIndex<res.breaks.get(i))
                    System.out.print("_");
            }
            System.out.println();
        }
    }
}
