package com.spencerwi.hamcrestJDK8Time.matchers;

import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.UnsupportedTemporalTypeException;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsWithin.within;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class IsWithinLocalDateTest {
    
    @Test
    public void matchesWhenGivenDateIsWithinWindow(){
        LocalDate today = LocalDate.now(),
              yesterday = LocalDate.now().minusDays(1),
               tomorrow = LocalDate.now().plusDays(1);

        assertThat(yesterday, is(within(2, DAYS).of(today)));
        assertThat(tomorrow,  is(within(2, DAYS).of(today)));
    }

    @Test
    public void doesNotMatchWhenGivenDateIsNotWithinWindow() throws Exception {
        LocalDate today = LocalDate.now(),
               lastWeek = LocalDate.now().minusWeeks(1),
               nextWeek = LocalDate.now().plusWeeks(1);

        assertThat(lastWeek, is(not(within(6, DAYS).of(today))));
        assertThat(nextWeek, is(not(within(6, DAYS).of(today))));
    }

    @Test(expected = UnsupportedTemporalTypeException.class)
    public void blowsUpWhenUsingTimeBasedUnits(){
        LocalDate today = LocalDate.now();
        LocalDate yesterday = LocalDate.now().minusDays(1);

        assertThat(yesterday, is(within(2, HOURS).of(today)));
    }

    @Test
    public void describesItselfCorrectly(){
        LocalDate today = LocalDate.now();
        LocalDate lastWeek = LocalDate.now().minusWeeks(1l);

        try {
            assertThat(lastWeek, is(within(1, DAYS).of(today))); /* false assertion to trigger AssertionError */
            fail();
        } catch(AssertionError e){
            assertThat(e.getMessage(), containsString("a LocalDate that is within <1L> <" + DAYS.toString() + "> of <" + today.toString() + ">"));
        }
    }
}
