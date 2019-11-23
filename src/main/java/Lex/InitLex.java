package Lex;

import Core.Expr;
import Core.Interpretator;
import Types.IntVar;
import Types.RealVar;
import Types.Var;

public class InitLex extends Lex {
    public InitLex(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretator inter) {

        String typeSt = getType(code);
        String varName = getVarName(code);
        createNewVar(inter, typeSt, varName);

        inter.nextLine();
    }


    private void createNewVar(Interpretator inter, String typeSt, String varName) {
        switch (typeSt) {
            case "Int":
                //create var
                inter.varList.vars.put(varName, new IntVar(varName));

                //calc value
                Integer intVal = Expr.eval(inter.varList, getValueStr(code)).intValue();

                //set value
                Var intVar = inter.varList.vars.get(varName);
                intVar.setValue(intVal);
                break;
            case "Real":
                //create var
                inter.varList.vars.put(varName, new RealVar(varName));

                //calc value
                Double doubleVal = Expr.eval(inter.varList, getValueStr(code));

                //set value
                Var realVar = inter.varList.vars.get(varName);
                realVar.setValue(doubleVal);
                break;

        }
    }

    private String getValueStr(String line) {
        Integer indexStart = line.indexOf("=") + 1;
        Integer indexEnd = line.indexOf(";");
        return line.substring(indexStart, indexEnd).trim();
    }

    private String getType(String line) {
        String[] parts = line.split(" ");
        return parts[0];
    }

    private String getVarName(String line) {
        String[] parts = line.split(" ");
        Integer index = parts[1].indexOf("=");
        if (index == -1) {
            return parts[1].trim();
        } else {
            return parts[1].substring(0, index);
        }
    }
}
