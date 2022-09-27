package com.github.alphagodzilla.random;

import java.util.Collection;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author AlphaGodzilla
 * @date 2022/3/8 16:54
 */
public class WeightRandom<T> {
    private final TreeMap<Double, T> weightMap;

    public WeightRandom(Collection<WeightObject<T>> weightObjects) {
        this();
        for (final WeightObject<T> weightObject : weightObjects) {
            add(weightObject);
        }
    }

    public WeightRandom() {
        this(new TreeMap<>(), Collections.emptyList());
    }

    public WeightRandom(TreeMap<Double, T> weightMap, Collection<WeightObject<T>> weightObjects) {
        this.weightMap = weightMap;
        for (final WeightObject<T> weightObject : weightObjects) {
            add(weightObject);
        }
    }

    public WeightRandom<T> add(WeightObject<T> weightObject) {
        if (weightObject == null) {
            return this;
        }
        double weight = weightObject.getWeight();
        if (weight <= 0) {
            return this;
        }
        double lastWeight = weightMap.size() <= 0 ? 0 : this.weightMap.lastKey();
        weightMap.put(weight + lastWeight, weightObject.getObject());
        return this;
    }

    public T nextRandom() {
        double randomWeight = weightMap.lastKey() * ThreadLocalRandom.current().nextDouble();
        SortedMap<Double, T> tailMap = weightMap.tailMap(randomWeight, false);
        return this.weightMap.get(tailMap.firstKey());
    }

    public WeightRandom<T> clear() {
        if (weightMap != null) {
            weightMap.clear();
        }
        return this;
    }
}
