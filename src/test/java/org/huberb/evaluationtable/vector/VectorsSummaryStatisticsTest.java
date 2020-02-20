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
import java.math.BigInteger;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author berni3
 */
public class VectorsSummaryStatisticsTest {

    public VectorsSummaryStatisticsTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createIntSummaryStatistics method, of class
     * VectorsSummaryStatistics.
     */
    @Test
    public void testCreateIntSummaryStatistics() {
        IntegerVector iv = new IntegerVector(Arrays.asList(1, 2, 3, 4, 5, -5, -4, -3, -2, -1));
        VectorsSummaryStatistics instance = new VectorsSummaryStatistics();
        IntSummaryStatistics result = instance.createIntSummaryStatistics(iv);
        assertEquals(0.0d, result.getAverage(), 0.001d);
        assertEquals(10, result.getCount());
        assertEquals(5, result.getMax());
        assertEquals(-5, result.getMin());
        assertEquals(0, result.getSum());
    }

    /**
     * Test of createLongSummaryStatistics method, of class
     * VectorsSummaryStatistics.
     */
    @Test
    public void testCreateLongSummaryStatistics() {
        LongVector lv = new LongVector(Arrays.asList(1L, 2L, 3L, 4L, 5L, -5L, -4L, -3L, -2L, -1L));
        VectorsSummaryStatistics instance = new VectorsSummaryStatistics();
        LongSummaryStatistics result = instance.createLongSummaryStatistics(lv);
        assertEquals(0.0d, result.getAverage(), 0.001d);
        assertEquals(10L, result.getCount());
        assertEquals(5L, result.getMax());
        assertEquals(-5L, result.getMin());
        assertEquals(0L, result.getSum());
    }

    /**
     * Test of createDoubleSummaryStatistics method, of class
     * VectorsSummaryStatistics.
     */
    @Test
    public void testCreateDoubleSummaryStatistics() {
        DoubleVector lv = new DoubleVector(Arrays.asList(1.0d, 2.0d, 3.0d, 4.0d, 5.0d, -5.0d, -4.0d, -3.0d, -2.0d, -1.0d));
        VectorsSummaryStatistics instance = new VectorsSummaryStatistics();
        DoubleSummaryStatistics result = instance.createDoubleSummaryStatistics(lv);
        assertEquals(0.0d, result.getAverage(), 0.001d);
        assertEquals(10L, result.getCount());
        assertEquals(5.0d, result.getMax(), 0.001d);
        assertEquals(-5.0d, result.getMin(), 0.001d);
        assertEquals(0.0d, result.getSum(), 0.001d);

    }

    /**
     * Test of createBigIntegerSummaryStatistics method, of class
     * VectorsSummaryStatistics.
     */
    @Test
    public void testCreateBigIntegerSummaryStatistics() {
        BigIntegerVector bdv = new BigIntegerVector(Arrays.asList(
                BigInteger.valueOf(1L), BigInteger.valueOf(2L), BigInteger.valueOf(3L), BigInteger.valueOf(4L), BigInteger.valueOf(5L),
                BigInteger.valueOf(-5L), BigInteger.valueOf(-4L), BigInteger.valueOf(-3L), BigInteger.valueOf(-2L), BigInteger.valueOf(-1L)
        ));
        VectorsSummaryStatistics instance = new VectorsSummaryStatistics();
        BigIntegerSummaryStatistics result = instance.createBigIntegerSummaryStatistics(bdv);
        assertEquals(BigDecimal.ZERO, result.getAverage());
        assertEquals(10L, result.getCount());
        assertEquals(BigInteger.valueOf(5L), result.getMax());
        assertEquals(BigInteger.valueOf(-5L), result.getMin());
        assertEquals(BigInteger.ZERO, result.getSum());
    }

    /**
     * Test of createBigDecimalSummaryStatistics method, of class
     * VectorsSummaryStatistics.
     */
    @Test
    public void testCreateBigDecimalSummaryStatistics() {
        BigDecimalVector bdv = new BigDecimalVector(Arrays.asList(
                BigDecimal.valueOf(1L), BigDecimal.valueOf(2L), BigDecimal.valueOf(3L), BigDecimal.valueOf(4L), BigDecimal.valueOf(5L),
                BigDecimal.valueOf(-5L), BigDecimal.valueOf(-4L), BigDecimal.valueOf(-3L), BigDecimal.valueOf(-2L), BigDecimal.valueOf(-1L)
        ));
        VectorsSummaryStatistics instance = new VectorsSummaryStatistics();
        BigDecimalSummaryStatistics result = instance.createBigDecimalSummaryStatistics(bdv);
        assertEquals(BigDecimal.ZERO, result.getAverage());
        assertEquals(10L, result.getCount());
        assertEquals(BigDecimal.valueOf(5L), result.getMax());
        assertEquals(BigDecimal.valueOf(-5L), result.getMin());
        assertEquals(BigDecimal.ZERO, result.getSum());
    }

}
