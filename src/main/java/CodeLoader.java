import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * Loads text of the program
 */
public class CodeLoader {

    //private String text;
    private String curPath;
    private List<String> listOfCode = Collections.emptyList();

    CodeLoader(String path) {
        this.curPath = path;
        listOfCode = LoadCodeToList();
        listOfCode = CleanListOfCode(listOfCode);
    }

    private List<String> CleanListOfCode(List<String> listOfCode) {
        for (int i = 0; i < listOfCode.size(); i++) {
            listOfCode.set(i, listOfCode.get(i).trim());
        }
        return listOfCode;
    }

    private List<String> LoadCodeToList() {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(curPath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public List<String> getListOfCode() {
        return listOfCode;
    }


}
