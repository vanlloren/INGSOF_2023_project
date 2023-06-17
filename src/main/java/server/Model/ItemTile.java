package server.Model;


import Util.Colour;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * This abstract Class represents a generic single {@link ItemTile ItemTile} in the {@link LivingRoom LivingRoom}.
 * There are two different types of {@link ItemTile} with theirs specific characteristics:
 * (1) {@link PlayableItemTile PlayableItemTile} (2) {@link NullItemTile NullItemTile}.
 * <i>Have a look at MyShelfie RuleBook for further information</i>
 */
public abstract class ItemTile implements Serializable {
        @Serial
        private static final long serialVersionUID = 3141006218010219600L;
        /**
         *
         * @return the {@link Colour Colour} of a {@link ItemTile ItemTile}
         */
        public abstract Colour getColour();

        /**
         *
         * @return the {@code idCode} of a {@link ItemTile ItemTile}
         */
        public abstract int getIdCode();

        /**
         *
         * @return {@code true} if the {@link ItemTile ItemTile} is a {@link NullItemTile NullItemTile},
         * {@code false} otherwise
         */
        public abstract boolean nullDetection();

        /**
         *
         * @return {@code true} if the {@link ItemTile ItemTile} is available,
         * {@code false} otherwise
         */
        public abstract boolean getAvailability();

        /**
         * Makes the {@link ItemTile ItemTile} <strong>available</strong> by setting
         * its {@code availability} attribute to {@code true}.
         * Has no effect on the {@link NullItemTile NullItemTiles}.
         */
        public abstract void makeAvailable();

        /**
         * Makes the {@link ItemTile ItemTile} <strong>not available</strong> by setting
         * its {@code availability} attribute to {@code false}.
         * Has no effect on the {@link NullItemTile NullItemTiles}.
         */
        public abstract void makeUnavailable();

        /**
         * Makes the {@link ItemTile ItemTile} <strong>adjacent</strong> by setting
         * its {@code adjacency} attribute to {@code true}.
         * Has no effect on the {@link NullItemTile NullItemTiles}.
         */
        public abstract void setAdjacency();

        /**
         *
         * @return {@code true} if the {@link PlayableItemTile PlayableItemTile} is adjacent
         * to another {@link PlayableItemTile PlayableItemTile} previously picked during the current
         * {@link Player Player's} turn, {@code false} otherwise
         */
        public abstract boolean getAdjacency();

        /**
         * Sets the {@link PlayableItemTile PlayableItemTile} adjacency to {@code false}
         */
        public abstract void resetAdjacency();

        /**
         * Sets the {@link ItemTile ItemTile} position on the {@link LivingRoom LivingRoom}.
         * Has no effect on the {@link NullItemTile NullItemTiles}.
         *
         * @param x the 'x' position in the {@link LivingRoom LivingRoom}
         * @param y the 'y' position in the {@link LivingRoom LivingRoom}
         */
        public abstract void setPosition(int x, int y);

        /**
         *
         * @return an {@link ArrayList ArrayList} containing the {@code xPos} and {@code yPos}
         * of an {@link ItemTile ItemTile} in the {@link LivingRoom LivingRoom}
         */
        public abstract ArrayList<Integer> getPosition();
}



