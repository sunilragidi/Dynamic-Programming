package dynamic.programming;

public class DP_02_CoinChange
{
    /**
     * @author srragidi
     * 
     * Given a value P, if we want to make change for P cents, and we have infinite supply of each of S = { V1, V2, .. , Vn} valued coins, 
     * how many minimum no of coins are required to make the change for amount P.

     * For example: 
     *      For P = 4 and S = {1,2,3}, 
     *      there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. 
     *      Minimum no of coins are 2
     *      So output should be 2. 
     * 
     *      For P = 10 and S = {2, 5, 3, 6}, 
     *      there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}.
     *      Minimum no of coins are 2
     *      So the output should be 2.
     *      
     * Algorithm:
     * 
     *      Let the coins be V1, V2, V3, V4,......,Vn
     *      Let C(P) = minimum no of coins required to make change for amount 'P'
     *            
     *      The recursive equation can be as follows
     *      
     *      C(P) = minimum { C( P-V(i) ) } + 1,     for i = 1,2,3,...,n
     *                i                
     *             (equation can be expanded as below)
     *                            
     *      C(P) = minimum { C(P-V1), C(P-V2), C(P-V3),.......,C(P-Vn) } + 1,    for i = 1,2,3,...,n
     *      
     *      For example: 
     *      
     *             Let the coins are V1 = 1, V2 = 2, V3 = 3 and
     *             Sum 'P' = 5
     *             
     *             C(1) = minimum { C(1-1) } + 1
     *                    minimum { 0 } + 1 = 0 + 1
     *                    1
     *             C(2) = minimum { C(2-1), C(2-2) } + 1
     *                    minimum { 0, 1 } + 1 = 0 + 1
     *                    1
     *             C(3) = minimum { C(3-1), C(3-2), C(3-3) } + 1
     *                    minimum { 1, 1, 0 } + 1 = 0 + 1
     *                    1
     *             C(4) = minimum { C(4-1), C(4-2), C(4-3) } + 1
     *                    minimum { 1, 1, 1 } + 1 = 1 + 1
     *                    2
     *             C(5) = minimum { C(5-1), C(5-2), C(5-3) } + 1
     *                    minimum { 2, 1, 1 } + 1 = 1 + 1
     *                    2
     *             
     *             So the minimum no of coins required to make the sum 5 is 2
     *             The coins are {2, 3}
     */ 
        
    public static int algorithm(int[] coins, int sum)
    {
        int[] cp = new int[sum+1];
        for ( int i=1; i<=sum; i++ )
        {
            int min = Integer.MAX_VALUE;
            for ( int j=1; j<=i; j++ )
            {
                if ( j > coins.length )
                {
                    break;
                }
                min = Math.min(min, cp[i-j]);
            }
            cp[i] = min + 1;
        }
        return cp[sum];
    }
    
    public static void main(String[] args)
    {
        System.out.println(algorithm(new int[]{1,2,3,4}, 14));
    }
}
