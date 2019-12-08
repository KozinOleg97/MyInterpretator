package Lex;

import Core.*;
import Errors.NoSuchVar;
import Errors.ValueErr;
import Types.Var;

public class AssignmentLex extends Lex {
    public AssignmentLex(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretator inter) throws Exception {

        Var var = getVar(code, inter.thisEnvironment);
        ExprResult result = calcRightPart(code, inter);


        Typecaster.setValue(var, result);


        inter.nextLine();
    }

    ExprResult calcRightPart(String code, Interpretator inter) throws Exception {

        String body = getBody(code);

        return Expr.multiCalc(inter, body);
    }

    private Var getVar(String code, Environment varList) {
        Integer endIndex = code.indexOf("=");
        String varName = code.substring(0, endIndex);
        varName = varName.trim();
        return varList.getVar(varName);
    }

    private String getBody(String line) {
        int beginIndex = line.indexOf("=") + 1;
        int endIndex = line.lastIndexOf(";");
        return line.substring(beginIndex, endIndex);
    }
}
