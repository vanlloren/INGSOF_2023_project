package Util;

import java.util.Random;

public class RandChairOwner {
    public static int ChooseRand(int i){
        Random rand = new Random();
        int n = rand.nextInt(i);
        return n;
    }
}
