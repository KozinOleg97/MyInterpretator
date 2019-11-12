import java.util.ArrayList;
import java.util.List;

/**
 * Allocates language tokens in the PROGRAM text
 */
public class LexicalAnalyzer {

    private List<Lex> allLex;

    LexicalAnalyzer(String[] arrayOfCode) {


        this.allLex = new ArrayList<Lex>();


        findAllLex(arrayOfCode);
    }

    /**
     * Main method of this class. Create list of Lex from prog code.
     *
     *
     * @param arrayOfCode
     * @return Main lex: Program (entry point)
     */
    private List<Lex> findAllLex(String[] arrayOfCode) {
        Lex mainLex = findProgram(arrayOfCode);

        //TODO Iterate prog code by lines & select token. For complex tokens use recursion
        //for ()

        return null;
    }

    /**
     * Create entry point lex: "Program"
     *
     * @param arrayOfCode
     * @return
     */
    private Program findProgram(String[] arrayOfCode) {
        Program mainLex = new Program();
        for (int i = 0; i < arrayOfCode.length; i++) {
            mainLex.type.checkStr(arrayOfCode[i]);
        }


        return mainLex;
    }

    /**
     * TODO specify correct fields
     */
    class Lex {
        private String[] body;
        private LexTypes type;
        private String name;
        private Integer firstLine;
        private Integer length;
    }

    class Program extends Lex {
        private LexTypes type = LexTypes.PROGRAM;
    }

    public void loadProgrammText(String text) {

    }

    public List<Lex> getLexList() {
        return this.allLex;
    }
}
