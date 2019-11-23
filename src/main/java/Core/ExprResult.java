package Core;

import Types.Var;

public class ExprResult {
    public String strVal;
    public Double realVal;
    public Integer intVal;
    public Boolean bolVal;

    public ExprResult(Double calculatedRes) {
        intVal = calculatedRes.intValue();
        realVal = calculatedRes;
        strVal = calculatedRes.toString();
        if (calculatedRes == 0.0) {
            bolVal = false;
        } else bolVal = true;
    }

    public ExprResult(String res) {
        strVal = res;
        try {
            realVal = Double.parseDouble(res);
        }catch (Exception e){
            realVal = null;
        }

        try {
            intVal = realVal.intValue();
        }catch (Exception e){
            realVal = null;
        }


        if (res.equals("0.0")) {
            bolVal = false;
        } else bolVal = true;
    }


}
