import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a MaxHeap.
 *
 * @author Puneet Bansal
 * @version 1.0
 * @userid pbansal43
 * @GTID 903589378
 * <p>
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 * <p>
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MaxHeap.
     * <p>
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     */
    public MaxHeap() {
        this.backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     * <p>
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     * <p>
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     * <p>
     * The backingArray should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY).
     * Index 0 should remain empty, indices 1 to n should contain the data in
     * proper order, and the rest of the indices should be empty.
     * <p>
     * Consider how to most efficiently determine if the list contains null data.
     *
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        this.backingArray = (T[]) new Comparable[2 * data.size() + 1];
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == null) {
                throw new IllegalArgumentException("a value in data is null");
            }
            this.backingArray[i + 1] = data.get(i);
        }
        this.size = data.size();
        for (int i = size / 2; i > 0; i--) {
            downHeap(i);
        }
    }

    /**
     * This method will restore the heap properties after an element is added.
     * @param index the index to start upheaping at
     */
    private void upHeap(int index) {
        while (index > 1 && (this.backingArray[index / 2].compareTo(this.backingArray[index])) < 0) {
            T swap = this.backingArray[index / 2];
            this.backingArray[index / 2] = this.backingArray[index];
            this.backingArray[index] = swap;
            index = index / 2;
        }
    }

    /**
     * Adds the data to the heap.
     * <p>
     * If sufficient space is not available in the backing array (the backing
     * array is full except for index 0), resize it to double the current
     * length.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data given was null");
        }
        if (this.size + 1 == this.backingArray.length) {
            T[] newArray = (T[]) new Comparable[this.backingArray.length * 2];
            for (int i = 0; i < this.backingArray.length; i++) {
                newArray[i] = this.backingArray[i];
            }
            this.backingArray = newArray;
        }
        this.size++;
        this.backingArray[this.size] = data;
        upHeap(this.size);
    }

    /**
     * Removes and returns the root of the heap.
     * <p>
     * Do not shrink the backing array.
     * <p>
     * Replace any unused spots in the array with null.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T removedData = this.backingArray[1];
        this.backingArray[1] = this.backingArray[this.size];
        this.backingArray[this.size] = null;
        this.size--;
        downHeap(1);
        return removedData;
    }

    /**
     * This method will downheap from a given index to restore order to heap
     * properties.
     * @param index the index to start downheap
     */
    private void downHeap(int index) {
        while (index * 2 <= this.size) {
            if (index * 2 + 1 > this.size) {
                if (this.backingArray[index].compareTo(this.backingArray[index * 2]) < 0) {
                    T swap = this.backingArray[index];
                    this.backingArray[index] = this.backingArray[index * 2];
                    this.backingArray[index * 2] = swap;
                }
                break;
            }
            boolean whichChild = false;
            if (this.backingArray[index * 2].compareTo(this.backingArray[index * 2 + 1]) > 0) {
                whichChild = true; // determines which child to go through
            }
            if (this.backingArray[index].compareTo(this.backingArray[index * 2]) < 0 && whichChild) {
                T swap = this.backingArray[index];
                this.backingArray[index] = this.backingArray[index * 2];
                this.backingArray[index * 2] = swap;
                index = index * 2;
            } else if (this.backingArray[index].compareTo(this.backingArray[index * 2 + 1]) < 0 && !whichChild) {
                T swap = this.backingArray[index];
                this.backingArray[index] = this.backingArray[index * 2 + 1];
                this.backingArray[index * 2 + 1] = swap;
                index = index * 2 + 1;
            } else {
                break;
            }
        }
    }


    /**
     * Returns the maximum element in the heap.
     *
     * @return the maximum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return backingArray[1];
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /**
     * Clears the heap.
     * <p>
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        this.backingArray = (T[]) new Comparable[this.backingArray.length];
        this.size = 0;
    }

    /**
     * Returns the backing array of the heap.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
