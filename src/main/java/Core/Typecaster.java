package Core;

import Errors.NoSuchVar;
import Types.Var;

public class Typecaster {
    public static void setValue(Var var, ExprResult result) throws Exception {

        if (var == null){
            throw new NoSuchVar("No such var", -1);
        }

        String type = var.getClass().getName();

        switch (type) {
            case "Types.IntVar":
                var.setValue(result.intVal);
                break;
            case "Types.RealVar":
                var.setValue(result.realVal);
                break;
            case "Types.StrVar":
                var.setValue(result.strVal);
                break;
            case "Types.BoolVar":
                var.setValue(result.bolVal);
                break;
        }

    }
}
