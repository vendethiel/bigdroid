package clonewars;

public class Stats {
    private int level;
    private double hp;
    private double def;
    private double power;
    private double force;
    private double intelligence;

    public Stats(double hp, double def, double power, double force, double intelligence) {
        this(0, hp, def, power, force, intelligence);
    }

    public Stats(int level, double hp, double def, double power, double force, double intelligence) {
        this.level = level;
        this.hp = hp;
        this.def = def;
        this.power = power;
        this.force = force;
        this.intelligence = intelligence;
    }

    protected double getModifier() {
        return 1; // by default...
    }

    public Stats.CharacterStats asCharacterStats(int level) {
        return new Stats.CharacterStats(level, hp, def, power, force, intelligence);
    }

    public Stats.EnemyStats asEnemyStats(int level) {
        return new Stats.EnemyStats(level, hp, def, power, force, intelligence);
    }

    public Stats.BossStats asBossStats(int level) {
        return new Stats.BossStats(level, hp, def, power, force, intelligence);
    }


    static public class CharacterStats extends Stats {
        public CharacterStats(int level, double hp, double def, double power, double force, double intelligence) {
            super(level, hp, def, power, force, intelligence);
        }

        @Override
        protected double getModifier() {
            return Math.pow(1.5, getLevel() - 1);
        }
    }

    static public class EnemyStats extends Stats {
        public EnemyStats(int level, double hp, double def, double power, double force, double intelligence) {
            super(level, hp, def, power, force, intelligence);
        }

        @Override
        protected double getModifier() {
            return Math.pow(1.3, getLevel() - 1);
        }
    }

    static public class BossStats extends Stats {
        public BossStats(int level, double hp, double def, double power, double force, double intelligence) {
            super(level, hp, def, power, force, intelligence);
        }

        @Override
        protected double getModifier() {
            return Math.pow(1.7, getLevel());
        }
    }

    // IDE vomit

    public int getLevel() {
        return level;
    }

    public double getHp() {
        return hp * getModifier();
    }

    public double getDef() {
        return def * getModifier();
    }

    public double getPower() {
        return power * getModifier();
    }

    public double getForce() {
        return force * getModifier();
    }

    public double getIntelligence() {
        return intelligence * getModifier();
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
