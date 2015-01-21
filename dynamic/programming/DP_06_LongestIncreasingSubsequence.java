package dynamic.programming;

import java.util.Arrays;

public class DP_06_LongestIncreasingSubsequence
{
    /**
     * @author srragidi
     * 
     * The longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given sequence 
     * such that all elements of the subsequence are sorted in increasing order. 
     * 
     * For example, 
     * 
     *          length of LIS for { 10, 22, 9, 33, 21, 50, 41, 60, 80 } is 6 and 
     *          LIS is {10, 22, 33, 50, 60, 80}.
     * 
     * Optimal Substructure:
     * ~~~~~~~~~~~~~~~~~~~~~
            Let Array[0..n-1] be the input Array and 
            L(i) be the length of the LIS till index i such that 
            Array[i] is part of LIS and Array[i] is the last element in LIS, then L(i) can be recursively written as.
            
            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            L(i) = { 1 + Max ( L(j) ) } where j < i and Array[j] < Array[i] and
                 = 1                    if there is no such j
            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            
            To get LIS of a given Arrayay, we need to return max(L(i)) where 0 < i < n
            So the LIS problem has optimal substructure property as the main problem can be solved using solutions to subproblems.

       Overlapping Subproblems:
       ~~~~~~~~~~~~~~~~~~~~~~~~
            Following is simple recursive implementation of the LIS problem. 
            The implementation simply follows the recursive structure mentioned above. 
            The value of lis ending with every element is returned using max_ending_here. 
            The overall lis is returned using pointer to a variable max.
            
            _________________________________recursive implementation_______________________________________
            
                        int _lis( int Array[], int n, int *max_ref)
                        {
                            // Base case
                            if(n == 1)
                                return 1;
                         
                            int res, max_ending_here = 1; // length of LIS ending with Array[n-1]
                         
                            // Recursively get all LIS ending with Array[0], Array[1] ... ar[n-2]. If
                            //   Array[i-1] is smaller than Array[n-1], and max ending with Array[n-1] needs
                            //   to be updated, then update it 
                            for(int i = 1; i < n; i++)
                            {
                                res = _lis(Array, i, max_ref);
                                if (Array[i-1] < Array[n-1] && res + 1 > max_ending_here)
                                    max_ending_here = res + 1;
                            }
                         
                            // Compare max_ending_here with the overall max. And update the
                            // overall max if needed
                            if (*max_ref < max_ending_here)
                               *max_ref = max_ending_here;
                         
                            // Return length of LIS ending with Array[n-1]
                            return max_ending_here;
                        }
             
                        // The wrapper function for _lis()
                        int lis(int Array[], int n)
                        {
                            // The max variable holds the result
                            int max = 1;
                         
                            // The function _lis() stores its result in max
                            _lis( Array, n, &max );
                         
                            // returns max
                            return max;
                        }
            ___________________________________________________________________________________________
            
            Considering the above implementation, following is recursion tree for an Arrayay of size 4. 
            lis(n) gives us the length of LIS for Array[].
            
                                                lis(4)           
                                             /    |      \
                                     lis(3)      lis(2)    lis(1)  
                                    /     \        /         
                              lis(2)  lis(1)   lis(1) 
                              /    
                            lis(1)
            
            We can see that there are many subproblems which are solved again and again. 
            So this problem has Overlapping Substructure property and recomputation of same subproblems 
            can be avoided by either using Memoization or Tabulation. 
            Following is a tabluated implementation for the LIS problem.
     * 
     */
    
    public static int algorithm(int[] array)
    {
        int n = array.length;
        int[] lis = new int[n];
        Arrays.fill(lis, 1);
        int[] pred = new int[n];
        Arrays.fill(pred, -1);
        for ( int i=1; i<n; i++ )
        {
            int maxj = 0;
            for ( int j=0; j<i; j++ )
            {
                if ( array[j] < array[i] && lis[j] > maxj )
                {
                    maxj = lis[j];
                    pred[i] = j;
                }
            }
            lis[i] = maxj + 1;
        }
        // rebuild the longest increasing subsequence
        int maxIndex = 0;
        for ( int i=1; i<n; i++ )
        {
            if (lis[maxIndex] < lis[i]) 
            {
                maxIndex = i;
            }
        }
        int cnt = lis[maxIndex];
        int[] res = new int[cnt];
        for (int i = maxIndex; i != -1; i = pred[i]) 
        {
            res[--cnt] = array[i];
        }
        
        System.out.print("Longest increasing subsequence: ");
        for ( int i : res )
        {
            System.out.print(i + " ");
        }
        System.out.println();
        return res.length;
    }
    
    public static void main(String[] args)
    {
        System.out.println(algorithm(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}));
    }
}
