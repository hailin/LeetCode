public class FriendCircles {
    /**
     * 1. set initial number of circles to the the number of people
     * 2. when looking at self, or no relationship, skip
     * 3. when found relationship M[i][j] = 1, subtract 1 circle, and make sure M[j][i] is set to
     *    0 to avoid double counting
     *
     *  TODO: use rank to improve runtime complexity
     */
    public int findCircleNum(int[][] M) {
        int[] parent = new int[M.length];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        int circles = M.length;

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                int circleA = findParent(parent, i);
                int circleB = findParent(parent, j);

                if (M[i][j] == 0 || i == j) continue;

                if (M[i][j] == 1 && circleA != circleB) {
                    M[j][i] = 0;
                    parent[circleA] = circleB;
                    circles--;
                }
            }
        }

        return circles;
    }

    public int findParent(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }

        parent[x] = findParent(parent, parent[x]);

        return parent[x];
    }
}
