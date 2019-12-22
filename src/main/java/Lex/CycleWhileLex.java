package Lex;


import Core.Expr;
import Core.Interpretator;

public class CycleWhileLex extends Lex {
    public CycleWhileLex(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretator inter) throws Exception {

        String body = getBody(code);

        while (true) {
            Boolean res = Expr.multiCalc(inter, body).bolVal;

            if (res) {
                doIteration(inter);
            } else {
                doSkip(inter);
                break;
            }

        }
    }

    private void doSkip(Interpretator inter) {
        Block block = new Block(inter);
        String[] codeFromBlock = block.getBody();

        Integer end = block.getEndIndex();
        inter.goTo(end + 1);
    }

    private void doIteration(Interpretator inter) {
        Block block = new Block(inter);
        String[] codeFromBlock = block.getBody();

        Interpretator interpretator = new Interpretator(codeFromBlock, inter.thisEnvironment);
        interpretator.run();
    }


    private String getBody(String line) {
        int beginIndex = line.indexOf("(") + 1;
        int endIndex = line.lastIndexOf(")");
        return line.substring(beginIndex, endIndex);
    }
}
