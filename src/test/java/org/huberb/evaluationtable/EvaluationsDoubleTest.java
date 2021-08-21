package org.huberb.evaluationtable;

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
import org.huberb.evaluationtable.EvaluationTable.Evaluations;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author berni3
 */
public class EvaluationsDoubleTest {

    private static final double DELTA = 0.000001d;

    static class EvaluationsDouble extends Evaluations<Double> {
    }

    enum EvaluationsEnum {
        A1_INPUT, A2OUTPUT_A1_PLUS1_MUL2,
        B1_INPUT, B2_INPUT, B3_SUM_B1_B2;
    }

    @Test
    public void testSimpleUsage_A() {
        final EvaluationsDouble instance = new EvaluationsDouble();
        instance.putIfAbsentValue(EvaluationsEnum.A1_INPUT, 1.0d);
        final double a1Mul2 = instance.evaluate(EvaluationsEnum.A1_INPUT, (d) -> 2 * d);
        final double a1plu1_Mul2 = instance.evaluateTo(EvaluationsEnum.A1_INPUT, EvaluationsEnum.A2OUTPUT_A1_PLUS1_MUL2, (d) -> (d + 1) * 2);

        assertEquals(1.0d, instance.get(EvaluationsEnum.A1_INPUT), DELTA);
        assertEquals(2.0d, a1Mul2, 0.001d);
        assertEquals(4.0d, instance.get(EvaluationsEnum.A2OUTPUT_A1_PLUS1_MUL2), DELTA);
        assertEquals(4.0d, a1plu1_Mul2, 0.001d);

        final String m = instance.toString();
        assertTrue(m, instance.toString().contains("A1_INPUT=1.0"));
        assertTrue(m, instance.toString().contains("A2OUTPUT_A1_PLUS1_MUL2=4.0"));
    }

    @Test
    public void testSimpleUsage_B() {
        final EvaluationsDouble instance = new EvaluationsDouble();
        instance.putIfAbsentValue(EvaluationsEnum.B1_INPUT, 1.0d);
        instance.putIfAbsentValue(EvaluationsEnum.B2_INPUT, 2.0d);
        final double b3SumB1B2 = instance.get(EvaluationsEnum.B1_INPUT) + instance.get(EvaluationsEnum.B2_INPUT);
        instance.putIfAbsentValue(EvaluationsEnum.B3_SUM_B1_B2, b3SumB1B2);

        assertEquals(1.0d, instance.get(EvaluationsEnum.B1_INPUT), DELTA);
        assertEquals(2.0d, instance.get(EvaluationsEnum.B2_INPUT), DELTA);
        assertEquals(3.0d, b3SumB1B2, DELTA);
        assertEquals(3.0d, instance.get(EvaluationsEnum.B3_SUM_B1_B2), DELTA);

        final String m = instance.toString();
        assertTrue(m, instance.toString().contains("B1_INPUT=1.0"));
        assertTrue(m, instance.toString().contains("B2_INPUT=2.0"));
        assertTrue(m, instance.toString().contains("B3_SUM_B1_B2=3.0"));
    }

}
