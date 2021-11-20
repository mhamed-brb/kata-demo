package org.kata.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.kata.service.KataService;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class KataTest {


    @Test
    void testKata_without_params() {
       String expected = "1 2 FooFoo 4 BarBar Foo Qix 8 Foo Bar ";
       StringBuilder obtained=new StringBuilder();
        Stream.iterate(1, n -> n + 1).limit(10).forEach(integer -> {
            obtained.append(KataService.translateToFooBarQixWithStream(integer)).append(" ");
        });
       Assertions.assertEquals(obtained.toString(),expected);
    }


    @ParameterizedTest(name = "{index} ==> Description du test : {0} {1}")
    @MethodSource("data_test")
    void testKata_with_params(Integer number,String expected) {
        Assertions.assertAll(
                () -> Assertions.assertEquals(KataService.translateToFooBarQixWithoutStream(number),expected),
                () -> Assertions.assertEquals(KataService.translateToFooBarQixWithStream(number),expected)
        );
    }

    static Stream<Arguments> data_test() {
        return Stream.of(
                arguments(51,"FooBar"),
                arguments(53,"BarFoo"),
                arguments(13,"Foo"),
                arguments(15,"FooBarBar"),
                arguments(33,"FooFooFoo"),
                arguments(27,"FooQix")
                );
    }
}
