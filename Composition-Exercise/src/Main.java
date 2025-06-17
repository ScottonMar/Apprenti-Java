import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Enter artifact name
        System.out.print("Enter the name of the artifact: ");
        String artifactName = scanner.nextLine();

        // 2. Enter year of discovery
        System.out.print("Enter the year of its discovery: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // 3. Discoverer details
        System.out.println("Enter details for the discoverer.");
        System.out.print("First name: ");
        String dFirstName = scanner.nextLine();
        System.out.print("Last name: ");
        String dLastName = scanner.nextLine();
        System.out.print("Primary specialty: ");
        String dSpecialty = scanner.nextLine();
        Person discoverer = new Person(dFirstName, dLastName, dSpecialty);

        // 4. Is the discoverer also the curator?
        System.out.print("Is the discoverer also the curator? (Y/N): ");
        String isSame = scanner.nextLine().trim().toUpperCase();

        Person curator;
        if (isSame.equals("Y")) {
            curator = discoverer;
        } else {
            System.out.println("Enter details for the curator.");
            System.out.print("First name: ");
            String cFirstName = scanner.nextLine();
            System.out.print("Last name: ");
            String cLastName = scanner.nextLine();
            System.out.print("Primary specialty: ");
            String cSpecialty = scanner.nextLine();
            curator = new Person(cFirstName, cLastName, cSpecialty);
        }

        // Create artifact and print report
        Artifact artifact = new Artifact(artifactName, year, discoverer, curator);
        System.out.println();
        System.out.println(artifact);
    }
}
