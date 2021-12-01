import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    Boolean[][] memo;
    public boolean isMatch(String s, String p) {
        memo = new Boolean[s.length()+1][p.length()+1];
        return dp(0,0,s,p);
    }

    private boolean dp(int i, int j, String s, String p) {
        boolean result;
        if(memo[i][j]!=null) {
            return memo[i][j] == true;
        }

        if(j==p.length()) {
            result = i == s.length();
        }else {
            boolean firstMatch = (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));

            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                result = (dp(i, j + 2, s, p) || firstMatch && dp(i + 1, j, s, p));
            } else {
                result = firstMatch && dp(i + 1, j + 1, s, p);
            }
        }

        memo[i][j] = result ?true: false;
        return result;
    }
}
