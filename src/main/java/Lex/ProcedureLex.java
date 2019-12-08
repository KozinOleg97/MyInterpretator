package Lex;

abstract class ProcedureLex extends Lex{
    ProcedureLex(String code) {
        super(code);
    }

    String getBody(String line) {
        int beginIndex = line.indexOf("(") + 1;
        int endIndex = line.lastIndexOf(")");
        return line.substring(beginIndex, endIndex);
    }
}
