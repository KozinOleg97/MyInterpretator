import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Loads text of the program
 */
public class CodeLoader {

    private String text;

    CodeLoader (String path){
        try {
            this.text = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            System.out.println("Read file error");
            e.printStackTrace();
        }
    }

    public String getText() {
        return text;
    }
}
