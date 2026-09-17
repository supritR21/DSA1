import java.util.*;

public class LCABinaryLifting {
    static int N;
    static int LOG;
    static ArrayList<ArrayList<Integer>> tree;
    static int[][] up;
    static int[] depth;

    private static void dfs(int node, int par) {
        up[node][0] = par;
        for(int i=1; i<LOG; i++) {
            if(up[node][i-1]!=-1) {
                up[node][i] = up[up[node][i-1]][i-1];
            } else {
                up[node][i] = -1;
            }
        }
        for(int child : tree.get(node)) {
            if(child==par) continue;
            depth[child] = depth[node]+1;
            dfs(child,node);
        }
    }

    private static int lca(int u, int v) {
        if(depth[u]<depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[u]-depth[v];
        for(int i=LOG-1; i>=0; i--) {
            if((diff&i)!=0) {
                u=up[u][i];
            }
        }
        if(u==v) return u;
        for(int i=LOG-1; i>=0; i--) {
            if(up[u][i]!=-1 && up[u][i]!=up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        return up[u][0];
    }

    public static void main(String[] args) {
        N=7;
        LOG = (int) (Math.log(N)/Math.log(2)) + 1;
        tree = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            tree.add(new ArrayList<>());
        }
        // Adding Edges
        up = new int[N+1][LOG];
        depth = new int[N+1];

        for(int i=0; i<=N; i++) {
            Arrays.fill(up[i],-1);
        }

        dfs(1,-1);
        // Checking LCA's
    }
}
