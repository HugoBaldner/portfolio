package datastructures.concrete;

import datastructures.interfaces.IList;

import misc.exceptions.EmptyContainerException;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Represents a data structure that contains an ordered and indexable sequence of elements.
 */
public class DoubleLinkedList<T> implements IList<T> {
    // You may not rename these fields or change their types.
    // We will be inspecting these in our private tests.
    // You also may not add any additional fields.
    private Node<T> front;
    private Node<T> back;
    private int size;
    //IN: None.
    //Creates a new DoubleLinkedList. 
    //It will start with null values but will grow as needed
    //Initializes the size parameter to keep track of the number of items in the list.
    //OUT:None.
    public DoubleLinkedList() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }
    //IN: item .
    //creates new item in double linked list adding to the back
    //increments size;
    //OUT:None.
    public void add(T item) {
        Node<T> addition = new Node<T>(item);        
        if (front == null) {
            front = addition;
            back = front;
        } else {
            back.next = addition;
            addition.prev = back;
            back = addition;
        }
        size++;
    }
    //IN:none.
    //removes final item in list.
    //If found it deletes the corresponding information and returns it.
    //If not found will throw a EmptyContainerException;
    //OUT:value of type T.
    public T remove() {
        if (front == null) {
            throw new EmptyContainerException();
        }
        T val = back.data;
        if (size == 1) {
            front = null;
            back = null;
            this.size = 0;
        } else {
            Node<T> behind = back.prev;
            behind.next = null;
            back = behind;
            size--;
        }
        return val;
    }
    //IN: index .
    //Iterates through the list looking for the key specified.
    //If found it returns the corresponding value.
    //If not found will throw a IndexOutOfBoundsException;
    //OUT:value of type E.
    public T get(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> curr = front;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }
    //IN: index, item.
    //Iterates through the list looking for the index specified.
    //If found it set the item to replace the one in that spot.
    //If not found will throw a IndexOutOfBoundsException;
    //OUT:None.
    public void set(int index, T item) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> ins = new Node<T>(item);
        if (size == 1) {
            front = ins;
            back = ins;
        } else if (index == 0) {
            Node<T> forward = front.next;
            ins.next = forward;
            forward.prev = ins;
            front = ins;
        } else if (index == size - 1) {
            Node<T> behind = back.prev;
            behind.next = ins;
            ins.prev = behind;
            back = ins;
        } else {
            Node<T> curr = front.next;
            for (int i = 1; i < index; i++) {
                curr = curr.next;
            }
            Node<T> previous = curr.prev;
            Node<T> next = curr.next;
            ins.next = next;
            ins.prev = previous;
            previous.next = ins;
            next.prev = ins;
        }
    }
    //IN: index, item.
    //Iterates through the list looking for the index specified.
    //If found it inserts the item to that spot.
    //If not found will throw a IndexOutOfBoundsException;
    //OUT:None.
    @Override
    public void insert(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            add(item);
        } else if (index == 0) {
            Node<T> insertion = new Node<T>(item);
            insertion.next = front;
            front.prev = insertion;
            front = insertion;
            size++;
        } else {
            Node<T> insertion = new Node<T>(item);
            Node<T> curr;
            if (index < size/2 || size < 5) {
                
                curr = front.next;
                    for (int i = 1; i < index; i++) {
                        curr = curr.next;
                    }
                        
             } else {
                curr = back.prev;
                for (int i = 0; i > this.size - index; i--) {
                    curr = curr.prev;
                }
            }
            Node<T> temp = curr.prev;
            insertion.next = curr;
            curr.prev = insertion;
            insertion.prev = temp;
            temp.next = insertion;
            this.size++;
        }
    }
    //IN: index .
    //Iterates through the list looking for the item specified.
    //If found it deletes it and returns value TRUE.
    //If not found it throws IndexOutOfBoundsException.
    //OUT:value of type E.
    @Override
    public T delete(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        size--;
        if (index == 0) {
            T val = front.data;
            if (size == -1) {
                front = null;
                back = null;
                return val; 
            }
            front = front.next;
            front.prev = null;
            return val;
        }
        if (index == size + 1) {
            T val = back.data;
            back = back.prev;
            back.next = null;
            return val;
        }
        Node<T> curr = front.next;
        for (int i = 1; i < index; i++) {
            curr = curr.next;
        }
        T val = curr.data;
        Node<T> prev = curr.prev;
        Node<T> next = curr.next;
        prev.next = next;
        next.prev = prev;
        return val;
    }
    //IN: item .
    //Iterates through the list looking for the key specified.
    //If found it returns index.
    //If not found it returns -1.
    //OUT:index.
    @Override
    public int indexOf(T item) {
        Node<T> curr = front;
        for (int i = 0; i < size; i++) {
            if (item == null && curr.data == null) {
                return i;
            }
            if (item != null && curr.data != null && item.equals(curr.data)) {
                return i;
            }
            curr = curr.next;
        }
        return -1;
    }
    //In:none
    //returns number of items in list
    //Out:none
    @Override
    public int size() {
        return size;
    }
    //IN: item .
    //goes through the list looking for the key specified.
    //If found it returns TRUE.
    //If not found it returns FALSE.
    //OUT:Boolean.
    @Override
    public boolean contains(T other) {
        return (indexOf(other) != -1);
    }

    @Override
    public Iterator<T> iterator() {
        // Note: we have provided a part of the implementation of
        // an iterator for you. You should complete the methods stubs
        // in the DoubleLinkedListIterator inner class at the bottom
        // of this file. You do not need to change this method.
        return new DoubleLinkedListIterator<>(this.front);
    }
    
   
    private static class Node<E> {
        // You may not change the fields in this node or add any new fields.
        public final E data;
        public Node<E> prev;
        public Node<E> next;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public Node(E data) {
            this(null, data, null);
        }
    }

    private static class DoubleLinkedListIterator<T> implements Iterator<T> {
        // You should not need to change this field, or add any new fields.
        private Node<T> current;

        public DoubleLinkedListIterator(Node<T> current) {
            // You do not need to make any changes to this constructor.
            this.current = current;
        }

        /**
         * Returns 'true' if the iterator still has elements to look at;
         * returns 'false' otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next item in the iteration and internally updates the
         * iterator to advance one element forward.
         *
         * @throws NoSuchElementException if we have reached the end of the iteration and
         *         there are no more elements to look at.
         */
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T temp = current.data;
            current = current.next;  // if next is null, hasNext will return false.
            return temp;
        }
    }
}

