package datastructures.concrete.dictionaries;


import java.util.Iterator;
import java.util.NoSuchElementException;

import datastructures.concrete.KVPair;
import datastructures.interfaces.IDictionary;
import misc.exceptions.NoSuchKeyException;

/**
 * Returns the value corresponding to the given key.
 *Is Generalized to use and variable type for keys or Values.
 * @throws NoSuchKeyException if the dictionary does not contain the given key.
 */
public class ArrayDictionary<K, V> implements IDictionary<K, V> {
    // You may not change or rename this field: we will be inspecting
    // it using our private tests.
    private Pair<K, V>[] pairs;
    private int size;
    // You're encouraged to add extra fields (and helper methods) though!
    //IN: None.
    //Creates a new ArrayDictionary. 
    //It will start with length of 16 but will grow as needed
    //Initializes the size parameter to keep track of the number of items in the array.
    //OUT:None.
    public ArrayDictionary() {
        this.pairs = makeArrayOfPairs(16);
        this.size = 0;
    }
 
    /**
     * This method will return a new, empty array of the given size
     * that can contain Pair<K, V> objects.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private Pair<K, V>[] makeArrayOfPairs(int arraySize) {
        // It turns out that creating arrays of generic objects in Java
        // is complicated due to something known as 'type erasure'.
        //
        // We've given you this helper method to help simplify this part of
        // your assignment. Use this helper method as appropriate when
        // implementing the rest of this class.
        //
        // You are not required to understand how this method works, what
        // type erasure is, or how arrays and generics interact. Do not
        // modify this method in any way.
        return (Pair<K, V>[]) (new Pair[arraySize]);

    }
    
    //IN: key .
    //Iterates through the array looking for the key specified.
    //If found it returns the corresponding value.
    //If not found will throw a NoSuchKeyException;
    //OUT:value of type V.
    @Override
    public V get(K key) {
        
            for (int i = 0; i < size; i++){    
                if (key == null) {
                    if (pairs[i].key == key) {
                        
                        return this.pairs[i].value;
                    }
                }else if (this.pairs[i].key != null && this.pairs[i].key.equals(key)) {
                    
                    return this.pairs[i].value;
                }
            }
        
        throw new NoSuchKeyException();
    }
    //IN: key, value .
    //If the dictionary already contains the key
    //iterates through the array looking for the key specified and replaces the value.
    //If not found it creates a new linking from key to value.
    //Capable of creating more space if necessary.
    //OUT:None.
    @Override
    public void put(K key, V value) {
   
        
            for (int i = 0; i < size; i++){
                    if (key == null) {
                        if (pairs[i].key == key) {
                            this.pairs[i].value = value;
                            return;
                        }
                    }else if (this.pairs[i].key != null && this.pairs[i].key.equals(key)) {
                        this.pairs[i].value = value;
                        return;
                    }
            }
        
        
            
                this.pairs[size] = new Pair<K, V>(key, value);  
                size++;
                if (size == this.pairs.length-1) {
                    Pair<K, V>[] pairs2 = makeArrayOfPairs(size*2);
                    for (int i = 0; i < size; i++){          
                        pairs2[i] = this.pairs[i];
                     }
                    this.pairs = pairs2;
                    
                }
        
    }
    
    //IN: key .
    //Iterates through the array looking for the key specified.
    //If found it deletes the corresponding information and returns it.
    //If not found will throw a NoSuchKeyException;
    //OUT:value of type V.
    @Override
    public V remove(K key) {
        
            for (int i = 0; i < size; i++){          
            
                if (key == null) {
                    if (pairs[i].key == key) {
                        
                        V val = this.pairs[i].value;
                        this.pairs[i] = this.pairs[size-1];
                        this.pairs[size-1] = null;
                        size--;
                        return val;
                    }
                }else if (this.pairs[i].key != null && pairs[i].key.equals(key)) {
                       V val = this.pairs[i].value;
                       this.pairs[i] = this.pairs[size-1];
                       this.pairs[size-1] = null;
                       size--;
                       return val;
                    }
                
            
            }
            throw new NoSuchKeyException();
        
    }
    //IN: key .
    //Iterates through the array looking for the key specified.
    //If found it returns TRUE.
    //If not found it returns FALSE.
    //OUT:Boolean.
    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++){
            
            if (key == null) {
                if (pairs[i].key == key) {
                    
                    return true;
                }
            }else if (this.pairs[i].key != null && this.pairs[i].key.equals(key)) {
                
                return true;
            }
        
        }
        return false;
    }
    //In:none
    //returns number of items in dictionary
    //Out:int
    @Override
    public int size() {
        return size;
    }

    private static class Pair<K, V> {
        public K key;
        public V value;

        // You may add constructors and methods to this class as necessary.
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }


    //In:none
    //returns Iterator for ArrayList
    //Out:Iterator
    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new ArrayDictionaryIterator<>(this.pairs, this.size);
    }


    private static class ArrayDictionaryIterator<K, V> implements Iterator<KVPair<K, V>> {
    private int current;
    private int size;
    private Pair<K, V>[] pairs;
    
    public ArrayDictionaryIterator(Pair<K, V>[] pairs, int size) {
        
        this.current = 0;
        this.size = size;
        this.pairs = pairs;
    }

    /**
     * Returns 'true' if the iterator still has elements to look at;
     * returns 'false' otherwise.
     */
    public boolean hasNext() {
        return current < size;
    }

    /**
     * Returns the next item in the iteration and internally updates the
     * iterator to advance one element forward.
     *
     * @throws NoSuchElementException if we have reached the end of the iteration and
     *         there are no more elements to look at.
     */
    public KVPair<K, V> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        KVPair<K, V> p = new KVPair<K, V>(this.pairs[current].key, this.pairs[current].value);
        current++;
        
        return p;
    }
}
}