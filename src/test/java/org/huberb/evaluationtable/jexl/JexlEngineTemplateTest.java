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

import java.io.IOException;
import java.io.StringWriter;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JxltEngine.Template;
import org.apache.commons.jexl3.MapContext;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author berni3
 */
public class JexlEngineTemplateTest {

    @Test
    public void testCalculateTax_Template_1() throws IOException {
        final JexlEngine jexl = new JexlBuilder()
                .cache(512)
                .strict(true)
                .silent(false)
                .create();
        // Assuming we have a JexlEngine instance initialized in our class named 'jexl':
        // Create an expression object for our calculation
        final String calculateTax = "((G1 + G2 + G3) * 0.1) + G4"; // taxManager.getTaxCalc(); //e.g. "((G1 + G2 + G3) * 0.1) + G4";
        final Template t = jexl.createJxltEngine(false).createTemplate(""
                + "Calculate Tax"
                + "\n"
                + "TotalSales: #{G1}, "
                + "TaxCredit: #{G2}, "
                + "InterPayments: #{G3}, "
                + "Allowances: #{G4},"
                + "\n"
                + "CalculatedTax: #{" + calculateTax + "}");
        // populate the context
        final JexlContext context = new MapContext() {
            {
                set("G1", 12000.0d); //businessObject.getTotalSales());
                set("G2", 5000.0d); //taxManager.getTaxCredit(businessObject.getYear()));
                set("G3", 2000.0d); //businessObject.getIntercompanyPayments());
                set("G4", -50.0d); //-taxManager.getAllowances());
            }
        };
        // ...

        // work it out
        try (StringWriter writer = new StringWriter()) {
            t.evaluate(context, writer);
            writer.flush();
            assertEquals("Calculate Tax"
                    + "\n"
                    + "TotalSales: 12000.0, "
                    + "TaxCredit: 5000.0, "
                    + "InterPayments: 2000.0, "
                    + "Allowances: -50.0,"
                    + "\n"
                    + "CalculatedTax: 1850.0", writer.toString());
        }
    }

}
