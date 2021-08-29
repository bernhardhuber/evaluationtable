/*
 * Copyright 2021 berni3.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.huberb.evaluationtable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import org.huberb.evaluationtable.EvaluationsMonad.Sheet;
import org.huberb.evaluationtable.EvaluationsMonad.ValueStringOrNumber;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author berni3
 */
public class SheetTest {

    @Test
    public void testValueStringOrNumberHelloWorld_MultiStep() {
        final List<ValueStringOrNumber> l0 = new ArrayList<>();
        final Sheet<ValueStringOrNumber> s0 = new Sheet<>(l0);

        s0.flatMap((l) -> {
            final String v = "Hello";
            return Arrays.asList(new ValueStringOrNumber(v));
        });

        s0.flatMap((l) -> {
            final String v = l.get(0).s("") + ", world!";
            return Arrays.asList(new ValueStringOrNumber(v));
        });
        s0.flatMap((l) -> {
            final String userName = System.getProperty("user.name", "<unknown>");
            final String v = "Greeting, " + userName;
            return Arrays.asList(new ValueStringOrNumber(v));
        });
        //---
        assertEquals("Sheet{"
                + "l=["
                + "ValueStringOrNumber{sOpt=Optional[Hello], nOpt=Optional.empty}, "
                + "ValueStringOrNumber{sOpt=Optional[Hello, world!], nOpt=Optional.empty}, "
                + "ValueStringOrNumber{sOpt=Optional[Greeting, berni3], nOpt=Optional.empty}"
                + "]"
                + "}", s0.toString());

        final List<ValueStringOrNumber> l1 = s0.l();
        assertEquals("Hello", l1.get(0).s(""));
        assertEquals("Hello, world!", l1.get(1).s(""));
        final String userName = System.getProperty("user.name", "<unknown>");
        assertEquals("Greeting, " + userName, l1.get(2).s(""));
    }

    @Test
    public void testValueStringOrNumberHelloWorld_SingleStep() {
        final List<ValueStringOrNumber> l0 = new ArrayList<>();
        final Sheet<ValueStringOrNumber> s0 = new Sheet<>(l0);

        s0.flatMap((final List<ValueStringOrNumber> l) -> {
            final String v0 = "Hello";
            final String v1 = v0 + ", world!";
            final String userName = System.getProperty("user.name", "<unknown>");
            final String v2 = "Greeting, " + userName;

            return Arrays.asList(
                    new ValueStringOrNumber(v0),
                    new ValueStringOrNumber(v1),
                    new ValueStringOrNumber(v2)
            );
        });

        assertEquals("Sheet{"
                + "l=["
                + "ValueStringOrNumber{sOpt=Optional[Hello], nOpt=Optional.empty}, "
                + "ValueStringOrNumber{sOpt=Optional[Hello, world!], nOpt=Optional.empty}, "
                + "ValueStringOrNumber{sOpt=Optional[Greeting, berni3], nOpt=Optional.empty}"
                + "]"
                + "}", s0.toString());
        final List<ValueStringOrNumber> l1 = s0.l();
        assertEquals("Hello", l1.get(0).s(""));
        assertEquals("Hello, world!", l1.get(1).s(""));
        final String userName = System.getProperty("user.name", "<unknown>");
        assertEquals("Greeting, " + userName, l1.get(2).s(""));
    }

