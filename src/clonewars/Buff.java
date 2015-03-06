package clonewars;

import java.util.Random;

abstract public class Buff {
    protected int duration;

    public Buff(int minDuration, int maxDuration) {
        Random random = new Random();
        duration = random.nextInt(maxDuration - minDuration) + minDuration;
    }


    abstract protected String getName();
    abstract protected int run(Stats stats);

    static public class DamageDebuff extends Buff {
        public DamageDebuff() { super(3, 5); }

        public String getName() { return "Saignement"; }

        public int run(Stats stats) {
            this.duration--;

            int damage = rollDamage(stats);
            P.s("Le saignement inflige ", damage, " degats");
            return damage;
        }

        private int rollDamage(Stats stats) {
            return 12 * (int)Math.pow(1.5, stats.getLevel());
        }
    }

    // IDE vomit
    public int getDuration() {
        return duration;
    }
}
