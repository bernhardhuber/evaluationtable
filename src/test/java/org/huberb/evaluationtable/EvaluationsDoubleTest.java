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
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author berni3
 */
public class EvaluationsDoubleTest {

    public EvaluationsDoubleTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    static class EvaluationsDouble extends Evaluations<Double> {
    }

    enum EvaluationsEnum {
        a1, a2, a3,
        b1, b2, b3;
    }

    @Test
    public void testSimpleUsage() {
        EvaluationsDouble instance = new EvaluationsDouble();
        instance.putIfAbsentValue(EvaluationsEnum.a1, 1.0d);
        double a1Mul2 = instance.evaluate(EvaluationsEnum.a1, (d) -> 2 * d);
        double a1plu1_Mul2 = instance.evaluateTo(EvaluationsEnum.a1, EvaluationsEnum.a2, (d) -> (d + 1) * 2);

        assertEquals(1.0d, instance.get(EvaluationsEnum.a1), 0.001d);
        assertEquals(2.0d, a1Mul2, 0.001d);
        assertEquals(4.0d, instance.get(EvaluationsEnum.a2), 0.001d);
        assertEquals(4.0d, a1plu1_Mul2, 0.001d);
    }
}
