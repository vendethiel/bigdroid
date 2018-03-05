package clonewars;

import java.util.Map;
import java.util.Random;

// stolen from https://stackoverflow.com/questions/6737283/weighted-randomness-in-java
public class WeightedRandom<E> {
    private Random random;
    private Map<E, Double> weights;

    public WeightedRandom(Random random, Map<E, Double> weights) {
        if (weights.isEmpty()) {
            throw new RuntimeException("Invalid weight set");
        }

        this.random = random;
        this.weights = weights;
    }

    public E getRandom() {
        double bestValue = Double.MAX_VALUE;
        E result = null;

        for (E element : weights.keySet()) {
            double value = -Math.log(random.nextDouble()) / weights.get(element);

            if (value < bestValue) {
                bestValue = value;
                result = element;
            }
        }
        return result; /* we know that weights isn't empty, so result will never end null */
    }
}
