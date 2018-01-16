package com.kushangupta.satact;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Locale;

public class BreakOne extends AppCompatActivity {

    TextView textView;
    private TextToSpeech mTts;
    private int milliseconds = 1000 * 60 * 10;
    private int test = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakone);

        textView = (TextView) findViewById(R.id.textView);
        mTts = new TextToSpeech(BreakOne.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                mTts.setLanguage(Locale.US);
                mTts.setSpeechRate((float) .85);
                mTts.setPitch((float) .8);

                mTts.speak(null, TextToSpeech.QUEUE_FLUSH, null);

            }
        });

        CountDownTimer countDownTimer = new CountDownTimer(test, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                long minutes = (millisUntilFinished / 1000 / 60);

                int seconds = (int) (millisUntilFinished / 1000) % 60;
                textView.setText("" + (int) millisUntilFinished / 1000 / 60 + ":" + seconds);

            }

            @Override
            public void onFinish() {
                mTts.speak("The break is now over.", TextToSpeech.QUEUE_FLUSH, null);
                textView.setText("STOP");
                Intent i = new Intent(getApplicationContext(), WritingInstructions.class);
                startActivity(i);
            }
        }.start();

    }


}


