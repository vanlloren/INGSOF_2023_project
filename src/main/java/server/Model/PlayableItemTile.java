package server.Model;

import Util.Colour;

import java.io.Serial;
import java.util.*;

/**
 * This Class represents a single {@link ItemTile ItemTile} in the {@link LivingRoom LivingRoom},
 * that <strong>is</strong> available to be picked or moved but under specific conditions.
 * A {@link PlayableItemTile PlayableItemTile} is <strong>available</strong> when at the beginning
 * of a {@link Player Player} turn at least one of its four sides is free. A {@link PlayableItemTile PlayableItemTile's} side
 * is considered as <strong>free</strong> when at least one of its sides touches the side of a {@link NullItemTile NullItemTile}
 * or when at least one of its sides does touch a {@code null} space in the {@link LivingRoom LivingRoom}.
 * A {@link PlayableItemTile PlayableItemTile} is <strong>adjacent</strong> when at least one of
 * its four sides touches the side of another {@link PlayableItemTile PlayableItemTile}
 * picked in the same turn.
 *
 * <i>Have a look at MyShelfie RuleBook for further information</i>
 */
public class PlayableItemTile extends ItemTile {
    @Serial
    private static final long serialVersionUID = -8977350255435956841L;
    private Colour colour;
    private int idCode;
    private boolean availability;
    private boolean adjacency = false;
    private int xPos;

    private int yPos;

    /**
     * Creates an instance of {@link PlayableItemTile PlayableItemTile} and
     * sets its attributes
     *
     * @param colour the {@link Colour Colour} to assign to the {@link PlayableItemTile PlayableItemTile}
     * @param id the {@code idCode} to assign to the {@link PlayableItemTile PlayableItemTile}
     */
    public PlayableItemTile(String colour, int id) {
        if (colour.equals("GREEN")) {
            Colour helperColour = Colour.GREEN;
            this.colour = helperColour;
            this.idCode = id;
        } else if (colour.equals("WHITE")) {
            Colour helperColour = Colour.WHITE;
            this.colour = helperColour;
            this.idCode = id;
        } else if (colour.equals("YELLOW")) {
            Colour helperColour = Colour.YELLOW;
            this.colour = helperColour;
            this.idCode = id;
        } else if (colour.equals("BLUE")) {
            Colour helperColour = Colour.BLUE;
            this.colour = helperColour;
            this.idCode = id;
        } else if (colour.equals("CYAN")) {
            Colour helperColour = Colour.CYAN;
            this.colour = helperColour;
            this.idCode = id;
        } else if (colour.equals("PINK")){
            Colour helperColour = Colour.PINK;
            this.colour = helperColour;
            this.idCode = id;
        }else{
            Colour helperColour = Colour.VOID;
            this.colour = helperColour;
            this.idCode = -1;
        }
    }

    /**
     *
     * @return the {@link Colour Colour} of a {@link PlayableItemTile PlayableItemTile}
     */
    @java.lang.Override
    public Colour getColour() {
        return colour;
    }

    /**
     *
     * @return the {@code idCode} of a {@link PlayableItemTile PlayableItemTile}
     */
    @Override
    public int getIdCode() {
        return idCode;
    }

    /**
     *
     * @return {@code false}
     */
    @Override
    public boolean nullDetection() {
        return false;
    }

    /**
     *
     * @return {@code true} if the {@link PlayableItemTile PlayableItemTile} is available,
     * {@code false} otherwise
     */
    @Override
    public boolean getAvailability() {
        return availability;
    }

    /**
     * Makes the {@link ItemTile ItemTile} <strong>available</strong> by setting
     * its {@code availability} attribute to {@code true}.
     * Has no effect on the {@link NullItemTile NullItemTiles}.
     */
    @Override
    public void makeAvailable() {
        availability = true;
    }

    /**
     * Makes the {@link ItemTile ItemTile} <strong>not available</strong> by setting
     * its {@code availability} attribute to {@code false}.
     * Has no effect on the {@link NullItemTile NullItemTiles}.
     */
    @Override
    public void makeUnavailable() {
        availability = false;
    }

    /**
     *
     * @return {@code true} if the {@link PlayableItemTile PlayableItemTile} is adjacent
     * to another {@link PlayableItemTile PlayableItemTile} previously picked during the current
     * {@link Player Player's} turn, {@code false} otherwise
     */
    @Override
    public boolean getAdjacency() {
        return adjacency;
    }

    /**
     * Makes the {@link ItemTile ItemTile} <strong>adjacent</strong> by setting
     * its {@code adjacency} attribute to {@code true}.
     * Has no effect on the {@link NullItemTile NullItemTiles}.
     */
    @Override
    public void setAdjacency() {
        adjacency = true;
    }

    /**
     * Sets the {@link PlayableItemTile PlayableItemTile} adjacency to {@code false}
     */
    @Override
    public void resetAdjacency() {
        adjacency = false;
    }

    /**
     *
     * @return an {@link ArrayList ArrayList} containing the {@code xPos} and {@code yPos}
     * of an {@link ItemTile ItemTile} in the {@link LivingRoom LivingRoom}
     */
    @Override
    public ArrayList<Integer> getPosition() {
        ArrayList<Integer> tilePosition = new ArrayList<Integer>();
        tilePosition.add(xPos);
        tilePosition.add(yPos);

        return tilePosition;
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
        xPos = x;
        yPos = y;
    }
}
