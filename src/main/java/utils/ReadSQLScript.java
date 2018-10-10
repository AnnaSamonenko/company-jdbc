package utils;

import java.io.*;

public class ReadSQLScript {

    private ReadSQLScript() {
    }

    public static String read(String filePath) {
        String currentLine;
        StringBuilder query = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + filePath))) {
            while ((currentLine = br.readLine()) != null) {
                query.append(currentLine + " ");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return query.toString();
    }
}
