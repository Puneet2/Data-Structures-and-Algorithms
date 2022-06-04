import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
 *
 * @author Puneet Bansal
 * @version 1.0
 * @userid pbansal43
 * @GTID Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class CircularSinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index was out of bounds");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data was null");
        }
        if (index == 0) {
            addToFront(data);
        } else {
            CircularSinglyLinkedListNode<T> curr = this.head;
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<>(data);
            newNode.setNext(this.head);
            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }
            newNode.setNext(curr.getNext());
            curr.setNext(newNode);
            this.size++;
        }
    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data was null");
        }
        CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(data);
        if (isEmpty()) {
            this.head = newNode;
            newNode.setNext(this.head);
        } else {
            newNode.setNext(this.head.getNext());
            newNode.setData(this.head.getData());
            this.head.setData(data);
            this.head.setNext(newNode);
        }
        this.size++;
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data was null");
        }
        CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(data);
        if (isEmpty()) {
            this.head = newNode;
            newNode.setNext(this.head);
        } else {
            newNode.setNext(this.head.getNext());
            newNode.setData(this.head.getData());
            this.head.setData(data);
            this.head.setNext(newNode);
            this.head = newNode;
        }
        this.size++;
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index was out of bounds");
        }
        T removedData;
        if (index == 0) {
            removedData = removeFromFront();
        } else {
            CircularSinglyLinkedListNode<T> curr = this.head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }
            removedData = curr.getNext().getData();
            curr.setNext(curr.getNext().getNext());
            this.size--;
        }
        return removedData;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        T removedData = this.head.getData();
        this.head.setData(this.head.getNext().getData());
        this.head.setNext(this.head.getNext().getNext());
        this.size--;
        return removedData;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        T removedData;
        CircularSinglyLinkedListNode<T> curr = this.head;
        while (curr.getNext().getNext() != this.head) {
            curr = curr.getNext();
        }
        removedData = curr.getNext().getData();
        curr.setNext(this.head);
        this.size--;
        return removedData;
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index was out of bounds");
        }
        CircularSinglyLinkedListNode<T> curr = this.head;
        if (index != 0) {
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
        }
        return curr.getData();
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data was null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("List was empty, could not find data");
        }
        T removedData;
        CircularSinglyLinkedListNode<T> curr = this.head;
        CircularSinglyLinkedListNode<T> lastOccurrence = null;
        if (curr.getData().equals(data)) {
            lastOccurrence = curr;
        }
        while (curr.getNext() != this.head) {
            if (curr.getNext().getData().equals(data)) {
                lastOccurrence = curr;
            }
            curr = curr.getNext();
        }
        if (lastOccurrence == null) {
            throw new NoSuchElementException("Data was not found in list");
        }
        if (lastOccurrence == this.head) {
            removedData = lastOccurrence.getData();
            removeFromFront();
        } else {
            removedData = lastOccurrence.getNext().getData();
            lastOccurrence.setNext(lastOccurrence.getNext().getNext());
            this.size--;
        }
        return removedData;
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] array = (T[]) new Object[this.size];
        CircularSinglyLinkedListNode<T> curr = this.head;
        for (int i = 0; i < this.size; i++) {
            array[i] = curr.getData();
            curr = curr.getNext();
        }
        return array;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     * 
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
