package com.spencerwi.hamcrestJDK8Time.localdate;

import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.UnsupportedTemporalTypeException;

import static com.spencerwi.hamcrestJDK8Time.localdate.IsWithinLocalDate.within;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static org.hamcrest.CoreMatchers.containsString;
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

    @Test(expected = UnsupportedTemporalTypeException.class)
    public void blowsUpWhenUsingTimeBasedUnites(){
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
        } catch(AssertionError e){
            assertThat(e.getMessage(), containsString("a LocalDate that is within <1L> <" + DAYS.toString() + "> of <" + today.toString() + ">"));
        }
    }
}