package Types;

abstract public class Var<V> {
    //Core.LanguageConfigurator.DataTypes;
    //Integer id;
        /*Integer intValue = null;
        Float realValue = null;
        String strValue = null;
        Boolean boolValue = null;*/
    private String name;
    V value;

    Var(String name) {
        this.name = name;
    }

    abstract public void setValue(V value);


    abstract public V getValue();

    public String getName(){
        return name;
    }
}