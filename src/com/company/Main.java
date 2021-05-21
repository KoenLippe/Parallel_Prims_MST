package com.company;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // write your code here

         /* Let us create the following graph
            2    3
        (0)--(1)--(2)
        | / \ |
        6| 8/ \5   |7
        | /     \ |
        (3)-------(4)
            9         */

        /*
            Edge   Weight
            0 - 1    2
            1 - 2    3
            0 - 3    6
            1 - 4    5

         */

        int graph2[][] = new int[][]{
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
        };


        int graph[][] = new int[][]{
                {0,5,7,3,4,6,8,5,3,3,10,1,9,4,3,2,2,2,4,4},
                {3,0,3,5,8,6,6,4,10,6,1,5,1,10,5,10,2,10,1,7},
                {1,8,0,10,5,10,6,1,10,3,9,1,4,8,7,7,5,8,3,2},
                {3,3,1,0,7,2,2,4,7,3,5,3,10,4,8,2,1,10,10,9},
                {5,3,2,9,0,1,6,2,1,5,9,2,9,1,4,9,2,9,5,10},
                {1,1,4,8,10,0,9,8,10,4,7,7,4,7,2,10,4,3,7,8},
                {5,1,3,3,10,10,0,6,10,7,2,10,6,10,7,5,9,10,6,7},
                {8,6,4,4,3,1,8,0,3,2,5,8,8,3,6,6,9,6,8,10},
                {2,4,1,9,8,2,9,4,0,8,4,6,7,2,10,8,7,2,5,1},
                {7,3,7,5,9,7,2,8,3,0,5,5,4,10,3,9,10,1,6,6},
                {8,6,3,10,10,6,7,4,9,2,0,5,3,2,8,6,6,9,6,9},
                {6,7,2,10,3,10,5,3,1,5,8,0,5,9,4,7,9,4,2,8},
                {2,9,1,9,3,4,3,8,5,4,9,6,0,7,2,4,4,4,4,10},
                {6,1,7,2,3,9,1,9,7,4,7,9,4,0,8,4,6,2,6,8},
                {8,3,2,2,6,9,10,1,7,7,10,5,4,9,0,10,5,8,1,8},
                {1,6,3,2,7,6,1,8,7,6,3,4,9,8,2,0,10,1,6,4},
                {10,10,10,8,7,7,6,7,4,10,1,3,2,3,3,7,0,1,9,4},
                {8,6,10,6,8,9,7,5,3,2,5,9,7,2,4,10,6,0,10,7},
                {4,8,8,7,10,10,8,8,2,9,6,8,2,3,3,2,2,2,0,8},
                {10,4,3,10,8,3,3,2,9,7,9,7,9,8,10,10,1,10,7,0},
        };


        SequentialMST t = new SequentialMST(graph);
        // TODO: validate number of edges


        // TODO Uitlegen waarom ROOT niet meedoet
        int[] MST = t.primMST();
        int[] MSTwithouthRoot = Arrays.stream(MST).filter(x -> x != -1).toArray();
        System.out.println(MSTwithouthRoot.length);
        System.out.println(graph[0].length);
        System.out.println();
        System.out.println(Arrays.toString(MST));
        System.out.println();
        t.printMST(MST);

        // TODO: Check if number of edges in MST has length of VERTICES-1
    }
}
