package ru.promo.lesson24;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
class CalculatorTest {

    Calculator calculator = new Calculator();

    @BeforeEach
    public void beforeEach() {
        System.out.println("before test method");
    }

    @ParameterizedTest
//    @CsvSource({
//            "1, 2, 3",
//            "1, 1, 2"
//    })
    @MethodSource("givenTwoInt_whenAdd_thenReturnResultArgs")
    void givenTwoInt_whenAdd_thenReturnResult(int a, int b, int expected) {
        System.out.println("test method");
        int actual = calculator.add(a, b);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenTwoInt_whenAdd_thenThrowsException() {
        int a = Integer.MAX_VALUE;
        int b = 1;

        Assertions.assertThatThrownBy(() -> calculator.add(a, b))
                .isExactlyInstanceOf(ArithmeticException.class)
                .hasMessage("integer overflow");
    }

    Stream<Arguments> givenTwoInt_whenAdd_thenReturnResultArgs() {
        return Stream.of(
                Arguments.of(1, 2, 3),
                Arguments.of(1, 1, 2)
        );
    }
}
