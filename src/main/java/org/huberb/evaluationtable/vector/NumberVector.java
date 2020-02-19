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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author berni3
 * @param <N>
 */
public abstract class NumberVector<N extends Number> {

    private final List<N> vector;

    protected NumberVector(N n) {
        this(Arrays.asList(n));
    }

    protected NumberVector(List<N> l) {
        this.vector = Collections.unmodifiableList(l);
    }

    public N scalar0() {
        return vector.get(0);
    }

    public List<N> vector() {
        return this.vector;
    }

    public long count() {
        return this.vector.size();
    }

    protected abstract NumberVector<N> add(N n0);

    protected abstract NumberVector<N> sub(N n0);

    protected abstract NumberVector<N> mul(N n0);

    protected abstract NumberVector<N> div(N n0);

    protected abstract NumberVector<N> neg();

    protected abstract N sum();

    protected abstract N min();

    protected abstract N max();

}
