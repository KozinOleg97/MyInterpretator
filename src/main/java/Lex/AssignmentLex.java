package Lex;

import Core.Expr;
import Core.ExprResult;
import Core.Interpretator;
import Core.Typecaster;
import Types.Var;

public class AssignmentLex extends Lex {
    public AssignmentLex(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretator inter) {

        Var var = getVar(code, inter.varList);
        ExprResult result = calcRightPart(code, var, inter);

        Typecaster.setValue(var, result);
        //var.setValue(result.getValue(var));
        inter.nextLine();

    }

    private ExprResult calcRightPart(String code, Var var, Interpretator inter) {

        String body = getBody(code);

        return Expr.multiCalc(inter, body);
    }

    private Var getVar(String code, Interpretator.VarList varList) {
        Integer endIntex = code.indexOf("=");
        String varName = code.substring(0, endIntex);
        varName = varName.trim();
        return varList.vars.get(varName);
    }

    private String getBody(String line) {
        Integer beginIndex = line.indexOf("=") + 1;
        Integer endIndex = line.lastIndexOf(";");
        return line.substring(beginIndex, endIndex);
    }
}
