package datastructures.concrete.dictionaries;

import datastructures.concrete.KVPair;
import datastructures.interfaces.IDictionary;
import misc.exceptions.NoSuchKeyException;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * See the spec and IDictionary for more details on what each method should do
 */
public class ChainedHashDictionary<K, V> implements IDictionary<K, V> {
    // You may not change or rename this field: we will be inspecting
    // it using our private tests.
    private IDictionary<K, V>[] chains;
    private int num;
    // You're encouraged to add extra fields (and helper methods) though!
    //In:none
    //creates a hash dictionary starting with size 10 that will grow or shrink as needed.
    //Out:none
    public ChainedHashDictionary() {
        this.chains = makeArrayOfChains(10);
        this.num = 0;
    }

    /**
     * This method will return a new, empty array of the given size
     * that can contain IDictionary<K, V> objects.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private IDictionary<K, V>[] makeArrayOfChains(int size) {
        // Note: You do not need to modify this method.
        // See ArrayDictionary's makeArrayOfPairs(...) method for
        // more background on why we need this method.
        return (IDictionary<K, V>[]) new IDictionary[size];
    }
    //key of type K
    // Retrieves the value stored under that key
    //if it isnt in the dictionary throws NoSuchKeyException
    //Out:value of type V
    @Override
    public V get(K key) {
        if (this.containsKey(key)) {
            return chains[hashCode(key, chains.length)].get(key);
        }
        throw new NoSuchKeyException();
    }
    //In:key of type T, value of type V
    //Adds the value to the dictionary. If the key is already used simply replaces the value
    //If the dictionary is getting to cluttered it expands the size. Reordering to be more efficient.
    //Out:none
    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
            chains[hashCode(key, chains.length)].put(key, value);
        }else { 
            if (num == chains.length) {
            
                Iterator<KVPair<K, V>> it = this.iterator();
                IDictionary<K, V>[] nchains = makeArrayOfChains(num*2);
                
                while (it.hasNext()) {
                    KVPair<K, V> curr = it.next();
                   
                    if (nchains[hashCode(curr.getKey(), num*2)] == null) {
                        nchains[hashCode(curr.getKey(), num*2)] = new ArrayDictionary<K, V>();
                    }
                    nchains[hashCode(curr.getKey(), num*2)].put(curr.getKey(), curr.getValue()); 
                }
                chains = nchains;
            
            }
                num++;
                if (chains[hashCode(key, chains.length)] == null) {
                    this.chains[hashCode(key, chains.length)] = new ArrayDictionary<K, V>();
                }
                chains[hashCode(key, chains.length)].put(key, value);
        }
    
    }
    //In:key of type T, size of type int
    //A private method that returns the proper index to use for a key.
    //Out:int
    private int hashCode(K key, int s){
        if (key == null) {
            return 0;
        }
        int keyh = key.hashCode();
        if (keyh < 0) {
            keyh = keyh * -1; 
        }
        return keyh%s;
    }
    
    //In:key of type T
    //removes the item from the dictionary, returning value of it. if not contained throws NoSuchKeyException. 
    //Out:Value of type V
    @Override
    public V remove(K key) {
        if (containsKey(key)) {
            num--;
            return chains[hashCode(key, chains.length)].remove(key);
        }
        throw new NoSuchKeyException();

    }
    //In:key of type T
    //returns whether the key is inside of the dictionary 
    //Out:Boolean
    @Override
    public boolean containsKey(K key) {
        
            if (chains[hashCode(key, chains.length)] != null) {
                
                   return chains[hashCode(key, chains.length)].containsKey(key);
                
            }
        
        
        return false;
    }
    //In:none
    //returns the number of keys contained in the dictionary
    //Out:int
    @Override
    public int size() {
        return this.num;
    }
    //In:none
    //Returns an iterator for the dictionary
    //Out:Iterator
    @Override
    public Iterator<KVPair<K, V>> iterator() {
        // Note: you do not need to change this method
        return new ChainedIterator<>(this.chains, this.num);
    }

    /**
     * Hints:
     *
     * 1. You should add extra fields to keep track of your iteration
     *    state. You can add as many fields as you want. If it helps,
     *    our reference implementation uses three (including the one we
     *    gave you).
     *
     * 2. Before you try and write code, try designing an algorithm
     *    using pencil and paper and run through a few examples by hand.
     *
     *    We STRONGLY recommend you spend some time doing this before
     *    coding. Getting the invariants correct can be tricky, and
     *    running through your proposed algorithm using pencil and
     *    paper is a good way of helping you iron them out.
     *
     * 3. Think about what exactly your *invariants* are. As a
     *    reminder, an *invariant* is something that must *always* be 
     *    true once the constructor is done setting up the class AND 
     *    must *always* be true both before and after you call any 
     *    method in your class.
     *
     *    Once you've decided, write them down in a comment somewhere to
     *    help you remember.
     *
     *    You may also find it useful to write a helper method that checks
     *    your invariants and throws an exception if they're violated.
     *    You can then call this helper method at the start and end of each
     *    method if you're running into issues while debugging.
     *
     *    (Be sure to delete this method once your iterator is fully working.)
     *
     * Implementation restrictions:
     *
     * 1. You **MAY NOT** create any new data structures. Iterators
     *    are meant to be lightweight and so should not be copying
     *    the data contained in your dictionary to some other data
     *    structure.
     *
     * 2. You **MAY** call the `.iterator()` method on each IDictionary
     *    instance inside your 'chains' array, however.
     */
    private static class ChainedIterator<K, V> implements Iterator<KVPair<K, V>> {
        private IDictionary<K, V>[] chains;
        private int checked; 
        private int on;
        private int num;
        private Iterator<KVPair<K, V>> current;
        
        public ChainedIterator(IDictionary<K, V>[] chains, int num) {
            this.chains = chains;
            this.checked = 0;
            this.on = 0;
            this.num = num;
            
        }

        @Override
        public boolean hasNext() {
            return checked < num;
        }

        @Override
        public KVPair<K, V> next() {
            if (current != null && current.hasNext()) {
                this.checked++;
                return current.next();
            } else {
                if (this.hasNext()) {
                    for (int i = on; i < this.chains.length; i++) {
                        this.on++;
                        if (this.chains[i] != null) {
                            current = this.chains[i].iterator();
                            
                            if (current.hasNext()) {
                                this.checked++;
                                return current.next();
                            }  
                            
                        }
                        
                        
                    }
                }
                throw new NoSuchElementException();
            }
            
            
        }
    }
}
