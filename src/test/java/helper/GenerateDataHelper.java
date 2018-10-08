package helper;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GenerateDataHelper {

    private static Random random = new Random();

    private GenerateDataHelper() {
    }

    public static List<String> generatePosition() {
        String[] positions = {"Java Developer", "Test Engineer", "Project Manager", "Solution Architect", "Team Lead"};
        return Arrays.asList(positions);
    }

    public static String getRandomPersonName() {
        String[] surnames = {"Murphy ", "Smith", "Williams", "Taylor", "Jones", "Davies", "Walker"};
        String[] names = {"Oliver", "Jake", "Amelia", "Margaret", "Elizabeth", "Charlie", "Joanne"};
        return names[random.nextInt(names.length)] + surnames[random.nextInt(surnames.length)];
    }

    public static String generateProjectName() {
        String[] name = {"Banker", "Cosmos", "Robotics", "CanadianTier"};
        String[] codeCharacters = {"X", "1", "2", "O"};
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 3; i++)
            code.append(codeCharacters[random.nextInt(codeCharacters.length)]);
        return name[random.nextInt(name.length)] + "-" + code;
    }

    public void generateRandomDate() {

    }

    public String generateContactInfo() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 8; i++)
            res.append(numbers[random.nextInt(numbers.length)]);
        return "phone number: " + res.toString();
    }

}