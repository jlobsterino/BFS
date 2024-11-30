import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ShortestPathJGraphTTest {

    @Test
    public void testConnectedGraph() {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        Map<String, Integer> distances = ShortestPathJGraphT.bfsShortestPaths(graph, "A");
        assertEquals(0, distances.get("A"));
        assertEquals(1, distances.get("B"));
        assertEquals(2, distances.get("C"));
    }

    @Test
    public void testDisconnectedGraph() {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");

        Map<String, Integer> distances = ShortestPathJGraphT.bfsShortestPaths(graph, "A");
        assertEquals(0, distances.get("A"));
        assertEquals(1, distances.get("B"));
        assertEquals(Integer.MAX_VALUE, distances.get("C"));
    }

    @Test
    public void testSingleNodeGraph() {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        graph.addVertex("A");

        Map<String, Integer> distances = ShortestPathJGraphT.bfsShortestPaths(graph, "A");
        assertEquals(0, distances.get("A"));
    }

    @Test
    public void testCyclicGraph() {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");

        Map<String, Integer> distances = ShortestPathJGraphT.bfsShortestPaths(graph, "A");
        assertEquals(0, distances.get("A"));
        assertEquals(1, distances.get("B"));
        assertEquals(1, distances.get("C"));
    }

    @Test
    public void testEmptyGraph() {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        try {
            Map<String, Integer> distances = ShortestPathJGraphT.bfsShortestPaths(graph, "A");
            fail("Ожидалось исключение IllegalArgumentException из-за отсутствия вершины в графе");
        } catch (IllegalArgumentException e) {
            assertEquals("no such vertex in graph: A", e.getMessage());
        }
    }
}