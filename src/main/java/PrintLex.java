import javax.script.ScriptException;

public class PrintLex  extends Lex{
    public PrintLex(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretator inter) {
        try {
            Object res = Expression.eval(inter.varList, code);
            System.out.println(res);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        inter.nextLine();
    }
}
