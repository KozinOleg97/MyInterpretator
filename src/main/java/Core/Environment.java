package Core;

import Types.Var;

import java.util.HashMap;
import java.util.Map;

public class Environment {

    private Map<String, Var> vars = new HashMap<>();

    private Environment parentEnvironment = null;
    private Integer depth = 0;

    public Environment(Environment parentEnvironment) {
        this.parentEnvironment = parentEnvironment;
        if (parentEnvironment != null) {
            this.depth = this.parentEnvironment.depth++;
        }
    }

    public void putVar(Var var) {
        vars.put(var.getName(), var);
    }

    public Var getVar(String varName) {

        Var var = null;

        Environment curEnv = this;

        while ((curEnv != null) && (var == null)) {
            var = curEnv.vars.get(varName);
            if (var == null) {
                curEnv = curEnv.parentEnvironment;
            }
        }

        return var;
    }
}
