package com.af.radar.model;

import com.af.radar.enums.AlienType;

/**
 * Created by AKilickaya
 */
public final class Alien extends Image {
    private final AlienType type;
    private final int alienCharCount;
    private final int totalCharCount;

    public Alien(char[][] matrix, AlienType type, int alienCharCount) {
        super(matrix);
        this.type = type;
        this.alienCharCount = alienCharCount;
        this.totalCharCount = super.getRowLength() * super.getColumnLength();
    }

    public AlienType getType() {
        return type;
    }

    public int getAlienCharCount() {
        return alienCharCount;
    }

    public int getTotalCharCount() {
        return totalCharCount;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "type=" + type +
                ", alienCharCount=" + alienCharCount +
                ", totalCharCount=" + totalCharCount +
                '}';
    }
}
