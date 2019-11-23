package Core;

import Types.Var;

public class Typecaster {
    public static Var setValue(Var var, ExprResult result) {

        String type = var.getClass().getName();

        switch (type){
            case "Types.IntVar":
                var.setValue(result.intVal);
                break;
            case "Types.RealVar" :
                var.setValue(result.realVal);
                break;
            case "Types.StrVar":
                var.setValue(result.strVal);
                break;
            case "Types.BoolVar":
                var.setValue(result.bolVal);
                break;
        }

        return null;
    }
}
