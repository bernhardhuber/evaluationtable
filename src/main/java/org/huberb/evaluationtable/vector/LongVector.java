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
class LongVector extends NumberVector<Long> {

    public LongVector(Long n) {
        super(n);
    }

    public LongVector(List<Long> l) {
        super(l);
    }

    @Override
    public NumberVector<Long> add(Long n0) {
        List<Long> r = new ArrayList<>();
        for (Long v : vector()) {
            r.add(v + n0);
        }
        return new LongVector(r);
    }

    @Override
    public NumberVector<Long> sub(Long n0) {
        List<Long> r = new ArrayList<>();
        for (Long v : vector()) {
            r.add(v - n0);
        }
        return new LongVector(r);
    }

    @Override
    public NumberVector<Long> mul(Long n0) {
        List<Long> r = new ArrayList<>();
        for (Long v : vector()) {
            r.add(v * n0);
        }
        return new LongVector(r);
    }

    @Override
    public NumberVector<Long> div(Long n0) {
        List<Long> r = new ArrayList<>();
        for (Long v : vector()) {
            r.add(v / n0);
        }
        return new LongVector(r);
    }

    @Override
    public NumberVector<Long> neg() {
        List<Long> r = new ArrayList<>();
        for (Long v : vector()) {
            r.add(-v);
        }
        return new LongVector(r);
    }

    @Override
    public Long sum() {
        long r = 0L;
        for (Long v : vector()) {
            r += v;
        }
        return r;
    }

    @Override
    public Long min() {
        long r = scalar0();
        for (Long v : vector()) {
            r = Math.min(r, v);
        }
        return r;

    }

    @Override
    public Long max() {
        long r = scalar0();
        for (Long v : vector()) {
            r = Math.max(r, v);
        }
        return r;
    }

}
