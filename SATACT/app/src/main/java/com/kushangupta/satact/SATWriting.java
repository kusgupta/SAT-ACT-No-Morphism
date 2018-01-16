package com.kushangupta.satact;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Locale;

public class SATWriting extends AppCompatActivity {

    TextView textView;
    private TextToSpeech mTts;
    private int milliseconds = 1000 * 60 * 35 + 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writingclock);

        textView = (TextView) findViewById(R.id.textView);
        mTts = new TextToSpeech(SATWriting.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                mTts.setLanguage(Locale.US);
                mTts.setSpeechRate((float) .85);
                mTts.setPitch((float) .8);

                mTts.speak(null, TextToSpeech.QUEUE_FLUSH, null);

            }
        });

        CountDownTimer countDownTimer = new CountDownTimer(milliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                long minutes = (millisUntilFinished / 1000 / 60);

                int seconds = (int) (millisUntilFinished / 1000) % 60;
                textView.setText("" + (int) millisUntilFinished / 1000 / 60 + ":" + seconds);

            }

            @Override
            public void onFinish() {
                mTts.speak("Stop", TextToSpeech.QUEUE_FLUSH, null);
                textView.setText("STOP");
                //Intent i = new Intent(getApplicationContext(), SATWriting.class);
                //startActivity(i);
            }
        }.start();

    }


}


