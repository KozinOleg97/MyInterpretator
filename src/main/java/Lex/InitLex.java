package Lex;

import Core.Expr;
import Core.Interpretator;
import Types.IntVar;

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
                inter.varList.list.add(new IntVar(varName));

                Integer val = Expr.eval(inter.varList, getValueStr(code)).intValue();

                //Установить значение переменной. Возможно поменять структуру хранения переменных
                inter.varList.list.size();

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
