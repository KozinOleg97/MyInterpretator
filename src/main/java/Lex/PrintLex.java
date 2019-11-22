package Lex;

import Core.Expr;
import Core.Interpretator;

public class PrintLex  extends Lex{
    public PrintLex(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretator inter) {

            Object res = Expr.eval(inter.varList, code);
            System.out.println(res);

        inter.nextLine();
    }
}
