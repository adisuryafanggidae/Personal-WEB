import java.util.LinkedList;

public class praktikumDFS {

    private int V = 10;
    private LinkedList<Integer>[] adj;

    praktikumDFS() {

        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }

        addEdge(0, 2);
        addEdge(0, 1);

        addEdge(2, 6);
        addEdge(2, 5);

        addEdge(1, 4);
        addEdge(1, 3);

        addEdge(4, 9);

        addEdge(3, 8);
        addEdge(3, 7);
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void DFSUtil(int v, boolean visited[], int target) {
        visited[v] = true;
        System.out.println("Memeriksa node a" + v);

        if (v == target) {
            System.out.println("\nNode a" + target + " DITEMUKAN!");
            System.exit(0);
        }

        for (int n : adj[v]) {
            if (!visited[n]) {
                DFSUtil(n, visited, target);
            }
        }
    }

    public static void main(String[] args) {
        praktikumDFS g = new praktikumDFS();
        int target = 9;

        System.out.println("Pencarian DFS untuk node a" + target + ":\n");
        g.DFSUtil(0, new boolean[10], target);

        System.out.println("Node tidak ditemukan.");
    }
}