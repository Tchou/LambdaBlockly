package fr.lri.kn.calculus;

/**
 * Created by kim on 19/05/16.
 */
public class Variable implements ITerm {

    private String name;

    Variable (String name) {
        this.name = name;
    }

    public ITerm eval(Env<String, ITerm> env) {
        ITerm v = env.get(name);
        if (v == null)
            return this;
        else
            return v;
    }
    @Override
    public String toString() {
        return name;
    }
}
