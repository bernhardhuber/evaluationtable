/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
