package server.Model;

import Util.Colour;

public class PlayableItemTile extends ItemTile {
    private Colour colour;
    private int idCode;
    private boolean availability;
    private boolean adjacency = false;

    @java.lang.Override
    public Colour getColour() {
        return colour;
    }

    @Override
    public int getIdCode() {
        return idCode;
    }

    @Override
    public boolean nullDetection() {
        return false;
    }

    @Override
    public boolean isAvailable() {
        return availability;
    }

    @Override
    public void makeAvailable() {
        availability = true;
    }

    @Override
    public void makeUnavailable() {
        availability = false;
    }

    @Override
    public boolean getAdjacency() {
        return adjacency;
    }

    @Override
    public void setAdjacency() {
        adjacency = true;
    }

    @Override
    public void resetAdjacency() {
        adjacency = false;
    }

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
        } else {
            Colour helperColour = Colour.PINK;
            this.colour = helperColour;
            this.idCode = id;
        }
    }
}
