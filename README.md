hamcrest-jdk8-time
==================

Hamcrest matchers for Java 8's new java.time classes.

[![Build Status](https://travis-ci.org/spencerwi/hamcrest-jdk8-time.svg?branch=master)](https://travis-ci.org/spencerwi/hamcrest-jdk8-time)

Included matchers
-----------------

### Common matchers (defined for LocalDateTime, DateTime, and ZonedDateTime)

#### `between(start, end)`

Matches if a date/time is between two other dates/times, **inclusively**.

For example:

```java
LocalDateTime start = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
       equalToStart = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
    strictlyBetween = LocalDateTime.of(2014, Month.JUNE, 1, 0, 0, 0, 0),
         equalToEnd = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99),
                end = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

assertThat(equalToStart,    is(between(start, end)));
assertThat(strictlyBetween, is(between(start, end)));
assertThat(equalToEnd,      is(between(start, end)));
```

#### `strictlyBetween(start, end)`

Matches if a date/time is **strictly** between two other dates/times.

For example:

```java
LocalDateTime start = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
       equalToStart = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
    strictlyBetween = LocalDateTime.of(2014, Month.JUNE, 1, 0, 0, 0, 0),
         equalToEnd = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99),
                end = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

assertThat(equalToStart,    is(not(strictlyBetween(start, end))));
assertThat(strictlyBetween, is(strictlyBetween(start, end)));
assertThat(equalToEnd,      is(not(strictlyBetween(start, end))));
```

#### `before(other)`

Matches if a date/time is **strictly** before another date/time.

For example:

```java
LocalDateTime startOfYear = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
          alsoStartOfYear = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                endOfYear = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

assertThat(startOfYear, is(before(endOfYear)));
assertThat(startOfYear, is(not(before(alsoStartOfYear))));
```

#### `after(other)`

Matches if a date/time is **strictly** after another date/time.

For example:

```java
LocalDateTime startOfYear = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                endOfYear = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99),
            alsoEndOfYear = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

assertThat(endOfYear, is(after(startOfYear)));
assertThat(endOfYear, is(not(after(alsoEndOfYear))));
```

#### `within(window, units).of(other)`

Matches if a date/time is within X `TemporalUnits` of another date/time.

For example:

```java
ZonedDateTime now = ZonedDateTime.now(),
        yesterday = ZonedDateTime.now().minusDays(1),
         tomorrow = ZonedDateTime.now().plusDays(1),
    twoMinutesAgo = ZonedDateTime.now().minusMinutes(2),
twoMinutesFromNow = ZonedDateTime.now().plusMinutes(2);

assertThat(yesterday, is(within(2, ChronoUnits.DAYS).of(now)));
assertThat(tomorrow,  is(within(2, ChronoUnits.DAYS).of(now)));

assertThat(twoMinutesAgo,     is(within(5, ChronoUnits.MINUTES).of(now)));
assertThat(twoMinutesFromNow, is(within(5, ChronoUnits.MINUTES).of(now)));
```

**Be careful when using this with LocalDate!** You can't use time-based TemporalUnits with LocalDates.

This will throw an `UnsupportedTemporalTypeException`:

```java
LocalDate today = LocalDate.now();
LocalDate yesterday = LocalDate.now().minusDays(1);

assertThat(yesterday, is(within(2, HOURS).of(today)));
```

How to get it
-------------

These matchers are available from the [Maven Central Repository](http://search.maven.org/#search%7Cga%7C1%7Cg%3Acom.spencerwi%20a%3A%22hamcrest-jdk8-time%22), so your favorite build tool should be able to pull them down.

Using Gradle:

```groovy
dependencies {
       ...
       testCompile group: 'com.spencerwi', name: 'hamcrest-jdk8-time', version: '0.4'
       ...
}
```

Using Maven:

```xml
<dependency>
       <groupId>com.spencerwi</groupId>
       <artifactId>hamcrest-jdk8-time</artifactId>
       <scope>test</scope>
</dependency>
```

Using SBT (Scala Build Tool):

```scala
libraryDependencies += "com.spencerwi" % "hamcrest-jdk8-time" % "0.4"
```
