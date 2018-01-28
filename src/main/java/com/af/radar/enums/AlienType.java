package com.af.radar.enums;

/**
 * Created by AKilickaya
 */
public enum AlienType {
    ORC("orc"),
    GOBLIN("goblin");

    private String name;

    AlienType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


