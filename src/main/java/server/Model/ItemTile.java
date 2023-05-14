package server.Model;


import Util.Colour;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class ItemTile implements Serializable {

        @Serial
        private static final long serialVersionUID = 3141006218010219600L;
        private Colour colour;
        private int idCode;
        private boolean availability;
        private boolean adjacency;
        private int xPos;
        private int yPos;

        public abstract Colour getColour();

        public abstract int getIdCode();

        public abstract boolean nullDetection();

        public abstract boolean getAvailability();

        public abstract void makeAvailable();

        public abstract void makeUnavailable();

        public abstract void setAdjacency();

        public abstract boolean getAdjacency();

        public abstract void resetAdjacency();

        public abstract void setPosition(int x, int y);

        public abstract ArrayList<Integer> getPosition();
}



