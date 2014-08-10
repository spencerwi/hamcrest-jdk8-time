package com.spencerwi.hamcrestJDK8Time.localdate;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDate;

public class IsAfterLocalDate extends TypeSafeMatcher<LocalDate>{
    private LocalDate other;

    public IsAfterLocalDate(LocalDate other) { this.other = other; }

    @Override
    protected boolean matchesSafely(LocalDate item) { return item.isAfter(other); }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a LocalDate that is after ")
                .appendValue(other);
    }

    @Factory
    public static IsAfterLocalDate after(LocalDate end){ return new IsAfterLocalDate(end); }
}
