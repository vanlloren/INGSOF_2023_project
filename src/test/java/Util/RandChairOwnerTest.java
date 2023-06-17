package Util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandChairOwnerTest {
    @Test
    public void testRandChairOwner(){
        int i = RandChairOwner.ChooseRand(2);
        Assertions.assertTrue(i < 2);

    }
}
