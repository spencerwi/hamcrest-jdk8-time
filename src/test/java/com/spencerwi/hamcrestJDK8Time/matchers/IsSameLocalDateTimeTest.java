package com.spencerwi.hamcrestJDK8Time.matchers;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsSame.same;
import static java.time.temporal.ChronoField.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class IsSameLocalDateTimeTest {

    @Test
    public void matchesIfSpecifiedFieldHasTheSameValue(){
        LocalDateTime midnightJanuaryFirst1970 = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        LocalDateTime oneAMDecemberThirtyFirst1970 = LocalDateTime.of(1970, 12, 30, 1, 0, 0);

        assertThat(midnightJanuaryFirst1970, is(same(ChronoField.YEAR).as(oneAMDecemberThirtyFirst1970)));
    }

    @Test
    public void doesNotMatchIfSpecifiedFieldHasDifferentValue(){
        LocalDateTime midnightJanuaryFirst1970 = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        LocalDateTime oneAMDecemberThirtyFirst1970 = LocalDateTime.of(1970, 12, 30, 1, 0, 0);

        assertThat(midnightJanuaryFirst1970, is(not(same(DAY_OF_MONTH).as(oneAMDecemberThirtyFirst1970))));
        assertThat(midnightJanuaryFirst1970, is(not(same(MONTH_OF_YEAR).as(oneAMDecemberThirtyFirst1970))));
        assertThat(midnightJanuaryFirst1970, is(not(same(HOUR_OF_DAY).as(oneAMDecemberThirtyFirst1970))));
    }
}