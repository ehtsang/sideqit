import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FileFinder {

    /*
    O(N)
    Pre-condition: Path is a file
    Post-condition: String with the contents of the file
    */
    public static String readFile(String path) {
        String contents = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            contents = sb.toString();
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(1);
        }
        return contents;
    }

    /*
    O(N^2)
    Pre-condition: Path exists
    Post-condition: An ArrayList of Strings that includes the files with the inputted ending
    */
    public static void findFiles(String path, ArrayList<String> fileNames, String ending) {
        File directory = new File(path);
        File[] files = directory.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                findFiles(file.getAbsolutePath(), fileNames, ending);
            }
            else {
                String fileName = file.getName();
                if (fileName.endsWith(ending)) {
                    fileNames.add(fileName);
                }
            }
        }
    }
}
