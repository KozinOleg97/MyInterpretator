package Core;

import Types.Var;
import org.mariuszgromada.math.mxparser.Expression;


public class Expr {
    public static ExprResult eval(Environment vars, String code) {

        String resCode = setValues(vars, code);


        Expression e = new Expression(resCode);
        ExprResult res = new ExprResult(e.calculate());

        return res;

    }

    // a + 23 * f
    // a+23+f
    private static String setValues(Environment vars, String code) {

        //String[] parts = code.split("(?=[+\\-*\\/])|(?<=[+\\-*\\/])");

        String modCode = code.replaceAll("\\+", " + ")
                .replaceAll("-", " - ")
                .replaceAll("\\)", " ) ")
                .replaceAll("\\*", " * ")
                .replaceAll("\\(", " ( ")
                .replaceAll("/", " / ")
                .replaceAll("<", " < ")
                .replaceAll(">", " > ")
                .replaceAll("=", " = ")
                .replaceAll("\\|", " | ")
                .replaceAll("&", " & ")
                .replaceAll("#", " # ");

        String[] parts = modCode.split(" ");

        String res = "";

        // Map<String, Var> curVars = vars.vars;
        //TODO сделать нормально
        for (int i = 0; i < parts.length; i++) {

            String curPart = parts[i];

            if (curPart.equals("")) {
                continue;
            }

            Var var = vars.getVar(curPart);

            if (var != null) {
                curPart = var.getValue().toString();
            }



            /*for (Map.Entry<String, Var> entry : curVars.entrySet()) {
                String varName = entry.getKey();
                Var aVar = entry.getValue();

                curPart = curPart.replace(varName, aVar.getValue().toString());

            }*/
            res += curPart;
        }
        return res;
    }

    public static ExprResult multiCalc(Interpretator inter, String body) {
        String exprPart = "";
        String res = "";
        Boolean stringFlag = false;
        char curSymb;
        for (int i = 0; i < body.length(); i++) {
            curSymb = body.charAt(i);

            if (curSymb != '"') {
                if (stringFlag) {
                    res += curSymb;
                } else {
                    exprPart += curSymb;
                }
            } else {
                if (stringFlag) {
                    stringFlag = false;
                } else {

                    stringFlag = true;
                    if (!exprPart.isEmpty()) {
                        //возможно нужно удалять последний +. done!
                        exprPart = exprPart.trim();
                        exprPart = exprPart.substring(0, exprPart.length() - 1);
                        res += Expr.eval(inter.thisEnvironment, exprPart).strVal;
                    }

                    exprPart = "";
                }
            }
        }
        if (!exprPart.isEmpty()) {

            exprPart = exprPart.trim();
            if (exprPart.charAt(0) == '+') {
                exprPart = exprPart.replaceFirst("\\+", "");
            }

            res += Expr.eval(inter.thisEnvironment, exprPart).strVal;
            exprPart = "";
        }

        ExprResult result = new ExprResult(res);
        return result;
    }
}
