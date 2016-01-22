import java.io.File;
import java.util.ArrayList;

public class FileFinder {

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
