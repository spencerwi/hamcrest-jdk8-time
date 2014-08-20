package com.spencerwi.hamcrestJDK8Time.matchers;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsBefore.before;
import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class IsBeforeLocalDateTimeTest {
    @Test
    public void matchesIfBeforeEnd(){
        LocalDateTime startOfYear = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                        endOfYear = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

        assertThat(startOfYear, is(before(endOfYear)));
    }

    @Test
    public void doesNotMatchIfEqualToEnd(){
        LocalDateTime startOfYear = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                  alsoStartOfYear = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0);

        assertThat(startOfYear, is(not(before(alsoStartOfYear))));
    }

    @Test
    public void doesNotMatchIfAfterEnd(){
        LocalDateTime startOfYear = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                        endOfYear = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

        assertThat(endOfYear, is(not(before(startOfYear))));
    }

    @Test
    public void describesItselfCorrectly(){
        LocalDateTime startOfYear = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                        endOfYear = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

        try {
            assertThat(endOfYear, is(before(startOfYear)));
            fail("should have thrown an assertionError because of the intentionally-false assertion");
        } catch (AssertionError e){
            assertThat(e.getMessage(), containsString("Expected: is a LocalDateTime that is before <" + startOfYear.toString() + ">"));
        }
    }
}
