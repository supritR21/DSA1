import java.util.*;

public class Main {

    static class SegTree {
        int len;
        int[] t;          // segment tree values
        int[] unpropUpd;  // pending update value
        boolean[] isLazy; // whether node has a pending update

        SegTree(int len) {
            this.len = len;
            t = new int[4 * len];
            unpropUpd = new int[4 * len];
            isLazy = new boolean[4 * len];
        }

        // apply update to node v covering [tl, tr]
        void apply(int v, int tl, int tr, int val) {
            if (tl != tr) { // not a leaf
                isLazy[v] = true;
                unpropUpd[v] = val;
            }
            t[v] = (tr - tl + 1) * val; // range assign
        }

        // push lazy value to children
        void pushDown(int v, int tl, int tr) {
            if (!isLazy[v]) return;

            isLazy[v] = false;
            int tm = (tl + tr) / 2;

            apply(2 * v, tl, tm, unpropUpd[v]);
            apply(2 * v + 1, tm + 1, tr, unpropUpd[v]);

            unpropUpd[v] = 0;
        }

        // build from array
        void build(int[] a, int v, int tl, int tr) {
            if (tl == tr) {
                t[v] = a[tl];
                return;
            }

            int tm = (tl + tr) / 2;
            build(a, 2 * v, tl, tm);
            build(a, 2 * v + 1, tm + 1, tr);
            t[v] = t[2 * v] + t[2 * v + 1];
        }

        void build(int[] a) {
            build(a, 1, 0, len - 1);
        }

        // range sum query
        int query(int v, int tl, int tr, int l, int r) {
            if (tl > r || tr < l) return 0; // no overlap
            if (l <= tl && tr <= r) return t[v]; // full overlap

            pushDown(v, tl, tr);

            int tm = (tl + tr) / 2;
            int leftAns = query(2 * v, tl, tm, l, r);
            int rightAns = query(2 * v + 1, tm + 1, tr, l, r);
            return leftAns + rightAns;
        }

        int query(int l, int r) {
            return query(1, 0, len - 1, l, r);
        }

        // range assign update: set all values in [l, r] to val
        void update(int v, int tl, int tr, int l, int r, int val) {
            if (tl > r || tr < l) return; // no overlap

            if (l <= tl && tr <= r) {
                apply(v, tl, tr, val);
                return;
            }

            pushDown(v, tl, tr);

            int tm = (tl + tr) / 2;
            update(2 * v, tl, tm, l, r, val);
            update(2 * v + 1, tm + 1, tr, l, r, val);

            t[v] = t[2 * v] + t[2 * v + 1];
        }

        void update(int l, int r, int val) {
            update(1, 0, len - 1, l, r, val);
        }
    }

    public static void main(String[] args) {
        int n = 8;
        int[] a = {1, 2, 1, 4, 2, 3, 1, 1};

        SegTree segTree = new SegTree(n);
        segTree.build(a);

        // Build - View Build Data
        for (int i = 0; i < n; i++) {
            System.out.print(segTree.query(i, i) + " ");
        }
        System.out.println();

        // Query - Range Query
        int sum = segTree.query(1, 5);
        System.out.println("Sum for range id = 1 to id = 5 is: " + sum);

        // Update - Point Update
        segTree.update(2, 2, 10);
        sum = segTree.query(1, 5);
        System.out.println("New Sum for range id = 1 to id = 5 is: " + sum);

        for (int i = 0; i < n; i++) {
            System.out.print(segTree.query(i, i) + " ");
        }
        System.out.println("\n");

        segTree.update(2, 7, 10);
        segTree.update(2, 7, 20);

        for (int i = 0; i < n; i++) {
            System.out.print(segTree.query(i, i) + " ");
        }
        System.out.println();

        sum = segTree.query(2, 3);
        System.out.println("New Sum for range l = 2 to r = 3 is: " + sum);
    }
}