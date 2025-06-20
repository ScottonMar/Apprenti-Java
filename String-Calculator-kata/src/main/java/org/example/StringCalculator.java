package org.example;

import java.util.ArrayList;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else if (!numbers.contains(",") && (!numbers.contains("\n"))){
            return Integer.parseInt(numbers);
        } else {
            String[] nums;
            String delimiter = "";
            if (numbers.startsWith("//")) {
                String[] splitNumbers = numbers.split("\n");
                delimiter = splitNumbers[0].substring(2);
                numbers = splitNumbers[1];
            }

            nums = numbers.split(",|;|\n" + delimiter);
            return getSum(nums);
        }
    }



    private int getSum(String[] nums) {
        int sum = 0;
        String negatives = "";

        for (String num : nums) {
            if (num.startsWith("-")) {
                negatives += num + " ";
            } else if (!num.isEmpty()){
                if (Integer.parseInt(num) <= 1000) {
                    sum += Integer.parseInt(num);
                }
            }
        }



        if (negatives.length() > 0) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }

        return sum;
    }
}