public class FindRedundantConnection {
    public int[] findRedundantConnectionII(int[][] edges) {
        if (edges == null || edges.length == 0) return null;

        int[] parent = new int[edges.length + 1];

        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int[] edge: edges) {
            int parentU = findParent(parent, edge[0]);
            int parentV = findParent(parent, edge[1]);

            if (parentU == parentV) return edge;
            parent[parentU] = parentV;
        }

        return null;
    }

    public int findParent(int[] parent, int x) {
        if (parent[x] == x) return x;

        parent[x] = findParent(parent, parent[x]);

        return parent[x];
    }
}
