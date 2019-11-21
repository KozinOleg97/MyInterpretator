abstract public class Lex {

    String code;

    public  Lex (String code){
        this.code = code;
    }

    abstract public void exec (Interpretator inter);


}
