import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIO {

    private BufferedReader reader = null;
    private BufferedWriter writer = null;
    private String line = null;

    public List<String> read(String fileName) throws IOException {
        reader = new BufferedReader(new FileReader(fileName));
        List<String> strings = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            strings.add(line);
        }
        return strings;
    }

    public void save(String fileName, List<String> strings) throws IOException {
        writer = new BufferedWriter(new FileWriter(fileName));
        for (String s : strings) {
            writer.write(s + System.getProperty("line.separator"));
        }
        writer.flush();
        writer.close();
    }

}
