package dynamic.programming;

public class DP_10_Knapsack01
{
    /**
     * @author srragidi
     * 
     * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack. 
     * In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with n items respectively. 
     * Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that 
     * sum of the weights of this subset is smaller than or equal to W. 
     * You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
     * 
     * A simple solution is to consider all subsets of items and calculate the total weight and value of all subsets. 
     * Consider the only subsets whose total weight is smaller than W. From all such subsets, pick the maximum value subset.
     * ___________________________________________________________________________________________________________________________________________
     * Solution:
     * 
     *      1) Optimal Substructure:
               To consider all subsets of items, there can be two cases for every item: 
                     (1) the item is included in the optimal subset, 
                     (2) not included in the optimal set.
               Therefore, the maximum value that can be obtained from n items is max of following two values.
                      
                       |  1) Maximum value obtained by n-1 items and W weight (excluding nth item).
                   MAX |  
                       |  2) Value of nth item plus maximum value obtained by n-1 items and W minus weight of the nth item (including nth item).
       ___________________________________________________________________________________________________________________________________________                 
     */

    /**
     * @param n -> no of items
     * @param W -> Max weight of knapsack
     * @param weights -> weights of items
     * @param values -> values of items
     */
    public static int algorithm(int n, int W, int[] weights, int[] values)
    {
        int[][] knapsack = new int[n+1][W+1];
        
        for ( int i=0; i<=W; i++ )
        {
            knapsack[0][i] = 0;
        }
        for ( int i=1; i<=n; i++ )
        {
            knapsack[i][0] = 0;
        }
        for ( int items = 1; items <= n; items++ )
        {
            for ( int curweight = 1; curweight <= W; curweight++ )
            {
                knapsack[items][curweight] = knapsack[items-1][curweight];
                if ( curweight >= weights[items-1] )
                {
                    knapsack[items][curweight] = Math.max(
                                                            values[items-1] + knapsack[items-1][curweight-weights[items-1]],
                                                            knapsack[items-1][curweight]
                                                         );
                }
            }
        }
        return knapsack[n][W];
    }
}
