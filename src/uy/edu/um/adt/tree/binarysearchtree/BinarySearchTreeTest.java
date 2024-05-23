package uy.edu.um.adt.tree.binarysearchtree;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.um.adt.linkedlist.MyList;

public class BinarySearchTreeTest {

    private BinarySearchTree<Integer, String> bst;

    @BeforeEach
    public void setUp() {
        bst = new BinarySearchTree<>();
    }

    @Test
    public void testInsertAndFind() {
        bst.insert(1, "one");
        bst.insert(2, "two");
        assertEquals("one", bst.find(1));
        assertEquals("two", bst.find(2));
        assertNull(bst.find(3));
    }

    @Test
    public void testInsertAndFindDuplicateKey() {
        bst.insert(1, "one");
        bst.insert(1, "uno");
        assertEquals("uno", bst.find(1));
    }

    @Test
    public void testDeleteLeaf() {
        bst.insert(1, "one");
        bst.insert(2, "two");
        bst.delete(2);
        assertNull(bst.find(2));
        assertEquals("one", bst.find(1));
    }

    @Test
    public void testDeleteNodeWithOneChild() {
        bst.insert(1, "one");
        bst.insert(2, "two");
        bst.insert(3, "three");
        bst.delete(2);
        assertNull(bst.find(2));
        assertEquals("three", bst.find(3));
    }

    @Test
    public void testDeleteNodeWithTwoChildren() {
        bst.insert(1, "one");
        bst.insert(2, "two");
        bst.insert(3, "three");
        bst.delete(1);
        assertNull(bst.find(1));
        assertEquals("two", bst.find(2));
        assertEquals("three", bst.find(3));
    }


    @Test
    public void testInOrder() {
        bst.insert(2, "two");
        bst.insert(1, "one");
        bst.insert(3, "three");
        MyList<Integer> inOrder = bst.inOrder();
        assertEquals(3, inOrder.size());
        assertEquals(1, inOrder.get(0));
        assertEquals(2, inOrder.get(1));
        assertEquals(3, inOrder.get(2));
    }

    @Test
    public void testPreOrder() {
        bst.insert(2, "two");
        bst.insert(1, "one");
        bst.insert(3, "three");
        MyList<Integer> preOrder = bst.preOrder();
        assertEquals(3, preOrder.size());
        assertEquals(2, preOrder.get(0));
        assertEquals(1, preOrder.get(1));
        assertEquals(3, preOrder.get(2));
    }

    @Test
    public void testPostOrder() {
        bst.insert(2, "two");
        bst.insert(1, "one");
        bst.insert(3, "three");
        MyList<Integer> postOrder = bst.postOrder();
        assertEquals(3, postOrder.size());
        assertEquals(1, postOrder.get(0));
        assertEquals(3, postOrder.get(1));
        assertEquals(2, postOrder.get(2));
    }
}
