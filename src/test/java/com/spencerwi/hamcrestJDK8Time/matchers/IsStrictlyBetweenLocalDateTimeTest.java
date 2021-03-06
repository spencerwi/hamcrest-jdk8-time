package com.spencerwi.hamcrestJDK8Time.matchers;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsStrictlyBetween.strictlyBetween;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class IsStrictlyBetweenLocalDateTimeTest {

    @Test
    public void doesNotMatchIfEqualToStart(){
        LocalDateTime start = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                timeToTest = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                end = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

        assertThat(timeToTest, is(not(strictlyBetween(start, end))));
    }

    @Test
    public void doesNotMatchIfEqualToEnd(){
        LocalDateTime start = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                timeToTest = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99),
                end = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

        assertThat(timeToTest, is(not(strictlyBetween(start, end))));
    }

    @Test
    public void matchesIfStrictlyBetweenStartAndEnd(){
        LocalDateTime start = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                timeToTest = LocalDateTime.of(2014, Month.JUNE, 30, 0, 0, 0, 0),
                end = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

        assertThat(timeToTest, is(strictlyBetween(start, end)));
    }

    @Test
    public void doesNotMatchIfOutsideOfGivenWindow(){
        LocalDateTime start = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                timeToTest = LocalDateTime.of(1970, Month.JUNE, 30, 0, 0, 0, 0),
                end = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

        assertThat(timeToTest, is(not(strictlyBetween(start, end))));
    }

    @Test
    public void describesItselfCorrectly(){
        LocalDateTime start = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                timeToTest = LocalDateTime.of(1970, Month.JUNE, 30, 0, 0, 0, 0),
                end = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);


        try {
            assertThat(timeToTest, is(strictlyBetween(start, end))); /* Should fail, throwing AssertionError */
        } catch(AssertionError e){
            assertThat(e.getMessage(), containsString("Expected: is a LocalDateTime strictly between <" + start.toString() + "> and <" + end.toString() + ">"));
        }
    }
}
