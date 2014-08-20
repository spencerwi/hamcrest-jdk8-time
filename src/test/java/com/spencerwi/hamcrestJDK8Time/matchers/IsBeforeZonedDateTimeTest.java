package com.spencerwi.hamcrestJDK8Time.matchers;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsBefore.before;
import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class IsBeforeZonedDateTimeTest {
    @Test
    public void matchesIfBeforeEnd(){
        ZonedDateTime startOfYear = ZonedDateTime.of(2014, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
                        endOfYear = ZonedDateTime.of(2014, 12, 31, 23, 59, 59, 99, ZoneId.systemDefault());

        assertThat(startOfYear, is(before(endOfYear)));
    }

    @Test
    public void doesNotMatchIfEqualToEnd(){
        ZonedDateTime startOfYear = ZonedDateTime.of(2014, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
                  alsoStartOfYear = ZonedDateTime.of(2014, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault());

        assertThat(startOfYear, is(not(before(alsoStartOfYear))));
    }

    @Test
    public void doesNotMatchIfAfterEnd(){
        ZonedDateTime startOfYear = ZonedDateTime.of(2014, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
                        endOfYear = ZonedDateTime.of(2014, 12, 31, 23, 59, 59, 99, ZoneId.systemDefault());

        assertThat(endOfYear, is(not(before(startOfYear))));
    }

    @Test
    public void describesItselfCorrectly(){
        ZonedDateTime startOfYear = ZonedDateTime.of(2014, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
                        endOfYear = ZonedDateTime.of(2014, 12, 31, 23, 59, 59, 99, ZoneId.systemDefault());

        try {
            assertThat(endOfYear, is(before(startOfYear)));
            fail("should have thrown an assertionError because of the intentionally-false assertion");
        } catch (AssertionError e){
            assertThat(e.getMessage(), containsString("Expected: is a ZonedDateTime that is before <" + startOfYear.toString() + ">"));
        }
    }
}
