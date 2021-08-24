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
package org.huberb.evaluationtable.vector;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author berni3
 */
public class BigDecimalSummaryStatisticsTest {

    /**
     * Test of accept method, of class BigDecimalSummaryStatistics.
     */
    @Test
    public void testAccept() {
        final BigDecimal value0 = BigDecimal.ZERO;
        final BigDecimal value1 = BigDecimal.ONE;
        final BigDecimal value10 = BigDecimal.TEN;
        final BigDecimalSummaryStatistics instance = new BigDecimalSummaryStatistics();
        instance.accept(value0);
        instance.accept(value1);
        instance.accept(value1);
        instance.accept(value10);
        assertEquals(3L, instance.getAverage().longValue());
        assertEquals(4L, instance.getCount());
        assertEquals(10L, instance.getMax().longValue());
        assertEquals(0L, instance.getMin().longValue());
        assertEquals(12L, instance.getSum().longValue());
    }

    /**
     * Test of accept method, of class BigDecimalSummaryStatistics.
     */
    @Test
    public void testAccept_andThen() {
        final BigDecimal value0 = BigDecimal.ZERO;
        final BigDecimal value1 = BigDecimal.ONE;
        final BigDecimal value10 = BigDecimal.TEN;
        final BigDecimalSummaryStatistics instance = new BigDecimalSummaryStatistics();
        final BigDecimalSummaryStatistics instanceOther = new BigDecimalSummaryStatistics();
        instance.andThen(instanceOther).accept(value0);
        instance.andThen(instanceOther).accept(value1);
        instance.andThen(instanceOther).accept(value1);
        instance.andThen(instanceOther).accept(value10);
        //---
        assertEquals(3L, instance.getAverage().longValue());
        assertEquals(4L, instance.getCount());
        assertEquals(10L, instance.getMax().longValue());
        assertEquals(0L, instance.getMin().longValue());
        assertEquals(12L, instance.getSum().longValue());
        //---
        assertEquals(3L, instanceOther.getAverage().longValue());
        assertEquals(4L, instanceOther.getCount());
        assertEquals(10L, instanceOther.getMax().longValue());
        assertEquals(0L, instanceOther.getMin().longValue());
        assertEquals(12L, instanceOther.getSum().longValue());
    }

    /**
     * Test of toString method, of class BigDecimalSummaryStatistics.
     */
    @Test
    public void testToString() {
        final BigDecimal value0 = BigDecimal.ZERO;
        final BigDecimal value1 = BigDecimal.ONE;
        final BigDecimal value10 = BigDecimal.TEN;
        final BigDecimalSummaryStatistics instance = new BigDecimalSummaryStatistics();
        instance.accept(value0);
        instance.accept(value1);
        instance.accept(value1);
        instance.accept(value10);
        String result = instance.toString();
        assertEquals("BigDecimalSummaryStatistics{count=4, sum=12.000000, min=0.000000, average=3.000000, max=10.000000}", result);
    }

    @Test
    public void testForEachAccept() {
        final BigDecimal value0 = BigDecimal.ZERO;
        final BigDecimal value1 = BigDecimal.ONE;
        final BigDecimal value10 = BigDecimal.TEN;
        final List<BigDecimal> bigDecimalList = Arrays.asList(value0, value1, value1, value10);

        final BigDecimalSummaryStatistics instance = new BigDecimalSummaryStatistics();

        bigDecimalList.stream().forEach((bd) -> instance.accept(bd));
        String result = instance.toString();
        assertEquals("BigDecimalSummaryStatistics{count=4, sum=12.000000, min=0.000000, average=3.000000, max=10.000000}", result);
    }

    @Test
    public void testForEachAccept_neg_and_plus() {
        final List<BigDecimal> bigDecimalList = new ArrayList<>();
        for (int i = 0; i <= 99; i++) {
            bigDecimalList.add(BigDecimal.valueOf(i));
            bigDecimalList.add(BigDecimal.valueOf(-i));
        }

        final BigDecimalSummaryStatistics instance = new BigDecimalSummaryStatistics();

        bigDecimalList.stream().forEach((bd) -> instance.accept(bd));
        assertEquals(BigDecimal.ZERO, instance.getAverage());
        assertEquals(200, instance.getCount());
        assertEquals(BigDecimal.valueOf(99), instance.getMax());
        assertEquals(BigDecimal.valueOf(-99), instance.getMin());
        assertEquals(BigDecimal.valueOf(0), instance.getSum());
    }
}
