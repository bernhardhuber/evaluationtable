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
import org.apache.commons.jexl3.JexlInfo;
import org.apache.commons.jexl3.JexlScript;
import org.apache.commons.jexl3.MapContext;
import org.huberb.evaluationtable.jexl.BusinessObjects.BusinessObject;
import org.huberb.evaluationtable.jexl.BusinessObjects.TaxManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author berni3
 */
public class JexlEngineScriptTest {

    @Test
    public void testCalculateTax_createExpression_with_BusinessObject_and_TaxManager() {
        final JexlEngine jexl = new JexlBuilder()
                .cache(512)
                .strict(true)
                .silent(false)
                .debug(true)
                .create();
        final JexlInfo jexlInfo = jexl.createInfo();

        // Assuming we have a JexlEngine instance initialized in our class named 'jexl':
        // Create an expression object for our calculation
        final String calculateTax = ""
                + "var G1 = businessObject.totalSales;\n"
                + "var G2 = taxManager.taxCredit;\n"
                + "var G3 = businessObject.intercompanyPayments;\n"
                + "var G4 = -taxManager.allowances;\n"
                + "\n"
                + "return ((G1 + G2 + G3) * 0.1) + G4;\n"; // taxManager.getTaxCalc(); //e.g. "((G1 + G2 + G3) * 0.1) + G4";

        final JexlScript e = jexl.createScript(jexlInfo, calculateTax);

        final BusinessObject businessObject = new BusinessObject(); //businessObject.getTotalSales());
        final TaxManager taxManager = new TaxManager();
        // populate the context
        final JexlContext context = new MapContext();
        context.set("businessObject", businessObject);
        context.set("taxManager", taxManager); //businessObject.getTotalSales());
        // ...

        // work it out
        final Number result = (Number) e.execute(context);
        assertEquals(1850.0d, result.doubleValue(), 0.0001d);
    }

}