    @Test
    public void testValueStringOrNumberFibonacci() {
        final List<ValueStringOrNumber> l0 = new ArrayList<>(
                Arrays.asList(
                        new ValueStringOrNumber(0),
                        new ValueStringOrNumber(1))
        );
        final Sheet<ValueStringOrNumber> s0 = new Sheet<>(l0);

        final Function<List<ValueStringOrNumber>, List<ValueStringOrNumber>> f = (final List<ValueStringOrNumber> l) -> {
            final int lSize = l.size();
            final int lIndexMinus1 = lSize - 1;
            final int lIndexMinus2 = lSize - 2;
            final Number n = l.get(lIndexMinus1).n().get().intValue() + l.get(lIndexMinus2).n().get().intValue();
            final List<ValueStringOrNumber> ret = Arrays.asList(new ValueStringOrNumber(n));
            return ret;
        };
        for (int i = 1; i <= 10; i++) {
            s0.flatMap(f);
        }
        assertEquals("Sheet{l=["
                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[0]}, "
                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[1]}, "
                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[1]}, "
                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[2]}, "
                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[3]}, "
                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[5]}, "
                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[8]}, "
                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[13]}, "
                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[21]}, "
                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[34]}, "
                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[55]}, "
                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[89]}"
                + "]}", s0.toString());
        final List<ValueStringOrNumber> l1 = s0.l();
        assertEquals(12, l1.size());
        assertEquals(0, l1.get(0).n().get());
        assertEquals(1, l1.get(1).n().get());
        assertEquals(1, l1.get(2).n().get());
        assertEquals(2, l1.get(3).n().get());
        assertEquals(3, l1.get(4).n().get());
        assertEquals(5, l1.get(5).n().get());
        assertEquals(8, l1.get(6).n().get());
        assertEquals(13, l1.get(7).n().get());
        assertEquals(21, l1.get(8).n().get());
        assertEquals(34, l1.get(9).n().get());
        assertEquals(55, l1.get(10).n().get());
        assertEquals(89, l1.get(11).n().get());
        assertEquals(13, l1.get(7).n().get());
    }

    @ParameterizedTest
    @MethodSource("xxx")
    public void testValueStringOrNumberConditional(BigDecimal inputValue, int expectedResultIndex, BigDecimal expectedResultValue) {
        final List<ValueStringOrNumber> l0 = new ArrayList<>(
                Arrays.asList(
                        new ValueStringOrNumber(0),
                        new ValueStringOrNumber(10),
                        new ValueStringOrNumber(BigDecimal.valueOf(5.5)),
                        //
                        new ValueStringOrNumber(11),
                        new ValueStringOrNumber(50),
                        new ValueStringOrNumber(BigDecimal.valueOf(10.0)),
                        //
                        new ValueStringOrNumber(51),
                        new ValueStringOrNumber(Integer.MAX_VALUE),
                        new ValueStringOrNumber(BigDecimal.valueOf(20.0))
                )
        );
        final Sheet<ValueStringOrNumber> s0 = new Sheet<>(l0);

        final Function<BigDecimal[], Optional<BigDecimal>> fRule = (BigDecimal[] bds) -> {
            final BigDecimal ctx = bds[0];
            final BigDecimal g1Min = bds[1];
            final BigDecimal g1Max = bds[2];
            final BigDecimal g1Percentage = bds[3];
            final Optional<BigDecimal> ret;
            if (ctx.compareTo(g1Min) >= 0 && ctx.compareTo(g1Max) <= 0) {
                final BigDecimal minusValue = ctx.divide(BigDecimal.valueOf(100L)).multiply(g1Percentage);
                ret = Optional.of(ctx.subtract(minusValue));
            } else {
                ret = Optional.empty();
            }
            return ret;
        };

        final ValueStringOrNumber valueStringOrNumberCtx = new ValueStringOrNumber(inputValue);
        final Function<List<ValueStringOrNumber>, List<ValueStringOrNumber>> f = (final List<ValueStringOrNumber> l) -> {
            final BigDecimal ctx = BigDecimal.valueOf(valueStringOrNumberCtx.n(-1).doubleValue());
            final List<ValueStringOrNumber> ret = new ArrayList<>();
            // G1
            {
                final int g1Min = l.get(0).n().get().intValue();
                final int g1Max = l.get(1).n().get().intValue();
                final BigDecimal g1Percentage = BigDecimal.valueOf(l.get(2).n().get().doubleValue());

                final BigDecimal[] bds = new BigDecimal[]{ctx, BigDecimal.valueOf(g1Min), BigDecimal.valueOf(g1Max), g1Percentage};
                final Optional<BigDecimal> g1Ret = fRule.apply(bds);
                ret.add(new ValueStringOrNumber(g1Ret.orElse(BigDecimal.ONE.negate())));
            }
            // G2
            {
                final int g2Min = l.get(3).n().get().intValue();
                final int g2Max = l.get(4).n().get().intValue();
                final BigDecimal g2Percentage = BigDecimal.valueOf(l.get(5).n().get().doubleValue());

                final BigDecimal[] bds = new BigDecimal[]{ctx, BigDecimal.valueOf(g2Min), BigDecimal.valueOf(g2Max), g2Percentage};
                final Optional<BigDecimal> g2Ret = fRule.apply(bds);
                ret.add(new ValueStringOrNumber(g2Ret.orElse(BigDecimal.ONE.negate())));
            }
            // G3
            {
                final int g3Min = l.get(6).n().get().intValue();
                final int g3Max = l.get(7).n().get().intValue();
                final BigDecimal g3Percentage = BigDecimal.valueOf(l.get(8).n().get().doubleValue());
                final BigDecimal[] bds = new BigDecimal[]{ctx, BigDecimal.valueOf(g3Min), BigDecimal.valueOf(g3Max), g3Percentage};
                final Optional<BigDecimal> g3Ret = fRule.apply(bds);
                ret.add(new ValueStringOrNumber(g3Ret.orElse(BigDecimal.ONE.negate())));
            }
            return ret;
        };
        s0.flatMap(f);
//        assertEquals("Sheet{l=["
//                // G1 0
//                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[0]}, "
//                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[10]}, "
//                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[5.5]}, "
//                // G2 3
//                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[11]}, "
//                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[50]}, "
//                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[10.0]}, "
//                // G3 6
//                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[51]}, "
//                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[2147483647]}, "
//                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[20.0]}, "
//                // G1 result 9
//                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[-1]}, "
//                // G2 result 10
//                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[20.700]}, "
//                // G3 result 11
//                + "ValueStringOrNumber{sOpt=Optional.empty, nOpt=Optional[-1]}]}", s0.toString());
        final List<ValueStringOrNumber> l1 = s0.l();
//        assertEquals(BigDecimal.valueOf(-1), l1.get(9).n().get());
//        assertEquals(20.7, l1.get(10).n().get().doubleValue(), 0.0001);
//        assertEquals(BigDecimal.valueOf(-1), l1.get(11).n().get());

        if (expectedResultIndex == 9) {
            assertEquals(BigDecimal.valueOf(-1), l1.get(10).n().get());
            assertEquals(BigDecimal.valueOf(-1), l1.get(11).n().get());
        } else if (expectedResultIndex == 10) {
            assertEquals(BigDecimal.valueOf(-1), l1.get(9).n().get());
            assertEquals(BigDecimal.valueOf(-1), l1.get(11).n().get());
        } else if (expectedResultIndex == 11) {
            assertEquals(BigDecimal.valueOf(-1), l1.get(9).n().get());
            assertEquals(BigDecimal.valueOf(-1), l1.get(10).n().get());
        }
        assertEquals(expectedResultValue.doubleValue(), l1.get(expectedResultIndex).n().get().doubleValue(), 0.0001);

    }

    public static Stream<Object[]> xxx() {
        List<Object[]> l = new ArrayList<>();
        l.add(new Object[]{BigDecimal.valueOf(10.0), 9, BigDecimal.valueOf(9.45)});
        l.add(new Object[]{BigDecimal.valueOf(23.0), 10, BigDecimal.valueOf(20.7)});
        l.add(new Object[]{BigDecimal.valueOf(100.0), 11, BigDecimal.valueOf(80.0)});
        l.add(new Object[]{BigDecimal.valueOf(1000.0), 11, BigDecimal.valueOf(800.0)});
        Stream<Object[]> ret = l.stream();
        return ret;
    }
}
