import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class ShortestPathJGraphT {
    public static <V> Map<V, Integer> bfsShortestPaths(Graph<V, DefaultEdge> graph, V start) {
        Map<V, Integer> distances = new HashMap<>();
        for (V vertex : graph.vertexSet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        Queue<V> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            int currentDistance = distances.get(current);

            for (V neighbor : graph.edgesOf(current).stream()
                    .map(edge -> graph.getEdgeTarget(edge).equals(current) ?
                            graph.getEdgeSource(edge) : graph.getEdgeTarget(edge))
                    .toList()) {
                if (distances.get(neighbor) == Integer.MAX_VALUE) {
                    distances.put(neighbor, currentDistance + 1);
                    queue.add(neighbor);
                }
            }
        }

        return distances;
    }
}