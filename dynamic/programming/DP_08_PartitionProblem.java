package dynamic.programming;

public class DP_08_PartitionProblem
{
    /**
     * Partition problem is to determine whether a given set can be partitioned into two subsets such that the sum of elements in both subsets is same.
     * 
     * Examples

            array[] = {1, 5, 11, 5}
            Output: true 
            The array can be partitioned as {1, 5, 5} and {11}
            
            array[] = {1, 5, 3}
            Output: false 
            The array cannot be partitioned into equal sum sets.

       Following are the two main steps to solve this problem:
       
            1) Calculate sum of the array. If sum is odd, there can not be two subsets with equal sum, so return false.
          
            2) If sum of array elements is even, calculate sum/2 and find a subset of array with sum equal to sum/2.

            The first step is simple. The second step is crucial, it can be solved either using recursion or Dynamic Programming.
          
            The problem can be solved using dynamic programming when the sum of the elements is not too big.
            We can create a 2D array part[][] of size (sum/2+1)*(n+1).
            And we can construct the solution in bottom up manner such that every filled entry has following property

                part[i][j] = TRUE if a subset of {array[0], array[1], ..array[j-1]} has sum equal to i
                             FALSE otherwise
     */
    public static boolean equalPartition(int[] array)
    {
        int sum = 0;
        for ( int a : array )
        {
            sum = sum + a;
        }
        if ( sum % 2 != 0 )
        {
            return false;
        }
        int half = sum / 2;
        int n = array.length;
        boolean[][] partition = new boolean[half+1][n+1];
        for ( int i=0; i<=n; i++ )
        {
            partition[0][i] = true;
        }
        for ( int i=1; i<=half; i++ )
        {
            partition[i][0] = false;
        }
        for ( int i=1; i<=half; i++ )
        {
            for ( int j=1; j<=n; j++ )
            {
                partition[i][j] = partition[i][j-1];
                if ( i >= array[j-1] )
                {
                    partition[i][j] = partition[i][j-1] || partition[i-array[j-1]][j-1];
                }
            }
        }
        return partition[half][n];
    }
    
    /**
     * Partition the array into 2 subsets such that the difference between the sum of two subsets is minimum.
     */
    public static void balancedPartition(int array[])
    {
        /*The value of subset[i][j] will be true if there is a subset
        of set[0..j-1] with sum equal to i */
        int n = array.length;
        int sum = 0;

        for (int i = 0; i < n; i++)
        {
            sum += array[i];
        }

        //sum = 8;
        int[][] P = new int[sum + 1][n + 1];
        // If sum is 0, then answer is true
        for (int i = 0; i <= n; i++)
        {
            P[0][i] = 1;
        }
        // If sum is not 0 and set is empty, then answer is false
        for (int i = 1; i <= sum; i++)
        {
            P[i][0] = 0;
        }
        // Fill the subset table in botton up manner
        for (int i = 1; i <= sum; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                P[i][j] = P[i][j - 1];
                if (i >= array[j - 1])
                {
                    P[i][j] = Math.max( P[i][j], P[i - array[j - 1]][j - 1] );
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= sum; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                /* If there is s subset with sum i, then check if the
                difference between overall sum and twice this sum is least or not.
                If yes update the min */
                if (P[i][j] == 1)
                {
                    if (Math.abs(sum - 2 * i) < min)
                    {
                        min = Math.abs(sum - 2 * i);
                        break;
                    }
                }
            }
        }

        System.out.println("Differece between two sub sets will be : " + min);
        System.out.println();
        for ( int x=0; x<=sum; x++ )
        {
            for ( int y=0; y<=n; y++ )
            {
                System.out.print(P[x][y] + " ");
            }
            System.out.println();
        }
    }
    
    public static boolean[][] solveOptimalProblems(int[] array, int sum)
    {
        int n = array.length;
        boolean[][] optSols = new boolean[sum+1][n+1];
        for ( int i=0; i<=n; i++ )
        {
            optSols[0][i] = true;
        }
        for ( int i=1; i<=sum; i++ )
        {
            optSols[i][0] = false;
        }
        for ( int i=1; i<=sum; i++ )
        {
            for ( int j=1; j<=n; j++ )
            {
                if ( i >= array[j-1] )
                {
                    optSols[i][j] = optSols[i][j-1] || optSols[i-array[j-1]][j-1];
                }
                else
                {
                    optSols[i][j] = optSols[i][j-1];
                }
            }
        }
        return optSols;
    }
    
    public static void main(String[] args)
    {
        System.out.println(equalPartition(new int[]{5,1,2,4}));
    }
}
