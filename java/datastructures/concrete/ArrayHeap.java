package datastructures.concrete;

import datastructures.interfaces.IPriorityQueue;
import misc.exceptions.EmptyContainerException;

/**
 * See IPriorityQueue for details on what each method must do.
 */
public class ArrayHeap<T extends Comparable<T>> implements IPriorityQueue<T> {
    // See spec: you must implement a implement a 4-heap.
    private static final int NUM_CHILDREN = 4;

    // You MUST use this field to store the contents of your heap.
    // You may NOT rename this field: we will be inspecting it within
    // our private tests.
    private T[] heap;
    private int size;
    // Feel free to add more fields and constants.

    public ArrayHeap() {
        this.heap = makeArrayOfT(21);
        this.size = 0; 
    }

    /**
     * This method will return a new, empty array of the given size
     * that can contain elements of type T.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private T[] makeArrayOfT(int n) {
        // This helper method is basically the same one we gave you
        // in ArrayDictionary and ChainedHashDictionary.
        //
        // As before, you do not need to understand how this method
        // works, and should not modify it in any way.
        return (T[]) (new Comparable[n]);
    }

    @Override
    public T removeMin() {
        if (size == 0) {
            throw new EmptyContainerException();
        }
       T curr = heap[0];
       heap[0] = heap[size-1];
       heap[size-1] = null;
       size--;
       percolateDown(0);
       return curr;
    }

    @Override
    public T peekMin() {
        if (size == 0) {
            throw new EmptyContainerException();
        }
        return heap[0];
    }

    @Override
    public void insert(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size*4 > heap.length) {
            T[] nHeap = makeArrayOfT(NUM_CHILDREN*heap.length+2);
            for (int i=0; i < size; i++) {
                nHeap[i] = heap[i];
            }
            heap = nHeap;
        }
        heap[size] = item;
        size++;
        
            percolateUp(size-1);
        
    }
    private int cmp(int n, int m) {
        if (heap[m] == null) {
            return -1;
        }
     
        return heap[n].compareTo(heap[m]);
    }
    private int percolateDown(int node) {
        while (cmp(node, 4*(node+1)-3)>0 || cmp(node, 4*(node+1)-1)>0 || 
               cmp(node, 4*(node+1)-2)>0 || cmp(node, 4*(node+1))>0){ 
            
                if (cmp(4*(node+1)-3, 4*(node+1)-1)<0 && cmp(4*(node+1)-3, 4*(node+1)-2)<0 
                        && cmp(4*(node+1)-3, 4*(node+1))<0){
                    T curr = heap[node];
                    heap[node] = heap[4*(node+1)-3];
                    heap[4*(node+1)-3] = curr; 
                    node = 4*(node+1)-3;
                            if (node >= size) {
                                return 1;
                            }
                }else if (cmp(4*(node+1)-2, 4*(node+1)-1)<0 && cmp(4*(node+1)-2, 4*(node+1))<0){
                    T curr = heap[node];
                    heap[node] = heap[4*(node+1)-2];
                    heap[4*(node+1)-2] = curr; 
                    node = 4*(node+1)-2;
                            if (node >= size) {
                                return 1;
                            }
                }else if (cmp(4*(node+1)-1, 4*(node+1)) <0){
                    T curr = heap[node];
                    heap[node] = heap[4*(node+1)-1];
                    heap[4*(node+1)-1] = curr; 
                    node = 4*(node+1)-1;
                            if (node >= size) {
                                return 1;
                            }
                }else {
                    T curr = heap[node];
                    heap[node] = heap[4*(node+1)];
                    heap[4*(node+1)] = curr; 
                    node = 4*(node+1);
                            if (node >= size) {
                                return 1;
                            }
                }
            

            } 
        return 1;
        
    }
    private void percolateUp(int node) {
        while (heap[node].compareTo(heap[(node+2)/4])<0) { //node.data is smaller than its parent
             T curr = heap[node];
             heap[node] = heap[(node+2)/4];
             heap[(node+2)/4] = curr; 
             node = (node+2)/4;
                     if (node<=0) {
                         return;
                     }
            } 
        
    }
    
    @Override
    public int size() {
        return size;
    }
}
