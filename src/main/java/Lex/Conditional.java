package Lex;

import Core.Expr;
import Core.Interpretator;

public class Conditional extends Lex {
    public Conditional(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretator inter) {



        String body = getBody(code);
        Boolean res = Expr.multiCalc(inter, body).bolVal;

        if (res) {
            doThen();
        }
        else doElse();



    }

    private void doElse() {

    }

    private void doThen() {

    }

    private String getBody(String line) {
        Integer beginIndex = line.indexOf("(") + 1;
        Integer endIndex = line.lastIndexOf(")");
        return line.substring(beginIndex, endIndex);
    }
}

