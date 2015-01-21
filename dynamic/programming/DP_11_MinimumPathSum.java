package dynamic.programming;

public class DP_11_MinimumPathSum
{
    /**
     * @author srragidi
     * 
     * Given a triangle, find the minimum path sum from top to bottom. 
         
        Dynamic Programming – The Algorithm

                We can make a short-cut with the algorithm, as we don’t have to break the problem into sub-problems, 
                but can start from the bottom and work the way up through the triangle until we reach the top and the algorithm spits out a number.
                
                We start with a triangle that looks like
                
                                                           3
                                                          7 4
                                                         2 4 6
                                                        8 5 9 3
                
                Applying the algorithm to the small problem we will need three iterations. 
                The first iteration we apply the rule a + max(b,c) which creates a new triangle which looks as
                
                                                            3
                                                           7 4
                                                         10 13 15
                
                Making the second iteration of the algorithm makes the triangle look
                
                                                            3
                                                          20 19
                
                And if we run the algorithm once more, the triangle collapses to one number – 23 – which is the answer to the question.
        
     */
    
    public static void algorithm()
    {
        algorithm(getInputTriangle());
    }
    
    public static void algorithm(int[][] triangle)
    {
        int n = triangle.length;
        
        for ( int i=n-2; i>=0; i-- )
        {
            for ( int j=0; j<=i; j++ )
            {
                triangle[i][j] = triangle[i][j] + Math.max(triangle[i+1][j], triangle[i+1][j+1]);
            }
        }
        System.out.println("Max path sum: "+triangle[0][0]);
    }
    
    private static int[][] getInputTriangle()
    {
        int[][] triangle = new int[4][4];
        int[] a1 = {3};
        int[] a2 = {7, 4};
        int[] a3 = {2, 4, 6};
        int[] a4 = {8, 5, 9, 3};
        
        triangle[0] = a1;
        triangle[1] = a2;
        triangle[2] = a3;
        triangle[3] = a4;
        
        return triangle;
    }
    
    public static void main(String[] args)
    {
        algorithm();
    }
}
