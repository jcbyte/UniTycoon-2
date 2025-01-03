package com.vikingz.unitycoon.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import com.vikingz.unitycoon.util.Point;
import org.junit.jupiter.api.Test;

public class PointTests extends AbstractHeadlessGdxTest {

    @Test
    public void testPointGet(){
        Point thing = new Point(4,10);
        assertEquals(thing.getX(), 4);
        assertEquals(thing.getY(), 10);
    }

    @Test
    public void testPointSet(){
        Point thing = new Point(0,0);
        thing.setX(5);
        thing.setY(10);
        assertEquals(thing.getX(), 5);
        assertEquals(thing.getY(), 10);
   }
    
}
