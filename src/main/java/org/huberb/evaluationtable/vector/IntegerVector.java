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
import java.util.IntSummaryStatistics;
import java.util.List;

/**
 *
 * @author berni3
 */
class IntegerVector extends NumberVector<Integer> {

    public IntegerVector(Integer n) {
        super(n);
    }

    public IntegerVector(List<Integer> l) {
        super(l);
    }

    @Override
    public NumberVector<Integer> add(Integer n0) {
        List<Integer> r = new ArrayList<>();
        for (Integer v : vector()) {
            r.add(v + n0);
        }
        return new IntegerVector(r);
    }

    @Override
    public NumberVector<Integer> sub(Integer n0) {
        List<Integer> r = new ArrayList<>();
        for (Integer v : vector()) {
            r.add(v - n0);
        }
        return new IntegerVector(r);
    }

    @Override
    public NumberVector<Integer> mul(Integer n0) {
        List<Integer> r = new ArrayList<>();
        for (Integer v : vector()) {
            r.add(v * n0);
        }
        return new IntegerVector(r);
    }

    @Override
    public NumberVector<Integer> div(Integer n0) {
        List<Integer> r = new ArrayList<>();
        for (Integer v : vector()) {
            r.add(v / n0);
        }
        return new IntegerVector(r);
    }

    @Override
    public NumberVector<Integer> neg() {
        List<Integer> r = new ArrayList<>();
        for (Integer v : vector()) {
            r.add(-v);
        }
        return new IntegerVector(r);
    }

    public Integer sum() {
        int r = 0;
        for (Integer v : vector()) {
            r += v;
        }
        return r;
    }

    @Override
    public Integer min() {
        int r = scalar0();
        for (Integer v : vector()) {
            r = Math.min(r, v);
        }
        return r;

    }

    @Override
    public Integer max() {
        int r = scalar0();
        for (Integer v : vector()) {
            r = Math.max(r, v);
        }
        return r;
    }


}
