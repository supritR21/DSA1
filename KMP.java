import java.util.*;

public class KMP {
    public List<Integer> kmp(String text, String pat) {
        List<Integer> ls = new ArrayList<>();
        int n = text.length();
        int m = pat.length();
        if(m>n) return ls;
        int[] lps = buildLPS(pat);
        int i=0;
        int j=0;
        while(i<n) {
            if(text.charAt(i)==pat.charAt(j)) {
                i++;
                j++;
            }
            if(j==pat.length()) {
                ls.add(i-j);
                j=lps[j-1];
            } else if(i<text.length() && text.charAt(i)!=pat.charAt(j)) {
                if(j!=0) {
                    j=lps[j-1];
                } else {
                    i++;
                }
            }
        }
        return ls;
    }
    private int[] buildLPS(String pat) {
        int[] lps = new int[pat.length()];
        int len = 0;
        int i=1;
        while(i<pat.length()) {
            if(pat.charAt(i)==pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if(len!=0) {
                    len = lps[len-1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
