package misc.graphs;



import datastructures.concrete.ArrayDisjointSet;
import datastructures.concrete.ArrayHeap;
import datastructures.concrete.ChainedHashSet;
import datastructures.concrete.DoubleLinkedList;
import datastructures.concrete.KVPair;
import datastructures.concrete.dictionaries.ChainedHashDictionary;
import datastructures.interfaces.IDictionary;
import datastructures.interfaces.IDisjointSet;
import datastructures.interfaces.IList;
import datastructures.interfaces.IPriorityQueue;
import datastructures.interfaces.ISet;
import misc.Searcher;
import misc.exceptions.NoPathExistsException;
import misc.exceptions.NotYetImplementedException;

/**
 * Represents an undirected, weighted graph, possibly containing self-loops, parallel edges,
 * and unconnected components.
 *
 * Note: This class is not meant to be a full-featured way of representing a graph.
 * We stick with supporting just a few, core set of operations needed for the
 * remainder of the project.
 */
public class Graph<V, E extends Edge<V> & Comparable<E>> {
    // NOTE 1:
    //
    // Feel free to add as many fields, private helper methods, and private
    // inner classes as you want.
    //
    // And of course, as always, you may also use any of the data structures
    // and algorithms we've implemented so far.
    //
    // Note: If you plan on adding a new class, please be sure to make it a private
    // static inner class contained within this file. Our testing infrastructure
    // works by copying specific files from your project to ours, and if you
    // add new files, they won't be copied and your code will not compile.
    //
    //
    // NOTE 2:
    //
    // You may notice that the generic types of Graph are a little bit more
    // complicated then usual.
    //
    // This class uses two generic parameters: V and E.
    //
    // - 'V' is the type of the vertices in the graph. The vertices can be
    //   any type the client wants -- there are no restrictions.
    //
    // - 'E' is the type of the edges in the graph. We've contrained Graph
    //   so that E *must* always be an instance of Edge<V> AND Comparable<E>.
    //
    //   What this means is that if you have an object of type E, you can use
    //   any of the methods from both the Edge interface and from the Comparable
    //   interface
    //
    // If you have any additional questions about generics, or run into issues while
    // working with them, please ask ASAP either on Piazza or during office hours.
    //
    // Working with generics is really not the focus of this class, so if you
    // get stuck, let us know we'll try and help you get unstuck as best as we can.

    /**
     * Constructs a new graph based on the given vertices and edges.
     *
     * @throws IllegalArgumentException  if any of the edges have a negative weight
     * @throws IllegalArgumentException  if one of the edges connects to a vertex not
     *                                   present in the 'vertices' list
     */
    private IList<E> reedges;
    private IList<E> edges;
    private IList<V> vertices;
    private IDictionary<V, ISet<E>>  graph;
    public Graph(IList<V> vertices, IList<E> edges) {
        graph = new ChainedHashDictionary<V, ISet<E>>();
        for(V vert: vertices) {
            graph.put(vert, new ChainedHashSet<E>());
            
        }
        for(E e: edges) {
            if(e.getWeight() < 0 || !vertices.contains(e.getVertex1()) || !vertices.contains(e.getVertex2())) {
                throw new IllegalArgumentException();
            }
            
            
        }
        
        this.edges = edges;
        this.vertices = vertices;
        ISet<E> removededges = findMinimumSpanningTree();
        this.reedges = setToList(findMinimumSpanningTree());
        for(E e: removededges) {
            graph.get(e.getVertex1()).add(e);
            graph.get(e.getVertex2()).add(e);
        }
       
    }

    /**
     * Sometimes, we store vertices and edges as sets instead of lists, so we
     * provide this extra constructor to make converting between the two more
     * convenient.
     */
    public Graph(ISet<V> vertices, ISet<E> edges) {
        // You do not need to modify this method.
        this(setToList(vertices), setToList(edges));
    }

    // You shouldn't need to call this helper method -- it only needs to be used
    // in the constructor above.
    private static <T> IList<T> setToList(ISet<T> set) {
        IList<T> output = new DoubleLinkedList<>();
        for (T item : set) {
            output.add(item);
        }
        return output;
    }

    /**
     * Returns the number of vertices contained within this graph.
     */
    public int numVertices() {
        return vertices.size();
    }

    /**
     * Returns the number of edges contained within this graph.
     */
    public int numEdges() {
        return edges.size();
    }

    /**
     * Returns the set of all edges that make up the minimum spanning tree of
     * this graph.
     *
     * If there exists multiple valid MSTs, return any one of them.
     *
     * Precondition: the graph does not contain any unconnected components.
     */
    public ISet<E> findMinimumSpanningTree() {
        ISet<E> out = new ChainedHashSet<>();
        IDisjointSet<V> s = new ArrayDisjointSet<>();
        for(V vert : vertices) {
            s.makeSet(vert);
        }
       
        IList<E> sortedEdges = Searcher.topKSort(this.numEdges(), edges);
        for(E edge : sortedEdges) {
            if(s.findSet(edge.getVertex1()) != s.findSet(edge.getVertex2())) {
                out.add(edge);
                s.union(edge.getVertex1(), edge.getVertex2());
            }
        }
        return out;
    }

    /**
     * Returns the edges that make up the shortest path from the start
     * to the end.
     *
     * The first edge in the output list should be the edge leading out
     * of the starting node; the last edge in the output list should be
     * the edge connecting to the end node.
     *
     * Return an empty list if the start and end vertices are the same.
     *
     * @throws NoPathExistsException  if there does not exist a path from the start to the end
     */
    public IList<E> findShortestPathBetween(V start, V end) {
        
        IDictionary<V, double[]> info = new ChainedHashDictionary<>();
        for (V v: vertices) {
            info.put(v, new double[2]);
            info.get(v)[0] = 0;
            info.get(v)[1] = Double.POSITIVE_INFINITY;
        }
        IList<E> out = new DoubleLinkedList<>();
        info.get(start)[1] = 0;
        V curr = start;
       
        while (info.get(end)[1] == Double.POSITIVE_INFINITY){
            for(E e : graph.get(curr)) {
                if(info.get(curr)[1] + 1 < info.get(e.getOtherVertex(curr))[1]){ 
                    info.get(e.getOtherVertex(curr))[1] = info.get(curr)[1] + 1;
                }
            }
             info.get(curr)[0] = 1;
             V next  = null ; 
             E edge = null;
             for(E e : graph.get(curr)) {
                 if(info.get(e.getOtherVertex(curr))[0]==0 && (next == null || info.get(e.getOtherVertex(curr))[1] < info.get(next)[1])){ 
                     next = e.getOtherVertex(curr);
                     edge = e;
                 }
             }
             if(next == null) {
                next =  out.remove().getOtherVertex(curr);
                
             }else {
             out.add(edge);
             }
             curr = next;
        }
        return out;
    }
}
