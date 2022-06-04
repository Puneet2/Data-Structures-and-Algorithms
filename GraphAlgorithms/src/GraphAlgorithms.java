import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Puneet Bansal
 * @version 1.0
 * @userid pbansal43
 * @GTID 903589378
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     * <p>
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     * <p>
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     * <p>
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null || !graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("input is null, or start doesn't exist in the graph");
        }
        Set<Vertex<T>> visited = new HashSet<>();
        List<Vertex<T>> list = new LinkedList<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            Vertex<T> vertex = queue.remove();
            list.add(vertex);
            for (VertexDistance<T> w : graph.getAdjList().get(vertex)) {
                if (!visited.contains(w.getVertex())) {
                    visited.add(w.getVertex());
                    //list.add(w.getVertex());
                    queue.add(w.getVertex());
                }
            }
        }
        return list;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     * <p>
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     * <p>
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * all points for this method.
     * <p>
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     * <p>
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null || !graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("input is null, or start doesn't exist in the graph");
        }
        Set<Vertex<T>> visited = new HashSet<>();
        List<Vertex<T>> list = new LinkedList<>();
        dfs(graph, start, visited, list);
        return list;
    }

    /**
     * This is a helper method to dfs, and will do the search for dfs.
     * @param graph the graph to search through
     * @param vertex the vertex to begin the dfs on
     * @param visited the set of vertices visited
     * @param list the list to return
     * @param <T> the generic typing of the data
     */
    private static <T> void dfs(Graph<T> graph, Vertex<T> vertex, Set<Vertex<T>> visited, List<Vertex<T>> list) {
        visited.add(vertex);
        list.add(vertex);
        for (VertexDistance<T> w : graph.getAdjList().get(vertex)) {
            if (!visited.contains(w.getVertex())) {
                dfs(graph, w.getVertex(), visited, list);
            }
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     * <p>
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     * <p>
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     * <p>
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     * <p>
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty.
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null || graph == null || !graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("input is null, or start doesn't exist in the graph");
        }

        Set<Vertex<T>> visited = new HashSet<>();
        PriorityQueue<VertexDistance<T>> pqQueue = new PriorityQueue<>();
        Map<Vertex<T>, Integer> dmMap = new HashMap<>();
        for (Vertex<T> v : graph.getAdjList().keySet()) {
            //if(v.equals(start)){
            //    DM.put(v,0);
            // }
            // else {
            dmMap.put(v, Integer.MAX_VALUE);
            //}
        }
        pqQueue.add(new VertexDistance<T>(start, 0));
        while (!pqQueue.isEmpty() && visited.size() != graph.getVertices().size()) {
            VertexDistance<T> temp = pqQueue.remove();
            if (!visited.contains(temp.getVertex())) {
                visited.add(temp.getVertex());
                dmMap.put(temp.getVertex(), temp.getDistance());
                for (VertexDistance<T> w : graph.getAdjList().get(temp.getVertex())) {
                    pqQueue.add(new VertexDistance<T>(w.getVertex(), temp.getDistance() + w.getDistance()));
                }
            }
        }
        return dmMap;
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     * <p>
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     * <p>
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     * <p>
     * You may assume that there will only be one valid MST that can be formed.
     * <p>
     * You should NOT allow self-loops or parallel edges in the MST.
     * <p>
     * You may import/use PriorityQueue, java.util.Set, and any class that
     * implements the aforementioned interface.
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     * <p>
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null || !graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("input is null, or start doesn't exist in the graph");
        }
        Set<Vertex<T>> visited = new HashSet<>();
        Set<Edge<T>> edgeSet = new HashSet<>();
        PriorityQueue<Edge<T>> pqQueue = new PriorityQueue<>();
        for (VertexDistance<T> vertexDistance : graph.getAdjList().get(start)) {
            pqQueue.add(new Edge<T>(start, vertexDistance.getVertex(), vertexDistance.getDistance()));
        }
        visited.add(start);
        while (!pqQueue.isEmpty() && visited.size() != graph.getVertices().size()) {
            Edge<T> temp = pqQueue.remove();
            if (!visited.contains(temp.getV())) {
                visited.add(temp.getV());
                edgeSet.add(temp);
                edgeSet.add(new Edge<T>(temp.getV(), temp.getU(), temp.getWeight()));
                for (VertexDistance<T> vertexDistance : graph.getAdjList().get(temp.getV())) {
                    if (!visited.contains(vertexDistance.getVertex())) {
                        pqQueue.add(new Edge<T>(temp.getV(), vertexDistance.getVertex(), vertexDistance.getDistance()));
                    }
                }
            }
        }
        if (edgeSet.size() / 2 != graph.getVertices().size() - 1) {
            return null;
        }
        return edgeSet;
    }
}