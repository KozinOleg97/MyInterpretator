package Core;

import Types.Var;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.Map;

public class Expr {
    public static Double eval(Interpretator.VarList vars, String code) {

        String resCode = setValues(vars, code);
        Expression e = new Expression(resCode);
        //System.out.println();

        return e.calculate();

    }

    // a + 23 * f
    // a+23+f
    private static String setValues(Interpretator.VarList vars, String code) {

        String[] parts = code.split("(?=[+\\-*\\/])|(?<=[+\\-*\\/])");

        String res = "";

        Map<String, Var> curVars = vars.vars;
        //TODO сделать нормально
        for (int i = 0; i < parts.length; i++) {

            String curPart = parts[i];

            for (Map.Entry<String, Var> entry : curVars.entrySet()) {
                String varName = entry.getKey();
                Var aVar = entry.getValue();

                curPart = curPart.replace(varName, aVar.getValue().toString());

            }
            res += curPart;
        }
        return res;
    }
}
