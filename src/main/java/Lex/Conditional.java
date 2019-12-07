package Lex;

import Core.Block;
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

        Integer goToIndex;

        if (res) {
           goToIndex = doThen(inter);
        }
        else  goToIndex = doElse(inter);

        inter.goTo(goToIndex);

    }

    private Integer doElse(Interpretator inter) {

        return null;
    }

    private Integer doThen(Interpretator inter) {
        Block block = new Block(inter);
        String[] codeFromBlock = block.getBody();
        Integer endIndex = block.getEndIndex();
        Interpretator interpretator = new Interpretator(codeFromBlock, inter.thisEnvironment);
        interpretator.run();

        return endIndex;
    }

    private String getBody(String line) {
        Integer beginIndex = line.indexOf("(") + 1;
        Integer endIndex = line.lastIndexOf(")");
        return line.substring(beginIndex, endIndex);
    }
}

