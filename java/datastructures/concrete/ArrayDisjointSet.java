package datastructures.concrete;


import datastructures.concrete.dictionaries.ChainedHashDictionary;
import datastructures.interfaces.IDictionary;
import datastructures.interfaces.IDisjointSet;

/**
 * See IDisjointSet for more details.
 */
public class ArrayDisjointSet<T> implements IDisjointSet<T> {
    // Note: do NOT rename or delete this field. We will be inspecting it
    // directly within our private tests.
    private int[] pointers;
    private IDictionary<T, Integer> map;
    int size;
    // However, feel free to add more methods and private helper methods.
    // You will probably need to add one or two more fields in order to
    // successfully implement this class.

    public ArrayDisjointSet() {
       this.pointers = new int[20];
       this.map = new ChainedHashDictionary<>();
       this.size = 0;
    }

    @Override
    public void makeSet(T item) {
        if(!map.containsKey(item)) {
            
            map.put(item, size);
            
            add();
            size++;
        }else {
            throw new IllegalArgumentException();
        }
    }
    private void add(){
        if(pointers.length < size*2) {
           int [] newpointers = new int[size*2];
           for(int i = 0; i < pointers.length;i++) {
               newpointers[i] = pointers[i];
           }
           this.pointers = newpointers;
        }
        pointers[size] = -1;

    }
    
    @Override
    public int findSet(T item) {
        if(!map.containsKey(item)) {
            throw new IllegalArgumentException();
        }
        int curr = map.get(item);
        int rep = curr;
        while(curr > -1) {
            rep = curr;
            curr = pointers[curr];
        }
        return rep;
    }

    @Override
    public void union(T item1, T item2) {
        int s1 = findSet(item1);
        int s2 = findSet(item2);
        if(s1 == s2) {
            throw new IllegalArgumentException();
        }
        if(pointers[s1] < pointers[s2]) {
            pointers[s1] +=pointers[s2];
            pointers[s2] = s1;
        }else {
            pointers[s2] +=pointers[s1];
            pointers[s1] = s2;
        }
    }
}
