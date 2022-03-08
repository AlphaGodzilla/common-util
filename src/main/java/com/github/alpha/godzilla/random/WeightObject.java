package com.github.alpha.godzilla.random;

import java.util.Objects;

/**
 * @author AlphaGodzilla
 * @date 2022/3/8 16:55
 */
public class WeightObject<T> {
    /**
     * 关联对象
     */
    private final T object;

    /**
     * 权重
     */
    private final double weight;


    public WeightObject(T object, double weight) {
        this.object = object;
        this.weight = weight;
    }

    public T getObject() {
        return object;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WeightObject)) {
            return false;
        }
        final WeightObject<?> that = (WeightObject<?>) o;
        return Double.compare(that.getWeight(), getWeight()) == 0 && getObject().equals(that.getObject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getObject(), getWeight());
    }
}
