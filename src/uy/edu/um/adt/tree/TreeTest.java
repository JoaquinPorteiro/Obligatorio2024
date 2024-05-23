package uy.edu.um.adt.tree;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.um.adt.linkedlist.MyList;

public class TreeTest {

    private Tree<Integer, String> tree;

    @BeforeEach
    public void setUp() {
        tree = new Tree<>();
    }

    @Test
    public void testInsertAndFind() {
        tree.insert(1, "one", null);
        tree.insert(2, "two", 1);
        assertEquals("one", tree.find(1));
        assertEquals("two", tree.find(2));
        assertNull(tree.find(3));
    }

    @Test
    public void testInsertAndFindDuplicateKey() {
        tree.insert(1, "one", null);
        tree.insert(1, "uno", null);
        assertEquals("uno", tree.find(1));
    }

    @Test
    public void testDeleteLeaf() {
        tree.insert(1, "one", null);
        tree.insert(2, "two", 1);
        tree.delete(2);
        assertNull(tree.find(2));
        assertEquals(1, tree.size());
    }

    @Test
    public void testDeleteNodeWithOneChild() {
        tree.insert(1, "one", null);
        tree.insert(2, "two", 1);
        tree.insert(3, "three", 2);
        tree.delete(2);
        assertNull(tree.find(2));
        assertEquals("three", tree.find(3));
    }

    @Test
    public void testDeleteNodeWithTwoChildren() {
        tree.insert(1, "one", null);
        tree.insert(2, "two", 1);
        tree.insert(3, "three", 1);
        tree.delete(1);
        assertNull(tree.find(1));
        assertEquals("two", tree.find(2));
        assertEquals("three", tree.find(3));
    }

    @Test
    public void testSize() {
        assertEquals(0, tree.size());
        tree.insert(1, "one", null);
        assertEquals(1, tree.size());
        tree.insert(2, "two", 1);
        assertEquals(2, tree.size());
    }

    @Test
    public void testCountLeaf() {
        assertEquals(0, tree.countLeaf());
        tree.insert(1, "one", null);
        assertEquals(1, tree.countLeaf());
        tree.insert(2, "two", 1);
        assertEquals(1, tree.countLeaf());
        tree.insert(3, "three", 1);
        // assertEquals(2, tree.countLeaf());
    }

    @Test
    public void testCountCompleteElements() {
        assertEquals(0, tree.countCompleteElements());
        tree.insert(1, "one", null);
        assertEquals(0, tree.countCompleteElements());
        tree.insert(2, "two", 1);
        tree.insert(3, "three", 1);
        // assertEquals(1, tree.countCompleteElements());
    }

    @Test
    public void testInOrder() {
        tree.insert(2, "two", null);
        tree.insert(1, "one", 2);
        tree.insert(3, "three", 2);
        MyList<Integer> inOrder = tree.inOrder();
        assertEquals(3, inOrder.size());
        assertEquals(1, inOrder.get(0));
        assertEquals(2, inOrder.get(1));
        assertEquals(3, inOrder.get(2));
    }

    @Test
    public void testPreOrder() {
        tree.insert(2, "two", null);
        tree.insert(1, "one", 2);
        tree.insert(3, "three", 2);
        MyList<Integer> preOrder = tree.preOrder();
        assertEquals(3, preOrder.size());
        assertEquals(2, preOrder.get(0));
        assertEquals(1, preOrder.get(1));
        assertEquals(3, preOrder.get(2));
    }

    @Test
    public void testPostOrder() {
        tree.insert(2, "two", null);
        tree.insert(1, "one", 2);
        tree.insert(3, "three", 2);
        MyList<Integer> postOrder = tree.postOrder();
        assertEquals(3, postOrder.size());
        assertEquals(1, postOrder.get(0));
        assertEquals(3, postOrder.get(1));
        assertEquals(2, postOrder.get(2));
    }

    @Test
    public void testLevelOrder() {
        tree.insert(2, "two", null);
        tree.insert(1, "one", 2);
        tree.insert(3, "three", 2);
        MyList<Integer> levelOrder = tree.levelOrder();
        assertEquals(3, levelOrder.size());
        assertEquals(2, levelOrder.get(0));
        assertEquals(1, levelOrder.get(1));
        assertEquals(3, levelOrder.get(2));
    }

    @Test
    public void testLoadPostFijaExpression() {
        tree.loadPostFijaExpression("3 1 + 2 *");
        MyList<Integer> inOrder = tree.inOrder();
        // assertEquals(3, inOrder.size());
        // assertEquals("+", inOrder.get(0));
        // assertEquals("*", inOrder.get(1));
        // assertEquals("3", inOrder.get(2));
    }
}

