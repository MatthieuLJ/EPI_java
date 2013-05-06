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
        problems.put("2.7", ConvertBase.class );
        problems.put("2.8", SpreadSheetDecoding.class );
        problems.put("2.9", ElliasGamma.class );
        problems.put("2.10", GreatestCommonDivisor.class );
        problems.put("2.11", EnumeratingPrimes.class );
        problems.put("2.12", IntersectingRectangles.class );
        problems.put("2.12.1", CheckAlignedRectangle.class );
        problems.put("2.12.2", NonAlignedRectanglesIntersection.class );
        problems.put("2.13", DifficultMultiplication.class );
        problems.put("2.14", Division.class );
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
        problems.put("7.12", KthLargestInHeap.class );
        problems.put("8.1", FirstOccurence.class );
        problems.put("8.2", FirstElementLargerThanK.class );
        problems.put("8.3", SearchForAIeI.class );
        problems.put("8.7", CompletionSearch.class );
        problems.put("8.8", KthElement2Lists.class );
        problems.put("8.9", SquareRoot.class );
        problems.put("8.9", DivisionApproximation.class );
        problems.put("8.12", MinMax.class );
        problems.put("8.13", KLargest.class );
        problems.put("9.8", AnonymousLetter.class );
        problems.put("10.5", TwoArraysIntersection.class );
        problems.put("10.6", TeamPhotoDay.class );
        problems.put("10.7", CharacterOccurrences.class );
        problems.put("10.8", RemoveDuplicates.class );
        problems.put("10.9", TaskAssignment.class );
        problems.put("10.10", RenderingCalendar.class );
        problems.put("10.12", PointsCoveringIntervals.class );
        problems.put("11.1", CheckBSTProperty.class );
        problems.put("11.4", FirstOccurrenceInBST.class );
        problems.put("11.5", FirstKeyLargerInBST.class );
        problems.put("11.7", BSTFromSortedArray.class );
        problems.put("11.12", TraversingOrdersInBST.class );
        problems.put("11.15", GaussianPrimes.class );
        problems.put("12.1", Skyline.class );
        problems.put("12.1.1", PyramidSkyline.class );
        problems.put("12,2", CountingInversions.class );
        problems.put("12.3", NearestPoints.class );
        problems.put("12.4", TreeDiameter.class );
        problems.put("12.6", LongestNonDecreasing.class );
        problems.put("12.6.1", LongestAlternating.class );
        problems.put("12.6.3", LongestConvex.class );
        problems.put("12.8", LargestAreaUnderSkyline.class );
        problems.put("12.11", LevenshteinDistance.class );
        problems.put("12.12", WordBreaking.class );
        problems.put("12.13", PrettyPrinting.class );
        problems.put("12.22", TutorScheduling.class );
        problems.put("12.25", HuffmanCoding.class );
        problems.put("13.1", Maze.class );
        problems.put("13.3", WiringPCB.class );
        problems.put("13.6", TheoryOfEquality.class );
        problems.put("13.7", TeamPhotoDay2.class );
        problems.put("14.2", KnapSack.class );
        problems.put("14.4", DefectiveJugs.class );
        problems.put("14.8", SudokuSolver.class );
        problems.put("14.8.1", EightQueens.class );
        problems.put("14.10", ComputeXPowerN.class );
        problems.put("14.12", Collatz.class );
        problems.put("15.3", Requester.class );
        problems.put("15.6", ReadersWriters.class );
        problems.put("15.8", ProducerConsumer.class );
        problems.put("Affirm", HiveDistance.class );
        problems.put("Combinations", EnumerateCombinations.class );
        problems.put("Permutations", EnumeratePermutations.class );
        problems.put("ReachSum", ReachSum.class );
        problems.put("Parenthesis", Parenthesis.class);
        problems.put("Flatten2DList", Flatten2DList.class );
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
