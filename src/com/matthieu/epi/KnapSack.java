package com.matthieu.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnapSack implements Solution {
    public static class Item {
        int weight;
        int value;
        public Item(int w, int v) {
            weight = w;
            value = v;
        }
        @Override
        public String toString() {
            return "{value: "+value+", weight: "+weight+"}";
        }
    }

    private static class PossibleSolution {
        List<Item> items = new ArrayList<Item>();
        int value=0;
        public PossibleSolution(int v) { value = v; }
        public PossibleSolution(PossibleSolution p) { value = p.value; items= new ArrayList<Item>(p.items); }
    }

    public static List<Item> maximizeFit(List<Item> allItems, int weightLimit) {
        PossibleSolution possibilities[] = new PossibleSolution[weightLimit+1];
        Arrays.fill(possibilities, null);
        possibilities[0] = new PossibleSolution(0);

        for (Item item:allItems) {
            for (int i=possibilities.length-item.weight-1; i>=0; i--) {
                if ((possibilities[i] != null) && ((possibilities[i+item.weight]==null) ||
                        (possibilities[i+item.weight].value < possibilities[i].value+item.value))) {
                    PossibleSolution newBest = new PossibleSolution(possibilities[i]);
                    newBest.items.add(item);
                    newBest.value = newBest.value+item.value;
                    possibilities[i+item.weight] = newBest;
                }
            }
        }

        for (int i=possibilities.length-1; i>=0; i--) {
            if (possibilities[i]!=null)
                return possibilities[i].items;
        }
        return null;
    }


    @Override
    public void solveProblem() {
        List<Item> allItems = new ArrayList<Item>();
        allItems.add(new Item(20, 65));
        allItems.add(new Item(8, 35));
        allItems.add(new Item(60, 245));
        allItems.add(new Item(55, 195));
        allItems.add(new Item(40, 65));
        allItems.add(new Item(70, 150));
        allItems.add(new Item(85, 275));
        allItems.add(new Item(25, 155));
        allItems.add(new Item(30, 120));
        allItems.add(new Item(65, 320));
        allItems.add(new Item(75, 75));
        allItems.add(new Item(10, 40));
        allItems.add(new Item(95, 200));
        allItems.add(new Item(50, 100));
        allItems.add(new Item(40, 220));
        allItems.add(new Item(10, 99));

        System.out.println("The best solution is "+maximizeFit(allItems, 130));
    }
}
