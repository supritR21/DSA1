import java.util.*;

public class EulerTour {
    static List<List<Integer>> adj;
    static int[] tin;
    static int[] tout;
    static int[] euler;
    static int timer = 0;

    static void dfs(int node, int parent) {
        tin[node] = timer;
        euler[timer] = node;
        timer++;
        for(int nei : adj.get(node)) {
            if(nei==parent) continue;
            dfs(nei, node);
        }
        tout[node] = timer-1;
    }

    static int[] getSubtreeElements(int u, int[] tin, int[] tout, int[] euler) {
        int size = tout[u]-tin[u]+1;
        int[] result = new int[size];
        for(int i=0; i<size; i++) {
            result[i] = euler[tin[u]+i];
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 7;
        adj = new ArrayList<>();
        for(int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }
        addEdge(0, 1);
        addEdge(0,2);
        addEdge(1, 3);
        addEdge(1, 4);
        addEdge(2, 5);
        addEdge(5,6);

        tin = new int[n];
        tout = new int[n];
        euler = new int[n];

        dfs(0,-1);

        System.out.println("Euler: " + Arrays.toString(euler));
        System.out.println("tin: " + Arrays.toString(tin));
        System.out.println("tout: " + Arrays.toString(tout));

        int u = 1;
        int[] subtree = getSubtreeElements(u,tin,tout,euler);

        System.out.println("Subtree of " + u + ": " + Arrays.toString(subtree));
    }
    static void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
}
