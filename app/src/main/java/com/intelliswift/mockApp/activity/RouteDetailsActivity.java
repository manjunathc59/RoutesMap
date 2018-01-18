package com.intelliswift.mockApp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.intelliswift.mockApp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class RouteDetailsActivity extends Activity {
    TextView tvname, tvdesc;
    ImageView routeImage, ivaccessability;

    // products JSONArray
    JSONArray jsonArray;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);
        Intent intentFromActivity = getIntent();
        if (intentFromActivity.hasExtra("RouteName")) {

            String routeName = getIntent().getStringExtra("RouteName");
            String routeDesc = getIntent().getStringExtra("Desc");
            String Accessability = getIntent().getStringExtra("Access");
            String stops = getIntent().getStringExtra("Stops");
            String image = getIntent().getStringExtra("Image");
            tvname = (TextView) findViewById(R.id.routeName);
            tvdesc = (TextView) findViewById(R.id.description);
            routeImage = (ImageView) findViewById(R.id.routeImage);
            ivaccessability = (ImageView) findViewById(R.id.access);
            Log.d("route name",stops);

            try {
                jsonArray = new JSONArray(stops);

                //traversing through all the object
                LinearLayout ll = (LinearLayout) findViewById(R.id.linearlayout);
                for (int i = 0; i < jsonArray.length(); i++) {

                    //getting product object from json array
                    JSONObject product = jsonArray.getJSONObject(i);
                    TextView tv = new TextView(this);
                    Log.d("route name",product.getString("name"));
                    tv.setText("   "+product.getString("name"));
                    tv.setId(i);
                    Drawable img = this.getResources().getDrawable( R.drawable.dot);
                    img.setBounds( 0, 0, 60, 60 );
                    tv.setCompoundDrawables(img,null,null,null);
                    ll.addView(tv);

                    if(i!=jsonArray.length()-1){

                        ImageView im = new ImageView(this);
                        im.setMaxHeight(10);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        lp.setMargins(22,0, 0,0);
                        im.setLayoutParams(lp);
                        im.setImageResource(R.drawable.line);
                        ll.addView(im);
                    }
                }
            }
            catch (JSONException e){

            }

            tvname.setText(routeName);
            tvdesc.setText(routeDesc);
            if (Accessability.equalsIgnoreCase("false")) {
                ivaccessability.setVisibility(View.INVISIBLE);
            }
            else {
                ivaccessability.setImageResource(R.drawable.access);
            }

            Picasso.with(this).load(image).fit().into(routeImage);


        }
    }
}
