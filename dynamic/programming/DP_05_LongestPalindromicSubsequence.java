package dynamic.programming;

public class DP_05_LongestPalindromicSubsequence
{
    /**
     * @author srragidi
     * 
     * Given a sequence, find the length of the longest palindromic subsequence in it. 
     * For example, if the given sequence is “BBABCBCAB”, then the output should be 7 as “BABCBAB” is the longest palindromic subseuqnce in it. 
     * “BBBBB” and “BBCBB” are also palindromic subsequences of the given sequence, but not the longest ones.

     * The naive solution for this problem is to generate all subsequences of the given sequence and find the longest palindromic subsequence. 
     * This solution is exponential in term of time complexity. 
     * 
     * Let us see how this problem possesses both important properties of a Dynamic Programming (DP) Problem and can efficiently solved using Dynamic Programming.
     * 
     * 1) Optimal Substructure:
             
            Let X[0..n-1] be the input sequence of length n and LPS(0, n-1) be the length of the longest palindromic subsequence of X[0..n-1].
            
            Following is a general recursive solution with all cases handled.
            
            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                               =  1                                   if i = j
                               =  1                                   if j = i+1 & X[i] != X[j]
                LPS(i, j)      =  2                                   if j = i+1 & X[i] = X[j]
                               =  LPS(i+1, j-1) + 2                   if X[i] = X[j]
                               =  Max{ LPS(i+1, j), LPS(i, j-1) }     if X[i] != X[j]
            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
            
            Let X[0..n-1] be the input sequence of length n and L(0, n-1) be the length of the longest palindromic subsequence of X[0..n-1].

            If last and first characters of X are same, then 
                L(0, n-1) = L(1, n-2) + 2.
            Else 
                L(0, n-1) = MAX (L(1, n-1), L(0, n-2)).
            
            Following is a general recursive solution with all cases handled.
            
            // Everay single character is a palindrom of length 1
            L(i, i) = 1 for all indexes i in given sequence
            
            // IF first and last characters are not same
            If (X[i] != X[j])  L(i, j) =  max{L(i + 1, j),L(i, j - 1)} 
            
            // If there are only 2 characters and both are same
            Else if (j == i + 1) L(i, j) = 2  
            
            // If there are more than two characters, and first and last 
            // characters are same
            Else L(i, j) =  L(i + 1, j - 1) + 2 

            
       2) Overlapping Subproblems:
       
            Following is simple recursive implementation of the LPS problem. 
            The implementation simply follows the recursive structure mentioned above.
            
            int lps(char[] seq, int i, int j)
            {
               // Base Case 1: If there is only 1 character
               if (i == j)
                 return 1;
             
               // Base Case 2: If there are only 2 characters and both are same
               if (seq[i] == seq[j] && i + 1 == j)
                 return 2;
             
               // If the first and last characters match
               if (seq[i] == seq[j])
                  return lps (seq, i+1, j-1) + 2;
             
               // If the first and last characters do not match
               return max( lps(seq, i, j-1), lps(seq, i+1, j) );
            }
            
            Considering the above implementation, following is a partial recursion tree for a sequence of length 6 
            with all different characters.
            
                                                   L(0, 5)
                                                 /        \ 
                                                /          \  
                                            L(1,5)          L(0,4)
                                           /    \            /    \
                                          /      \          /      \
                                      L(2,5)    L(1,4)  L(1,4)  L(0,3)
                                      
            In the above partial recursion tree, L(1, 4) is being solved twice. 
            If we draw the complete recursion tree, then we can see that there are many subproblems which are solved again and again. 
            Since same suproblems are called again, this problem has Overlapping Subprolems property. 

     */

    public static int algorithm(String source)
    {
        int n = source.length();
        int[][] LPS = new int[n][n];
        for ( int i=0; i<n; i++ )
        {
            LPS[i][i] = 1;                               // 1'st formula
        }
        for ( int cl=2; cl<=n; cl++ )
        {
            for ( int i=0; i<n-cl+1; i++ )
            {
                int j = i + cl - 1;
                if ( source.charAt(i) == source.charAt(j) && cl == 2 )
                {
                    LPS[i][j] = 2;
                    LPS[i][j] = LPS[i+1][j-1] + 2;
                }
                else if ( source.charAt(i) == source.charAt(j) )
                {
                    LPS[i][j] = LPS[i+1][j-1] + 2;
                }
                else
                {
                    LPS[i][j] = Math.max(LPS[i+1][j], LPS[i][j-1]);
                }
            }
        }
        
        //Rebuilding string from LP matrix
        StringBuffer strBuff = new StringBuffer();
        int i = 0;
        int j = n - 1;
        while (i < j)
        {
            if (source.charAt(i) == source.charAt(j))
            {
                strBuff.append(source.charAt(i));
                i++;
                j--;
            }
            else if (LPS[i][j - 1] > LPS[i + 1][j] ) 
            {
                j--;
            }
            else
            {
                i++;
            }
        }
        StringBuffer strBuffCopy = new StringBuffer(strBuff);
        String str = strBuffCopy.reverse().toString();
        if (i == j)
        {
            strBuff.append(source.charAt(i)).append(str);
        }
        else
        {
            strBuff.append(str);
        }
        System.out.println(strBuff.toString());
        return LPS[0][n-1];
    }
    
    public static int algorithmRecursive(String str, int i, int j)
    {
        if ( i == j )
        {
            return 1;
        }
        if ( (i+1 == j) && str.charAt(i) == str.charAt(j) )
        {
            return 2;
        }
        if ( str.charAt(i) == str.charAt(j) )
        {
            return 2 + algorithmRecursive(str, i+1, j-1);
        }
        
        return Math.max( algorithmRecursive(str, i+1, j), algorithmRecursive(str, i, j-1) );
    }
    
    public static String LPS(int i, int j, String s)
    {
        if (i > j)
            return "";
        if (i == j)
            return s.charAt(i) + "";
        if (s.charAt(i) == s.charAt(j))
            return s.charAt(i) + LPS(i + 1, j - 1, s) + s.charAt(j);
        String a = LPS(i + 1, j, s);
        String b = LPS(i, j - 1, s);
        return (a.length() > b.length()) ? a : b;
    }
    
    public static void main(String[] args)
    {
        System.out.println(algorithm("BBABCBCAB"));
    }
}
