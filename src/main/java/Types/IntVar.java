package Types;


import Core.Interpretator;

public class IntVar extends Var<Integer> {

    Integer value = Interpretator.DEFAULT_INT_VALUE;

    public IntVar(String name) {
        super(name);

    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}