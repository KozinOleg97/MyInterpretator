package Lex;

import Core.Expr;
import Core.Interpretator;

public class PrintLex extends Lex {
    public PrintLex(String code) {
        super(code);
    }

    //Print();
    //Print(a);
    //Print("qwe");
    //Print(a+"qwe');
    @Override
    public void exec(Interpretator inter) {

        String body = getBody(code);
        String res = calcBody(inter, body);

        //Object res = Expr.eval(inter.varList, code);

        System.out.println(res);

        inter.nextLine();
    }

    private String calcBody(Interpretator inter, String body) {
        String exprPart = "";
        String res = "";
        Boolean stringFlag = false;
        char curSymb;
        for (int i = 0; i < body.length(); i++) {
            curSymb = body.charAt(i);

            if (curSymb != '"') {
                if (stringFlag) {
                    res += curSymb;
                } else {
                    exprPart += curSymb;
                }
            } else {
                if (stringFlag){
                    stringFlag = false;
                }
                else {

                    stringFlag = true;
                    //возможно нужно удалять последний +. done!
                    exprPart = exprPart.trim();
                    exprPart = exprPart.substring(0,exprPart.length()-1);
                    res += Expr.eval(inter.varList, exprPart).toString();
                    exprPart="";
                }
            }
        }
        if (!exprPart.isEmpty()){
            res += Expr.eval(inter.varList, exprPart).toString();
            exprPart="";
        }
        return res;
    }

    private String getBody(String line) {
        Integer beginIndex = line.indexOf("(") + 1;
        Integer endIndex = line.lastIndexOf(")");
        return line.substring(beginIndex, endIndex);
    }
}
