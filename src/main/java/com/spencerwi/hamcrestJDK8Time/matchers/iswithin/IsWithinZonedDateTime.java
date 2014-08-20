package com.spencerwi.hamcrestJDK8Time.matchers.iswithin;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.time.ZonedDateTime;
import java.time.temporal.TemporalUnit;

public class IsWithinZonedDateTime extends TypeSafeMatcher<ZonedDateTime> {
    private long window;
    private TemporalUnit units;
    private ZonedDateTime other;

    public IsWithinZonedDateTime(long window, TemporalUnit units, ZonedDateTime other) {
        this.window = window;
        this.units = units;
        this.other = other;
    }

    @Override
    protected boolean matchesSafely(ZonedDateTime item) {
        ZonedDateTime startOfWindow = other.minus(window, units);
        ZonedDateTime endOfWindow = other.plus(window, units);
        return (
                (startOfWindow.isEqual(item) || startOfWindow.isBefore(item))
                &&
                (endOfWindow.isEqual(item) || endOfWindow.isAfter(item))
        );
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText(" a ZonedDateTime that is within ")
                .appendValue(window).appendText(" ")
                .appendValue(units).appendText(" of ")
                .appendValue(other);
    }

    public static RequiredZonedDateTime within(long window, TemporalUnit units){
        return new Builder(window, units);
    }


    public interface RequiredZonedDateTime { IsWithinZonedDateTime of(ZonedDateTime other);}
    public static class Builder implements RequiredZonedDateTime {
        private long window;
        private TemporalUnit units;

        private Builder(long window, TemporalUnit units) {
            this.window = window;
            this.units = units;
        }

        @Override
        public IsWithinZonedDateTime of(ZonedDateTime other) {
            return new IsWithinZonedDateTime(this.window, this.units, other);
        }
    }
}
