package server.Model;

import Util.Colour;

import java.io.Serial;
import java.util.ArrayList;

/**
 * This Class represents a single {@link ItemTile ItemTile} in the {@link LivingRoom LivingRoom}
 * that <strong>is not</strong> available to be picked or moved but has the only purpose of completing the structure
 * of the {@link LivingRoom LivingRoom} at its boards.
 * For this reason, a {@link NullItemTile NullItemTile} is always <strong>not available</strong>,
 * <strong>not adjacent</strong>, has {@link Colour Colour} {@code VOID} and {@code idCode} 0.

 * <i>Have a look at MyShelfie RuleBook for further information</i>
 */
public class NullItemTile extends ItemTile {
    @Serial
    private static final long serialVersionUID = -8726544593745400731L;
    private final Colour colour;
    private final int idCode;
    private boolean adjacency;

    /**
     * Creates an instance of {@link NullItemTile NullItemTile} and sets its attributes
     */
    public NullItemTile() {
        colour = Colour.VOID;
        adjacency = false;
        idCode = 0;
    }

    /**
     *
     * @return the {@link Colour Colour} of a {@link NullItemTile NullItemTile}
     */
    @Override
    public Colour getColour() {
        return colour;
    }

    /**
     *
     * @return the {@code idCode} of a {@link NullItemTile NullItemTile}
     */
    @Override
    public int getIdCode() {
        return idCode;
    }

    /**
     * Makes the {@link ItemTile ItemTile} <strong>not available</strong>.
     * Has no effect on the {@link NullItemTile NullItemTiles}.
     */
    @Override
    public void makeUnavailable() {
    }

    /**
     *
     * @return {@code false}
     */
    @Override
    public boolean getAvailability() {
        return false;
    }

    /**
     * Makes the {@link ItemTile ItemTile} <strong>available</strong>.
     * Has no effect on the {@link NullItemTile NullItemTiles}.
     */
    @Override
    public void makeAvailable() {

    }

    /**
     *
     * @return {@code true}
     */
    @Override
    public boolean nullDetection() {
        return true;
    }

    /**
     * Makes the {@link ItemTile ItemTile} <strong>adjacent</strong>.
     * Has no effect on the {@link NullItemTile NullItemTiles}.
     */
    public void setAdjacency() {
    }

    /**
     *
     * @return {@code false}
     */
    public boolean getAdjacency() {
        return adjacency;
    }

    /**
     * Sets the {@link NullItemTile NullItemTile} adjacency to {@code false}
     */
    @Override
    public void resetAdjacency() {
        adjacency = false;
    }

    /**
     * Sets the {@link ItemTile ItemTile} position on the {@link LivingRoom LivingRoom}.
     * Has no effect on the {@link NullItemTile NullItemTiles}.
     *
     * @param x the 'x' position in the {@link LivingRoom LivingRoom}
     * @param y the 'y' position in the {@link LivingRoom LivingRoom}
     */
    @Override
    public void setPosition(int x, int y) {

    }

    /**
     *
     * @return an {@link ArrayList ArrayList} containing the {@code xPos} and {@code yPos}
     * of an {@link ItemTile ItemTile} in the {@link LivingRoom LivingRoom}
     */
    @Override
    public ArrayList<Integer> getPosition(){
        return new ArrayList<>();

    }
}
