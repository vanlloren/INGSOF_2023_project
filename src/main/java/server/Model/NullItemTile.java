package server.Model;

import Util.Colour;

public class NullItemTile extends ItemTile {
    private Colour colour;
    private int idCode;
    private boolean availability;
    private boolean adjacency;

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
}
