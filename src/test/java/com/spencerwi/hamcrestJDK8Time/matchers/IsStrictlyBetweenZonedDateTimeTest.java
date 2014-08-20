package com.spencerwi.hamcrestJDK8Time.matchers;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsStrictlyBetween.strictlyBetween;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class IsStrictlyBetweenZonedDateTimeTest {

    @Test
    public void doesNotMatchIfEqualToStart(){
        ZonedDateTime start = ZonedDateTime.of(2014, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
                 timeToTest = ZonedDateTime.of(2014, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
                        end = ZonedDateTime.of(2014, 12, 31, 23, 59, 59, 99, ZoneId.systemDefault());

        assertThat(timeToTest, is(not(strictlyBetween(start, end))));
    }

    @Test
    public void doesNotMatchIfEqualToEnd(){
        ZonedDateTime start = ZonedDateTime.of(2014, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
                 timeToTest = ZonedDateTime.of(2014, 12, 31, 23, 59, 59, 99, ZoneId.systemDefault()),
                        end = ZonedDateTime.of(2014, 12, 31, 23, 59, 59, 99, ZoneId.systemDefault());

        assertThat(timeToTest, is(not(strictlyBetween(start, end))));
    }

    @Test
    public void matchesIfStrictlyBetweenStartAndEnd(){
        ZonedDateTime start = ZonedDateTime.of(2014, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
                 timeToTest = ZonedDateTime.of(2014, 6, 30, 0, 0, 0, 0, ZoneId.systemDefault()),
                        end = ZonedDateTime.of(2014, 12, 31, 23, 59, 59, 99, ZoneId.systemDefault());

        assertThat(timeToTest, is(strictlyBetween(start, end)));
    }

    @Test
    public void doesNotMatchIfOutsideOfGivenWindow(){
        ZonedDateTime start = ZonedDateTime.of(2014, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
                 timeToTest = ZonedDateTime.of(1970, 6, 30, 0, 0, 0, 0, ZoneId.systemDefault()),
                        end = ZonedDateTime.of(2014, 12, 31, 23, 59, 59, 99, ZoneId.systemDefault());

        assertThat(timeToTest, is(not(strictlyBetween(start, end))));
    }

    @Test
    public void describesItselfCorrectly(){
        ZonedDateTime start = ZonedDateTime.of(2014, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
                 timeToTest = ZonedDateTime.of(1970, 6, 30, 0, 0, 0, 0, ZoneId.systemDefault()),
                        end = ZonedDateTime.of(2014, 12, 31, 23, 59, 59, 99, ZoneId.systemDefault());


        try {
            assertThat(timeToTest, is(strictlyBetween(start, end))); /* Should fail, throwing AssertionError */
        } catch(AssertionError e){
            assertThat(e.getMessage(), containsString("Expected: is a ZonedDateTime strictly between <" + start.toString() + "> and <" + end.toString() + ">"));
        }
    }
}
