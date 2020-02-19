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
package org.huberb.evaluationtable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author berni3
 */
public class EvaluationTable {

    public static class Evaluations<Value> {

        private final Map<Enum, Value> m;

        public Evaluations() {
            this.m = new HashMap<>();
        }

        void putValue(Enum key, Value v) {
            m.put(key, v);
        }

        void putIfAbsentValue(Enum key, Value v) {
            m.putIfAbsent(key, v);
        }

        Value get(Enum key) {
            return m.get(key);
        }

        Value evaluate(Enum key, Function<Value, Value> f) {
            final Value v = m.get(key);
            final Value v2 = f.apply(v);
            return v2;
        }

        Value evaluateTo(Enum srcKey, Enum dstKey, Function<Value, Value> f) {
            final Value result = evaluate(srcKey, f);
            m.put(dstKey, result);
            return result;
        }
    }
}
