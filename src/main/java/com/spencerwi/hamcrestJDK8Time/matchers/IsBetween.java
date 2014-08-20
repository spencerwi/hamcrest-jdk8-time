package com.spencerwi.hamcrestJDK8Time.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

import java.time.temporal.Temporal;

public class IsBetween<T extends Temporal & Comparable<T>> extends TypeSafeMatcher<T>{
    private T start, end;

    public IsBetween(T start, T end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected boolean matchesSafely(T item) {
        final boolean isAtOrAfterStart = item.compareTo(start) >= 0;
        final boolean isAtOrBeforeEnd  = item.compareTo(end)   <= 0;
        return isAtOrAfterStart && isAtOrBeforeEnd;
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a " + start.getClass().getSimpleName() + " between ")
                .appendValue(start)
                .appendText(" and ")
                .appendValue(end)
                .appendText(", inclusively");
    }

    @Factory
    public static <T extends Temporal & Comparable<T>> IsBetween<T> between(T start, T end){
        return new IsBetween<>(start, end);
    }
}
