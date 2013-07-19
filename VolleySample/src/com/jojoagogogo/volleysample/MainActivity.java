package com.jojoagogogo.volleysample;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jojoagogogo.volleysample.R;
import com.google.gson.Gson;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    static ProgressDialog dialog = null;
    public void requestJson(final Context context) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://ajax.googleapis.com/ajax/services/search/news?v=1.0&q=android";
        queue.add(new JsonObjectRequest(
                Method.GET,
                url,
                null,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonRoot) {
                        if (dialog != null){
                            dialog.dismiss();
                        }
                        Gson gson = new Gson();
                        gson.toJson(jsonRoot);
                        Toast.makeText(context,gson.toJson(jsonRoot), Toast.LENGTH_LONG).show();
                    }
                }, null));
        dialog = loadingDialog("", "now loading ... ");
        queue.start();
    }

    public void onClickRequestJson(View view){
        this.requestJson(this);
    }

    public ProgressDialog loadingDialog(String title, String message) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

        return dialog;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
