import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ShortestPathJGraphTTest {

    @Test
    public void testConnectedGraph() {
        // Создаём связный граф
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        // Проверяем кратчайшие расстояния
        Map<String, Integer> distances = ShortestPathJGraphT.bfsShortestPaths(graph, "A");
        assertEquals(0, distances.get("A"));
        assertEquals(1, distances.get("B"));
        assertEquals(2, distances.get("C"));
    }

    @Test
    public void testDisconnectedGraph() {
        // Создаём неразвязный граф
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");

        // Проверяем кратчайшие расстояния
        Map<String, Integer> distances = ShortestPathJGraphT.bfsShortestPaths(graph, "A");
        assertEquals(0, distances.get("A"));
        assertEquals(1, distances.get("B"));
        assertEquals(Integer.MAX_VALUE, distances.get("C")); // Вершина "C" недостижима
    }

    @Test
    public void testSingleNodeGraph() {
        // Создаём граф из одной вершины
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        graph.addVertex("A");

        // Проверяем кратчайшие расстояния
        Map<String, Integer> distances = ShortestPathJGraphT.bfsShortestPaths(graph, "A");
        assertEquals(0, distances.get("A"));
    }

    @Test
    public void testCyclicGraph() {
        // Создаём граф с циклом
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");

        // Проверяем кратчайшие расстояния
        Map<String, Integer> distances = ShortestPathJGraphT.bfsShortestPaths(graph, "A");
        assertEquals(0, distances.get("A"));
        assertEquals(1, distances.get("B"));
        assertEquals(1, distances.get("C"));
    }

    @Test
    public void testEmptyGraph() {
        // Создаём пустой граф
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        try {
            // Пытаемся выполнить BFS на пустом графе
            Map<String, Integer> distances = ShortestPathJGraphT.bfsShortestPaths(graph, "A");
            fail("Ожидалось исключение IllegalArgumentException из-за отсутствия вершины в графе");
        } catch (IllegalArgumentException e) {
            assertEquals("no such vertex in graph: A", e.getMessage());
        }
    }
}