package Core;

import Lex.*;
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
        put("^Print *\\(.*\\);$", PrintLex.class);
        put(".*=.*;$", AssignmentLex.class);
        put("^if *\\(.*\\)$", Conditional.class);
    }};

    /*public String[][] dataList = {
            {"intVar", "^Int *[a-zA-Z] *= *.*;$"},
            {"realVar", "^Real *[a-zA-Z] *= *.*;$"}
    };*/

    public static final Integer DEFAULT_INT_VALUE = 0;
    public static final Double DEFAULT_REAL_VALUE = 0.0;
    public static final String DEFAULT_STR_VALUE = "";
    public static final Boolean DEFAULT_BOOL_VALUE = false;


    public Environment thisEnvironment = null;
    //public VarList varList = new VarList();
    String[] allCode;

    private Integer curLine;

    public Integer getCurLine() {
        return curLine;
    }


    //String pathToCode = "src\\main\\resources\\ProgrammText1.prog";
    String pathToConfig;


    CodeLoader mainCodeLoader = null;
    LanguageConfigurator configurator = null;

    /**
     * Коструктор для первого интерпретатора, точка входа
     * Поэтому передаётся null в parent
     *
     * @param codeLoader
     */
    public Interpretator(CodeLoader codeLoader) {
        //loadCodeToArrayOfCode();
        allCode = codeLoader.getArrayOfCode();
        thisEnvironment = new Environment(null);

        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Interpretator(String[] code, Environment parentEnvironment) {
        thisEnvironment = new Environment(parentEnvironment);
        allCode = code;

    }

    public void nextLine() {
        curLine++;
        if (curLine == allCode.length) {
            progExit("end_of_code");
        }
    }

    private void progExit(String reason) {
        System.out.println("Exit program : " + reason);
        System.exit(0);
    }

    public void goTo(Integer line) {
        curLine = line;
    }

    //int a=0
    public Lex checkLine(Integer lineNumb, String line) throws Exception {

        for (Map.Entry<String, Class> entry : ops.entrySet()) {
            String regex = entry.getKey();
            Class aClass = entry.getValue();

            if (line.equals(""))
                return null;

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

    public void run() {
        curLine = 0;
        while (true) {

            Lex newLex = null;
            try {
                newLex = checkLine(curLine, allCode[curLine]);
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (newLex != null) {
                newLex.exec(this);
            } else {
                nextLine();
            }


        }
    }

    /*public class VarList {
        public List<Var> list = new ArrayList<>();
        public Map<String, Var> vars = new HashMap<String, Var>();

    }*/


   /* public void loadCodeToArrayOfCode() {
        mainCodeLoader = new CodeLoader(pathToCode);
    }*/

    public void loadLangConfig() {
        configurator = new LanguageConfigurator(pathToConfig);
    }


}
