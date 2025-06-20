public class FireballSpell implements Spell {
    @Override
    public void cast() {
        System.out.println("You hurl a massive fireball that explodes on impact!");
    }

    @Override
    public String getIncantation() {
        return "fireball";
    }

    @Override
    public String getHelp() {
        return "Hurls a ball of fire at a target";
    }
}
