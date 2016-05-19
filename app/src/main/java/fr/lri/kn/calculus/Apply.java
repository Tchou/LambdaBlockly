package fr.lri.kn.calculus;

/**
 * Created by kim on 19/05/16.
 */
public class Apply implements ITerm {
    private ITerm m;
    private ITerm n;

    public Apply(ITerm m, ITerm n) {
        this.m = m;
        this.n = n;
    }

    public ITerm eval(Env<String, ITerm> env) {

        ITerm em = m.eval(env);
        ITerm en = n.eval(env);

        if (em instanceof Lambda) {
            Lambda fun = (Lambda) em;
            String param = fun.getParam();
            ITerm body = fun.getBody();
            env.set(param, en);
            ITerm ebody = body.eval(env);
            env.remove(param);
            return ebody;
        } else return new Apply(em, en);

    }

    @Override
    public String toString() {
        return "(" + m.toString() + ") (" + n.toString() + ")";
    }
}