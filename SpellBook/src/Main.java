import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SpellBook spellBook = new SpellBook();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the SpellBook!");
        System.out.println("Type 'help' to see all available spells.");

        while (true) {
            System.out.print("Recite a spell: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("help")) {
                spellBook.displayHelp();
            } else {
                spellBook.tryIncantation(input);
            }
        }
    }
}
