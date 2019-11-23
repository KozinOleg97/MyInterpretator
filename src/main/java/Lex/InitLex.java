package Lex;

import Core.Expr;
import Core.ExprResult;
import Core.Interpretator;
import Core.Typecaster;
import Types.*;

public class InitLex extends AssignmentLex {
    public InitLex(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretator inter) {

        Var var = getVar(code, inter.varList);
        ExprResult result = calcRightPart(code, var, inter);

        Typecaster.setValue(var, result);
        addVarToVarList(var, inter.varList);

        inter.nextLine();
    }

    private void addVarToVarList(Var var, Interpretator.VarList varList) {
        varList.vars.put(var.getName(), var);
    }


    private Var getVar(String code, Interpretator.VarList varList) {

        Integer endIntex = code.indexOf("=");
        String leftPart = code.substring(0, endIntex);
        leftPart = leftPart.trim();
        String[] parts = leftPart.split(" ");
        String type = parts[0];
        String name = parts[1];

        Var var = createNewVar(type, name);

        return var;
    }

    private Var createNewVar(String type, String name) {

        Var var = null;
        switch (type) {
            case "Int":
                var = new IntVar(name);
                break;
            case "Real":
                var = new RealVar(name);
                break;
            case "Str":
                var = new StrVar(name);
                break;
            case "Bool":
                var = new BoolVar(name);
                break;
        }
        return var;
    }
}

