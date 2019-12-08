package Core;

import Errors.ValueErr;
import Types.Var;
import org.mariuszgromada.math.mxparser.Expression;


public class Expr {
    private static ExprResult eval(Environment vars, String code) throws ValueErr {

        String resCode = setValues(vars, code);


        Expression e = new Expression(resCode);

        ExprResult result =  new ExprResult(e.calculate());

        if (result.strVal.equals("NaN")){
            //TODO нужно ловить некорректные выражения
            //throw new ValueErr(code ,-1 );
        }

        return result;


    }

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

        StringBuilder res = new StringBuilder();

        // Map<String, Var> curVars = vars.vars;
        //TODO сделать нормально
        for (String part : parts) {

            String curPart = part;

            if (curPart.equals("")) {
                continue;
            }

            Var var = vars.getVar(curPart);

            if (var != null) {
                curPart = var.getValue().toString();
            }

            res.append(curPart);
        }
        return res.toString();
    }

    public static ExprResult multiCalc(Interpretator inter, String body) throws Exception {
        StringBuilder exprPart = new StringBuilder();
        StringBuilder res = new StringBuilder();
        boolean stringFlag = false;
        char curSymb;
        for (int i = 0; i < body.length(); i++) {
            curSymb = body.charAt(i);

            if (curSymb != '"') {
                if (stringFlag) {
                    res.append(curSymb);
                } else {
                    exprPart.append(curSymb);
                }
            } else {
                if (stringFlag) {
                    stringFlag = false;
                } else {

                    stringFlag = true;
                    if (exprPart.length() > 0) {
                        //возможно нужно удалять последний +. done!
                        exprPart = new StringBuilder(exprPart.toString().trim());
                        exprPart = new StringBuilder(exprPart.substring(0, exprPart.length() - 1));
                        res.append(Expr.eval(inter.thisEnvironment, exprPart.toString()).strVal);
                    }

                    exprPart = new StringBuilder();
                }
            }
        }
        if (exprPart.length() > 0) {

            exprPart = new StringBuilder(exprPart.toString().trim());
            if (exprPart.charAt(0) == '+') {
                exprPart = new StringBuilder(exprPart.toString().replaceFirst("\\+", ""));
            }

            res.append(Expr.eval(inter.thisEnvironment, exprPart.toString()).strVal);
            exprPart = new StringBuilder();
        }



        return new ExprResult(res.toString());
    }
}
