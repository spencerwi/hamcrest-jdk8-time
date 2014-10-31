package com.spencerwi.hamcrestJDK8Time.matchers;

import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsSame.same;
import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class IsSameLocalDateTest {

    @Test
    public void matchesIfSpecifiedFieldHasTheSameValue(){
        LocalDate januaryFirst1970 = LocalDate.of(1970, 1, 1);
        LocalDate decemberThirtyFirst1970 = LocalDate.of(1970, 12, 30);

        assertThat(januaryFirst1970, is(same(ChronoField.YEAR).as(decemberThirtyFirst1970)));
    }

    @Test
    public void doesNotMatchIfSpecifiedFieldHasDifferentValue(){
        LocalDate januaryFirst1970 = LocalDate.of(1970, 1, 1);
        LocalDate decemberThirtyFirst1970 = LocalDate.of(1970, 12, 30);

        assertThat(januaryFirst1970, is(not(same(DAY_OF_MONTH).as(decemberThirtyFirst1970))));
        assertThat(januaryFirst1970, is(not(same(MONTH_OF_YEAR).as(decemberThirtyFirst1970))));
    }
}