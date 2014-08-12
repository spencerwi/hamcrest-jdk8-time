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
