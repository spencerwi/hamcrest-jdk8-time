package com.spencerwi.hamcrestJDK8Time.localdate;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDate;

public class IsStrictlyBetweenLocalDate extends TypeSafeMatcher<LocalDate> {
    private LocalDate start, end;

    public IsStrictlyBetweenLocalDate(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected boolean matchesSafely(LocalDate item) { return (item.isAfter(start) && item.isBefore(end)); }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a LocalDate strictly between ")
                .appendValue(start)
                .appendText(" and ")
                .appendValue(end);
    }

    @Factory
    public static Matcher<LocalDate> strictlyBetween(LocalDate start, LocalDate end){
        return new IsStrictlyBetweenLocalDate(start, end);
    }
}
