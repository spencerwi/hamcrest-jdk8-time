package com.spencerwi.hamcrestJDK8Time;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDateTime;

public class IsBetweenLocalDateTime extends TypeSafeMatcher<LocalDateTime> {
    private LocalDateTime start, end;

    public IsBetweenLocalDateTime(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected boolean matchesSafely(LocalDateTime item) {
        return (
            (item.isEqual(start) || item.isAfter(start))
            &&
            (item.isEqual(end)   || item.isBefore(end))
        );
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a LocalDateTime between ")
                .appendValue(start)
                .appendText(" and ")
                .appendValue(end)
                .appendText(", inclusively");
    }

    @Factory
    public static Matcher<LocalDateTime> between(LocalDateTime start, LocalDateTime end){
        return new IsBetweenLocalDateTime(start, end);
    }
}
