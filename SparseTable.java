import java.util.*;

public class SparseTable {
    static int[][] st;
    static int[] log;

    public SparseTable(int[] arr) {
        int n = arr.length;
        log = new int[n+1];

        for(int i=2; i<=n; i++) {
            log[i] = log[i/2]+1;
        }
        int K = log[n]+1;
        st = new int[K][n];

        for(int i=0; i<n; i++) {
            st[0][i] = arr[i];
        }

        for(int k=1; k<K; k++) {
            int len = 1<<k;
            for(int i=0; i+len<=n; i++) {
                st[k][i] = Math.min(st[k-1][i], st[k-1][i+(1<<(k-1))]);
            }
        }
    }
    // Range Minimum Query [L,R];
    public int query(int l, int r) {
        int len = r-l+1;
        int k = log[len];

        return Math.min(st[k][l],st[k][r-(1<<k)+1]);
    }
    public static void main(String[] args) {
        int[] arr = {4, 6, 1, 5, 7, 3, 2, 8};

        SparseTable sparseTable = new SparseTable(arr);

        System.out.println(sparseTable.query(0, 3)); // 1
        System.out.println(sparseTable.query(2, 6)); // 1
        System.out.println(sparseTable.query(4, 6)); // 2
        System.out.println(sparseTable.query(5, 7)); // 2
    }
}
