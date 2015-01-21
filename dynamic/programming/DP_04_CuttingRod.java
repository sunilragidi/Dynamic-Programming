package dynamic.programming;

public class DP_04_CuttingRod
{
    /**
     *  @author srragidi
     * 
     *  Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n. 
     *  Determine the maximum value obtainable by cutting up the rod and selling the pieces. 
     *  For example, if length of the rod is 8 and the values of different pieces are given as following, 
     *  then the maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and 6)

        length   | 1   2   3   4   5   6   7   8  
        --------------------------------------------
        price    | 1   5   8   9  10  17  17  20
        
        And if the prices are as following, then the maximum obtainable value is 24 (by cutting in eight pieces of length 1)
        
        length   | 1   2   3   4   5   6   7   8  
        --------------------------------------------
        price    | 3   5   8   9  10  17  17  20
        
        The naive solution for this problem is to generate all configurations of different pieces and find the highest priced configuration. 
        This solution is exponential in term of time complexity. 
        
        Let us see how this problem possesses both important properties of a Dynamic Programming (DP) Problem and can efficiently solved using Dynamic Programming.
        
        1) Optimal Substructure:
                We can get the best price by making a cut at different positions and comparing the values obtained after a cut. 
                We can recursively call the same function for a piece obtained after a cut.
                
                Let B(n) be the required (best possible price) value for a rod of lenght n. 
                B(n) can be written as following.
                
                 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                   B(n) = MAX { price[i] + B(n-i-1) } for all i in {0, 1 .. n-1}   
                 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * 
     */
    
    public static int algorithm(int[] price, int n)
    {
        int[] B = new int[n+1];
        B[0] = 0;
        for ( int i=1; i<=n; i++ )
        {
            int max = Integer.MIN_VALUE;
            for ( int j=0; j<i; j++ )
            {
                max = Math.max( price[j] + B[i-j-1], max );
            }
            B[i] = max;
        }
        return B[n];
    }
    
    public static void main(String[] args)
    {
        //int[] price =  {1,   5,   8,   9,  10,  17,  17,  20};
        int[] price =  {3,   5,   8,   9,  10,  17,  17,  20};
        System.out.println(algorithm(price, 8));
    }
}
