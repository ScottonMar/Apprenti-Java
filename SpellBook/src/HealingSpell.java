public class HealingSpell implements Spell {
    @Override
    public void cast() {
        System.out.println("A warm light surrounds you. Your wounds begin to heal.");
    }

    @Override
    public String getIncantation() {
        return "heal";
    }

    @Override
    public String getHelp() {
        return "Restores health over time";
    }
}
