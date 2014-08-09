package com.spencerwi.hamcrestJDK8Time.localdatetime;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDateTime;

public class IsBeforeLocalDateTime extends TypeSafeMatcher<LocalDateTime>{
    private LocalDateTime other;

    public IsBeforeLocalDateTime(LocalDateTime other) {
        this.other = other;
    }

    @Override
    protected boolean matchesSafely(LocalDateTime item) {
        return item.isBefore(other);
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a LocalDateTime that is before ")
                .appendValue(other);
    }

    @Factory
    public static IsBeforeLocalDateTime before(LocalDateTime end){
        return new IsBeforeLocalDateTime(end);
    }
}
