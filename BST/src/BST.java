import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Your implementation of a BST.
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
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     * <p>
     * This constructor should initialize an empty BST.
     * <p>
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     * <p>
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     * <p>
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null || data.contains(null)) {
            throw new IllegalArgumentException("Data was null or element in collection was null");
        }
        for (T value : data) {
            add(value);
        }
    }

    /**
     * Adds the data to the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * The data becomes a leaf in the tree.
     * <p>
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data was null");
        }
        this.root = addHelper(data, this.root);
    }

    /**
     * Adds the data to the tree.
     * <p>
     * done recursively.
     * <p>
     * The data becomes a leaf in the tree.
     * <p>
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @param curr the current node
     * @return return the current node
     */
    private BSTNode addHelper(T data, BSTNode curr) {
        if (curr == null) {
            this.size++;
            return new BSTNode<T>(data);
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(addHelper(data, curr.getLeft()));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(addHelper(data, curr.getRight()));
        }
        return curr;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     * <p>
     * This must be done recursively.
     * <p>
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     * <p>
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     * <p>
     * Hint: Should you use value equality or reference equality?
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data given was null");
        }
        BSTNode<T> removedData = new BSTNode<T>(null);
        this.root = removeHelper(this.root, data, removedData);
        this.size--;
        return removedData.getData();
    }

    /**
     * Must be O(log n) for best and average cases and O(n) for worst case.
     * This removeHelper will recursively go through a tree and find the data
     * that needs to be removed. Handles the case when there are two child nodes
     * one child node, or none.
     *
     * @param curr        The current node to start looking at.
     * @param data        The data to remove.
     * @param removedData The data that is being removed.
     * @return The node that is going to be replaced at the removed node.
     * @throws NoSuchElementException if there is no value to be removed.
     */
    private BSTNode<T> removeHelper(BSTNode<T> curr, T data, BSTNode<T> removedData) {
        if (curr == null) {
            throw new NoSuchElementException("Data was not in tree");
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(removeHelper(curr.getLeft(), data, removedData));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(removeHelper(curr.getRight(), data, removedData));
        } else {
            if (curr.getLeft() == null && curr.getRight() == null) {  // no children
                removedData.setData(curr.getData());
                return null;
            } else if (curr.getLeft() == null) {
                removedData.setData(curr.getData());
                return curr.getRight();
            } else if (curr.getRight() == null) { // one child
                removedData.setData(curr.getData());
                return curr.getLeft();
            } else if (curr.getLeft() != null && curr.getRight() != null) { // 2 child
                removedData.setData(curr.getData());
                curr.setData(successorNode(curr.getRight()));
                BSTNode<T> blank = new BSTNode<>(null);
                curr.setRight(removeHelper(curr.getRight(), curr.getData(), blank));
                return curr;
            }
        }
        return curr;
    }

    /**
     * This method will find the successor node, used when there are two
     * child nodes to a node being removed.
     *
     * @param curr the current node to start looking at
     * @return return the successor node
     */
    private T successorNode(BSTNode<T> curr) {
        if (curr.getLeft() == null) {
            return curr.getData();
        }
        return successorNode(curr.getLeft());
    }

    /**
     * Returns the data from the tree matching the given parameter.
     * <p>
     * This must be done recursively.
     * <p>
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     * <p>
     * Hint: Should you use value equality or reference equality?
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data given was null");
        }
        BSTNode<T> node = getHelper(this.root, data);
        return node.getData();
    }

    /**
     * This method will recurse the tree to find a certain element that it is
     * looking for.
     *
     * @param curr The current node to start looking at
     * @param data The data to find
     * @return return the node at where data is located at.
     * @throws NoSuchElementException if the data is not in the tree.
     */
    private BSTNode getHelper(BSTNode<T> curr, T data) {
        if (curr == null) {
            throw new NoSuchElementException("Data not in tree");
        } else if (curr.getData().compareTo(data) > 0) {
            return getHelper(curr.getLeft(), data);
        } else if (curr.getData().compareTo(data) < 0) {
            return getHelper(curr.getRight(), data);
        }
        return curr;
    }


    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * Hint: Should you use value equality or reference equality?
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data given was null");
        }
        boolean found = containsHelper(this.root, data);
        return found;
    }

    /**
     * Will recurse through the tree looking if it contains a certain value
     * will return true or false if found.
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param curr the current node to start looking at
     * @param data the data to look for
     * @return boolean if the data was found
     */
    private boolean containsHelper(BSTNode<T> curr, T data) {
        if (curr == null) {
            return false;
        } else if (curr.getData().compareTo(data) > 0) {
            return containsHelper(curr.getLeft(), data);
        } else if (curr.getData().compareTo(data) < 0) {
            return containsHelper(curr.getRight(), data);
        }
        return true;
    }

    /**
     * Generate a pre-order traversal of the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * Must be O(n).
     *
     * @return the pre-order traversal of the tree
     */
    public List<T> preorder() {
        List<T> list = new ArrayList<T>();
        preorderHelper(this.root, list);
        return list;
    }

    /**
     * Generate a pre-order traversal of the tree.
     * This must be done recursively.
     * Must be O(n).
     *
     * @param curr The current node to start looking at.
     * @param list The list to add to.
     */
    private void preorderHelper(BSTNode<T> curr, List<T> list) {
        if (curr != null) {
            list.add(curr.getData());
            preorderHelper(curr.getLeft(), list);
            preorderHelper(curr.getRight(), list);
        }
    }

    /**
     * Generate an in-order traversal of the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * Must be O(n).
     *
     * @return the in-order traversal of the tree
     */
    public List<T> inorder() {
        List<T> list = new ArrayList<T>();
        inorderHelper(this.root, list);
        return list;
    }

    /**
     * Generate an in-order traversal of the tree.
     * This must be done recursively.
     * Must be O(n).
     *
     * @param curr The current node to start looking at.
     * @param list The list to add to.
     */
    private void inorderHelper(BSTNode<T> curr, List<T> list) {
        if (curr != null) {
            inorderHelper(curr.getLeft(), list);
            list.add(curr.getData());
            inorderHelper(curr.getRight(), list);
        }
    }

    /**
     * Generate a post-order traversal of the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * Must be O(n).
     *
     * @return the post-order traversal of the tree
     */
    public List<T> postorder() {
        List<T> list = new ArrayList<T>();
        postorderHelper(this.root, list);
        return list;
    }

    /**
     * Generate a post-order traversal of the tree.
     * This must be done recursively.
     * Must be O(n).
     *
     * @param curr The current node to start looking at.
     * @param list The list to add to.
     */
    private void postorderHelper(BSTNode<T> curr, List<T> list) {
        if (curr != null) {
            postorderHelper(curr.getLeft(), list);
            postorderHelper(curr.getRight(), list);
            list.add(curr.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     * <p>
     * This does not need to be done recursively.
     * <p>
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     * <p>
     * Must be O(n).
     *
     * @return the level-order traversal of the tree
     */
    public List<T> levelorder() {
        Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
        List<T> list = new ArrayList<>();
        if (this.root == null) {
            return list;
        }
        queue.add(this.root);
        while (!(queue.isEmpty())) {
            BSTNode<T> curr = queue.remove();
            list.add(curr.getData());
            if (curr != null) {
                if (curr.getLeft() != null) {
                    queue.add(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    queue.add(curr.getRight());
                }
            }
        }
        return list;
    }

    /**
     * Returns the height of the root of the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     * <p>
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightHelper(this.root);
    }

    /**
     * Returns the height of the root of the tree.
     * This must be done recursively
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     * Must be O(n).
     *
     * @param curr The current node to start looking at
     * @return the height of the node given.
     */
    private int heightHelper(BSTNode<T> curr) {
        if (curr == null) {
            return -1;
        }
        int leftHeight = heightHelper(curr.getLeft());
        int rightHeight = heightHelper(curr.getRight());
        if (leftHeight > rightHeight) {
            return leftHeight + 1;
        }
        return rightHeight + 1;
    }

    /**
     * Clears the tree.
     * <p>
     * Clears all data and resets the size.
     * <p>
     * Must be O(1).
     */
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Finds the path between two elements in the tree, specifically the path
     * from data1 to data2, inclusive of both.
     * <p>
     * To do this, you must first find the deepest common ancestor of both data
     * and add it to the list. Then traverse to data1 while adding its ancestors
     * to the front of the list. Finally, traverse to data2 while adding its
     * ancestors to the back of the list. Please note that there is no
     * relationship between the data parameters in that they may not belong
     * to the same branch. You will most likely have to split off and
     * traverse the tree for each piece of data.
     * *
     * You may only use 1 list instance to complete this method. Think about
     * what type of list to use since you will have to add to the front and
     * back of the list.
     * <p>
     * This method only need to traverse to the deepest common ancestor once.
     * From that node, go to each data in one traversal each. Failure to do
     * so will result in a penalty.
     * <p>
     * If both data1 and data2 are equal and in the tree, the list should be
     * of size 1 and contain the element from the tree equal to data1 and data2.
     * <p>
     * Ex:
     * Given the following BST composed of Integers
     * 50
     * /        \
     * 25         75
     * /    \
     * 12    37
     * /  \    \
     * 11   15   40
     * /
     * 10
     * findPathBetween(10, 40) should return the list [10, 11, 12, 25, 37, 40]
     * findPathBetween(50, 37) should return the list [50, 25, 37]
     * findPathBetween(75, 75) should return the list [75]
     * <p>
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     *
     * @param data1 the data to start the path from
     * @param data2 the data to end the path on
     * @return the unique path between the two elements
     * @throws java.lang.IllegalArgumentException if either data1 or data2 is
     *                                            null
     * @throws java.util.NoSuchElementException   if data1 or data2 is not in
     *                                            the tree
     */
    public List<T> findPathBetween(T data1, T data2) {
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("Data given was null");
        }
        LinkedList<T> list = new LinkedList<T>();
        if (data1.equals(data2)) {
            if (contains(data1)) {
                list.addFirst(data1);
                return list;
            } else {
                throw new NoSuchElementException("Data1 or Data2 not in the tree");
            }
        }
        BSTNode<T> ancestor = findDCA(this.root, data1, data2);
        boolean location = true;
        pathHelper(ancestor, list, data1, location);
        list.removeLast();
        location = false;
        pathHelper(ancestor, list, data2, location);
        return list;
    }

    /**
     * Recurse to find the Deepest common ancestor for both data values.
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     *
     * @param curr  the current node to start looking at
     * @param data1 the first piece of data
     * @param data2 the second piece of data
     * @return return the node that is the deepest common ancestor for data
     */
    private BSTNode<T> findDCA(BSTNode<T> curr, T data1, T data2) {
        if (curr == null) {
            throw new NoSuchElementException("Data1 or Data2 not in the tree");
        } else if (curr.getData().compareTo(data1) < 0 && curr.getData().compareTo(data2) < 0) {
            return findDCA(curr.getRight(), data1, data2);
        } else if (curr.getData().compareTo(data1) > 0 && curr.getData().compareTo(data2) > 0) {
            return findDCA(curr.getLeft(), data1, data2);
        }
        return curr;
    }

    /**
     * Finds the path from DCA to the data1 and data2
     * Does this recursively O(log n) and O(n) during worst case
     *
     * @param curr     The current node to look at
     * @param list     The list to add values to
     * @param data     The data you are trying to look for
     * @param location Tells whether to add to front or back
     * @return Returns the node to look at next.
     */
    private BSTNode<T> pathHelper(BSTNode<T> curr, LinkedList<T> list, T data, boolean location) {
        if (curr == null) {
            throw new NoSuchElementException("Data1 or Data2 not in the tree");
        } else if (curr.getData().compareTo(data) < 0) {
            if (location) {
                list.addFirst(curr.getData());
            } else {
                list.addLast(curr.getData());
            }
            return pathHelper(curr.getRight(), list, data, location);
        } else if (curr.getData().compareTo(data) > 0) {
            if (location) {
                list.addFirst(curr.getData());
            } else {
                list.addLast(curr.getData());
            }
            return pathHelper(curr.getLeft(), list, data, location);
        }
        if (location) {
            list.addFirst(curr.getData());
        } else {
            list.addLast(curr.getData());
        }
        return curr;
    }


    /**
     * Returns the root of the tree.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
