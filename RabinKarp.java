import java.util.*;

class DoubleHash {
    static final long mod1 = 1_000_000_007L;
    static final long mod2 = 1_000_000_009L;

    static final int base1 = 31;
    static final int base2 = 37;

    long[] pow1, pow2;
    long[] hash1, hash2;

    DoubleHash(String str) {
        int n = str.length();
        pow1 = new long[n+1];
        pow2 = new long[n+1];

        hash1 = new long[n+1];
        hash2 = new long[n+1];

        pow1[0] = 1;
        pow2[0] = 1;

        for(int i=1; i<=n; i++) {
            pow1[i] = (pow1[i-1]*base1)%mod1;
            pow2[i] = (pow2[i-1]*base2)%mod2;
        }
        for(int i=0; i<n; i++) {
            int val = str.charAt(i)-'a'+1;
            hash1[i+1] = (hash1[i]*base1+val)%mod1;
            hash2[i+1] = (hash2[i]*base2+val)%mod2;
        }
    }
    long getHash1(int l, int r) {
        long ans = (hash1[r+1]-hash1[l]*pow1[r-l+1])%mod1;
        if(ans<0) ans+=mod1;
        return ans;
    }
    long getHash2(int l, int r) {
        long ans = (hash2[r+1]-hash2[l]*pow2[r-l+1])%mod2;
        if(ans<0) ans+=mod2;
        return ans;
    }
}

public class RabinKarp {
    public List<Integer> rabinKarp(String text, String pat) {
        List<Integer> ans = new ArrayList<>();
        int n = text.length();
        int m = pat.length();
        if(m>n) return ans;
        DoubleHash textHash = new DoubleHash(text);
        DoubleHash patHash = new DoubleHash(pat);

        long p1 = patHash.getHash1(0, m-1);
        long p2 = patHash.getHash2(0, m-1);

        for(int i=0; i<=n-m; i++) {
            long h1 = textHash.getHash1(i, i+m-1);
            long h2 = textHash.getHash2(i, i+m-1);
            if(h1==p1 && h2==p2) {
                ans.add(i);
            }
        }
        return ans;
    }
}
