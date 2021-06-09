package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testChange() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(4);
        Assert.assertTrue(null == App.makeChange(3, list));
    }

    @Test
    public void testChangeValid() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(5);
        list.add(10);
        list.add(25);
        Map<Integer, Integer> expectedMap = new HashMap<>();
        expectedMap.put(1, 2);
        expectedMap.put(5, 1);
        Assert.assertEquals(expectedMap, App.makeChange(7,list));
    }
}
