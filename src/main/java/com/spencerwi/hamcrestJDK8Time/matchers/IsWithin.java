package com.spencerwi.hamcrestJDK8Time.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

public class IsWithin<T extends Temporal & Comparable<? super T>> extends TypeSafeMatcher<T> {
    private long window;
    private TemporalUnit units;
    private T other;

    public IsWithin(long window, TemporalUnit units, T other) {
        this.window = window;
        this.units = units;
        this.other = other;
    }

    @Override
    protected boolean matchesSafely(T item) {
        T startOfWindow = (T) other.minus(window, units);
        T endOfWindow = (T) other.plus(window, units);
        return (
                (startOfWindow.compareTo(item) <= 0)
                &&
                (endOfWindow.compareTo(item) >= 0)
        );
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a " + other.getClass().getSimpleName() + " that is within ")
                .appendValue(window).appendText(" ")
                .appendValue(units).appendText(" of ")
                .appendValue(other);
    }

    public static Builder within(long window, TemporalUnit units){ return new Builder(window, units); }

    public static class Builder {
        private long window;
        private TemporalUnit units;

        private Builder(long window, TemporalUnit units) {
            this.window = window;
            this.units = units;
        }

        public <T extends Temporal & Comparable<? super T>> IsWithin<T> of(T other) { return new IsWithin<>(window, units, other); }
    }

}
