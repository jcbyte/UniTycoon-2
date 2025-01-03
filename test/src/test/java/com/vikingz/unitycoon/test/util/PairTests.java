package com.vikingz.unitycoon.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import com.vikingz.unitycoon.util.Pair;
import org.junit.jupiter.api.Test;


public class PairTests extends AbstractHeadlessGdxTest {
    
    @Test
    public void TestPair(){
        Pair pair = new Pair(11,15);
        assertEquals(pair.first, 11);
        assertEquals(pair.second, 15);
    }
}
