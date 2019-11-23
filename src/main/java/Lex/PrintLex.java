package Lex;

import Core.Expr;
import Core.Interpretator;

public class PrintLex extends Lex {
    public PrintLex(String code) {
        super(code);
    }

    //Print();
    //Print(a);
    //Print("qwe");
    //Print(a+"qwe');
    @Override
    public void exec(Interpretator inter) {

        String body = getBody(code);
        String res = Expr.multiCalc(inter, body).strVal;

        //Object res = Expr.eval(inter.varList, code);

        System.out.println(res);

        inter.nextLine();
    }



    private String getBody(String line) {
        Integer beginIndex = line.indexOf("(") + 1;
        Integer endIndex = line.lastIndexOf(")");
        return line.substring(beginIndex, endIndex);
    }
}
