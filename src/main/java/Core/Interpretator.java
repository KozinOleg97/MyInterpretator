package Core;

import Lex.InitLex;
import Lex.Lex;
import Types.Var;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Executes the parse tree
 */
public class Interpretator {

    public Map<String, Class> ops = new HashMap<String, Class>() {{
        put("^Int *[a-zA-Z]+ *=*.*;$", InitLex.class);
        put("^Real *[a-zA-Z] *= *.*;$", InitLex.class);
        put("^String *[a-zA-Z] *= *.*;$", InitLex.class);
    }};

    /*public String[][] dataList = {
            {"intVar", "^Int *[a-zA-Z] *= *.*;$"},
            {"realVar", "^Real *[a-zA-Z] *= *.*;$"}
    };*/

    public static final Integer DEFAULT_INT_VALUE = 0;
    public static final Double DEFAULT_REAL_VALUE = 0.0;
    public static final String DEFAULT_STR_VALUE = "";
    public static final Boolean DEFAULT_BOOL_VALUE = false;

    public VarList varList = new VarList();
    String[] allCode;

    Integer curLine;

    String pathToCode = "src\\main\\resources\\ProgrammText1.prog";
    String pathToConfig;


    CodeLoader mainCodeLoader = null;
    LanguageConfigurator configurator = null;

    public Interpretator(CodeLoader codeLoader) {
        loadCodeToArrayOfCode();
        allCode = mainCodeLoader.getArrayOfCode();

        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextLine() {
        curLine++;
    }

    public void goTo(Integer line) {
        curLine = line;
    }

    //int a=0
    public Lex checkLine(Integer lineNumb, String line) throws Exception {

        for (Map.Entry<String, Class> entry : ops.entrySet()) {
            String regex = entry.getKey();
            Class aClass = entry.getValue();

            if (line.matches(regex)) {
                try {
                    return (Lex) aClass.getConstructor(String.class).newInstance(line);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new Exception("No such lex: " + line);
    }

    public void run() throws Exception {
        curLine = 0;
        while (true) {

            Lex newLex = checkLine(curLine, allCode[curLine]);

            newLex.exec(this);


        }
    }

    public class VarList {
        public List<Var> list = new ArrayList<>();

    }


    public void loadCodeToArrayOfCode() {
        mainCodeLoader = new CodeLoader(pathToCode);
    }

    public void loadLangConfig() {
        configurator = new LanguageConfigurator(pathToConfig);
    }


}
