package Lex;

import Core.Expr;
import Core.Interpretator;

public class PrintLex extends ProcedureLex {
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
}
