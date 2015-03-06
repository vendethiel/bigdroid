package clonewars;

public class Main {
    static public void main(String[] args) {
        Spec jedi = new Spec("Jedi", new Spell[]{
                new Spell.PhysicalAttack(), new Spell.ForceAttack(),
                new Spell.BetterForceAttack(), new Spell.ChargedJediAttack(),
        }, new Stats(100, 200, 100, 200, 200));
        Spec sith = new Spec("Sith", new Spell[]{
                new Spell.PhysicalAttack(), new Spell.ForceAttack(),
                new Spell.BetterForceAttack(), new Spell.ChargedSithAttack(),
        }, new Stats(100, 100, 200, 200, 200));
        Spec bounty_hunter = new Spec("Bounty Hunter", new Spell[]{
                new Spell.PhysicalAttack(), new Spell.ArmedAttack(),
                new Spell.BetterPhysicalAttack(), new Spell.BetterArmedAttack(),
        }, new Stats(100, 100, 100, 0, 350));
        Spec imperial_agent = new Spec("Imperial Agent", new Spell[]{
                new Spell.PhysicalAttack(), new Spell.ArmedAttack(),
                new Spell.BetterPhysicalAttack(), new Spell.BetterArmedAttack(),
        }, new Stats(350, 100, 100, 0, 100));
        Spec mercenary = new Spec("Mercenary", new Spell[]{
                new Spell.PhysicalAttack(), new Spell.ArmedAttack(),
                new Spell.BetterPhysicalAttack(), new Spell.BetterArmedAttack(),
        }, new Stats(100, 350, 100, 0, 100));
        Spec jawa = new Spec("Jawa", new Spell[]{
                new Spell.PhysicalAttack(), new Spell.ArmedAttack(),
                new Spell.BetterPhysicalAttack(), new Spell.BetterArmedAttack(),
                new Spell.JawAttack(),
        }, new Stats(400, 400, 400, 0, 400));

        Spec[] specs = {jedi, sith, bounty_hunter, imperial_agent, mercenary, jawa};

        ZoneFactory zoneFactory = new ZoneFactory(5, 8);
        // monsterfactory?

        new World(specs, zoneFactory).run();
    }
}