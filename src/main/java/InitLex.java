import javax.script.ScriptException;

public class InitLex extends Lex {
    public InitLex(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretator inter) {
        try {
            Object res = Expression.eval(inter.varList, code);

        } catch (ScriptException e) {
            e.printStackTrace();
        }
        inter.nextLine();
    }
}
