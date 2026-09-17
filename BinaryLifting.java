import java.util.*;

public class BinaryLifting {
    static int LOG;
    static int[][] up;
    static int[] depth;
    static List<List<Integer>> adj;

    static void dfs(int node, int parent) {
        up[node][0] = parent;
        for(int j=1; j<LOG; j++) {
            up[node][j] = up[up[node][j-1]][j-1];
        }
        for(int nei : adj.get(node)) {
            if(nei==parent) continue;
            depth[nei] = depth[node]+1;
            dfs(nei,node);
        }
    }

    static int KthAncestor(int node, int k) {
        for(int j=0; j<LOG; j++) {
            if((k&(1<<j))!=0) {
                node = up[node][j];
                if(node==0) return -1;
            }
        }
        return node;
    }
    static int lca(int u, int v) {
        if(depth[u]<depth[v]) {
            int temp = u;
            u=v;
            v=temp;
        }
        int diff = depth[u]-depth[v];
        for(int j=0; j<LOG; j++) {
            if((diff&(1<<j))!=0) {
                u = up[u][j];
            }
        }
        if(u==v) return u;
        for(int j=LOG-1; j>=0; j--) {
            if(up[u][j]!=up[v][j]) {
                u=up[u][j];
                v=up[v][j];
            }
        }
        return up[u][0];
    }
    public static void main(String[] args) {
        //
        Scanner sc = new Scanner(System.in);
        int n = 7;
        adj = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(2, 4);
        addEdge(2, 5);
        addEdge(3, 6);
        addEdge(5, 7);

        LOG = 1;

        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[n + 1][LOG];
        depth = new int[n + 1];

        depth[1] = 0;

        dfs(1, 0);

        System.out.println("3rd ancestor of 7 = "
                + KthAncestor(7, 3));

        System.out.println("1st ancestor of 7 = "
                + KthAncestor(7, 1));

        System.out.println("2nd ancestor of 7 = "
                + KthAncestor(7, 2));

        
        System.out.println("LCA(4, 7) = "
                + lca(4, 7));

        System.out.println("LCA(4, 6) = "
                + lca(4, 6));

        System.out.println("LCA(7, 6) = "
                + lca(7, 6));

        sc.close();
    }
    static void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
}
