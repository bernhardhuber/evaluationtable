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
import org.huberb.evaluationtable.vector.BigDecimalVector;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author berni3
 */
public class EvaluationsTest {

    static enum InputAndSumEnum {
        inputListOf,
        resultSumOfInputListOf,
        resultMinOfInputListOf,
        resultMaxOfInputListOf;
    }

    @Test
    public void test_InputAndSumEnum() {
        final Function<BigDecimalVector, BigDecimalVector> sumFunc = (BigDecimalVector bdv) -> {
            return new BigDecimalVector(bdv.sum());
        };
        final Function<BigDecimalVector, BigDecimalVector> minFunc = (BigDecimalVector bdv) -> {
            return new BigDecimalVector(bdv.min());
        };
        final Function<BigDecimalVector, BigDecimalVector> maxFunc = (BigDecimalVector bdv) -> {
            return new BigDecimalVector(bdv.max());
        };
        final Evaluations<BigDecimalVector> ev = new Evaluations<>();
        final List<BigDecimal> listOfBigDecimal = Arrays.asList(
                BigDecimal.valueOf(1L),
                BigDecimal.valueOf(2L),
                BigDecimal.valueOf(3L),
                BigDecimal.valueOf(4L),
                BigDecimal.valueOf(5L)
        );
        ev.putIfAbsentValue(InputAndSumEnum.inputListOf, new BigDecimalVector(listOfBigDecimal));
        //--- sum
        ev.evaluateTo(InputAndSumEnum.inputListOf,
                InputAndSumEnum.resultSumOfInputListOf,
                sumFunc
        );
        assertEquals(BigDecimal.valueOf(15L), ev.get(InputAndSumEnum.resultSumOfInputListOf).scalar0());
        //--- min
        ev.evaluateTo(InputAndSumEnum.inputListOf,
                InputAndSumEnum.resultMinOfInputListOf,
                minFunc
        );
        assertEquals(BigDecimal.valueOf(1L), ev.get(InputAndSumEnum.resultMinOfInputListOf).scalar0());
        //--- max
        ev.evaluateTo(InputAndSumEnum.inputListOf,
                InputAndSumEnum.resultMaxOfInputListOf,
                maxFunc
        );
        assertEquals(BigDecimal.valueOf(5L), ev.get(InputAndSumEnum.resultMaxOfInputListOf).scalar0());
    }

}
