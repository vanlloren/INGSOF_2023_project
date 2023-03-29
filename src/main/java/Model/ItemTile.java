package Model;


import Util.Colour;

public abstract class ItemTile {
        private Colour colour;
        private int idCode;
        private boolean availability;
        private boolean adjacency;

        public abstract Colour getColour();

        public abstract int getIdCode();

        public abstract boolean nullDetection();

        public abstract boolean isAvailable();

        public abstract void makeAvailable();

        public abstract void makeUnavailable();

        public abstract void setAdjacency();

        public abstract boolean getAdjacency();

        public abstract void resetAdjacency();
    }



