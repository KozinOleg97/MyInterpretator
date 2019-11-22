package Core;

import org.mariuszgromada.math.mxparser.Expression;

public class Expr {
    public static Double eval(Interpretator.VarList vars, String code) {

        Expression e = new Expression(code);
        System.out.println();

        return e.calculate();

    }
}
