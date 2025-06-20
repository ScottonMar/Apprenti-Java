import java.util.ArrayList;

public class SpellBook {
    private ArrayList<Spell> spells;

    public SpellBook() {
        spells = new ArrayList<>();
        spells.add(new ExitSpell());
        spells.add(new FireballSpell());
        spells.add(new HealingSpell());
        spells.add(new InvisibilitySpell());
    }

    public void tryIncantation(String incantation) {
        for (Spell spell : spells) {
            if (spell.getIncantation().equalsIgnoreCase(incantation)) {
                spell.cast();
                return;
            }
        }
        System.out.println("The spell fizzled out! Try again.");
    }

    public void displayHelp() {
        System.out.println("\nIncantation     | Description");
        System.out.println("----------------|------------------------------");
        for (Spell spell : spells) {
            System.out.printf("%-16s| %s\n", spell.getIncantation(), spell.getHelp());
        }
        System.out.println();
    }
}
