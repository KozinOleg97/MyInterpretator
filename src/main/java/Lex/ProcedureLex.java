package Lex;

abstract public class ProcedureLex extends Lex{
    public ProcedureLex(String code) {
        super(code);
    }

    protected String getBody(String line) {
        Integer beginIndex = line.indexOf("(") + 1;
        Integer endIndex = line.lastIndexOf(")");
        return line.substring(beginIndex, endIndex);
    }
}
