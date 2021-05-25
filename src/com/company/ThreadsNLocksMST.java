package com.company;

import java.util.Arrays;

// REF: https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/
public class ThreadsNLocksMST {
    // Number of vertices in the graph
    private final int VERTICES;
    private final int[][] graph;
    private final int CORES;

    public ThreadsNLocksMST(int[][] graph, int cores) {
        this.VERTICES = graph[0].length;
        this.graph = graph;
        this.CORES = cores;
    }


    // A utility function to find the vertex with minimum key
    // value, from the set of vertices not yet included in MST
    int minKey(int[] key, Boolean[] mstSet) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < VERTICES; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    // A utility function to print the constructed MST stored in
    // parent[]
    void printMST(int[] parent) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < VERTICES; i++)
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }

    // Function to construct and print MST for a graph represented
    // using adjacency matrix representation
    int[] primMST() {
        // Clone actual array
        int[][] graph = Arrays.stream(this.graph).map(int[]::clone).toArray(int[][]::new);

        // Array to store constructed MST
        int[] parent = new int[VERTICES];

        // Key values used to pick minimum weight edge in cut
        int[] key = new int[VERTICES];

        // To represent set of vertices included in MST
        Boolean[] mstSet = new Boolean[VERTICES];

        // Initialize all keys as INFINITE
        for (int i = 0; i < VERTICES; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0; // Make key 0 so that this vertex is picked as first vertex
        parent[0] = -1; // zFirst node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < VERTICES - 1; count++) {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < VERTICES; v++)

                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        return parent;
    }
}