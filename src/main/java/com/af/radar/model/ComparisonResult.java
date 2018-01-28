package com.af.radar.model;

import java.util.Objects;

/**
 * Created by AKilickaya
 */
public final class ComparisonResult {
    private final int matchedCharCount;
    private final int matchedAlienCharCount;
    private final double totalCharAccuracy;
    private final double alienCharAccuracy;

    public ComparisonResult(int matchedCharCount, int matchedAlienCharCount, int totalCharCount, int alienCharCount) {
        this.matchedCharCount = matchedCharCount;
        this.matchedAlienCharCount = matchedAlienCharCount;
        this.totalCharAccuracy = (double) matchedCharCount / totalCharCount;
        this.alienCharAccuracy = (double) matchedAlienCharCount / alienCharCount;
    }

    public final double getTotalCharAccuracy() {
        return totalCharAccuracy;
    }

    public final double getAlienCharAccuracy() {
        return alienCharAccuracy;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComparisonResult that = (ComparisonResult) o;
        return matchedCharCount == that.matchedCharCount &&
                matchedAlienCharCount == that.matchedAlienCharCount;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(matchedCharCount, matchedAlienCharCount);
    }

    @Override
    public final String toString() {
        return "ComparisonResult{" +
                "matchedCharCount=" + matchedCharCount +
                ", matchedAlienCharCount=" + matchedAlienCharCount +
                '}';
    }
}
