package com.spencerwi.hamcrestJDK8Time.matchers;

import org.junit.Test;

import java.time.ZonedDateTime;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsWithin.within;
import static java.time.temporal.ChronoUnit.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;


public class IsWithinZonedDateTimeTest {
    
    @Test
    public void matchesWhenGivenDateIsWithinWindow(){
        ZonedDateTime now = ZonedDateTime.now(),
                yesterday = ZonedDateTime.now().minusDays(1),
                 tomorrow = ZonedDateTime.now().plusDays(1),
            twoMinutesAgo = ZonedDateTime.now().minusMinutes(2),
        twoMinutesFromNow = ZonedDateTime.now().plusMinutes(2);

        assertThat(yesterday, is(within(2, DAYS).of(now)));
        assertThat(tomorrow,  is(within(2, DAYS).of(now)));

        assertThat(twoMinutesAgo,     is(within(5, MINUTES).of(now)));
        assertThat(twoMinutesFromNow, is(within(5, MINUTES).of(now)));
    }

    @Test
    public void doesNotMatchWhenGivenDateIsNotWithinWindow(){
        ZonedDateTime now = ZonedDateTime.now(),
                yesterday = ZonedDateTime.now().minusDays(1),
                 tomorrow = ZonedDateTime.now().plusDays(1),
            sixMinutesAgo = ZonedDateTime.now().minusMinutes(6),
        sixMinutesFromNow = ZonedDateTime.now().plusMinutes(6);

        assertThat(yesterday, is(not(within(20, HOURS).of(now))));
        assertThat(tomorrow,  is(not(within(20, HOURS).of(now))));

        assertThat(sixMinutesAgo,     is(not(within(5, MINUTES).of(now))));
        assertThat(sixMinutesFromNow, is(not(within(5, MINUTES).of(now))));
    }

    @Test
    public void describesItselfCorrectly(){
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime lastWeek = ZonedDateTime.now().minusWeeks(1l);

        try {
            assertThat(lastWeek, is(within(1, DAYS).of(now))); /* false assertion to trigger AssertionError */
            fail();
        } catch(AssertionError e){
            assertThat(e.getMessage(), containsString("a ZonedDateTime that is within <1L> <" + DAYS.toString() + "> of <" + now.toString() + ">"));
        }
    }
}
