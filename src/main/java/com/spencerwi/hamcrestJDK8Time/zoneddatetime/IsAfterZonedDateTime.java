package com.spencerwi.hamcrestJDK8Time.zoneddatetime;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

import java.time.ZonedDateTime;

public class IsAfterZonedDateTime extends TypeSafeMatcher<ZonedDateTime>{
    private ZonedDateTime other;

    public IsAfterZonedDateTime(ZonedDateTime other) { this.other = other; }

    @Override
    protected boolean matchesSafely(ZonedDateTime item) { return item.isAfter(other); }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a ZonedDateTime that is after ")
                .appendValue(other);
    }

    @Factory
    public static IsAfterZonedDateTime after(ZonedDateTime end){ return new IsAfterZonedDateTime(end); }
}
