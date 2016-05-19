package fr.lri.kn.calculus;

import android.webkit.JavascriptInterface;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by kim on 19/05/16.
 */
public class TermBuilder {


    private Deque<ITerm> stack;

    public TermBuilder() {
        stack = new ArrayDeque<>();
    }

    @JavascriptInterface
    public void pushVar(String v) {
        stack.push(new Variable(v));
    }

    @JavascriptInterface
    public void pushLambda(String v) {
        stack.push(new Lambda(v, stack.pop()));
    }

    @JavascriptInterface
    public void pushApply() {
        ITerm n = stack.pop();
        ITerm m = stack.pop();
        stack.push(new Apply(m,n));
    }

    @JavascriptInterface
    public void reset() {
        stack.clear();
    }


    public ITerm getTerm() {
        return stack.peek();
    }

}
