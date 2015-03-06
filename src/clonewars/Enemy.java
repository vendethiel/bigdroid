package clonewars;

public class Enemy {
    private String name;
    private Stats.EnemyStats stats;
    private int level;

    public Enemy(String name, Stats.EnemyStats stats, int level) {
        this.name = name;
        this.stats = stats;
        this.level = level;
    }

    public double attack(double def) {
        return Math.max(0, stats.getPower() + (stats.getIntelligence() / 10) - def);
    }

    // IDE vomit
    public String getName() { return name; }

    public Stats getStats() {
        stats.setLevel(level);
        return stats;
    }

    public void setStats(Stats.EnemyStats stats) {
        this.stats = stats;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
