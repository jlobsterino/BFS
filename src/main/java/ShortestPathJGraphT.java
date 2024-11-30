import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class ShortestPathJGraphT {
    public static <V> Map<V, Integer> bfsShortestPaths(Graph<V, DefaultEdge> graph, V start) {
        // Карта для хранения кратчайших расстояний
        Map<V, Integer> distances = new HashMap<>();
        for (V vertex : graph.vertexSet()) {
            distances.put(vertex, Integer.MAX_VALUE); // Инициализируем расстояния как бесконечные
        }
        distances.put(start, 0); // Расстояние до начальной вершины = 0

        // Очередь для BFS
        Queue<V> queue = new LinkedList<>();
        queue.add(start);

        // BFS
        while (!queue.isEmpty()) {
            V current = queue.poll();
            int currentDistance = distances.get(current);

            // Обрабатываем всех соседей текущей вершины
            for (V neighbor : graph.edgesOf(current).stream()
                    .map(edge -> graph.getEdgeTarget(edge).equals(current) ?
                            graph.getEdgeSource(edge) : graph.getEdgeTarget(edge))
                    .toList()) {
                // Если сосед ещё не посещён
                if (distances.get(neighbor) == Integer.MAX_VALUE) {
                    distances.put(neighbor, currentDistance + 1);
                    queue.add(neighbor); // Добавляем соседа в очередь
                }
            }
        }

        return distances; // Возвращаем карту с кратчайшими расстояниями
    }
}