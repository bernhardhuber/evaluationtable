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
 * Holder class of inner classes defining some sort of evaluation of values.
 *
 * @author berni3
 */
public class EvaluationTable {

    /**
     * A simple map of values.
     * <p>
     * The keys of the map are some enums.
     *
     * @param <Value>
     */
    public static class Evaluations<Value> {

        private final Map<Enum, Value> m;

        /**
         * Create an empty map.
         */
        public Evaluations() {
            this.m = new HashMap<>();
        }

        /**
         * Put a value into the map.
         * <p>
         * Note: If a value already exists, it is overwritten.
         *
         * @param key
         * @param v
         */
        void putValue(Enum key, Value v) {
            m.put(key, v);
        }

        /**
         * Put a value into the map, only if no value exists already.
         *
         * @param key
         * @param v
         */
        void putIfAbsentValue(Enum key, Value v) {
            m.putIfAbsent(key, v);
        }

        /**
         * Retrieve a value.
         * <p>
         * Note: Returns null if there is no value yet.
         *
         * @param key
         * @return
         */
        Value get(Enum key) {
            return m.get(key);
        }

        /**
         * Evaluate a new value from a value stored in the map.
         *
         * @param key
         * @param f
         * @return
         */
        Value evaluate(Enum key, Function<Value, Value> f) {
            final Value v = m.get(key);
            final Value v2 = f.apply(v);
            return v2;
        }

        /**
         * Evaluate a value and store it in the map.
         *
         * @param srcKey
         * @param dstKey
         * @param f
         * @return
         */
        Value evaluateTo(Enum srcKey, Enum dstKey, Function<Value, Value> f) {
            final Value result = evaluate(srcKey, f);
            m.put(dstKey, result);
            return result;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.getClass().getName())
                    .append('@')
                    .append(Integer.toHexString(System.identityHashCode(this)))
                    .append(' ')
                    .append(this.m);

            return sb.toString();
        }
    }
}
