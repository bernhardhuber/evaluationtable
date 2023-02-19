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
package org.huberb.evaluationtable.jexl;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author berni3
 */
public class JexlEngineTest {

    @Test
    public void testCalculateTax_createExpression() {
        final JexlEngine jexl = new JexlBuilder()
                .cache(512)
                .strict(true)
                .silent(false)
                .create();

        // Assuming we have a JexlEngine instance initialized in our class named 'jexl':
        // Create an expression object for our calculation
        // taxManager.getTaxCalc(); //e.g. "((G1 + G2 + G3) * 0.1) + G4";
        final String calculateTax = "((G1 + G2 + G3) * 0.1) + G4";
        final JexlExpression e = jexl.createExpression(calculateTax);

        // populate the context
        final JexlContext context = new MapContext() {
            {
                set("G1", 12000); //businessObject.getTotalSales());
                set("G2", 5000); //taxManager.getTaxCredit(businessObject.getYear()));
                set("G3", 2000); //businessObject.getIntercompanyPayments());
                set("G4", -50); //-taxManager.getAllowances());
            }
        };
        // ...

        // work it out
        Number result = (Number) e.evaluate(context);
        assertEquals(1850.0d, result.doubleValue(), 0.0001d);
    }

}
