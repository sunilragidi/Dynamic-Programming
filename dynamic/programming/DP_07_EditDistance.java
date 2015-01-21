package dynamic.programming;

public class DP_07_EditDistance
{
    /**
     * @author srragidi
     * 
     * Problem: Given two strings of size 'm', 'n' and 
     *          set of operations 
     *                          1. REPLACE - (R)
     *                          2. INSERT  - (I)
     *                          3. DELETE  - (D) 
     *          all at equal cost. 
     *          Find minimum number of edits (operations) required to convert one string into another.
     
       Identifying Recursive Methods:
       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

           What will be sub-problem in this case? Consider finding edit distance of part of the strings, say small prefix. 
           Let us denote them as [1…i] and [1…j] for some 1< i < m and 1 < j < n. 
           Clearly it is solving smaller instance of final problem, denote it as E(i, j). 
           Our goal is finding E(m, n) and minimizing the cost.
                
           In the prefix, we can right align the strings in three ways 
                            (i, -), 
                            (-, j) and 
                            (i, j)
           The hyphen symbol (-) representing no character. 
           
           An example can make it more clear.
           
           Given strings SUNDAY and SATURDAY. We want to convert SUNDAY into SATURDAY with minimum edits. 
           Let us pick i = 2 and j = 4 
           i.e. prefix strings are SU and SATU respectively (assume the strings indices start at 1). 
           The right most characters can be aligned in three different ways.
           
           Case 1: Align characters U and U. They are equal, no edit is required. 
                   We still left with the problem of i = 1 and j = 3, E(i-1, j-1).

           Case 2: Align right character from first string and no character from second string. 
                   We need a deletion (D) here. 
                   We still left with problem of i = 1 and j = 4, E(i-1, j).

           Case 3: Align right character from second string and no character from first string. 
                   We need an insertion (I) here. 
                   We still left with problem of i = 2 and j = 3, E(i, j-1).
                   
           Combining all the subproblems minimum cost of aligning prefix strings ending at i and j given by

           E(i, j) = min { 
                           [E(i-1, j) + D], 
                           [E(i, j-1) + I],  
                           [E(i-1, j-1) + R if i,j characters are not same] 
                         }

           We still not yet done. What will be base case(s)?

           When both of the strings are of size 0, the cost is 0. When only one of the string is zero, we need edit operations as that of non-zero length string.
           Mathematically,
                           E(0, 0) = 0, 
                           E(i, 0) = i, 
                           E(0, j) = j

           Now it is easy to complete recursive method. Go through the code for recursive algorithm (edit_distance_recursive).
           
           
       Dynamic Programming Method:
       ~~~~~~~~~~~~~~~~~~~~~~~~~~~

           Let S1(n) and S2(m) be two strings.
           
           Let E(i, j) be the minimum no of operations needed to change S(1...i) to S2(1...j).
           
           S1(1...i) is the prefix of S1 of length i.
           S2(1...j) is the prefix of S2 of length j.
           
           Now we need to change S1(1...i) to S2(1...j).
           
           E(i, j) be the minimum no of operations needed to change S(1...i) to S2(1...j).
           
           -------Is there any recursive subproblem?-------
           
                                | E(i-1, j-1)                 if S1(i) == S2(j)
           E(i, j)   =     MIN  | E(i-1, j-1) + 1             if S1(i) != S2(j) -- REPLACEMENT
                                | E(i-1, j) + 1                                 -- DELETE
                                | E(i, j-1) + 1                                 -- INSERT
        Applications:
        ~~~~~~~~~~~~~
           
           There are many practical applications of edit distance algorithm, refer Lucene API for sample. 
           Another example, display all the words in a dictionary that are near proximity to a given word\incorrectly spelled word.
     */
    
    /**
     * reference: http://www.javacodegeeks.com/2014/03/easy-to-understand-dynamic-programming-edit-distance.html
     */
    public static int algorithm(String s1, String s2)
    {
        int m = s1.length();
        int n = s2.length();
        int[][] E = new int[m+1][n+1];
        
        // base case 1: edit distance between a string of length 0 to a string of length i will be i
        for ( int i=0; i<=n; i++ )
        {
            E[0][i] = i;
        }
        
        // base case 2: edit distance between a string of length i to a string of length 0 will be i
        for ( int i=0; i<=m; i++ )
        {
            E[i][0] = i;
        }
        
        for ( int i=1; i<=m; i++ )
        {
            for ( int j=1; j<=n; j++ )
            {
                int min = Math.min( E[i-1][j], E[i][j-1] ) + 1; // find the minimum out of insert & delete operations
                E[i][j] = Math.min( min, E[i-1][j-1] + ( s1.charAt(i-1) == s2.charAt(j-1) ? 0 : 1 ) );
            }
        }
        System.out.println("Matrix(optimal subproblems and solutions)");
        for ( int x=0; x<=m; x++ )
        {
            for ( int y=0; y<=n; y++ )
            {
                System.out.print(E[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
        
        return E[m][n];
    }
    
    public static void main(String[] args)
    {
        String s1 = "money";
        String s2 = "monkey";
        System.out.println("Edit distance between '" + s1 + "' and '" + s2 + "' : " + algorithm("money", "monkey"));
    }
}
