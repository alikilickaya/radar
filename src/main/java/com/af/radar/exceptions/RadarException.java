package com.af.radar.exceptions;

import java.util.Objects;

/**
 * Created by AKilickaya
 */
public final class RadarException extends Exception {
    private final String errorCode;
    private final String description;

    public RadarException(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    @Override
    public final String toString() {
        return "RadarException{" +
                "errorCode='" + errorCode + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RadarException that = (RadarException) o;

        return errorCode.equals(that.errorCode) && description.equals(that.description);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(errorCode, description);
    }
}
