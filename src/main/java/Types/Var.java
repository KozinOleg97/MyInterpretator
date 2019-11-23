package Types;

import Core.ExprResult;

abstract public class Var<V> {
    //Core.LanguageConfigurator.DataTypes;
    //Integer id;
        /*Integer intValue = null;
        Float realValue = null;
        String strValue = null;
        Boolean boolValue = null;*/
    String name;
    V value;

    public Var(String name) {
        this.name = name;
    }

    abstract public void setValue(V value);


    abstract public V getValue();

    public String getName(){
        return name;
    }
}