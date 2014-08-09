hamcrest-jdk8-time
==================

Hamcrest matchers for Java 8's new java.time classes.

[![Build Status](https://travis-ci.org/spencerwi/hamcrest-jdk8-time.svg?branch=master)](https://travis-ci.org/spencerwi/hamcrest-jdk8-time)

Included matchers
-----------------

### LocalDateTime matchers

#### `between(LocalDateTime start, LocalDateTime end)`

Matches if a LocalDateTime is between two other LocalDateTimes, **inclusively**.

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

#### `strictlyBetween(LocalDateTime start, LocalDateTime end)`

Matches if a LocalDateTime is **strictly** between two other LocalDateTimes.

For example:

```java
LocalDateTime start = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
       equalToStart = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
    strictlyBetween = LocalDateTime.of(2014, Month.JUNE, 1, 0, 0, 0, 0),
         equalToEnd = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99),
                end = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

assertThat(equalToStart,    is(not(between(start, end))));
assertThat(strictlyBetween, is(between(start, end)));
assertThat(equalToEnd,      is(not(between(start, end))));
```

#### `before(LocalDateTime other)`

Matches if a LocalDateTime is **strictly** before another LocalDateTime.

For example:

```java
LocalDateTime startOfYear = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
          alsoStartOfYear = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                endOfYear = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

assertThat(startOfYear, is(before(endOfYear)));
assertThat(startOfYear, is(not(before(alsoStartOfYear))));
```

#### `after(LocalDateTime other)`

Matches if a LocalDateTime is **strictly** after another LocalDateTime.

For example:

```java
LocalDateTime startOfYear = LocalDateTime.of(2014, Month.JANUARY, 1, 0, 0, 0, 0),
                endOfYear = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99),
            alsoEndOfYear = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59, 99);

assertThat(endOfYear, is(after(startOfYear)));
assertThat(endOfYear, is(not(after(alsoEndOfYear))));
```
