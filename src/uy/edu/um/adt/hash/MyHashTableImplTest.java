package uy.edu.um.adt.hash;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyHashTableImplTest {

    private MyHashTableImpl<String, Integer> hashTableLinear;
    private MyHashTableImpl<String, Integer> hashTableQuadratic;

    @BeforeEach
    public void setUp() {
        hashTableLinear = new MyHashTableImpl<>(false);
        hashTableQuadratic = new MyHashTableImpl<>(true);
    }

    @Test
    public void testPutAndContains() {
        hashTableLinear.put("one", 1);
        hashTableLinear.put("two", 2);
        assertTrue(hashTableLinear.contains("one"));
        assertTrue(hashTableLinear.contains("two"));
        assertFalse(hashTableLinear.contains("three"));

        hashTableQuadratic.put("one", 1);
        hashTableQuadratic.put("two", 2);
        assertTrue(hashTableQuadratic.contains("one"));
        assertTrue(hashTableQuadratic.contains("two"));
        assertFalse(hashTableQuadratic.contains("three"));
    }

    @Test
    public void testPutUpdate() {
        hashTableLinear.put("one", 1);
        hashTableLinear.put("one", 10);
        assertTrue(hashTableLinear.contains("one"));

        hashTableQuadratic.put("one", 1);
        hashTableQuadratic.put("one", 10);
        assertTrue(hashTableQuadratic.contains("one"));
    }

    @Test
    public void testRemove() {
        hashTableLinear.put("one", 1);
        hashTableLinear.put("two", 2);
        hashTableLinear.remove("one");
        assertFalse(hashTableLinear.contains("one"));
        assertTrue(hashTableLinear.contains("two"));

        hashTableQuadratic.put("one", 1);
        hashTableQuadratic.put("two", 2);
        hashTableQuadratic.remove("one");
        assertFalse(hashTableQuadratic.contains("one"));
        assertTrue(hashTableQuadratic.contains("two"));
    }

    @Test
    public void testResize() {
        for (int i = 0; i < 10; i++) {
            hashTableLinear.put("key" + i, i);
            hashTableQuadratic.put("key" + i, i);
        }

        for (int i = 0; i < 10; i++) {
            assertTrue(hashTableLinear.contains("key" + i));
            assertTrue(hashTableQuadratic.contains("key" + i));
        }
    }

    @Test
    public void testCollisionHandlingLinear() {
        hashTableLinear.put("Aa", 1);
        hashTableLinear.put("BB", 2);
        assertTrue(hashTableLinear.contains("Aa"));
        assertTrue(hashTableLinear.contains("BB"));
    }

    @Test
    public void testCollisionHandlingQuadratic() {
        hashTableQuadratic.put("Aa", 1);
        hashTableQuadratic.put("BB", 2);
        assertTrue(hashTableQuadratic.contains("Aa"));
        assertTrue(hashTableQuadratic.contains("BB"));
    }
}
