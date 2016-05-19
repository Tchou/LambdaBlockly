package fr.lri.kn.calculus;

/**
 * Created by kim on 19/05/16.
 */
public interface ITerm {

    ITerm eval(Env<String, ITerm> env);

}
