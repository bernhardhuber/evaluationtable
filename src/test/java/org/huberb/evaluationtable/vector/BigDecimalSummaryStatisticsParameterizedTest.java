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
package org.huberb.evaluationtable.vector;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.huberb.evaluationtable.vector.BigDecimalSummaryStatistics;
import org.huberb.evaluationtable.vector.BigDecimalVector;
import org.huberb.evaluationtable.vector.VectorsSummaryStatistics;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author berni3
 */
public class BigDecimalSummaryStatisticsParameterizedTest {

    @ParameterizedTest
    @MethodSource("m1")
    public void testX(BigDecimalVector in, Long count, BigDecimal min, BigDecimal max, BigDecimal sum, BigDecimal avg) {
        final BigDecimalSummaryStatistics bigDecimalSummaryStatistics
                = new VectorsSummaryStatistics()
                        .createBigDecimalSummaryStatistics(in);
        assertEquals(count, bigDecimalSummaryStatistics.getCount());
        assertEquals(min, bigDecimalSummaryStatistics.getMin());
        assertEquals(max, bigDecimalSummaryStatistics.getMax());
        assertEquals(sum, bigDecimalSummaryStatistics.getSum());
        assertEquals(avg, bigDecimalSummaryStatistics.getAverage());

    }

    public static Stream<Object[]> m1() {
        List<Object[]> l = new ArrayList<>();
        l.add(new Object[]{
            new BigDecimalVector(Arrays.asList(
            BigDecimal.valueOf(11L), BigDecimal.valueOf(22L), BigDecimal.valueOf(33L))),
            Long.valueOf(3L),
            BigDecimal.valueOf(11L), BigDecimal.valueOf(33L),
            BigDecimal.valueOf(66L), BigDecimal.valueOf(22L)
        });
        l.add(new Object[]{
            new BigDecimalVector(Arrays.asList(
            BigDecimal.valueOf(-100L), BigDecimal.valueOf(-200L), BigDecimal.valueOf(-300L),
            BigDecimal.valueOf(100L), BigDecimal.valueOf(200L), BigDecimal.valueOf(300L))),
            Long.valueOf(6L),
            BigDecimal.valueOf(-300L), BigDecimal.valueOf(300L),
            BigDecimal.valueOf(0L), BigDecimal.valueOf(0L)
        });

        Stream< Object[]> ret = l.stream();
        return ret;
    }
}
