import java.util.Random;

public class PinGenerator {
    private static Random rand = new Random();

    public static String generatePin() {
        return String.format("%04d", rand.nextInt(10000));
    }
}
