package clonewars;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class EnemyFactory {
    private int minLevel;
    private int maxLevel;
    private WeightedRandom<MonsterFactory> weighed;

    interface MonsterFactory {
        Enemy create();
    }

    public EnemyFactory(int minLevel, int maxLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        Map<MonsterFactory, Double> creators = new TreeMap<>();
        creators.put(() -> {
            int level = rollLevel();
            Stats.EnemyStats stats = new Stats(50, 70, 25, 0, 200).asEnemyStats(level);
            return new Enemy("Droid", stats, level);
        }, 1d);
        creators.put(() -> {
            int level = rollLevel();
            Stats.EnemyStats stats = new Stats(100, 65, 125, 0, 125).asEnemyStats(level);
            return new Enemy("Soldier", stats, level);
        }, 3d);
        creators.put(() -> {
            int level = rollLevel();
            Stats.EnemyStats stats = new Stats(100, 60, 100, 0, 100).asEnemyStats(level);
            return new Enemy("Clone", stats, level);
        }, 5d);
        this.weighed = new WeightedRandom<>(new Random(), creators);
    }

    public Enemy random() {
        return weighed.getRandom().create();
    }

    public int rollLevel() {
        Random random = new Random();
        return random.nextInt(maxLevel - minLevel) + minLevel;
    }
}
