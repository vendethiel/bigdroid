package clonewars;

public class ExperienceManager {
    // c'est biaisé!
    static final public int BIAS = 10;
    private Character character;
    private Enemy enemy;

    public ExperienceManager(Character character, Enemy enemy) {
        this.character = character;
        this.enemy = enemy;
    }

    public int calculateExpRequired() {
        int level = character.getLevel();
        int modifier = level < 5 ? 5 : 25;
        // exp
        return (int)(level * modifier + (level * Math.log(50) * 10) * BIAS);
    }

    public int calculateGain() {
        // base gain of five
        return 5 + enemy.getLevel() * 2 * BIAS;
    }
}
