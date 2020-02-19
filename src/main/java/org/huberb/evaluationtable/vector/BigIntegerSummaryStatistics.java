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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

/**
 *
 * @author berni3
 */
public class BigIntegerSummaryStatistics implements BigIntegerConsumer {

    private long count = 0L;
    private BigInteger sum = BigInteger.ZERO;
    private BigInteger min = null;
    private BigInteger max = null;

    /**
     * Construct an empty instance with zero count, {@code .ZERO} sum,
     * {@code .ZERO} min, {@code .ZERO} max and zero average.
     */
    public BigIntegerSummaryStatistics() {
    }

    /**
     * Records a new value into the summary information
     *
     * @param value the input value
     */
    @Override
    public void accept(BigInteger value) {
        ++count;
        sum = sum.add(value);
        if (min == null) {
            min = value;
        } else {
            final int compareValue = min.compareTo(value);
            if (compareValue > 0) {
                min = value;
            }
        }
        if (max == null) {
            max = value;
        }
        {
            final int compareValue = max.compareTo(value);
            if (compareValue < 0) {
                max = value;
            }
        }
    }

    /**
     * Combines the state of another {@code SummaryStatistics} into this one.
     *
     * @param other another {@code BigIntegerSummaryStatistics}
     * @throws NullPointerException if {@code other} is null
     */
    public void combine(BigIntegerSummaryStatistics other) {
        count += other.count;
        sum = sum.add(other.sum);
        if (min == null) {
            min = other.min;
        } else if (other.min != null) {
            //min = Math.min(min, other.min);
            final int compareValue = min.compareTo(other.min);
            if (compareValue > 0) {
                min = other.min;
            }
        }
        if (max != null) {
            max = other.max;
        } else if (other.max != null) {
            //max = Math.max(max, other.max);
            final int compareValue = max.compareTo(other.max);
            if (compareValue < 0) {
                max = other.max;
            }
        }
    }

    /**
     * Returns the count of values recorded.
     *
     * @return the count of values
     */
    public final long getCount() {
        return count;
    }

    /**
     * Returns the sum of values recorded, or zero if no values have been
     * recorded.
     *
     * @return the sum of values, or zero if none
     */
    public final BigInteger getSum() {
        return sum;
    }

    /**
     * Returns the minimum value recorded, or {@code BigInteger.ZERO} if no
     * values have been recorded.
     *
     * @return the minimum value, or {@code BigInteger.ZERO} if none
     */
    public final BigInteger getMin() {
        return Optional.ofNullable(min).orElse(BigInteger.ZERO);
    }

    /**
     * Returns the maximum value recorded, or {@code BigInteger.ZERO} if no
     * values have been recorded.
     *
     * @return the maximum value, or {@code BigInteger.ZERO} if none
     */
    public final BigInteger getMax() {
        return Optional.ofNullable(max).orElse(BigInteger.ZERO);
    }

    /**
     * Returns the arithmetic mean of values recorded, or zero if no values have
     * been recorded.
     *
     * @return the arithmetic mean of values, or zero if none
     */
    public final BigDecimal getAverage() {
        BigDecimal bd = BigDecimal.ZERO;
        if (getCount() > 0) {
            bd = new BigDecimal(getSum());
            bd = bd.divide(new BigDecimal(getCount()));
        }
        return bd;
    }

    @Override
    /**
     * {@inheritDoc}
     *
     * Returns a non-empty string representation of this object suitable for
     * debugging. The exact presentation format is unspecified and may vary
     * between implementations and versions.
     */
    public String toString() {
        return String.format(
                "%s{count=%d, sum=%d, min=%d, average=%f, max=%d}",
                this.getClass().getSimpleName(),
                getCount(),
                getSum(),
                getMin(),
                getAverage(),
                getMax());
    }
}
