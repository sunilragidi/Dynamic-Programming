package dynamic.programming;

public class DP_03_MaxSubArraySum
{
    /**
     * @author srragidi
     * 
     * The maximum subarray problem is the task of finding the contiguous subarray within a one-dimensional array of numbers 
     * (containing at least one positive number) which has the largest sum. 
     * 
     * For example, for the sequence of values -2, 1, -3, 4, -1, 2, 1, -5, 4; 
     * the contiguous subarray with the largest sum is 4, -1, 2, 1, with sum 6.
     * 
     * Algorithm(Kadene's algorithm):
     * 
     *      1.  scan through the array values, computing at each position the maximum (positive sum) subarray ending at that position. 
     *      
     *      2.  This subarray is either empty (in which case its sum is zero) or consists of one more element than the maximum subarray ending at the previous position.
     *      
     *          Initialize:
                    max_so_far = 0
                    max_ending_here = 0
            
                Loop for each element of the array
                  (a) max_ending_here = max_ending_here + a[i]
                  (b) if(max_ending_here < 0)
                            max_ending_here = 0
                  (c) if(max_so_far < max_ending_here)
                            max_so_far = max_ending_here
                return max_so_far
                
                Explanation:
                Simple idea of the Kadane's algorithm is to look for all positive contiguous segments of the array (max_ending_here is used for this). 
                And keep track of maximum sum contiguous segment among all positive segments (max_so_far is used for this). 
                Each time we get a positive sum compare it with max_so_far and update max_so_far if it is greater than max_so_far
                
            Formula:
                Let S(i) = Max subarray sum till i'th element
                    
                    S(i) = Maximum { S(i-1) + A(i), A(i) }
                    
                Example: A = {-2, -3, 4, -1, -2, 1, 5, -3}
                         S(1) = -2
                         S(2) = Max{ S(1)+A(2), A(2) } => max{ -5, -3 } => -3
                         S(3) = Max{ S(2)+A(3), A(3) } => max{ -3+4, 4 } => 4
                         s(4) = Max{ S(3)+A(4), A(4) } => max{ 4-1, -1 } => 3
                                                   .
                                                   .
                                                   .
                                                   .
     */
    
    public static int algorithm(int[] array)
    {
        boolean isCompleteNegative = true;
        boolean isCompletePositive = true;
        boolean zeroSequence = true;
        
        int sumIfCompletePositive = 0;
        int sumIfCompleteNegative = Integer.MIN_VALUE;
        
        for ( int x : array )
        {
            if ( x > 0 )
            {
                isCompleteNegative = false;
                zeroSequence = false;
                sumIfCompletePositive = sumIfCompletePositive + x;
            }
            else if ( x < 0 )
            {
                isCompletePositive = false;
                zeroSequence = false;
                sumIfCompleteNegative = Math.max(sumIfCompleteNegative, x);
            }
            else
            {
                zeroSequence = zeroSequence && true;
                isCompleteNegative = false;
                isCompletePositive = false;
            }
        }
        if ( isCompleteNegative )
        {
            System.out.println("The array is complete negative(-ve)");
            System.out.println("Max sum is largest element in the array");
            System.out.println("Max sum: "+sumIfCompleteNegative);
            return sumIfCompleteNegative;
        }
        else if ( isCompletePositive )
        {
            System.out.println("The array is complete positive(+ve)");
            System.out.println("Max sum is sum of all elements in the array");
            System.out.println("Max sum: "+sumIfCompletePositive);
            return sumIfCompletePositive;
        }
        else if ( zeroSequence )
        {
            System.out.println("The array is a zero sequence");
            System.out.println("Max sum: "+0);
            return 0;
        }
        else // mixed type('+'ve, '-'ve elements)
        {
            int curMax = 0;
            int maxSoFar = 0;
            int curMinIndex = 0;
            int curMaxIndex = 0;
            for ( int i=0; i<array.length; i++ )
            {
                curMax = curMax + array[i];
                if ( curMax < 0 )
                {
                    curMinIndex = i + 1;
                    curMaxIndex = curMinIndex;
                    curMax = 0;
                }
                if ( curMax > maxSoFar )
                {
                    curMaxIndex = i;
                    maxSoFar = curMax;
                }
            }
            System.out.println("The max subarray starts from index: " + curMinIndex + " and ends at index: "+ curMaxIndex);
            System.out.print("The max subarray is: ");
            for ( int i=curMinIndex; i<=curMaxIndex; i++ )
            {
                System.out.print(array[i] + " ");
            }
            System.out.println();
            System.out.println("Max sum is "+maxSoFar);
            return maxSoFar;
        }
    }
    
    public static void main(String[] args)
    {
        //int[] array = {-2, -3, 4, -1, -2, 1, 5, -3};
        //int[] array = {0,0,0,0};
        //int[] array = {1,2,3,4};
        //int[] array = {-1,2,3,-4};
        int[] array = {-1,-2,-3};
        
        
        //int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        //int[] array = {1, 2, 3, 4, 2};
        //int[] array = {-10, -2, -3, -4, -1};
        algorithm(array);
    }
}
