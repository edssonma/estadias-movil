package mx.uni.uttics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView titlepage,subtitlepage,btnexcersise;
    ImageView imgpage;
    Animation animimgpage,bttone,btntwo,btnthree,ltr;
    View bgprogress, bgprogresstop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load animation
        animimgpage = AnimationUtils.loadAnimation(this,R.anim.animimgpage);
        bttone = AnimationUtils.loadAnimation(this,R.anim.bttone);
        btntwo = AnimationUtils.loadAnimation(this,R.anim.btntwo);
        btnthree = AnimationUtils.loadAnimation(this,R.anim.btnthree);
        ltr = AnimationUtils.loadAnimation(this, R.anim.ltr);

        //import font
        Typeface Mlight = Typeface.createFromAsset(getAssets(), "fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");
        Typeface Vidaloka = Typeface.createFromAsset(getAssets(), "fonts/Vidaloka.ttf");

        titlepage = (TextView) findViewById(R.id.titlepage);
        subtitlepage = (TextView) findViewById(R.id.subtitlepage);
        btnexcersise = (TextView) findViewById(R.id.btnexcersise);
        imgpage = (ImageView) findViewById(R.id.imgpage);
        bgprogress = (View) findViewById(R.id.bgprogress);
        bgprogresstop = (View) findViewById(R.id.bgprogresstop);

        //customize font
        titlepage.setTypeface(Vidaloka);
        subtitlepage.setTypeface(Mlight);
        btnexcersise.setTypeface(Vidaloka);

        //export animate
        imgpage.startAnimation(animimgpage);
        titlepage.startAnimation(bttone);
        subtitlepage.startAnimation(bttone);

        btnexcersise.startAnimation(btnthree);
        bgprogress.startAnimation(btntwo);
        bgprogresstop.startAnimation(ltr);

        btnexcersise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, homePage.class);
                startActivity(myIntent);
            }
        });

    }
}
