// Week3
// you are provided coordinates of houses in 2D map and write an algorithm that will print
// optimal path to construct a road connecting all houses with minimum cost. cost between 
// two coordinates is the Manhattan distance between them.
// Input: [{0,0}, {2,2}, {3,10}, {4,4}, {5,2}, {7,0}]
// Output: {0,0} to {2,2}
//  {2,2} to {3,10}
// {2,2} to {4,4}
//  {4,4} to {5,2}
// {5,2} to {0,7}
//  Minimum cost: 24


package Week3;


 // Java program for the above approach
import java.util.*;

// Class for DSU implementation
class DSU {

    int parent[];
    int size[];

    // Constructor for class DSU
    DSU(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
            size[i] = 1;
        }
    }

    // Utility function to find set of an
    // element v using path compression
    // technique
    int find_set(int v) {

        // If v is the parent
        if (parent[v] == -1)
            return v;

        // Otherwise, recursively
        // find its parent
        return parent[v] = find_set(parent[v]);
    }

    // Function to perform union
    // of the sets a and b
    void union_sets(int a, int b) {

        // Find parent of a and b
        int firstpoint = find_set(a);
        int secondpoint = find_set(b);

        // If parent are different
        if (firstpoint != secondpoint) {

            // Swap Operation
            if (size[firstpoint] > size[secondpoint]) {
                parent[secondpoint] = firstpoint;
                size[firstpoint] += size[secondpoint];
            } else {
                parent[firstpoint] = secondpoint;
                size[secondpoint] += size[firstpoint];
            }
        }
    }
}

class GFG {

    // Function to create a Minimum Cost
    // Spanning tree for given houses
    static void MST(int houses[][], int n) {
        int cost = 0;
        ArrayList<int[]> edges = new ArrayList<>();

        // Travelling each points
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                // Find the Manhattan distance
                int p = Math.abs(houses[i][0] -
                        houses[j][0]);

                p += Math.abs(houses[i][1] -
                        houses[j][1]);

                // Add the edges
                edges.add(new int[] { p, i, j });
            }
        }

        // Sorting arraylist using custome comparator
        // on the basis of weight i.e first element in
        // array object stored in Arraylist
        Collections.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        // Class call DSU
        DSU d = new DSU(n);
        for (int i = 0; i < edges.size(); i++) {
            int from = edges.get(i)[1];
            int to = edges.get(i)[2];

            // Checking if they lie in different component
            // or not i.e they have same parent or not in
            // DSU
            if (d.find_set(from) != d.find_set(to)) {

                // Calling union_sets
                d.union_sets(from, to);
                cost += edges.get(i)[0];
            }
        }

        // Printing the minimum cost
        System.out.println("The minimum cost is " + cost);
    }

    
    public static void main(String args[]) {

        // Creating graph in 2D form
        int houses[][] = { { 0, 0 }, { 2, 2 }, { 3, 10 }, { 4, 4 }, { 5, 2 }, { 7, 0 } };
        int n = houses.length;

        // Function Call for MST
        MST(houses, n);
    }
}
