import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.*;

public class Runner {
    public static void main(String[] args) {
        // Создаём граф
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        // Добавляем вершины
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        // Добавляем рёбра (они автоматически имеют единичный вес)
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");

        // Запускаем алгоритм
        Map<String, Integer> distances = ShortestPathJGraphT.bfsShortestPaths(graph, "A");

        // Вывод результатов
        System.out.println("Кратчайшие расстояния от вершины A:");
        for (Map.Entry<String, Integer> entry : distances.entrySet()) {
            System.out.println("Вершина " + entry.getKey() + ": " +
                    (entry.getValue() == Integer.MAX_VALUE ? "недостижима" : entry.getValue()));
        }
    }
}
