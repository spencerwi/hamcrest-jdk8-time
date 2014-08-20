package com.spencerwi.hamcrestJDK8Time.matchers;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsBetween.between;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class IsBetweenLocalDateTest {
    @Test
    public void matchesIfEqualToStart(){
        LocalDate start = LocalDate.of(2014, Month.JANUARY, 1),
             timeToTest = LocalDate.of(2014, Month.JANUARY, 1),
                    end = LocalDate.of(2014, Month.DECEMBER, 31);

        assertThat(timeToTest, is(between(start, end)));
    }

    @Test
    public void matchesIfEqualToEnd(){
        LocalDate start = LocalDate.of(2014, Month.JANUARY, 1),
             timeToTest = LocalDate.of(2014, Month.DECEMBER, 31),
                    end = LocalDate.of(2014, Month.DECEMBER, 31);

        assertThat(timeToTest, is(between(start, end)));
    }

    @Test
    public void matchesIfStrictlyBetweenStartAndEnd(){
        LocalDate start = LocalDate.of(2014, Month.JANUARY, 1),
             timeToTest = LocalDate.of(2014, Month.JUNE, 30),
                    end = LocalDate.of(2014, Month.DECEMBER, 31);

        assertThat(timeToTest, is(between(start, end)));
    }

    @Test
    public void doesNotMatchIfOutsideOfGivenWindow(){
        LocalDate start = LocalDate.of(2014, Month.JANUARY, 1),
             timeToTest = LocalDate.of(1970, Month.JUNE, 30),
                    end = LocalDate.of(2014, Month.DECEMBER, 31);

        assertThat(timeToTest, is(not(between(start, end))));
    }

    @Test
    public void describesItselfCorrectly(){
        LocalDate start = LocalDate.of(2014, Month.JANUARY, 1),
             timeToTest = LocalDate.of(1970, Month.JUNE, 30),
                    end = LocalDate.of(2014, Month.DECEMBER, 31);


        try {
            assertThat(timeToTest, is(between(start, end))); /* Should fail, throwing AssertionError */
        } catch(AssertionError e){
            assertThat(e.getMessage(), containsString("Expected: is a LocalDate between <" + start.toString() + "> and <" + end.toString() + ">, inclusively"));
        }
    }
}
