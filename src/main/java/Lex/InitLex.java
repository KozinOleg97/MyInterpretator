package Lex;

import Core.*;
import Types.*;

public class InitLex extends AssignmentLex {
    public InitLex(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretator inter) throws Exception {

        Var var = getVar(code, inter.thisEnvironment);
        ExprResult result = calcRightPart(code, inter);


        Typecaster.setValue(var, result);

        addVarToVarList(var, inter.thisEnvironment);

        inter.nextLine();
    }

    private void addVarToVarList(Var var, Environment varList) {
        varList.putVar(var);
    }


    private Var getVar(String code, Environment varList) {

        int endIntex = code.indexOf("=");

        if (endIntex == -1) {
            String[] parts = code.split(" ");
            String varName = parts[1].replaceAll(";", "");
            return createNewVar(parts[0], varName);
        }

        String leftPart = code.substring(0, endIntex);
        leftPart = leftPart.trim();
        String[] parts = leftPart.split(" ");
        String type = parts[0];
        String name = parts[1];

        return createNewVar(type, name);
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

