package com.spencerwi.hamcrestJDK8Time.localdatetime;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;

public class IsWithinLocalDateTime extends TypeSafeMatcher<LocalDateTime> {
    private long window;
    private TemporalUnit units;
    private LocalDateTime other;

    public IsWithinLocalDateTime(long window, TemporalUnit units, LocalDateTime other) {
        this.window = window;
        this.units = units;
        this.other = other;
    }

    @Override
    protected boolean matchesSafely(LocalDateTime item) {
        LocalDateTime startOfWindow = other.minus(window, units);
        LocalDateTime endOfWindow = other.plus(window, units);
        return (
                (startOfWindow.isEqual(item) || startOfWindow.isBefore(item))
                &&
                (endOfWindow.isEqual(item) || endOfWindow.isAfter(item))
        );
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText(" a LocalDateTime that is within ")
                .appendValue(window).appendText(" ")
                .appendValue(units).appendText(" of ")
                .appendValue(other);
    }

    public static RequiredLocalDateTime within(long window, TemporalUnit units){
        return new Builder(window, units);
    }


    public interface RequiredLocalDateTime { IsWithinLocalDateTime of(LocalDateTime other);}
    public static class Builder implements RequiredLocalDateTime {
        private long window;
        private TemporalUnit units;

        private Builder(long window, TemporalUnit units) {
            this.window = window;
            this.units = units;
        }

        @Override
        public IsWithinLocalDateTime of(LocalDateTime other) {
            return new IsWithinLocalDateTime(this.window, this.units, other);
        }
    }
}
