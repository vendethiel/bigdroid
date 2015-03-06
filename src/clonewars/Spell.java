package clonewars;

abstract public class Spell {
    public int getRequiredLevel() {
        return 1;
    }
    abstract public String getName();

    abstract public int run(Stats fighterStats, Stats enemyStats);

    // XXX this is really a hack :-'
    public int tellAndProcess(int baseDamage, int blockedDamage) {
        int inflictedDamage = Math.max(0, baseDamage - blockedDamage);
        P.s("Vous infligez ", inflictedDamage, " degats", (blockedDamage > 0 ? " (" + blockedDamage + " degats pares)" : ""));
        return inflictedDamage;
    }

    static public class PhysicalAttack extends Spell {
        public String getName() { return "Attaque physique"; }

        public int run(Stats fighterStats, Stats enemyStats) {
            return tellAndProcess((int) fighterStats.getPower(), (int) enemyStats.getDef());
        }
    }

    static public class ForceAttack extends Spell {
        public String getName() { return "La Force"; }

        public int run(Stats fighterStats, Stats enemyStats) {
            return tellAndProcess((int) (fighterStats.getForce() / 10) * 2, 0);
        }
    }

    static public class ArmedAttack extends Spell {
        public String getName() { return "Attaque armée"; }

        public int run(Stats fighterStats, Stats enemyStats) {
            return tellAndProcess((int)fighterStats.getIntelligence(), (int)enemyStats.getDef());
        }
    }

    static public class BetterPhysicalAttack extends Spell {
        public int getRequiredLevel() { return 3; }
        public String getName() { return "Attaque améliorée"; }

        public int run(Stats fighterStats, Stats enemyStats) {
            return tellAndProcess((int) (fighterStats.getPower() * 1.2), (int) enemyStats.getDef());
        }
    }

    static public class BetterForceAttack extends Spell {
        public int getRequiredLevel() { return 3; }
        public String getName() { return "La Force Puissante"; }

        public int run(Stats fighterStats, Stats enemyStats) {
            return tellAndProcess((int) (fighterStats.getIntelligence() * 1.5), 0);
        }
    }

    static public class BetterArmedAttack extends Spell {
        public int getRequiredLevel() { return 3; }
        public String getName() { return "Attaque Armee Puissante"; }

        public int run(Stats fighterStats, Stats enemyStats) {
            return tellAndProcess((int) (fighterStats.getForce() / 8) * 2, 0);
        }
    }

    static public class ChargedJediAttack extends Spell {
        public int getRequiredLevel() { return 5; }
        public String getName() { return "Cote pur de la force"; }

        public int run(Stats fighterStats, Stats enemyStats) {
            return tellAndProcess((int) (fighterStats.getForce() * 2.5), 0);
        }
    }

    static public class ChargedSithAttack extends Spell {
        public int getRequiredLevel() { return 5; }
        public String getName() { return "Cote obscur de la force"; }

        public int run(Stats fighterStats, Stats enemyStats) {
            return tellAndProcess((int) ((fighterStats.getForce() * 2) + (fighterStats.getPower() * 0.2)), 0);
        }
    }

    static public class JawAttack extends Spell {
        public String getName() {
            return "JawAttaque";
        }

        public int run(Stats fighterStats, Stats enemyStats) {
            return tellAndProcess((int)enemyStats.getHp(), 0);
        }
    }
}