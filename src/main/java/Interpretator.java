import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.put;

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

    final Integer DEFAULT_INT_VALUE = 0;
    final Double DEFAULT_REAL_VALUE = 0.0;
    final String DEFAULT_STR_VALUE = "";
    final Boolean DEFAULT_BOOL_VALUE = false;

    VarList varList;
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
        List<Var> list;
    }

    interface Var<V> {
        //LanguageConfigurator.DataTypes;
        //Integer id;
        /*Integer intValue = null;
        Float realValue = null;
        String strValue = null;
        Boolean boolValue = null;*/

        void setValue(V value);

        V getValue();
    }

    public class IntVar implements Var<Integer> {

        Integer value = DEFAULT_INT_VALUE;

        @Override
        public void setValue(Integer value) {

        }

        @Override
        public Integer getValue() {
            return null;
        }
    }

    public class RealVar implements Var<Double> {

        Double value = DEFAULT_REAL_VALUE;

        @Override
        public void setValue(Double value) {

        }

        @Override
        public Double getValue() {
            return null;
        }
    }

    public class StrVar implements Var<String> {

        String value = DEFAULT_STR_VALUE;

        @Override
        public void setValue(String value) {

        }

        @Override
        public String getValue() {
            return null;
        }
    }

    public class BoolVar implements Var<Boolean> {

        Boolean value = DEFAULT_BOOL_VALUE;

        @Override
        public void setValue(Boolean value) {

        }

        @Override
        public Boolean getValue() {
            return null;
        }
    }

    public void loadCodeToArrayOfCode() {
        mainCodeLoader = new CodeLoader(pathToCode);
    }

    public void loadLangConfig() {
        configurator = new LanguageConfigurator(pathToConfig);
    }



}
