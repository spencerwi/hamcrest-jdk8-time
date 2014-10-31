package com.spencerwi.hamcrestJDK8Time.matchers;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsSame.same;
import static java.time.temporal.ChronoField.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class IsSameZonedDateTimeTest {

    @Test
    public void matchesIfSpecifiedFieldHasTheSameValue(){
        ZonedDateTime midnightJanuaryFirst1970 = ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("Z"));
        ZonedDateTime oneAMDecemberThirtyFirst1970 = ZonedDateTime.of(1970, 12, 30, 1, 0, 0, 0, ZoneId.of("Z"));

        assertThat(midnightJanuaryFirst1970, is(same(ChronoField.YEAR).as(oneAMDecemberThirtyFirst1970)));
    }

    @Test
    public void doesNotMatchIfSpecifiedFieldHasDifferentValue(){
        ZonedDateTime midnightJanuaryFirst1970 = ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("Z"));
        ZonedDateTime oneAMDecemberThirtyFirst1970 = ZonedDateTime.of(1970, 12, 30, 1, 0, 0, 0, ZoneId.of("Z"));

        assertThat(midnightJanuaryFirst1970, is(not(same(DAY_OF_MONTH).as(oneAMDecemberThirtyFirst1970))));
        assertThat(midnightJanuaryFirst1970, is(not(same(MONTH_OF_YEAR).as(oneAMDecemberThirtyFirst1970))));
        assertThat(midnightJanuaryFirst1970, is(not(same(HOUR_OF_DAY).as(oneAMDecemberThirtyFirst1970))));
    }
}