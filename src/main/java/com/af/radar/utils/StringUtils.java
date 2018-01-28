package com.af.radar.utils;

import java.util.Arrays;

/**
 * Created by AKilickaya
 */
public final class StringUtils {

    private StringUtils() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * Returns a String with desired length and filled with desired character.
     *
     * @param length    desired String length
     * @param character desired character is filled with
     * @return String
     */
    public static final String getStringWithLengthAndFilledWithCharacter(int length, char character) {
        String result = "";
        if (length > 0) {
            char[] charArray = new char[length];
            Arrays.fill(charArray, character);
            result = new String(charArray);
        }

        return result;
    }
}
