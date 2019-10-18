import java.util.ArrayList;
import java.util.List;


/**
 * Allocates language tokens in the program text
 */
public class LexicalAnalyzer {

    private List<Lex> allLex;

    LexicalAnalyzer() {
        this.allLex = new ArrayList<Lex>();
    }

    class Lex {
        private String body;
        private LanguageConfigurator.LexTypes type;
        private String name;
        private Integer firstLine;
        private Integer length;
    }

    public void loadProgrammText(String text) {

    }

    public List<Lex> getLexList() {
        return this.allLex;
    }
}
