package com.spencerwi.hamcrestJDK8Time.localdatetime;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDateTime;

public class IsAfterLocalDateTime extends TypeSafeMatcher<LocalDateTime>{
    private LocalDateTime other;

    public IsAfterLocalDateTime(LocalDateTime other) { this.other = other; }

    @Override
    protected boolean matchesSafely(LocalDateTime item) { return item.isAfter(other); }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a LocalDateTime that is after ")
                .appendValue(other);
    }

    @Factory
    public static IsAfterLocalDateTime after(LocalDateTime end){ return new IsAfterLocalDateTime(end); }
}
