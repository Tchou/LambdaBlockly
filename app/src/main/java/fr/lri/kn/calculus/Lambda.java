package fr.lri.kn.calculus;

/**
 * Created by kim on 19/05/16.
 */
public class Lambda implements ITerm {

    private String param;
    private ITerm body;

    Lambda(String param, ITerm body) {
        this.param = param;
        this.body = body;
    }
    public ITerm eval(Env<String, ITerm> env) {
        return this;
    }
    public ITerm getBody() {
        return body;
    }
    public String getParam() {
        return param;
    }
    @Override
    public String toString() {
        return "λ" +param +"•"+body.toString();
    }
}
