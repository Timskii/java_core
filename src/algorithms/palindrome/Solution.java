package algorithms.palindrome;

public class Solution {
    public boolean isPalindrome(String s){
        int i = 0;
        int g = s.length() - 1;
        while(i<=g){
            i++; g--;
            if(s.charAt(i) != s.charAt(g)){
                return false;
            }
        }
        return true;
    }
}
