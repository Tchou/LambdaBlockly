package fr.lri.kn.calculus;

import android.webkit.JavascriptInterface;

import java.util.ArrayDeque;
import java.util.Deque;

import fr.lri.kn.utils.Observer;

public class TermBuilder {


    private Deque<ITerm> stack;
    private Observer<String> gui;

    public TermBuilder(Observer<String> gui) {
        stack = new ArrayDeque<>();
        this.gui = gui;
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

    @JavascriptInterface
    public void eval() {
        ITerm t = getTerm();
        if (t != null) {
            ITerm s = t.eval(new Env<String, ITerm>());
            gui.notify(s.toString());
        }
    }
}
