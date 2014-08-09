package com.spencerwi.hamcrestJDK8Time.localdatetime;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDateTime;

public class IsStrictlyBetweenLocalDateTime extends TypeSafeMatcher<LocalDateTime> {
    private LocalDateTime start, end;

    public IsStrictlyBetweenLocalDateTime(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected boolean matchesSafely(LocalDateTime item) {
        return (item.isAfter(start) && item.isBefore(end));
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a LocalDateTime strictly between ")
                .appendValue(start)
                .appendText(" and ")
                .appendValue(end);
    }

    @Factory
    public static Matcher<LocalDateTime> strictlyBetween(LocalDateTime start, LocalDateTime end){
        return new IsStrictlyBetweenLocalDateTime(start, end);
    }
}
