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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author berni3
 */
public class EvaluationTable {

    static class Evaluations<Value> {

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
            Value v = m.get(key);
            Value v2 = f.apply(v);
            return v2;
        }

        Value evaluateTo(Enum srcKey, Enum dstKey, Function<Value, Value> f) {
            Value result = evaluate(srcKey, f);
            m.put(dstKey, result);
            return result;
        }
    }

    static class NBigDecimalValue {

        final List<BigDecimal> l;

        static NBigDecimalValue fromNBigDecimalValue(NBigDecimalValue p) {
            List<BigDecimal> bds = new ArrayList<>();
            bds.addAll(p.l.stream().collect(Collectors.toList()));
            return new NBigDecimalValue(Collections.unmodifiableList(bds));
        }

        static NBigDecimalValue fromNBigDecimalValue(List<NBigDecimalValue> p) {
            List<BigDecimal> bds = new ArrayList<>();
            for (NBigDecimalValue bdv : p) {
                bds.addAll(bdv.l.stream().collect(Collectors.toList()));
            }
            return new NBigDecimalValue(Collections.unmodifiableList(bds));
        }

        public NBigDecimalValue(BigDecimal p) {
            l = Arrays.asList(p);
        }

        public NBigDecimalValue(List<BigDecimal> l) {
            this.l = Collections.unmodifiableList(l);
        }

        BigDecimal bigDecimal() {
            return l.get(0);
        }

        int count() {
            return l.size();
        }

        BigDecimal sumBigDecimal(BigDecimal base) {
            BigDecimal result;

            if (base == null) {
                result = BigDecimal.ZERO;
            } else {
                result = BigDecimal.ZERO.add(base);
            }
            for (BigDecimal bd : l) {
                result = result.add(bd);
            }
            return result;
        }

        NBigDecimalValue sum() {
            BigDecimal bd = sumBigDecimal(BigDecimal.ZERO);
            final NBigDecimalValue result = new NBigDecimalValue(bd);
            return result;
        }
    }
}
