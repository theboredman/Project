package application;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFileLoader {

    public static List<UserData> loadUserDataFromFile(String filePath) {
        List<UserData> userDataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDataArray = line.split(",");

                if (userDataArray.length == 8) { // Assuming there are 8 fields
                    UserData userData = new UserData(
                            userDataArray[0],
                            userDataArray[1],
                            userDataArray[2],
                            userDataArray[3],
                            userDataArray[4],
                            userDataArray[5],
                            userDataArray[6],
                            userDataArray[7]
                    );
                    userDataList.add(userData);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userDataList;
    }

}
