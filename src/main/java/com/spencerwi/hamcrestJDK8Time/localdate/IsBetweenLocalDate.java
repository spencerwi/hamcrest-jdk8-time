package com.spencerwi.hamcrestJDK8Time.localdate;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDate;

public class IsBetweenLocalDate extends TypeSafeMatcher<LocalDate> {
    private LocalDate start, end;

    public IsBetweenLocalDate(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected boolean matchesSafely(LocalDate item) {
        return (
            (item.isEqual(start) || item.isAfter(start))
            &&
            (item.isEqual(end)   || item.isBefore(end))
        );
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a LocalDate between ")
                .appendValue(start)
                .appendText(" and ")
                .appendValue(end)
                .appendText(", inclusively");
    }

    @Factory
    public static Matcher<LocalDate> between(LocalDate start, LocalDate end){
        return new IsBetweenLocalDate(start, end);
    }
}
