package Types;

import Core.Interpretator;

public class StrVar extends Var<String> {

    String value = Interpretator.DEFAULT_STR_VALUE;

    public StrVar(String name) {
        super(name);
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String getValue() {
        return value;
    }


}