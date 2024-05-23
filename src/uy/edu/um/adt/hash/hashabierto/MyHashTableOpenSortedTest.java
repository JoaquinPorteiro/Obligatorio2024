package uy.edu.um.adt.hash.hashabierto;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyHashTableOpenSortedTest {

    private MyHashTableOpenSorted<String, Integer> hashTable;

    @BeforeEach
    public void setUp() {
        hashTable = new MyHashTableOpenSorted<>();
    }

    @Test
    public void testPutAndContains() {
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        assertTrue(hashTable.contains("one"));
        assertTrue(hashTable.contains("two"));
        assertFalse(hashTable.contains("three"));
    }

    @Test
    public void testPutUpdate() {
        hashTable.put("one", 1);
        hashTable.put("one", 10);
        assertTrue(hashTable.contains("one"));
    }

    @Test
    public void testRemove() {
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.remove("one");
        assertFalse(hashTable.contains("one"));
        assertTrue(hashTable.contains("two"));
    }

    @Test
    public void testResize() {
        for (int i = 0; i < 10; i++) {
            hashTable.put("key" + i, i);
        }

        for (int i = 0; i < 10; i++) {
            assertTrue(hashTable.contains("key" + i));
        }
    }

    @Test
    public void testCollisionHandling() {
        hashTable.put("Aa", 1); // hash collision with "BB" in a table of size 11
        hashTable.put("BB", 2);
        assertTrue(hashTable.contains("Aa"));
        assertTrue(hashTable.contains("BB"));
    }
}
