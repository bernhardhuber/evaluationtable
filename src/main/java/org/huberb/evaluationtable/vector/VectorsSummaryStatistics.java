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
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;

/**
 *
 * @author berni3
 */
public class VectorsSummaryStatistics {

    public IntSummaryStatistics createIntSummaryStatistics(IntegerVector iv) {
        final IntSummaryStatistics iss = new IntSummaryStatistics();
        for (Integer v : iv.vector()) {
            iss.accept(v);
        }
        return iss;
    }

    public LongSummaryStatistics createLongSummaryStatistics(LongVector lv) {
        final LongSummaryStatistics lss = new LongSummaryStatistics();
        for (Long v : lv.vector()) {
            lss.accept(v);
        }
        return lss;
    }

    public DoubleSummaryStatistics createDoubleSummaryStatistics(DoubleVector lv) {
        final DoubleSummaryStatistics dss = new DoubleSummaryStatistics();
        for (Double v : lv.vector()) {
            dss.accept(v);
        }
        return dss;
    }

    public BigIntegerSummaryStatistics createBigIntegerSummaryStatistics(BigIntegerVector bdv) {
        BigIntegerSummaryStatistics biss = new BigIntegerSummaryStatistics();
        for (BigInteger v : bdv.vector()) {
            biss.accept(v);
        }
        return biss;
    }

    public BigDecimalSummaryStatistics createBigDecimalSummaryStatistics(BigDecimalVector bdv) {
        BigDecimalSummaryStatistics bdss = new BigDecimalSummaryStatistics();
        for (BigDecimal v : bdv.vector()) {
            bdss.accept(v);
        }
        return bdss;
    }
}
