import java.util.LinkedList;
import java.util.Queue;

public class praktikumBFS {

    private int V = 10;
    private LinkedList<Integer>[] adj;

    praktikumBFS() {

        adj = new LinkedList[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }

        addEdge(0, 1);
        addEdge(0, 2);

        addEdge(2, 5);
        addEdge(2, 6);

        addEdge(1, 3);
        addEdge(1, 4);

        addEdge(4, 7);

        addEdge(3, 8);
        addEdge(3, 9);
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void BFS(int start, int target) {

        boolean[] visited = new boolean[V];

        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {

            int node = queue.poll();

            System.out.println("Memeriksa node a" + node);

            if (node == target) {
                System.out.println("\nNode a" + target + " DITEMUKAN!");
                return;
            }

            for (int neighbor : adj[node]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        System.out.println("Node tidak ditemukan.");
    }

    public static void main(String[] args) {

        praktikumBFS g = new praktikumBFS();

        int target = 7;

        System.out.println("Pencarian BFS untuk node a" + target + ":\n");

        g.BFS(0, target);
    }
}
