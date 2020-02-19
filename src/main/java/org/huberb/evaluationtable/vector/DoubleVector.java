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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author berni3
 */
class DoubleVector extends NumberVector<Double> {

    public DoubleVector(Double n) {
        super(n);
    }

    public DoubleVector(List<Double> l) {
        super(l);
    }

    @Override
    public NumberVector<Double> add(Double n0) {
        List<Double> r = new ArrayList<>();
        for (Double v : vector()) {
            r.add(v + n0);
        }
        return new DoubleVector(r);
    }

    @Override
    public NumberVector<Double> sub(Double n0) {
        List<Double> r = new ArrayList<>();
        for (Double v : vector()) {
            r.add(v - n0);
        }
        return new DoubleVector(r);
    }

    @Override
    public NumberVector<Double> mul(Double n0) {
        List<Double> r = new ArrayList<>();
        for (Double v : vector()) {
            r.add(v * n0);
        }
        return new DoubleVector(r);
    }

    @Override
    public NumberVector<Double> div(Double n0) {
        List<Double> r = new ArrayList<>();
        for (Double v : vector()) {
            r.add(v / n0);
        }
        return new DoubleVector(r);
    }

    @Override
    public NumberVector<Double> neg() {
        List<Double> r = new ArrayList<>();
        for (Double v : vector()) {
            r.add(-v);
        }
        return new DoubleVector(r);
    }

    @Override
    public Double sum() {
        double r = 0.0d;
        for (Double v : vector()) {
            r += v;
        }
        return r;
    }

    @Override
    public Double min() {
        double r = scalar0();
        for (Double v : vector()) {
            r = Math.min(r, v);
        }
        return r;

    }

    @Override
    public Double max() {
        double r = scalar0();
        for (Double v : vector()) {
            r = Math.max(r, v);
        }
        return r;
    }
}
