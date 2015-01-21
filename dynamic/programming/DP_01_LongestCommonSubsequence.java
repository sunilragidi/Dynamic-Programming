package dynamic.programming;

public class DP_01_LongestCommonSubsequence
{
    /**
     * @author srragidi
     * 
     * Problem: Find the length of the longest common subsequences of two Strings
     * 
     * Example: Str1 = "APBCADCQER", 
     *          Str2 = "RASBTAUCVE"
     *          LCS = ABACE, Length is 5
     * 
     * Algorithm:
     * 
     *      1) Optimal Substructure:
     *      
                    Let the input sequences be X[0..m-1] and Y[0..n-1] of lengths m and n respectively. 
                    And let LCS(X[0..m-1], Y[0..n-1]) be the length of LCS of the two sequences X and Y. 
                    
                    Following is the recursive definition of LCS(X[0..m-1], Y[0..n-1]).
                    
                        1. If last characters of both sequences match (or X[m-1] == Y[n-1]) then
                            
                                   LCS(X[0..m-1], Y[0..n-1]) = 1 + LCS(X[0..m-2], Y[0..n-2])
                            
                        2. If last characters of both sequences do not match (or X[m-1] != Y[n-1]) then
                            
                                   LCS(X[0..m-1], Y[0..n-1]) = MAX { LCS(X[0..m-2], Y[0..n-1]), LCS(X[0..m-1], Y[0..n-2] }
                    
                    Examples:
                        1) Consider the input strings “AGGTAB” and “GXTXAYB”. Last characters match for the strings. 
                           So length of LCS can be written as:
                           
                           LCS(“AGGTAB”, “GXTXAYB”) = 1 + LCS(“AGGTA”, “GXTXAY”)
                        
                        2) Consider the input strings “ABCDGH” and “AEDFHR. Last characters do not match for the strings. 
                           So length of LCS can be written as:
                           
                           LCS(“ABCDGH”, “AEDFHR”) = MAX { LCS(“ABCDG”, “AEDFHR”), LCS(“ABCDGH”, “AEDFH”) }
                    
                    So the LCS problem has optimal substructure property as the main problem can be solved using solutions to subproblems.
                    
            2) Overlapping Subproblems:
                    Following is simple recursive implementation of the LCS problem. 
                    The implementation simply follows the recursive structure mentioned above.
                    
                    int lcs( char[] X, char[] Y, int m, int n )
                    {
                       if (m == 0 || n == 0)
                         return 0;
                       if (X[m-1] == Y[n-1])
                         return 1 + lcs(X, Y, m-1, n-1);
                       else
                         return max(lcs(X, Y, m, n-1), lcs(X, Y, m-1, n));
                    }
                    
               Time complexity of the above naive recursive approach is O(2^n) in worst case and 
               worst case happens when all characters of X and Y mismatch i.e., length of LCS is 0.
               Considering the above implementation, following is a partial recursion tree for input strings “AXYT” and “AYZX”
               
                                                       lcs("AXYT", "AYZX")
                                                       /                 \
                                                     /                     \
                                   lcs("AXY", "AYZX")                       lcs("AXYT", "AYZ")
                                         /   \                                    /      \
                                        /     \                                  /        \
                      lcs("AX", "AYZX")        lcs("AXY", "AYZ")       lcs("AXY", "AYZ")     lcs("AXYT", "AY")
                      
               In the above partial recursion tree, lcs(“AXY”, “AYZ”) is being solved twice. 
               If we draw the complete recursion tree, then we can see that there are many subproblems which are solved again and again. 
               So this problem has Overlapping Substructure property and re-computation of same subproblems can be avoided by either using Memoization or Tabulation.
     */
    
    public static int algorithm(String s1, String s2)
    {
        int m = s1.length();
        int n = s2.length();
        
        int[][] lcs = new int[m+1][n+1];
        
        // Actual logic - code for the above recursive equation
        for ( int i=1; i<=m; i++ )
        {
            for ( int j=1; j<=n; j++ )
            {
                if ( i == 0 || j == 0 )
                {
                    lcs[i][j] = 0;
                }
                else if ( s1.charAt(i-1) == s2.charAt(j-1) ) // if X[i] = Y[j] then 1'st equation: 1 + LCS(i-1, j-1)
                {
                    lcs[i][j] = 1 + lcs[i-1][j-1];
                }
                else                                    // 2'nd equation: Max { LCS(i-1, j), LCS(i, j-1) }
                {
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                }
            }
        }
        printLCS(s1, s2, lcs);
        return lcs[m][n];
    }
    
    /**
     *  1) Take LCS[m+1][n+1] matrix using the steps discussed(above method)
     * 
     *  2) The value L[m][n] contains length of LCS. Create a character array lcs[] of length equal to the length of lcs

        3) Traverse the 2D array starting from L[m][n]. 
           Do following for every cell L[i][j]
              …..a) If characters (in X and Y) corresponding to L[i][j] are same (Or X[i-1] == Y[j-1]), then include this character as part of LCS.
              …..b) Else compare values of L[i-1][j] and L[i][j-1] and go in direction of greater value.
     */
    private static void printLCS(String s1, String s2, int[][] lcs)
    {
        int i = lcs.length-1;
        int j = lcs[0].length-1;
        
        char[] lcsStr = new char[lcs[i][j]];
        int index = lcsStr.length;
        
        while ( i > 0 && j > 0 )
        {
            if ( s1.charAt(i-1) == s2.charAt(j-1) )
            {
                lcsStr[index-1] = s1.charAt(i-1);
                index--;
                i--;
                j--;
            }
            else if ( lcs[i-1][j] > lcs[i][j-1] )
            {
                i--;
            }
            else
            {
                j--;
            }
        }
        for ( char c : lcsStr )
        {
            System.out.print(c);
        }
        System.out.println();
    }
    
    public static void main(String[] args)
    {
        System.out.println(algorithm("AGGTAB", "GXTXAYB"));
    }
}
