package pl.kgrzeg.duckhunt;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class gameActivity extends AppCompatActivity {
    TextView textViewName, textViewTime, textViewCounter;
    ImageView duck;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Hide the action bar
        getSupportActionBar().hide();

        textViewName = findViewById(R.id.textViewName);
        textViewTime = findViewById(R.id.textViewTime);
        textViewCounter = findViewById(R.id.textViewCounter);
        duck = findViewById(R.id.imageViewDuck);

        Bundle extras = getIntent().getExtras();
        textViewName.setText(extras.getString("nick"));

        moveDuck();

        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                textViewTime.setText(millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                textViewTime.setText("Game Over!");
            }
        }.start();
    }

    public void duckClick(View view) {
        counter++;
        textViewCounter.setText(String.valueOf(counter));

        moveDuck();
    }

    private void moveDuck() {
        Random rand = new Random();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int heightixels = metrics.heightPixels;
        int widthPixels = metrics.widthPixels;

        int duckWidth = duck.getWidth();
        int duckHeight = duck.getHeight();

        int maxX = widthPixels - duckWidth;
        int maxY = heightixels - duckHeight;
        int min = 0;


        int randomX = rand.nextInt( (maxX - min) + 1) + min;
        int randomY = rand.nextInt( (maxY - min) + 1) + min;

        duck.setX(randomX);
        duck.setY(randomY);
    }
}
