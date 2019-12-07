package Lex;

import Core.*;
import Types.Var;

public class AssignmentLex extends Lex {
    public AssignmentLex(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretator inter) {

        Var var = getVar(code, inter.thisEnvironment);
        ExprResult result = calcRightPart(code, var, inter);

        Typecaster.setValue(var, result);
        //var.setValue(result.getValue(var));
        inter.nextLine();

    }

    protected ExprResult calcRightPart(String code, Var var, Interpretator inter) {

        String body = getBody(code);

        return Expr.multiCalc(inter, body);
    }

    private Var getVar(String code, Environment varList) {
        Integer endIndex = code.indexOf("=");
        String varName = code.substring(0, endIndex);
        varName = varName.trim();
        //return varList.vars.get(varName);
        return varList.getVar(varName);
    }

    private String getBody(String line) {
        Integer beginIndex = line.indexOf("=") + 1;
        Integer endIndex = line.lastIndexOf(";");
        return line.substring(beginIndex, endIndex);
    }
}
