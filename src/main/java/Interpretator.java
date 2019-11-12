import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * Executes the parse tree
 */
public class Interpretator {

    final Integer DEFAULT_INT_VALUE = 0;
    final Double DEFAULT_REAL_VALUE = 0.0;
    final String DEFAULT_STR_VALUE = "";
    final Boolean DEFAULT_BOOL_VALUE = false;

    String pathToCode = "src\\main\\resources\\ProgrammText1.prog";
    String pathToConfig;


    CodeLoader mainCodeLoader = null;
    LanguageConfigurator configurator = null;

    class varList {
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

    class intVar implements Var<Integer> {

        Integer value = DEFAULT_INT_VALUE;

        @Override
        public void setValue(Integer value) {

        }

        @Override
        public Integer getValue() {
            return null;
        }
    }

    class realVar implements Var<Double> {

        Double value = DEFAULT_REAL_VALUE;

        @Override
        public void setValue(Double value) {

        }

        @Override
        public Double getValue() {
            return null;
        }
    }

    class strVar implements Var<String> {

        String value = DEFAULT_STR_VALUE;

        @Override
        public void setValue(String value) {

        }

        @Override
        public String getValue() {
            return null;
        }
    }

    class boolVar implements Var<Boolean> {

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

    public void CodeToLexList(){
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(mainCodeLoader.getArrayOfCode());
    }
}
