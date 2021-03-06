package Lex;


import Core.Expr;
import Core.Interpretator;

public class Conditional extends Lex {
    public Conditional(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretator inter) throws Exception {


        String body = getBody(code);
        Boolean res = Expr.multiCalc(inter, body).bolVal;

        Integer goToIndex;

        if (res) {
            goToIndex = doThen(inter);
        } else goToIndex = doElse(inter);


        inter.goTo(goToIndex + 1);

    }

    private Integer doElse(Interpretator inter) {

        Integer beginningIndex = getSkipIndex(inter);

        inter.goTo(beginningIndex);

        Block block = new Block(inter);
        String[] codeFromBlock = block.getBody();
        Integer endIndex = block.getEndIndex();
        Interpretator interpretator = new Interpretator(codeFromBlock, inter.thisEnvironment);
        interpretator.run();

        return endIndex;
    }

    private Integer doThen(Interpretator inter) {
        Block block = new Block(inter);
        String[] codeFromBlock = block.getBody();
        Integer endIndex = block.getEndIndex();
        Interpretator interpretator = new Interpretator(codeFromBlock, inter.thisEnvironment);
        interpretator.run();

        inter.goTo(endIndex);
        endIndex = getSkipIndex(inter);

        return endIndex;
    }

    private Integer getSkipIndex(Interpretator inter) {

        Block block = new Block(inter);
        block.getBody();

        return block.getEndIndex();

    }

    private String getBody(String line) {
        int beginIndex = line.indexOf("(") + 1;
        int endIndex = line.lastIndexOf(")");
        return line.substring(beginIndex, endIndex);
    }
}

