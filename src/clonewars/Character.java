package clonewars;

import java.util.ArrayList;
import java.util.List;

public class Character {
    private Spec spec;
    private Stats.CharacterStats stats;
    private int level;
    private int xp;
    private String name;
    private List<Buff> buffs;

    public Character(Spec spec, String name) {
        this.level = 1;
        this.xp = 0;

        this.spec = spec;
        this.stats = spec.getDefaultStats().asCharacterStats(this.level);
        this.name = name;
        this.buffs = new ArrayList<>();
    }

    public List<Buff> getBuffs() {
        return buffs;
    }

    public void addBuff(Buff buff) {
        buffs.add(buff);
    }

    public boolean hasBuff(String buffName) {
        for (Buff buff : getBuffs()) {
            if (buff.getName().equals(buffName)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Spell> getAvailableSpells() {
        ArrayList<Spell> spells = new ArrayList<>();
        for (Spell spell : spec.getSpells()) {
            if (level >= spell.getRequiredLevel()) {
                spells.add(spell);
            }
        }
        return spells;
    }

    public void levelUp() {
        level++;
        xp = 0;
    }

    public Stats getStats() {
        stats.setLevel(this.level); // LOL HACK
        return stats;
    }

    // IDE vomit
    public String getName() {
        return name;
    }

    public Spec getSpec() {
        return spec;
    }

    public void setSpec(Spec spec) {
        this.spec = spec;
    }

    public void setStats(Stats.CharacterStats stats) {
        this.stats = stats;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}