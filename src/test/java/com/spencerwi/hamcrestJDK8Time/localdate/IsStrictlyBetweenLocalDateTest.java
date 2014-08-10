package com.spencerwi.hamcrestJDK8Time.localdate;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static com.spencerwi.hamcrestJDK8Time.localdate.IsStrictlyBetweenLocalDate.strictlyBetween;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class IsStrictlyBetweenLocalDateTest {

    @Test
    public void doesNotMatchIfEqualToStart(){
        LocalDate start = LocalDate.of(2014, Month.JANUARY, 1),
             dateToTest = LocalDate.of(2014, Month.JANUARY, 1),
                    end = LocalDate.of(2014, Month.DECEMBER, 31);

        assertThat(dateToTest, is(not(strictlyBetween(start, end))));
    }

    @Test
    public void doesNotMatchIfEqualToEnd(){
        LocalDate start = LocalDate.of(2014, Month.JANUARY, 1),
             dateToTest = LocalDate.of(2014, Month.DECEMBER, 31),
                    end = LocalDate.of(2014, Month.DECEMBER, 31);

        assertThat(dateToTest, is(not(strictlyBetween(start, end))));
    }

    @Test
    public void matchesIfStrictlyBetweenStartAndEnd(){
        LocalDate start = LocalDate.of(2014, Month.JANUARY, 1),
             dateToTest = LocalDate.of(2014, Month.JUNE, 30),
                    end = LocalDate.of(2014, Month.DECEMBER, 31);

        assertThat(dateToTest, is(strictlyBetween(start, end)));
    }

    @Test
    public void doesNotMatchIfOutsideOfGivenWindow(){
        LocalDate start = LocalDate.of(2014, Month.JANUARY, 1),
             dateToTest = LocalDate.of(1970, Month.JUNE, 30),
                    end = LocalDate.of(2014, Month.DECEMBER, 31);

        assertThat(dateToTest, is(not(strictlyBetween(start, end))));
    }

    @Test
    public void describesItselfCorrectly(){
        LocalDate start = LocalDate.of(2014, Month.JANUARY, 1),
             dateToTest = LocalDate.of(1970, Month.JUNE, 30),
                    end = LocalDate.of(2014, Month.DECEMBER, 31);


        try {
            assertThat(dateToTest, is(strictlyBetween(start, end))); /* Should fail, throwing AssertionError */
        } catch(AssertionError e){
            assertThat(e.getMessage(), containsString("Expected: is a LocalDate strictly between <" + start.toString() + "> and <" + end.toString() + ">"));
        }
    }
}
