package Types;

import Core.Interpretator;

public class BoolVar extends Var<Boolean> {

    Boolean value = Interpretator.DEFAULT_BOOL_VALUE;

    public BoolVar(String name) {
        super(name);
    }

    @Override
    public void setValue(Boolean value) {

    }

    @Override
    public Boolean getValue() {
        return null;
    }
}