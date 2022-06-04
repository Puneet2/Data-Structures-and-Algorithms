import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Puneet Bansal
 * @version 1.0
 * @userid pbansal43
 * @GTID 903589378
 */
public class AVL<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty AVL.
     * <p>
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it appears in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null || data.contains(null)) {
            throw new IllegalArgumentException("Data was null or element in collection was null");
        }
        for (T value : data) {
            add(value);
        }
    }

    /**
     * Adds the data to the AVL. Start by adding it as a leaf like in a regular
     * BST and then rotate the tree as needed.
     * <p>
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     * <p>
     * Remember to recalculate heights and balance factors going up the tree,
     * rebalancing if necessary.
     *
     * @param data the data to be added
     * @throws java.lang.IllegalArgumentException if the data is null
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
     * shouldn't get added, and size should not be incremented)
     * Will recalculate, and balance the nodes.
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @param curr the current node
     * @return return the current node
     */
    private AVLNode<T> addHelper(T data, AVLNode<T> curr) {
        if (curr == null) {
            this.size++;
            return new AVLNode<T>(data);
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(addHelper(data, curr.getLeft()));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(addHelper(data, curr.getRight()));
        }
        recalculateNode(curr);
        return balance(curr);
    }

    /**
     * This method will update, the height and balance factor of the node.
     * @param node the node that needs to be updated
     */
    private void recalculateNode(AVLNode<T> node) {
        if (node.getRight() == null && node.getLeft() == null) {
            node.setHeight(0);
            node.setBalanceFactor(0);
        } else if (node.getRight() == null && node.getLeft() != null) {
            node.setHeight(node.getLeft().getHeight() + 1);
            node.setBalanceFactor(node.getLeft().getHeight() + 1);
        } else if (node.getRight() != null && node.getLeft() == null) {
            node.setHeight(node.getRight().getHeight() + 1);
            node.setBalanceFactor(-1 - node.getRight().getHeight());
        } else if (node.getRight() != null && node.getLeft() != null) {
            node.setHeight(Math.max(node.getRight().getHeight(), node.getLeft().getHeight()) + 1);
            node.setBalanceFactor(node.getLeft().getHeight() - node.getRight().getHeight());
        }
    }

    /**
     * This method will balance the tree by preforming either left, right,
     * left-right, or right-left rotations.
     * @param node the node that needs to be balanced
     * @return the changed node
     */
    private AVLNode<T> balance(AVLNode<T> node) {
        if (node.getBalanceFactor() < -1) { // -2
            if (node.getRight().getBalanceFactor() <= 0) { //-1 or 0
                node = leftRotation(node);
            } else {
                node.setRight(rightRotation(node.getRight()));
                node = leftRotation(node);
            }
        } else if (node.getBalanceFactor() > 1) {
            if (node.getLeft().getBalanceFactor() >= 0) {
                node = rightRotation(node);
            } else {
                node.setLeft(leftRotation(node.getLeft()));
                node = rightRotation(node);
            }
        }
        return node;
    }

    /**
     * This method will preform a left rotation.
     * @param node the head of the node that needs to be rotated
     * @return will return the fixed node.
     */
    private AVLNode<T> leftRotation(AVLNode<T> node) {
        AVLNode<T> reference = node.getRight();
        node.setRight(reference.getLeft());
        reference.setLeft(node);
        recalculateNode(node);
        recalculateNode(reference);
        return reference;
    }

    /**
     * This method will preform a right rotation.
     * @param node the head of the node that needs to be rotated.
     * @return will return the fixed.
     */
    private AVLNode<T> rightRotation(AVLNode<T> node) {
        AVLNode<T> reference = node.getLeft();
        node.setLeft(reference.getRight());
        reference.setRight(node);
        recalculateNode(node);
        recalculateNode(reference);
        return reference;
    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     * <p>
     * 1: the data is a leaf. In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the successor to replace the data,
     * not the predecessor. As a reminder, rotations can occur after removing
     * the successor node.
     * <p>
     * Remember to recalculate heights going up the tree, rebalancing if
     * necessary.
     *
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     * @throws IllegalArgumentException         if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data given was null");
        }
        AVLNode<T> removedData = new AVLNode<T>(null);
        this.root = removeHelper(this.root, data, removedData);
        this.size--;
        return removedData.getData();
    }

    /**
     * Must be O(log n) for best and average cases and O(n) for worst case.
     * This removeHelper will recursively go through a tree and find the data
     * that needs to be removed. Handles the case when there are two child nodes
     * one child node, or none. Will recalculate the node height and balance
     * factor and then balance it.
     *
     * @param curr        The current node to start looking at.
     * @param data        The data to remove.
     * @param removedData The data that is being removed.
     * @return The node that is going to be replaced at the removed node.
     * @throws NoSuchElementException if there is no value to be removed.
     */
    private AVLNode<T> removeHelper(AVLNode<T> curr, T data, AVLNode<T> removedData) {
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
                AVLNode<T> successor = new AVLNode<>(null);
                curr.setRight(successorNode(curr.getRight(), successor));
                curr.setData(successor.getData());
            }
        }
        recalculateNode(curr);
        return balance(curr);
    }

    /**
     * This method will find the successor node, used when there are two
     * child nodes to a node being removed. Will also recalc the height and bf
     * will balance if needed.
     *
     * @param curr the current node to start looking at
     * @param successor the successor of the node
     * @return return the successor node
     */
    private AVLNode<T> successorNode(AVLNode<T> curr, AVLNode<T> successor) {
        if (curr.getLeft() == null) {
            successor.setData(curr.getData());
            return curr.getRight();
        }
        curr.setLeft(successorNode(curr.getLeft(), successor));
        recalculateNode(curr);
        return balance(curr);
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     * @throws IllegalArgumentException         if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data given was null");
        }
        AVLNode<T> node = getHelper(this.root, data);
        return node.getData();
    }

    /**
     * This method will recurse the AVL to find a certain element that it is
     * looking for.
     *
     * @param curr The current node to start looking at
     * @param data The data to find
     * @return return the node at where data is located at.
     * @throws NoSuchElementException if the data is not in the tree.
     */
    private AVLNode<T> getHelper(AVLNode<T> curr, T data) {
        if (curr == null) {
            throw new NoSuchElementException("Data not in AVL");
        } else if (curr.getData().compareTo(data) > 0) {
            return getHelper(curr.getLeft(), data);
        } else if (curr.getData().compareTo(data) < 0) {
            return getHelper(curr.getRight(), data);
        }
        return curr;
    }

    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
     *
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     * @throws IllegalArgumentException if the data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data given was null");
        }
        boolean found = containsHelper(this.root, data);
        return found;
    }

    /**
     * Will recurse through the AVL looking if it contains a certain value
     * will return true or false if found.
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param curr the current node to start looking at
     * @param data the data to look for
     * @return boolean if the data was found
     */
    private boolean containsHelper(AVLNode<T> curr, T data) {
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
     * The predecessor is the largest node that is smaller than the current data.
     * <p>
     * This method should retrieve (but not remove) the predecessor of the data
     * passed in. There are 2 cases to consider:
     * 1: The left subtree is non-empty. In this case, the predecessor is the
     * rightmost node of the left subtree.
     * 2: The left subtree is empty. In this case, the predecessor is the lowest
     * ancestor of the node containing data whose right child is also
     * an ancestor of data.
     * <p>
     * This should NOT be used in the remove method.
     * <p>
     * Ex:
     * Given the following AVL composed of Integers
     * 76
     * /    \
     * 34      90
     * \    /
     * 40  81
     * predecessor(76) should return 40
     * predecessor(81) should return 76
     *
     * @param data the data to find the predecessor of
     * @return the predecessor of data. If there is no smaller data than the
     * one given, return null.
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T predecessor(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        AVLNode<T> node = new AVLNode<T>(null);
        predecessorHelper(this.root, node, data);
        return node.getData();
    }

    /**
     * This will find the predecessor if there is no left subtree, and will call
     * the normal predecessor method if there exists a left substree.
     * @param curr The head of the node
     * @param node the node that holds the value
     * @param data the data you are looking for the predecessor
     * @return will return a node that holds the predecessor
     */
    private AVLNode<T> predecessorHelper(AVLNode<T> curr, AVLNode<T> node, T data) {
        if (curr == null) {
            throw new NoSuchElementException("Data not in tree");
        } else if (curr.getData().compareTo(data) > 0) {
            return predecessorHelper(curr.getLeft(), node, data);
        } else if (curr.getData().compareTo(data) < 0) {
            if (node.getData() == null || curr.getData().compareTo(node.getData()) > 0) {
                node.setData(curr.getData());
            }
            return predecessorHelper(curr.getRight(), node, data);
        }
        if (curr.getLeft() != null) {
            predecessorNode(curr.getLeft(), node);
            return node;
        }
        return node;
    }

    /**
     * The noraml predecessor method to find the predecessor when a left subtree
     * exists.
     * @param curr the node to look at.
     * @param node the node that holds the correct value.
     * @return will return the node, that holds the correct value
     */
    private AVLNode<T> predecessorNode(AVLNode<T> curr, AVLNode<T> node) {
        if (curr.getRight() == null) {
            node.setData(curr.getData());
            return node;
        }
        return predecessorNode(curr.getRight(), node);
    }

    /**
     * Finds and retrieves the k-smallest elements from the AVL in sorted order,
     * least to greatest.
     * <p>
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     * <p>
     * Ex:
     * Given the following AVL composed of Integers
     * 50
     * /    \
     * 25      75
     * /  \     / \
     * 13   37  70  80
     * /  \    \      \
     * 12  15    40    85
     * /
     * 10
     * kSmallest(0) should return the list []
     * kSmallest(5) should return the list [10, 12, 13, 15, 25].
     * kSmallest(3) should return the list [10, 12, 13].
     *
     * @param k the number of smallest elements to return
     * @return sorted list consisting of the k smallest elements
     * @throws java.lang.IllegalArgumentException if k < 0 or k > n, the number
     *                                            of data in the AVL
     */
    public List<T> kSmallest(int k) {
        if (k < 0 || k > this.size) {
            throw new IllegalArgumentException("K is out of bounds");
        }
        List<T> list = new ArrayList<>();
        list = kSmallestHelper(this.root, list, k);
        return list;
    }

    /**
     * Generate an in-order traversal of the tree, which will get the list.
     * Will stop at the smallest number required.
     *
     * @param curr The current node to start looking at.
     * @param list The list to add to.
     * @param k The number of values in the list
     * @return this will return the list that holds the values
     */
    private List<T> kSmallestHelper(AVLNode<T> curr, List<T> list, int k) {
        if (curr == null || list.size() >= k) {
            return list;
        }
        kSmallestHelper(curr.getLeft(), list, k);
        if (list.size() < k) {
            list.add(curr.getData());
        }
        kSmallestHelper(curr.getRight(), list, k);
        return list;
    }

    /**
     * Clears the tree.
     */
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Returns the height of the root of the tree.
     * <p>
     * Since this is an AVL, this method does not need to traverse the tree
     * and should be O(1)
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (this.root == null) {
            return -1;
        }
        return this.root.getHeight();
    }

    /**
     * Returns the size of the AVL tree.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return number of items in the AVL tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    /**
     * Returns the root of the AVL tree.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the AVL tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}