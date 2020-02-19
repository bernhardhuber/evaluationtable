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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author berni3
 */
class BigIntegerVector extends NumberVector<BigInteger> {

    public BigIntegerVector(BigInteger n) {
        super(n);
    }

    public BigIntegerVector(List<BigInteger> l) {
        super(l);
    }

    @Override
    public NumberVector<BigInteger> add(BigInteger n0) {
        List<BigInteger> r = new ArrayList<>();
        for (BigInteger v : vector()) {
            r.add(v.add(n0));
        }
        return new BigIntegerVector(r);
    }

    @Override
    public NumberVector<BigInteger> sub(BigInteger n0) {
        List<BigInteger> r = new ArrayList<>();
        for (BigInteger v : vector()) {
            r.add(v.subtract(n0));
        }
        return new BigIntegerVector(r);
    }

    @Override
    public NumberVector<BigInteger> mul(BigInteger n0) {
        List<BigInteger> r = new ArrayList<>();
        for (BigInteger v : vector()) {
            r.add(v.multiply(n0));
        }
        return new BigIntegerVector(r);
    }

    @Override
    public NumberVector<BigInteger> div(BigInteger n0) {
        List<BigInteger> r = new ArrayList<>();
        for (BigInteger v : vector()) {
            r.add(v.divide(n0));
        }
        return new BigIntegerVector(r);
    }

    @Override
    public NumberVector<BigInteger> neg() {
        List<BigInteger> r = new ArrayList<>();
        for (BigInteger v : vector()) {
            r.add(v.negate());
        }
        return new BigIntegerVector(r);
    }

    public BigInteger sum() {
        BigInteger r = BigInteger.ZERO;
        for (BigInteger v : vector()) {
            r = r.add(v);
        }
        return r;
    }

    @Override
    public BigInteger min() {
        BigInteger r = scalar0();
        for (BigInteger v : vector()) {
            final int compareValue = r.compareTo(v);
            if (compareValue > 0) {
                r = v;
            }
        }
        return r;
    }

    @Override
    public BigInteger max() {
        BigInteger r = scalar0();
        for (BigInteger v : vector()) {
            final int compareValue = r.compareTo(v);
            if (compareValue < 0) {
                r = v;
            }
        }
        return r;
    }

}
