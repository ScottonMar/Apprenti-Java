public class ExerciseDataTypes {
    public static void main(String[] args){
                // Part 1: Declaring and Assigning Variables

                // Sports Statistics
                String playerName = "Lionel Messi";
                int jerseyNumber = 10;
                String position = "Forward";
                boolean isStarter = true;
                String teamName = "Inter Miami";

                // Movie Information
                String movieTitle = "Inception";
                int releaseYear = 2010;
                String rating = "PG-13";
                boolean isSequel = false;
                String leadActor = "Leonardo DiCaprio";

                // Weather Report
                String cityName = "Seattle";
                double temperature = 65.3;
                boolean isRaining = true;
                int humidity = 80;
                String weatherCondition = "Cloudy";

                // Flight Information
                String flightNumber = "AA256";
                String departureCity = "New York";
                String arrivalCity = "Los Angeles";
                int gateNumber = 32;
                int terminal = 4;
                boolean isDelayed = false;

                // Part 2: Printing Variables

                System.out.println("Soccer Player: " + playerName + " wears jersey number " + jerseyNumber +
                        " and plays as a " + position + " for " + teamName + ". Starter? " + isStarter);

                System.out.println("The movie " + movieTitle + " was released in " + releaseYear +
                        ", rated " + rating + ", and stars " + leadActor + ". Sequel? " + isSequel);

                System.out.println("Weather Report: " + cityName + " has a temperature of " + temperature +
                        "°F with " + weatherCondition + ". Raining? " + isRaining + ". Humidity: " + humidity + "%");

                System.out.println("Flight Info: Flight " + flightNumber + " from " + departureCity +
                        " to " + arrivalCity + " departs from Terminal " + terminal + ", Gate " + gateNumber +
                        ". Delayed? " + isDelayed);

                // Part 3: Updating Variables
                jerseyNumber = 30;
                isStarter = false;

                rating = "R";
                isSequel = true;

                temperature = 72.5;
                weatherCondition = "Sunny";

                gateNumber = 45;
                isDelayed = true;

                System.out.println("\n-- Updated Information --");
                System.out.println("Updated Jersey Number: " + jerseyNumber + ", Starter? " + isStarter);
                System.out.println("Updated Movie Rating: " + rating + ", Sequel? " + isSequel);
                System.out.println("Updated Weather: " + temperature + "°F, " + weatherCondition);
                System.out.println("Updated Flight Gate: " + gateNumber + ", Delayed? " + isDelayed);

                // Part 4: Boolean Variables
                boolean hasHomework = true;
                boolean isWeekend = false;
                boolean attendedClass = true;

                System.out.println("\n-- School/Work Booleans --");
                System.out.println("Do I have homework? " + hasHomework);
                System.out.println("Is it the weekend? " + isWeekend);
                System.out.println("Did I attend class today? " + attendedClass);

                // Part 5: Char Data Type
                char firstInitial = 'J';
                char lastInitial = 'D';
                char favoriteGrade = 'A';

                System.out.println("\n-- Char Data Type --");
                System.out.println("My initials are " + firstInitial + lastInitial +
                        " and my favorite grade is " + favoriteGrade);
            }
        }
