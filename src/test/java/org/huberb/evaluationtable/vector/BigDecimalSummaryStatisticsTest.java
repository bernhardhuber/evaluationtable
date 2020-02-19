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
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author berni3
 */
public class BigDecimalSummaryStatisticsTest {

    public BigDecimalSummaryStatisticsTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of accept method, of class BigDecimalSummaryStatistics.
     */
    @Test
    public void testAccept() {
        BigDecimal value0 = BigDecimal.ZERO;
        BigDecimal value1 = BigDecimal.ONE;
        BigDecimal value10 = BigDecimal.TEN;
        BigDecimalSummaryStatistics instance = new BigDecimalSummaryStatistics();
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
     * Test of toString method, of class BigDecimalSummaryStatistics.
     */
    @Test
    public void testToString() {
        BigDecimal value0 = BigDecimal.ZERO;
        BigDecimal value1 = BigDecimal.ONE;
        BigDecimal value10 = BigDecimal.TEN;
        BigDecimalSummaryStatistics instance = new BigDecimalSummaryStatistics();
        instance.accept(value0);
        instance.accept(value1);
        instance.accept(value1);
        instance.accept(value10);
        String result = instance.toString();
        assertEquals("BigDecimalSummaryStatistics{count=4, sum=12.000000, min=0.000000, average=3.000000, max=10.000000}", result);
    }

    @Test
    public void testForEachAccept() {
        BigDecimal value0 = BigDecimal.ZERO;
        BigDecimal value1 = BigDecimal.ONE;
        BigDecimal value10 = BigDecimal.TEN;
        List<BigDecimal> bigDecimalList = Arrays.asList(value0, value1, value1, value10);

        BigDecimalSummaryStatistics instance = new BigDecimalSummaryStatistics();

        bigDecimalList.stream().forEach((bd) -> instance.accept(bd));
        String result = instance.toString();
        assertEquals("BigDecimalSummaryStatistics{count=4, sum=12.000000, min=0.000000, average=3.000000, max=10.000000}", result);
    }
}
