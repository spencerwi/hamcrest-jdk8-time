package com.spencerwi.hamcrestJDK8Time.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

import java.time.temporal.Temporal;

public class IsStrictlyBetween<T extends Temporal & Comparable<T>> extends TypeSafeMatcher<T> {
    private T start, end;

    public IsStrictlyBetween(T start, T end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected boolean matchesSafely(T item) {
        final boolean isAfterStart = item.compareTo(start) > 0;
        final boolean isBeforeEnd  = item.compareTo(end)   < 0;
        return isAfterStart && isBeforeEnd;
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a " + start.getClass().getSimpleName() + " strictly between ")
                .appendValue(start)
                .appendText(" and ")
                .appendValue(end);
    }

    @Factory
    public static <T extends Temporal & Comparable<T>> IsStrictlyBetween<T> strictlyBetween(T start, T end){
        return new IsStrictlyBetween<>(start, end);
    }
}
