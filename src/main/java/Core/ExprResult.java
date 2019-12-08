package Core;

public class ExprResult {
    public String strVal;
    public Double realVal;
    public Integer intVal;
    public Boolean bolVal;

    public ExprResult(Double calculatedRes) {
        intVal = calculatedRes.intValue();
        realVal = calculatedRes;
        strVal = calculatedRes.toString();
        bolVal = !(calculatedRes == 0.0);
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


        bolVal = !res.equals("0.0");
    }


}
