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
import java.util.Arrays;
import org.huberb.evaluationtable.EvaluationTable.Evaluations;
import org.huberb.evaluationtable.vector.BigDecimalSummaryStatistics;
import org.huberb.evaluationtable.vector.BigDecimalVector;
import org.huberb.evaluationtable.vector.VectorsSummaryStatistics;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author berni3
 */
public class EvaluationsBigDecimalSummaryStatisticsTest {

    static enum Names {
        InputPrices1,
        InputPrices2
    }

    @Test
    public void testEvaluationsWithBigDecimalVetor_1() {
        final BigDecimalVector bigDecimalVectorInputPrices1 = new BigDecimalVector(Arrays.asList(
                BigDecimal.valueOf(10L),
                BigDecimal.valueOf(20L),
                BigDecimal.valueOf(30L)
        ));

        final Evaluations<BigDecimalVector> evaluations = new Evaluations<>();
        evaluations.putIfAbsentValue(Names.InputPrices1, bigDecimalVectorInputPrices1);
        final BigDecimalSummaryStatistics bigDecimalSummaryStatisticsProces1
                = new VectorsSummaryStatistics()
                        .createBigDecimalSummaryStatistics(evaluations.getValue(Names.InputPrices1));
        assertEquals(3L, bigDecimalSummaryStatisticsProces1.getCount());
        assertEquals(BigDecimal.valueOf(10L), bigDecimalSummaryStatisticsProces1.getMin());
        assertEquals(BigDecimal.valueOf(30L), bigDecimalSummaryStatisticsProces1.getMax());
        assertEquals(BigDecimal.valueOf(60L), bigDecimalSummaryStatisticsProces1.getSum());
        assertEquals(BigDecimal.valueOf(20L), bigDecimalSummaryStatisticsProces1.getAverage());
    }

    @Test
    public void testEvaluationsWithBigDecimalVetor_2() {
        final BigDecimalVector bigDecimalVectorInputPrices2 = new BigDecimalVector(Arrays.asList(
                BigDecimal.valueOf(11L),
                BigDecimal.valueOf(22L),
                BigDecimal.valueOf(33L)
        ));

        final Evaluations<BigDecimalVector> evaluations = new Evaluations<>();
        evaluations.putIfAbsentValue(Names.InputPrices2, bigDecimalVectorInputPrices2);
        final BigDecimalSummaryStatistics bigDecimalSummaryStatisticsProces2
                = new VectorsSummaryStatistics()
                        .createBigDecimalSummaryStatistics(evaluations.getValue(Names.InputPrices2));
        assertEquals(3L, bigDecimalSummaryStatisticsProces2.getCount());
        assertEquals(BigDecimal.valueOf(11L), bigDecimalSummaryStatisticsProces2.getMin());
        assertEquals(BigDecimal.valueOf(33L), bigDecimalSummaryStatisticsProces2.getMax());
        assertEquals(BigDecimal.valueOf(66L), bigDecimalSummaryStatisticsProces2.getSum());
        assertEquals(BigDecimal.valueOf(22L), bigDecimalSummaryStatisticsProces2.getAverage());
    }
}
