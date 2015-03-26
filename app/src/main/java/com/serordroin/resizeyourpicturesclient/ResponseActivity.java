package com.serordroin.resizeyourpicturesclient;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ResponseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_response);
        parseJSON();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_response, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void parseJSON(){
        TextView urlText = (TextView) findViewById(R.id.urlTextView);
        TextView oldSizeText = (TextView) findViewById(R.id.oldSizeTextView);
        TextView newSizeText = (TextView) findViewById(R.id.newSizeTextView);
        ImageView urlImageView = (ImageView) findViewById(R.id.urlImageView);
        Intent intent = getIntent();
        String responseString = intent.getStringExtra("response");
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            int errorValue = jsonObject.getInt("error");
            String urlValue = jsonObject.getString("url");
            long oldSizeValue = jsonObject.getLong("oldsize");
            long newSizeValue = jsonObject.getLong("newsize");

            oldSizeText.setText(oldSizeValue+" bytes");
            newSizeText.setText(newSizeValue+" bytes");
            urlText.setText(urlValue);

            UrlImageViewHelper.setUrlDrawable(urlImageView, urlValue);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
