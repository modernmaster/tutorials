package uk.co.jamesmcguigan.patterns.creational.builder;

import java.awt.Color;
import java.awt.Point;

public class StreetMap {
    private final Point origin;
    private final Point destination;

    private final Color waterColor;
    private final Color landColor;
    private final Color highTrafficColor;
    private final Color mediumTrafficColor;
    private final Color lowTrafficColor;

    private StreetMap(final Builder builder) {
        // Required parameters
        origin = builder.origin;
        destination = builder.destination;

        // Optional parameters
        waterColor = builder.waterColor;
        landColor = builder.landColor;
        highTrafficColor = builder.highTrafficColor;
        mediumTrafficColor = builder.mediumTrafficColor;
        lowTrafficColor = builder.lowTrafficColor;
    }

    public static class Builder {
        // Required parameters
        private final Point origin;
        private final Point destination;
        private static final int RGB_RED = 30;
        private static final int RGB_GREEN = 30;
        private static final int RGB_BLUE = 30;

        // Optional parameters - initialize with default values
        private Color waterColor = Color.BLUE;
        private Color landColor = new Color(RGB_RED, RGB_GREEN, RGB_BLUE);
        private Color highTrafficColor = Color.RED;
        private Color mediumTrafficColor = Color.YELLOW;
        private Color lowTrafficColor = Color.GREEN;

        public Builder(final Point origin, final Point destination) {
            this.origin = origin;
            this.destination = destination;
        }

        public Builder waterColor(final Color color) {
            waterColor = color;
            return this;
        }

        public Builder landColor(final Color color) {
            landColor = color;
            return this;
        }

        public Builder highTrafficColor(final Color color) {
            highTrafficColor = color;
            return this;
        }

        public Builder mediumTrafficColor(final Color color) {
            mediumTrafficColor = color;
            return this;
        }

        public Builder lowTrafficColor(final Color color) {
            lowTrafficColor = color;
            return this;
        }

        public StreetMap build() {
            return new StreetMap(this);
        }

    }

}
