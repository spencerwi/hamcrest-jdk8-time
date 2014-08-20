package com.spencerwi.hamcrestJDK8Time.matchers;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsAfter.after;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.fail;

public class IsAfterLocalDateTest {
    @Test
    public void doesNotMatchIfBeforeEnd(){
        LocalDate startOfYear = LocalDate.of(2014, Month.JANUARY, 1),
                    endOfYear = LocalDate.of(2014, Month.DECEMBER, 31);

        assertThat(startOfYear, is(not(after(endOfYear))));
    }

    @Test
    public void doesNotMatchIfEqualToEnd(){
        LocalDate startOfYear = LocalDate.of(2014, Month.JANUARY, 1),
              alsoStartOfYear = LocalDate.of(2014, Month.JANUARY, 1);

        assertThat(startOfYear, is(not(after(alsoStartOfYear))));
    }

    @Test
    public void matchesIfAfterEnd(){
        LocalDate startOfYear = LocalDate.of(2014, Month.JANUARY, 1),
                    endOfYear = LocalDate.of(2014, Month.DECEMBER, 31);

        assertThat(endOfYear, is(after(startOfYear)));
    }

    @Test
    public void describesItselfCorrectly(){
        LocalDate startOfYear = LocalDate.of(2014, Month.JANUARY, 1),
                    endOfYear = LocalDate.of(2014, Month.DECEMBER, 31);

        try {
            assertThat(startOfYear, is(after(endOfYear)));
            fail("should have thrown an assertionError because of the intentionally-false assertion");
        } catch (AssertionError e){
            assertThat(e.getMessage(), containsString("Expected: is a LocalDate that is after <" + endOfYear.toString() + ">"));
        }
    }
}
