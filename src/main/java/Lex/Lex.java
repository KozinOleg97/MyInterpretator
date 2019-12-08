package Lex;

import Core.Interpretator;

abstract public class Lex {

    String code;

    Lex(String code){
        this.code = code;
    }

    abstract public void exec (Interpretator inter);


}
