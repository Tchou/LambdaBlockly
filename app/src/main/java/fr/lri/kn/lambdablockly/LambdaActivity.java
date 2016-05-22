package fr.lri.kn.lambdablockly;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import fr.lri.kn.calculus.TermBuilder;
import fr.lri.kn.utils.Observer;

class CustomWebChromeClient extends WebChromeClient {

    private Context mContext;

    CustomWebChromeClient(Context ctx) {
        super();
        mContext = ctx;
    }

    @Override
    public boolean onJsPrompt (WebView view, String url, String message, final String defaultValue, final JsPromptResult result)
    {
        View v = LayoutInflater.from(mContext).inflate(R.layout.variable_dialog,null);
        final EditText input = (EditText) v.findViewById(R.id.variable_name);
        input.setText(defaultValue);
        input.setSelection(defaultValue.length());
        new AlertDialog.Builder(mContext)
                .setView(v)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {

                                result.confirm(input.getText().toString());
                            }
                        })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                result.confirm(defaultValue);
                            }
                        })
                .create()
                .show();
        return true;

    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
        new AlertDialog.Builder(mContext)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                result.confirm();
                            }
                        })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                result.cancel();
                            }
                        })
                .create()
                .show();

        return true;
    }

}

public class LambdaActivity extends AppCompatActivity
                            implements Observer<String>
{
    private WebView mWebView;
    private View mCodeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mCodeView = findViewById(R.id.codeView);
        mCodeView.setVisibility(View.GONE);

        mWebView = (WebView) findViewById(R.id.webView);

        mWebView.setWebChromeClient(new CustomWebChromeClient(this));
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/blockly/index.html");
        mWebView.addJavascriptInterface(new TermBuilder(this), "JavaTermBuilder");


    }

    public void notify(String data) {
        TextView t = (TextView) findViewById(R.id.textView);
        t.setText(data);
    }
    public void evalBlock(View v) {
        mWebView.loadUrl("javascript:evalBlock()");
    }

    public void actionBlocks(MenuItem m) {
        mCodeView.setVisibility(View.GONE);
        mWebView.setVisibility(View.VISIBLE);
    }

    public void actionCode(MenuItem m) {
        mWebView.setVisibility(View.GONE);
        mCodeView.setVisibility(View.VISIBLE);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}

