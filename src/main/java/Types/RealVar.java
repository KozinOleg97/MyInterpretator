package Types;

import Core.Interpretator;

public class RealVar extends Var<Double> {

    Double value = Interpretator.DEFAULT_REAL_VALUE;

    public RealVar(String name) {
        super(name);
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }
}