public class ExerciseOperators {
    public static void main(String[] args) {

        // === Finance - Loan Payment Calculator ===
        double loanAmount = 25000.00;
        double annualInterestRate = 5.5;
        int loanTermYears = 5;

        // 1. Assignment Operators
        loanAmount += 5000;               // Increase by $5,000
        annualInterestRate -= 1;          // Reduce interest rate by 1%
        loanTermYears++;                  // Increase loan term by 1 year

        // 2. Monthly Payment Calculation
        double monthlyInterestRate = (annualInterestRate / 100) / 12;
        int totalPayments = loanTermYears * 12;
        double monthlyPayment = (loanAmount * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, -totalPayments));

        // 3. Comparison Operators
        boolean isLoanOver30k = loanAmount > 30000;
        boolean isPaymentOver500 = monthlyPayment > 500;

        // 4. Logical Operators
        boolean isAffordable = monthlyPayment < 500 && loanTermYears > 3;
        boolean isExpensive = monthlyPayment > 700 || annualInterestRate > 6;

        // 5. Print Results
        System.out.println("=== Loan Payment Calculator ===");
        System.out.printf("Loan Amount: $%.2f\n", loanAmount);
        System.out.printf("Annual Interest Rate: %.2f%%\n", annualInterestRate);
        System.out.printf("Loan Term: %d years\n", loanTermYears);
        System.out.printf("Monthly Payment: $%.2f\n", monthlyPayment);
        System.out.println("Loan exceeds $30,000? " + isLoanOver30k);
        System.out.println("Monthly payment over $500? " + isPaymentOver500);
        System.out.println("Loan is affordable? " + isAffordable);
        System.out.println("Loan is expensive? " + isExpensive);
        System.out.println();

        // === Weather - Temperature Conversion & Forecast Analysis ===
        double temperatureCelsius = 25.0;
        boolean isRaining = false;
        int windSpeedKmh = 10;

        // 1. Convert to Fahrenheit
        double temperatureFahrenheit = (temperatureCelsius * 9 / 5) + 32;

        // 2. Assignment Operators
        temperatureCelsius += 5;
        windSpeedKmh += 5;

        // Recalculate Fahrenheit after increase
        temperatureFahrenheit = (temperatureCelsius * 9 / 5) + 32;

        // 3. Comparison
        boolean isHot = temperatureFahrenheit > 85;
        boolean isWindy = windSpeedKmh > 20;

        // 4. Logical Operators
        boolean isGoodDay = !isRaining && temperatureFahrenheit >= 60 && temperatureFahrenheit <= 85;
        boolean isWarning = windSpeedKmh > 30 || temperatureCelsius < 0;

        // 5. Print Results
        System.out.println("=== Weather Forecast ===");
        System.out.printf("Temperature: %.1f°C (%.1f°F)\n", temperatureCelsius, temperatureFahrenheit);
        System.out.println("Raining? " + isRaining);
        System.out.println("Wind Speed: " + windSpeedKmh + " km/h");
        System.out.println("Hot (>85°F)? " + isHot);
        System.out.println("Windy (>20 km/h)? " + isWindy);
        System.out.println("Good day to go outside? " + isGoodDay);
        System.out.println("Weather warning? " + isWarning);
        System.out.println();

        // === Gaming - XP and Level System ===
        int currentXP = 1200;
        int level = 5;
        int xpToNextLevel = 1500;
        boolean levelUp;

        // 1. Arithmetic Operators
        currentXP += 300;                   // Completing quest
        xpToNextLevel -= 300;              // Reduce requirement
        currentXP *= 2;                     // Double XP boost

        // 2. Comparison
        boolean xpMet = currentXP >= xpToNextLevel;
        boolean isLevel10 = level >= 10;

        // 3. Logical Operators
        levelUp = xpMet && level < 10;
        boolean isPro = level > 7 || currentXP > 5000;

        // 4. Print Results
        System.out.println("=== Game Progress ===");
        System.out.println("Current XP: " + currentXP);
        System.out.println("Level: " + level);
        System.out.println("XP to Next Level: " + xpToNextLevel);
        System.out.println("XP requirement met? " + xpMet);
        System.out.println("Reached level 10? " + isLevel10);
        System.out.println("Level Up? " + levelUp);
        System.out.println("Is Pro Player? " + isPro);
    }
}
