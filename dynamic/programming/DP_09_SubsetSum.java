package dynamic.programming;

public class DP_09_SubsetSum
{
    /**
     * @author srragidi
     * 
     * Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.
     * 
     * Examples: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
       Output:  True  //There is a subset (4, 5) with sum 9.
       
       Let isSubSetSum(int set[], int n, int sum) be the function to find whether there is a subset of set[] with sum equal to sum. 
       n is the number of elements in set[].

       The isSubsetSum problem can be divided into two subproblems
            a) Include the last element, recur for n = n-1, sum = sum – set[n-1]
            b) Exclude the last element, recur for n = n-1.
       If any of the above the above subproblems return true, then return true.
       
       Following is the recursive formula for isSubsetSum() problem.
       
                 isSubsetSum(set, n, sum) = isSubsetSum(set, n-1, sum) || isSubsetSum(arr, n-1, sum-set[n-1])
               
                 Base Cases:
                            isSubsetSum(set, n, sum) = false, if sum > 0 and n == 0
                            isSubsetSum(set, n, sum) = true, if sum == 0
                            
                 Pseudo code: 
                 
                            boolean isSubsetSum(int set[], int n, int sum)
                            {
                               // Base Cases
                               if (sum == 0)
                                 return true;
                               if (n == 0 && sum != 0)
                                 return false;
                             
                               // If last element is greater than sum, then ignore it
                               if (set[n-1] > sum)
                                 return isSubsetSum(set, n-1, sum);
                             
                               // else, check if sum can be obtained by any of the following
                               //   (a) including the last element
                               //   (b) excluding the last element 
                               return isSubsetSum(set, n-1, sum) || isSubsetSum(set, n-1, sum-set[n-1]);
                            }
                            
         Following is a dynamic programming algorithm
     */
    
    public static boolean algorithm(int[] array, int sum)
    {
        return DP_08_PartitionProblem.solveOptimalProblems(array, sum)[sum][array.length];
    }
}
