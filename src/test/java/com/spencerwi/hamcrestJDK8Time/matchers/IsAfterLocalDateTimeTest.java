package com.spencerwi.hamcrestJDK8Time.matchers;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsAfter.after;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.fail;

public class IsAfterLocalDateTimeTest {
    @Test
    public void doesNotMatchIfBeforeEnd(){
        LocalDateTime startOfYear = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                        endOfYear = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

        assertThat(startOfYear, is(not(after(endOfYear))));
    }

    @Test
    public void doesNotMatchIfEqualToEnd(){
        LocalDateTime startOfYear = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                  alsoStartOfYear = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0);

        assertThat(startOfYear, is(not(after(alsoStartOfYear))));
    }

    @Test
    public void matchesIfAfterEnd(){
        LocalDateTime startOfYear = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                        endOfYear = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

        assertThat(endOfYear, is(after(startOfYear)));
    }

    @Test
    public void describesItselfCorrectly(){
        LocalDateTime startOfYear = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                        endOfYear = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

        try {
            assertThat(startOfYear, is(after(endOfYear)));
            fail("should have thrown an assertionError because of the intentionally-false assertion");
        } catch (AssertionError e){
            assertThat(e.getMessage(), containsString("Expected: is a LocalDateTime that is after <" + endOfYear.toString() + ">"));
        }
    }
}
