package com.spencerwi.hamcrestJDK8Time.localdate;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDate;

public class IsBeforeLocalDate extends TypeSafeMatcher<LocalDate>{
    private LocalDate other;

    public IsBeforeLocalDate(LocalDate other) {
        this.other = other;
    }

    @Override
    protected boolean matchesSafely(LocalDate item) {
        return item.isBefore(other);
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a LocalDate that is before ")
                .appendValue(other);
    }

    @Factory
    public static IsBeforeLocalDate before(LocalDate end){
        return new IsBeforeLocalDate(end);
    }
}
