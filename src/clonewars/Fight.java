package clonewars;

public class Fight {
    private Character fighter;
    private Enemy enemy;
    private double fighterHp;
    private double enemyHp;

    public Fight(Character fighter, Enemy enemy) {
        this.fighter = fighter;
        this.enemy = enemy;

        fighterHp = fighter.getStats().getHp();
        enemyHp = enemy.getStats().getHp();
    }

    public Character getFighter() {
        return fighter;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void fighterSuffer(double damage) {
        this.fighterHp -= damage;
    }

    public void enemySuffer(double damage) {
        this.enemyHp -= damage;
    }

    public boolean isFighterAlive() {
        return fighterHp > 0;
    }

    public boolean isEnemyAlive() {
        return enemyHp > 0;
    }

    public double getFighterHp() {
        return fighterHp;
    }

    public double getEnemyHp() {
        return enemyHp;
    }
}
