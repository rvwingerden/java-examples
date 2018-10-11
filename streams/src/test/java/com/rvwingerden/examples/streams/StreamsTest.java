package com.rvwingerden.examples.streams;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Optional.of;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StreamsTest {

    List<String> names = asList("f13", "z2", "k3", "d5", "f10", "f107", "f1");

    @Test
    public void d5IsFirstAfterSorting() {
        assertThat(names.stream().sorted().findFirst(), is(of("d5")));
    }

    @Test
    public void f13IsLastOfOccurancesStartingWithF() {
        assertThat(stringsStartingWith("f").sorted().reduce((s1, s2) -> s2), is(of("f13")));
    }

    @Test
    public void fOccurancesSortedInReversedOrder() {
        assertThat(stringsStartingWith("f").sorted((s1, s2) -> s1.compareTo(s2) * -1).collect(Collectors.toList()), is(asList("f13", "f107", "f10", "f1")));
    }

    @Test
    public void f1IsFirstOfOccurancesStartingWithF() {
        assertThat(stringsStartingWith("f").sorted().findFirst(), is(of("f1")));
    }

    private Stream<String> stringsStartingWith(String filterArgument) {
        return names.stream().filter(s -> s.startsWith(filterArgument));
    }
}