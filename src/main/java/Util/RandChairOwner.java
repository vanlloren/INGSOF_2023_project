package Util;

import java.util.Random;

/**
 * Static Class used to set the {@code chairOwner} of the match
 */
public class RandChairOwner {

    /**
     * Static method to choose randomly the {@code chairOwner} of the match between the {@link java.util.ArrayList ArrayList} that contains all the
     * {@link server.Model.Player Player} of the game
     *
     * @param i the index used as superior limit to choose the random value
     * @return a random number between 0 and i-1
     */
    public static int ChooseRand(int i){
        Random rand = new Random();
        int n = rand.nextInt(i);
        return n;
    }
}
