package com.spencerwi.hamcrestJDK8Time.zoneddatetime;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.time.ZonedDateTime;

public class IsBetweenZonedDateTime extends TypeSafeMatcher<ZonedDateTime> {
    private ZonedDateTime start, end;

    public IsBetweenZonedDateTime(ZonedDateTime start, ZonedDateTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected boolean matchesSafely(ZonedDateTime item) {
        return (
            (item.isEqual(start) || item.isAfter(start))
            &&
            (item.isEqual(end)   || item.isBefore(end))
        );
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a ZonedDateTime between ")
                .appendValue(start)
                .appendText(" and ")
                .appendValue(end)
                .appendText(", inclusively");
    }

    @Factory
    public static Matcher<ZonedDateTime> between(ZonedDateTime start, ZonedDateTime end){
        return new IsBetweenZonedDateTime(start, end);
    }
}
