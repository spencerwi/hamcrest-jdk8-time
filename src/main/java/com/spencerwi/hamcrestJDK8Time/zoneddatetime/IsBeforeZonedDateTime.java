package com.spencerwi.hamcrestJDK8Time.zoneddatetime;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

import java.time.ZonedDateTime;

public class IsBeforeZonedDateTime extends TypeSafeMatcher<ZonedDateTime>{
    private ZonedDateTime other;

    public IsBeforeZonedDateTime(ZonedDateTime other) { this.other = other; }

    @Override
    protected boolean matchesSafely(ZonedDateTime item) { return item.isBefore(other); }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a ZonedDateTime that is before ")
                .appendValue(other);
    }

    @Factory
    public static IsBeforeZonedDateTime before(ZonedDateTime end){ return new IsBeforeZonedDateTime(end); }
}
