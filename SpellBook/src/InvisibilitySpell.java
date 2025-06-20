public class InvisibilitySpell implements Spell {
    @Override
    public void cast() {
        System.out.println("You vanish from sight. You're now invisible!");
    }

    @Override
    public String getIncantation() {
        return "vanish";
    }

    @Override
    public String getHelp() {
        return "Grants temporary invisibility";
    }
}
