package Lex;


import Core.ExprResult;
import Core.Interpretator;
import Core.Typecaster;
import Types.Var;

import java.util.Scanner;


public class ReadLex extends ProcedureLex {
    public ReadLex(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretator inter) {
        String body = getBody(code);
        Var var = inter.thisEnvironment.getVar(body);

        System.out.print("-> ");

        Scanner in = new Scanner(System.in);
        ExprResult result = new ExprResult(in.nextLine());

        Typecaster.setValue(var, result);

        inter.nextLine();
    }
}
