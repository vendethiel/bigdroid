package clonewars;

import java.util.ArrayList;
import java.util.Random;

public class World {
    private Spec[] specs;
    private ZoneFactory zoneFactory;

    public World(Spec[] specs, ZoneFactory zoneFactory) {
        this.specs = specs;
        this.zoneFactory = zoneFactory;
    }

    public void run() {
        Character character = rollCharacter();
        Zone zone = zoneFactory.build();

        //P.s("Bienvenue parmi nous, jeune " + character.getSpec().getName());

        for (;;) {
            P.s("Vous etes dans une zone de niveau ", zone.getLevel(), ", piece ", zone.getRoomNumber() + 1, ".");

            if ((zone = playFight(character, zone)) == null) {
                break; // no zone = we died
            }
        }
    }

    private Zone playFight(Character character, Zone zone) {
        Fight fight = zone.startFight(character);
        if (runFight(fight)) {
            if (zone.isLastRoom()) {
                P.print("Vous etes dans la derniere piece de cette zone ! ");
            }

            if (P.ask("Voulez-vous avancer ?")) {
                if (zone.goNextRoom()) {
                    P.s("Vous avancez dans la piece suivante.\n");
                    return zone;
                } else {
                    P.s("Vous avancez dans la zone suivante...\n");
                    // no next room to go to...
                    // TODO boss fight?
                    return zoneFactory.build();
                }
            } else {
                P.s("\n-------------\n");
                return zone;
            }
        } else {
            // we lost!
            P.s("Vous etes mort, gros naze !");
            return null;
        }
    }

    public Character rollCharacter() {
        P.s("Bienvenue, nouveau joueur ! Choisi un personnage :");
        for (int i = 0; i < specs.length; i++) {
            P.s("[" + (i + 1) + "] " + specs[i].getName());
        }

        int select = P.readIntBound(specs.length);

        P.s("Entre un nom");
        String name = P.read();

        P.s("Bienvenue dans Clone Wars, ",name,", bonne chance a toi jeune ",specs[select-1].getName()," !\n");
        return new Character(specs[select - 1], name);
    }

    public boolean runFight(Fight fight) {
        Character fighter = fight.getFighter();
        Enemy enemy = fight.getEnemy();
        P.s("Un combat contre un " + enemy.getName() + " commence ! Vous etes niveau ", fighter.getLevel(), " avec ", P.r(fight.getFighterHp()), " points de vie ; l'ennemi est niveau ", enemy.getLevel(), " et a ", P.r(fight.getEnemyHp()), " points de vie.");

        for (;;) { // loop for the turns
            //player turn
            fight.enemySuffer(playerSpell(fighter, enemy));

            if (fight.isEnemyAlive()) {
                P.s("Il reste ", Math.ceil(fight.getEnemyHp()), " point(s) de vie a l'ennemi.");
            } else {
                // WE WON!
                P.s("\nVous avez gagné !");
                runExpRoutine(fight);
                return true;
            }


            monsterTurn(fight);
            if (fight.isFighterAlive()) {
                P.s("Il vous reste ", P.r(fight.getFighterHp()), " point(s) de vie.");
            } else {
                // we lost. fuck it
                return false;
            }

            // onto the next loop turn...
        }
    }

    private void monsterTurn(Fight fight) {
        // monster turn
        Enemy enemy = fight.getEnemy();
        Character fighter = fight.getFighter();

        P.s("Au tour du ", enemy.getName(), " ennemi");
        double enemyDamage = enemy.attack(fighter.getStats().getDef());
        P.s("Vous subissez ", P.r(enemyDamage), " point(s) de dégat (parés: ", P.r(fighter.getStats().getDef()), ").");
        fight.fighterSuffer(enemyDamage);

        // and now, for the buff things...
        Random random = new Random();
        if (random.nextInt(10) == 1 && !fighter.hasBuff("Saignement")) {
            P.s("vous vous mettez à saigner.");
            fighter.addBuff(new Buff.DamageDebuff());
        }

        for (Buff buff : fighter.getBuffs()) {
            buff.run(fighter.getStats());
        }
    }

    private void runExpRoutine(Fight fight) {
        Character fighter = fight.getFighter();
        ExperienceManager expMgr = new ExperienceManager(fighter, fight.getEnemy());

        int expGained = expMgr.calculateGain();
        int expNeeded = expMgr.calculateExpRequired();
        int expTotal = expGained + fighter.getXp();
        P.print("Vous avez gagné ", expGained, " points d'experience. ");
        if (expTotal > expNeeded) {
            fighter.levelUp();
            P.print("Vous gagné un niveau ! ");
            fighter.setXp(expTotal - expNeeded); // add remaining exp
        } else {
            // we didn't level up. just store xp
            fighter.setXp(fighter.getXp() + expGained);
        }
        P.s(" (niveau actuel : ", fighter.getLevel(),
                // recalculate required XP if we leveled up
                ". xp: ", fighter.getXp(), "/", expMgr.calculateExpRequired(), ")");
    }

    private int playerSpell(Character fighter, Enemy enemy) {
        P.s("Choisi une attaque : ");

        ArrayList<Spell> spells = fighter.getAvailableSpells();
        for (int i = 0; i < spells.size(); ++i) {
            System.out.print("[" + (i + 1) + "] " + spells.get(i).getName() + " ");
            if (i != 0 && (i + 1) % 3 == 0 && (i + 1) != spells.size()) {
                P.s("");
            }
        }
        P.s("");

        int select = P.readIntBound(spells.size());
        return spells.get(select - 1).run(fighter.getStats(), enemy.getStats());
    }
}
