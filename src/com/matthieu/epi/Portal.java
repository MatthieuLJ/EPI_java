package com.matthieu.epi;

import java.io.Console;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Portal {
    static HashMap<String, Class<? extends Solution>> problems;
    static {
        problems = new LinkedHashMap<String, Class<? extends Solution>>();
        problems.put("2.1", ComputingParity.class );
        problems.put("2.2", SwapBits.class );
        problems.put("2.3", BitReversal.class );
        problems.put("2.4", ClosestSameWeight.class );
        problems.put("2.5", PowerSet.class );
        problems.put("2.5.1", SubsetsOfSizeK.class );
        problems.put("2.6", StringIntegerConversions.class );
        problems.put("3.1", DutchFlag.class );
        problems.put("3.3", MaxDifference.class);
        problems.put("3.4", MaxDifferenceGeneralized.class);
        problems.put("3.9", BigIntegerMultiplication.class);
        problems.put("3.14", SudokuChecker.class );
        problems.put("3.22", PhoneNumberMnemonic.class );
        problems.put("4.1", MergeSortedLinkedList.class );
        problems.put("4.2", CheckingCyclicity.class );
        problems.put("4.4", OverlappingLists1.class );
        problems.put("4.5", OverlappingLists2.class );
        problems.put("4.6", EvenOddMerge.class );
        problems.put("4.9", ReverseLinkedList.class );
        problems.put("5.1", StackMax.class );
        problems.put("5.3", PrintingBST.class );
        problems.put("5.5", TowersOfHanoi.class );
        problems.put("5.5.1", TowersOfHanoiAlwaysUsingP3.class );
        problems.put("5.5.2", TowersOfHanoiToTheRight.class );
        problems.put("5.9", PrintingBSTLevelOrder.class );
        problems.put("5.10", CircularQueue.class );
        problems.put("5.12", QueueWithTwoStacks.class);
        problems.put("6.2", KBalancedNodes.class);
        problems.put("6.5", InOrderO1.class);
        problems.put("6.7", TreeReconstructionFromTransversalData.class );
        problems.put("6.8", TreeReconstructionFromTransversalWithMarker.class );
        problems.put("6.12", LCAWithParentField.class );
        problems.put("6.13", LCAWithParentFieldFaster.class );
        problems.put("7.1", MergingSortedFiles.class );
        problems.put("7.2", KIncreasingDecreasingSort.class );
        problems.put("7.6", KLargestStreaming.class );
        problems.put("7.7", ApproximateSort.class );
        problems.put("7.9", OnlineMedian.class );
        problems.put("8.1", FirstOccurence.class );
        problems.put("8.2", FirstElementLargerThanK.class );
        problems.put("8.3", SearchForAIeI.class );
        problems.put("8.9", SquareRoot.class );
        problems.put("8.12", MinMax.class );
        problems.put("8.13", KLargest.class );
        problems.put("9.8", AnonymousLetter.class );
        problems.put("10.5", TwoArraysIntersection.class );
        problems.put("10.6", TeamPhotoDay.class );
        problems.put("10.10", RenderingCalendar.class );
        problems.put("10.12", PointsCoveringIntervals.class );
        problems.put("11.1", CheckBSTProperty.class );
        problems.put("11.4", FirstOccurrenceInBST.class );
        problems.put("11.5", FirstKeyLargerInBST.class );
        problems.put("11.7", BSTFromSortedArray.class );
        problems.put("11.12", TraversingOrdersInBST.class );
        problems.put("12.3", NearestPoints.class );
        problems.put("12.11", LevenshteinDistance.class );
        problems.put("Affirm", HiveDistance.class );
    }

    private static void solve(String id) {
        try {
            Solution solution = problems.get(id).newInstance();
            solution.solveProblem();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) {
        Console console = System.console();

        if (console != null) {
            while(true) {
                String input = console.readLine("Enter problem to solve (q to quit):");
                if (problems.containsKey(input)) {
                    solve(input);
                } else {
                    if (input.equals("q"))
                        System.exit(0);
                    System.out.println("Cannot find that problem.");
                    System.out.println("The problems I know of are:");
                    System.out.println(problems.keySet().toString());
                }
            }
        }
        else {
            for (String problem : problems.keySet()) {
                System.out.println("### Problem "+problem);
                solve(problem);
            }
        }
    }
}
