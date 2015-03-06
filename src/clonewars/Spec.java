package clonewars;

public class Spec {
    private String name;
    private Spell[] spells;
    private Stats defaultStats;

    public Spec(String name, Spell[] spells, Stats defaultStats) {
        this.name = name;
        this.spells = spells;
        this.defaultStats = defaultStats;
    }

    // IDE vomit
    public Spell[] getSpells() { return spells; }

    public String getName() {
        return name;
    }

    public Stats getDefaultStats() {
        return defaultStats;
    }
}
