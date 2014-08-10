package com.spencerwi.hamcrestJDK8Time.zoneddatetime;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.time.ZonedDateTime;

public class IsStrictlyBetweenZonedDateTime extends TypeSafeMatcher<ZonedDateTime> {
    private ZonedDateTime start, end;

    public IsStrictlyBetweenZonedDateTime(ZonedDateTime start, ZonedDateTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected boolean matchesSafely(ZonedDateTime item) { return (item.isAfter(start) && item.isBefore(end)); }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a ZonedDateTime strictly between ")
                .appendValue(start)
                .appendText(" and ")
                .appendValue(end);
    }

    @Factory
    public static Matcher<ZonedDateTime> strictlyBetween(ZonedDateTime start, ZonedDateTime end){
        return new IsStrictlyBetweenZonedDateTime(start, end);
    }
}
