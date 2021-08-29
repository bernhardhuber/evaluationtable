/*
 * Copyright 2021 berni3.
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 *
 * @author berni3
 */
public class EvaluationsMonad {

    static class Sheet<V> {

        private List<V> l;

        public Sheet(List<V> l) {
            this.l = new ArrayList<>(l);
        }

        Sheet< V> flatMap(Function<List<V>, List<V>> f) {
            final List<V> lNew = f.apply(this.l);
            this.l.addAll(lNew);
            return this;
        }

        Sheet<V> map(Function<List<V>, List<V>> f) {
            final List<V> lNew = new ArrayList<>();
            lNew.addAll(this.l);

            final List<V> lFApplied = f.apply(this.l);
            lNew.addAll(lFApplied);
            return new Sheet< V>(lNew);
        }

        List<V> l() {
            return this.l;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 53 * hash + Objects.hashCode(this.l);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Sheet<?> other = (Sheet<?>) obj;
            if (!Objects.equals(this.l, other.l)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "Sheet{" + "l=" + l + '}';
        }

    }

    static class ValueStringOrNumber {

        private final Optional<String> sOpt;
        private final Optional<Number> nOpt;

        public ValueStringOrNumber(String s) {
            this.sOpt = Optional.of(s);
            this.nOpt = Optional.empty();
        }

        public ValueStringOrNumber(Number n) {
            this.sOpt = Optional.empty();
            this.nOpt = Optional.of(n);
        }

        Optional<String> s() {
            return this.sOpt;
        }

        Optional<Number> n() {
            return this.nOpt;
        }

        String s(String ifNotDefined) {
            return this.sOpt.orElse(ifNotDefined);
        }

        Number n(Number ifNotDefined) {
            return this.nOpt.orElse(ifNotDefined);
        }

        ValueStringOrNumber sf(String ifNotDefined, Function<String, String> f) {
            String s = s(ifNotDefined);
            String sNew = f.apply(s);
            return new ValueStringOrNumber(sNew);
        }

        ValueStringOrNumber nf(Number ifNotDefined, Function<Number, Number> f) {
            Number n = n(ifNotDefined);
            Number nNew = f.apply(n);
            return new ValueStringOrNumber(nNew);
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 23 * hash + Objects.hashCode(this.sOpt);
            hash = 23 * hash + Objects.hashCode(this.nOpt);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ValueStringOrNumber other = (ValueStringOrNumber) obj;
            if (!Objects.equals(this.sOpt, other.sOpt)) {
                return false;
            }
            if (!Objects.equals(this.nOpt, other.nOpt)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "ValueStringOrNumber{" + "sOpt=" + sOpt + ", nOpt=" + nOpt + '}';
        }

    }

    static class ValueStringOrNumberEnumed extends ValueStringOrNumber {

        private final Enum e;

        public ValueStringOrNumberEnumed(Enum e, String s) {
            super(s);
            this.e = e;
        }

        public ValueStringOrNumberEnumed(Enum e, Number n) {
            super(n);
            this.e = e;
        }

        Enum e() {
            return this.e;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 71 * hash + Objects.hashCode(this.e);
            hash = 71 * hash + super.hashCode();
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ValueStringOrNumberEnumed other = (ValueStringOrNumberEnumed) obj;
            if (this.e != other.e) {
                return false;
            }
            return super.equals(obj);
        }

        @Override
        public String toString() {
            return "ValueStringOrNumberEnumed{"
                    + "e=" + e
                    + ", sOpt=" + s("-") + ", nOpt=" + n(Double.NaN)
                    + '}';
        }

    }
}
