package com.spencerwi.hamcrestJDK8Time.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

import java.time.temporal.Temporal;

public class IsAfter<T extends Temporal & Comparable<T>> extends TypeSafeMatcher<T> {
    private T other;

    public IsAfter(T other){ this.other = other; }


    @Override
    protected boolean matchesSafely(T item) {
        return item.compareTo(other) > 0;
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a " + other.getClass().getSimpleName() + " that is after ")
                .appendValue(other);
    }

    @Factory
    public static <T extends Temporal & Comparable<T>> IsAfter<T> after(T end){ return new IsAfter<>(end); }
}
