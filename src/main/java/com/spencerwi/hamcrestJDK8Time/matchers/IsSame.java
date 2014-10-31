package com.spencerwi.hamcrestJDK8Time.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;

public class IsSame<T extends Temporal & Comparable<? super T>> extends TypeSafeMatcher<T> {
    private T other;
    private TemporalField fieldToCheck;

    public IsSame(T other, TemporalField fieldToCheck) {
        this.other = other;
        this.fieldToCheck = fieldToCheck;
    }

    @Override
    protected boolean matchesSafely(T item) {
        return other.getLong(fieldToCheck) == item.getLong(fieldToCheck);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" a " + other.getClass().toString() + " having the same ")
                .appendValue(fieldToCheck)
                .appendText(" as ")
                .appendValue(other);
    }

    public static IsSameBuilder same(TemporalField fieldToCheck){ return new IsSameBuilder(fieldToCheck); }

    public static class IsSameBuilder {
        private TemporalField fieldToCheck;

        private IsSameBuilder(TemporalField fieldToCheck) {
            this.fieldToCheck = fieldToCheck;
        }

        public <T extends Temporal & Comparable<? super T>> IsSame<T> as(T other){
            return new IsSame<>(other, fieldToCheck);
        }
    }
}
