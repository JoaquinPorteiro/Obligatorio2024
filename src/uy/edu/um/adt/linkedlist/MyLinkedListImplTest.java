package uy.edu.um.adt.linkedlist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.um.adt.queue.EmptyQueueException;
import uy.edu.um.adt.stack.EmptyStackException;

public class MyLinkedListImplTest {

    private MyLinkedListImpl<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new MyLinkedListImpl<>();
    }

    @Test
    public void testAdd() {
        list.add(1);
        assertEquals(1, list.get(0));
    }

    @Test
    public void testAddMultiple() {
        list.add(1);
        list.add(2);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    public void testGetInvalidPosition() {
        list.add(1);
        assertNull(list.get(5));
    }

    @Test
    public void testContains() {
        list.add(1);
        assertTrue(list.contains(1));
        assertFalse(list.contains(2));
    }

    @Test
    public void testRemove() {
        list.add(1);
        list.add(2);
        list.remove(1);
        assertEquals(2, list.get(0));
        assertFalse(list.contains(1));
    }

    @Test
    public void testRemoveNonExistent() {
        list.add(1);
        list.remove(2);
        assertEquals(1, list.get(0));
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size());
        list.add(1);
        assertEquals(1, list.size());
        list.add(2);
        assertEquals(2, list.size());
    }

    @Test
    public void testQueueOperations() throws EmptyQueueException {
        list.enqueue(1);
        list.enqueue(2);
        assertEquals(1, list.dequeue());
        assertEquals(2, list.dequeue());
        assertThrows(EmptyQueueException.class, () -> list.dequeue());
    }

    @Test
    public void testStackOperations() throws EmptyStackException {
        list.push(1);
        list.push(2);
        assertEquals(2, list.pop());
        assertEquals(1, list.pop());
        assertThrows(EmptyStackException.class, () -> list.pop());
    }

    @Test
    public void testPeek() throws EmptyStackException {
        list.push(1);
        list.push(2);
        assertEquals(2, list.peek());
        list.pop();
        assertEquals(1, list.peek());
    }
}
