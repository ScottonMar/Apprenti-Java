public class ExitSpell implements Spell {
    @Override
    public void cast() {
        System.out.println("A portal opens beneath your feet. Goodbye, wizard!");
        System.exit(0); // Ends the program
    }

    @Override
    public String getIncantation() {
        return "quit";
    }

    @Override
    public String getHelp() {
        return "Opens a portal to another dimension";
    }
}
