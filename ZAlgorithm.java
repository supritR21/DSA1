import java.util.*;

public class ZAlgorithm {

    public static int[] calculateZ(String s) {
        int n = s.length();
        int[] z = new int[n];

        int l=0;
        int r=0;

        for(int i=1; i<n; i++) {
            if(i>r) {
                l=i;
                r=i;
                while(r<n && s.charAt(r)==s.charAt(r-l)) {
                    r++;
                }
                z[i] = r-l;
                r--;
            } else {
                int k = i-l;
                if(z[k]<r-i+1) {
                    z[i]=z[k];
                } else {
                    l=i;
                    while(r+1<n && s.charAt(r+1)==s.charAt(r+1-l)) {
                        r++;
                    }
                    z[i] = r-l+1;
                }
            }
        }
        return z;
    }

    public static List<Integer> search(String text, String pattern) {
        List<Integer> ans = new ArrayList<>();
        int m = pattern.length();
        if(m==0) return ans;

        String combined = pattern +"$"+ text;
        int[] z = calculateZ(combined);
        for(int i=0; i<combined.length(); i++) {
            if(z[i]==m) {
                int textIndex = i-m-1;
                ans.add(textIndex);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        String text = "ababcababc";
        String pattern = "abc";

        List<Integer> occurences = search(text, pattern);
        System.out.println(occurences);
    }
}
