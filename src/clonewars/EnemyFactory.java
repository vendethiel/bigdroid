package clonewars;

import java.util.Random;

public class EnemyFactory {
    private int minLevel;
    private int maxLevel;

    public EnemyFactory(int minLevel, int maxLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    public Enemy random() {
        Random random = new Random();
        // nextEnemy = 0..9
        int nextEnemy = random.nextInt(10);

        // XXX fuck weighed random
        // XXX fuck factories
        if (nextEnemy == 0) {
            // '0'
            return createDroid();
        } else if (nextEnemy <= 4) {
            // '1'..'4'
            return createSoldier();
        } else {
            return createClone();
        }
    }

    public Enemy createClone() {
        int level = rollLevel();
        Stats.EnemyStats stats = new Stats(100, 60, 100, 0, 100).asEnemyStats(level);
        return new Enemy("Clone", stats, level);
    }

    public Enemy createSoldier() {
        int level = rollLevel();
        Stats.EnemyStats stats = new Stats(100, 65, 125, 0, 125).asEnemyStats(level);
        return new Enemy("Soldier", stats, level);
    }

    public Enemy createDroid() {
        int level = rollLevel();
        Stats.EnemyStats stats = new Stats(50, 70, 25, 0, 200).asEnemyStats(level);
        return new Enemy("Droid", stats, level);
    }

    public int rollLevel() {
        Random random = new Random();
        return random.nextInt(maxLevel - minLevel) + minLevel;
    }
}
