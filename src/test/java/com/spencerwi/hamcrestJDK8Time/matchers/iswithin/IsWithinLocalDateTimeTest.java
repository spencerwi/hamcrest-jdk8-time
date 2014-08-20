package com.spencerwi.hamcrestJDK8Time.matchers.iswithin;

import org.junit.Test;

import java.time.LocalDateTime;

import static com.spencerwi.hamcrestJDK8Time.matchers.iswithin.IsWithinLocalDateTime.within;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class IsWithinLocalDateTimeTest {
    
    @Test
    public void matchesWhenGivenDateIsWithinWindow(){
        LocalDateTime now = LocalDateTime.now(),
                yesterday = LocalDateTime.now().minusDays(1),
                 tomorrow = LocalDateTime.now().plusDays(1),
            twoMinutesAgo = LocalDateTime.now().minusMinutes(2),
        twoMinutesFromNow = LocalDateTime.now().plusMinutes(2);

        assertThat(yesterday, is(within(2, DAYS).of(now)));
        assertThat(tomorrow,  is(within(2, DAYS).of(now)));

        assertThat(twoMinutesAgo,     is(within(5, MINUTES).of(now)));
        assertThat(twoMinutesFromNow, is(within(5, MINUTES).of(now)));
    }

    @Test
    public void describesItselfCorrectly(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastWeek = LocalDateTime.now().minusWeeks(1l);

        try {
            assertThat(lastWeek, is(within(1, DAYS).of(now))); /* false assertion to trigger AssertionError */
        } catch(AssertionError e){
            assertThat(e.getMessage(), containsString("a LocalDateTime that is within <1L> <" + DAYS.toString() + "> of <" + now.toString() + ">"));
        }
    }
}