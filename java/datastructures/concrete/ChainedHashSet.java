package datastructures.concrete;

import datastructures.concrete.dictionaries.ChainedHashDictionary;
import datastructures.interfaces.IDictionary;
import datastructures.interfaces.ISet;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * See ISet for more details on what each method is supposed to do.
 */
public class ChainedHashSet<T> implements ISet<T> {
    // This should be the only field you need
    private IDictionary<T, Boolean> map;
    //In:none
    //returns Iterator for ArrayList
    //Out:none
    public ChainedHashSet() {
        // No need to change this method
        this.map = new ChainedHashDictionary<>();
    }
    //In:Item of type T
    //Adds the item to the set. 
    //Out:none
    @Override
    public void add(T item) {
        map.put(item, true);
    }
    //In:Item of type T
    //If the item is in the set removes the item. If not throws a NoSuchElementException 
    //Out:none
    @Override
    public void remove(T item) {
        if (map.containsKey(item)) {
            map.remove(item);
        }else {
            throw new NoSuchElementException();
        }
    }
    //In:Item of type T
    //checks if the set contains the item and returns answer
    //Out:Boolean
    @Override
    public boolean contains(T item) {
        
        return map.containsKey(item);
    }
    //In:none
    //returns size of set
    //Out:int
    @Override
    public int size() {
        return map.size();
    }
    //In:none
    //creates an iterator for the set
    //Out:Iterator
    @Override
    public Iterator<T> iterator() {
        return new SetIterator<>(this.map.iterator());
    }

    private static class SetIterator<T> implements Iterator<T> {
        // This should be the only field you need
        private Iterator<KVPair<T, Boolean>> iter;

        public SetIterator(Iterator<KVPair<T, Boolean>> iter) {
            // No need to change this method.
            this.iter = iter;
        }

        @Override
        public boolean hasNext() {
           return iter.hasNext();
        }

        @Override
        public T next() {
            return iter.next().getKey();
        }
    }
}
