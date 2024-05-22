package uy.edu.um.adt.linkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListImplTest {

    @Test
    public void testAdd() {
        MyLinkedListImpl<Integer> list = new MyLinkedListImpl<>();
        list.add(1);
        assertEquals(1, list.size());
        assertTrue(list.contains(1));
    }

    @Test
    public void testRemove() {
        MyLinkedListImpl<Integer> list = new MyLinkedListImpl<>();
        list.add(1);
        list.remove(1);
        assertEquals(0, list.size());
        assertFalse(list.contains(1));
    }

    @Test
    public void testContains() {
        MyLinkedListImpl<Integer> list = new MyLinkedListImpl<>();
        list.add(1);
        list.add(2);
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertFalse(list.contains(3));
    }

    @Test
    public void testSize() {
        MyLinkedListImpl<Integer> list = new MyLinkedListImpl<>();
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
    }
}

