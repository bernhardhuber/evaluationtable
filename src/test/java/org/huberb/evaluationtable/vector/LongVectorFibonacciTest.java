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

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author berni3
 */
public class LongVectorFibonacciTest {

    @Test
    public void testFibonacci_1_10() {
        final List l = Arrays.asList(0L, 1L
        );
        final LongVector instance = new LongVector(l);

        for (int i = 1; i <= 10; i++) {
            long f = instance.vector().get(i) + instance.vector().get(i - 1);
            instance.vector().add(f);
        }

        assertEquals("[0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89]", instance.vector().toString());
    }
}
