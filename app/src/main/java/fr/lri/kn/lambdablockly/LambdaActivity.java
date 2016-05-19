package fr.lri.kn.lambdablockly;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;

public class LambdaActivity extends AppCompatActivity {
    private WebView mWebView;
    final Context myApp = this;
    final class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsPrompt (WebView view, String url, String message, final String defaultValue, final JsPromptResult result)
        {

            View v = LayoutInflater.from(myApp).inflate(R.layout.variable_dialog,null);
            final EditText input = (EditText) v.findViewById(R.id.variable_name);
            input.setText(defaultValue);
            new AlertDialog.Builder(myApp)
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
            new AlertDialog.Builder(myApp)
                    .setTitle("App Titler")
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);

        mWebView = (WebView) findViewById(R.id.webView);

        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.loadUrl("file:///android_asset/blockly/index.html");



    }
}
