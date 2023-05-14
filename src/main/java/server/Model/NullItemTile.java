package server.Model;

import Util.Colour;

import java.io.Serial;
import java.util.ArrayList;

public class NullItemTile extends ItemTile {
    @Serial
    private static final long serialVersionUID = -8726544593745400731L;
    private Colour colour;
    private int idCode;
    private boolean availability;
    private boolean adjacency;

    private int xPos;

    private int yPos;

    public NullItemTile() {
        colour = Colour.VOID;
        availability = false;
        adjacency = false;
        idCode = 0;
    }

    @Override
    public Colour getColour() {
        return colour;
    }

    @Override
    public int getIdCode() {
        return idCode;
    }

    public void makeAvailable() {
    }

    public void makeUnavailable() {
    }

    @Override
    public boolean getAvailability() {
        return false;
    }

    @Override
    public boolean nullDetection() {
        return true;
    }

    public void setAdjacency() {
    }

    public boolean getAdjacency() {
        return adjacency;
    }



    @Override
    public void resetAdjacency() {
        adjacency = false;
    }

    @Override
    public void setPosition(int x, int y) {

    }

    @Override
    public ArrayList<Integer> getPosition(){
        ArrayList<Integer> noPos = new ArrayList<Integer>();

        return noPos;
    }
}
