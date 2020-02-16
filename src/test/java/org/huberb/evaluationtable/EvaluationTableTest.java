/*
 * Copyright 2020 berni3.
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
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import org.huberb.evaluationtable.EvaluationTable.Evaluations;
import org.huberb.evaluationtable.EvaluationTable.NBigDecimalValue;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author berni3
 */
public class EvaluationTableTest {

    public EvaluationTableTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    static enum InputAndSumEnum {
        inputListOf, resultSumOfInputListOf;
    }

    @Test
    public void testSomeMethod() {
        Evaluations<NBigDecimalValue> ev = new Evaluations<>();
        List<BigDecimal> l = Arrays.asList(
                BigDecimal.valueOf(1L),
                BigDecimal.valueOf(2L),
                BigDecimal.valueOf(3L),
                BigDecimal.valueOf(4L),
                BigDecimal.valueOf(5L)
        );
        ev.putIfAbsentValue(InputAndSumEnum.inputListOf, new NBigDecimalValue(l));
        ev.evaluateTo(InputAndSumEnum.inputListOf,
                InputAndSumEnum.resultSumOfInputListOf,
                (NBigDecimalValue t) -> {
                    NBigDecimalValue result = t.sum();
                    return result;
                }
        );
        assertEquals(BigDecimal.valueOf(15L), ev.get(InputAndSumEnum.resultSumOfInputListOf).bigDecimal());
    }

    enum Input2AndSum {
        input1, input1Sum,
        input2, input2Sum,
        input1SumInput2Sum,
        result
    };

    @Test
    public void testSomeMethod2() {

        Evaluations<NBigDecimalValue> ev = new Evaluations<>();
        List<BigDecimal> l1 = Arrays.asList(
                BigDecimal.valueOf(1L),
                BigDecimal.valueOf(2L),
                BigDecimal.valueOf(3L),
                BigDecimal.valueOf(4L),
                BigDecimal.valueOf(5L)
        );
        List<BigDecimal> l2 = Arrays.asList(
                BigDecimal.valueOf(-1L),
                BigDecimal.valueOf(-2L),
                BigDecimal.valueOf(-3L),
                BigDecimal.valueOf(-4L),
                BigDecimal.valueOf(-5L)
        );
        Function<NBigDecimalValue, NBigDecimalValue> f = (t) -> t.sum();

        ev.putIfAbsentValue(Input2AndSum.input1, new NBigDecimalValue(l1));
        ev.evaluateTo(Input2AndSum.input1, Input2AndSum.input1Sum, f);
        ev.putIfAbsentValue(Input2AndSum.input2, new NBigDecimalValue(l2));
        ev.evaluateTo(Input2AndSum.input2, Input2AndSum.input2Sum, f);

        assertEquals(BigDecimal.valueOf(15L), ev.get(Input2AndSum.input1Sum).bigDecimal());
        assertEquals(BigDecimal.valueOf(-15L), ev.get(Input2AndSum.input2Sum).bigDecimal());
        // variant 1
        assertEquals(BigDecimal.ZERO, ev.get(Input2AndSum.input1Sum).bigDecimal().add(ev.get(Input2AndSum.input2Sum).bigDecimal()));

        // variant 2
        ev.putValue(Input2AndSum.input1SumInput2Sum,
                new NBigDecimalValue(
                        Arrays.asList(
                                ev.get(Input2AndSum.input1Sum).bigDecimal(),
                                ev.get(Input2AndSum.input2Sum).bigDecimal())
                )
        );
        ev.evaluateTo(Input2AndSum.input1SumInput2Sum, Input2AndSum.result, f);
        assertEquals(BigDecimal.ZERO, ev.get(Input2AndSum.result).bigDecimal());

        // variant 3
        ev.putValue(Input2AndSum.input1SumInput2Sum, null);
        ev.putValue(Input2AndSum.result, null);
        ev.putValue(Input2AndSum.input1SumInput2Sum,
                NBigDecimalValue.fromNBigDecimalValue(Arrays.asList(
                        ev.get(Input2AndSum.input1Sum),
                        ev.get(Input2AndSum.input2Sum)
                ))
        );
        ev.evaluateTo(Input2AndSum.input1SumInput2Sum, Input2AndSum.result, f);
        assertEquals(BigDecimal.ZERO, ev.get(Input2AndSum.result).bigDecimal());
    }
}
