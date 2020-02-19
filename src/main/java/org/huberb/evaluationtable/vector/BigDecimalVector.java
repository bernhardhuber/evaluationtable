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
import java.util.List;

/**
 *
 * @author berni3
 */
public class BigDecimalVector extends NumberVector<BigDecimal> {

    public BigDecimalVector(BigDecimal n) {
        super(n);
    }

    public BigDecimalVector(List<BigDecimal> l) {
        super(l);
    }

    @Override
    public NumberVector<BigDecimal> add(BigDecimal n0) {
        List<BigDecimal> r = new ArrayList<>();
        for (BigDecimal v : vector()) {
            r.add(v.add(n0));
        }
        return new BigDecimalVector(r);
    }

    @Override
    public NumberVector<BigDecimal> sub(BigDecimal n0) {
        List<BigDecimal> r = new ArrayList<>();
        for (BigDecimal v : vector()) {
            r.add(v.subtract(n0));
        }
        return new BigDecimalVector(r);
    }

    @Override
    public NumberVector<BigDecimal> mul(BigDecimal n0) {
        List<BigDecimal> r = new ArrayList<>();
        for (BigDecimal v : vector()) {
            r.add(v.multiply(n0));
        }
        return new BigDecimalVector(r);
    }

    @Override
    public NumberVector<BigDecimal> div(BigDecimal n0) {
        List<BigDecimal> r = new ArrayList<>();
        for (BigDecimal v : vector()) {
            r.add(v.divide(n0));
        }
        return new BigDecimalVector(r);
    }

    @Override
    public NumberVector<BigDecimal> neg() {
        List<BigDecimal> r = new ArrayList<>();
        for (BigDecimal v : vector()) {
            r.add(v.negate());
        }
        return new BigDecimalVector(r);
    }

    public BigDecimal sum() {
        BigDecimal r = BigDecimal.ZERO;
        for (BigDecimal v : vector()) {
            r = r.add(v);
        }
        return r;
    }

    @Override
    public BigDecimal min() {
        BigDecimal r = scalar0();
        for (BigDecimal v : vector()) {
            final int compareValue = r.compareTo(v);
            if (compareValue > 0) {
                r = v;
            }
        }
        return r;
    }

    @Override
    public BigDecimal max() {
        BigDecimal r = scalar0();
        for (BigDecimal v : vector()) {
            final int compareValue = r.compareTo(v);
            if (compareValue < 0) {
                r = v;
            }
        }
        return r;
    }

}
