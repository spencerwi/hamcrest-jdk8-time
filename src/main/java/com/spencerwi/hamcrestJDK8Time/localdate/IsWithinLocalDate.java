package com.spencerwi.hamcrestJDK8Time.localdate;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDate;
import java.time.temporal.TemporalUnit;

public class IsWithinLocalDate extends TypeSafeMatcher<LocalDate> {
    private long window;
    private TemporalUnit units;
    private LocalDate other;

    public IsWithinLocalDate(long window, TemporalUnit units, LocalDate other) {
        this.window = window;
        this.units = units;
        this.other = other;
    }

    @Override
    protected boolean matchesSafely(LocalDate item) {
        LocalDate startOfWindow = other.minus(window, units);
        LocalDate endOfWindow = other.plus(window, units);
        return (
                (startOfWindow.isEqual(item) || startOfWindow.isBefore(item))
                &&
                (endOfWindow.isEqual(item) || endOfWindow.isAfter(item))
        );
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText(" a LocalDate that is within ")
                .appendValue(window).appendText(" ")
                .appendValue(units).appendText(" of ")
                .appendValue(other);
    }

    public static RequiredLocalDate within(long window, TemporalUnit units){
        return new Builder(window, units);
    }


    public interface RequiredLocalDate { IsWithinLocalDate of(LocalDate other);}
    public static class Builder implements RequiredLocalDate {
        private long window;
        private TemporalUnit units;

        public Builder(long window, TemporalUnit units) {
            this.window = window;
            this.units = units;
        }

        @Override
        public IsWithinLocalDate of(LocalDate other) {
            return new IsWithinLocalDate(this.window, this.units, other);
        }
    }
}
