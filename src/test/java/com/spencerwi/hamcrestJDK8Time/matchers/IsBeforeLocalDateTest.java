package com.spencerwi.hamcrestJDK8Time.matchers;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsBefore.before;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.fail;

public class IsBeforeLocalDateTest {
    @Test
    public void matchesIfBeforeEnd(){
        LocalDate startOfYear = LocalDate.of(2014, Month.JANUARY, 1),
                    endOfYear = LocalDate.of(2014, Month.DECEMBER, 31);

        assertThat(startOfYear, is(before(endOfYear)));
    }

    @Test
    public void doesNotMatchIfEqualToEnd(){
        LocalDate startOfYear = LocalDate.of(2014, Month.JANUARY, 1),
              alsoStartOfYear = LocalDate.of(2014, Month.JANUARY, 1);

        assertThat(startOfYear, is(not(before(alsoStartOfYear))));
    }

    @Test
    public void doesNotMatchIfAfterEnd(){
        LocalDate startOfYear = LocalDate.of(2014, Month.JANUARY, 1),
                    endOfYear = LocalDate.of(2014, Month.DECEMBER, 31);

        assertThat(endOfYear, is(not(before(startOfYear))));
    }

    @Test
    public void describesItselfCorrectly(){
        LocalDate startOfYear = LocalDate.of(2014, Month.JANUARY, 1),
                    endOfYear = LocalDate.of(2014, Month.DECEMBER, 31);

        try {
            assertThat(endOfYear, is(before(startOfYear)));
            fail("should have thrown an assertionError because of the intentionally-false assertion");
        } catch (AssertionError e){
            assertThat(e.getMessage(), containsString("Expected: is a LocalDate that is before <" + startOfYear.toString() + ">"));
        }
    }
}
