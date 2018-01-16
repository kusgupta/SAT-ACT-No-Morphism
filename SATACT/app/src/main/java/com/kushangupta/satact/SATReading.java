package com.kushangupta.satact;

import android.content.Intent;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;

public class SATReading extends AppCompatActivity {

    TextView textView;
    private TextToSpeech mTts;
    private int milliseconds = 1000 * 60 * 65 + 1000;
    private int test35 = 1000 * 60 * 35 + 2000;
    private int testend = 60 * 1000 * 5 + 2000;
    private int superfast = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satclock1);

        textView = (TextView) findViewById(R.id.textView);
        mTts = new TextToSpeech(SATReading.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                mTts.setLanguage(Locale.US);
                mTts.setSpeechRate((float) .85);
                mTts.setPitch((float) .8);

                mTts.speak(null, TextToSpeech.QUEUE_FLUSH, null);

            }
        });


        final CountDownTimer countDownTimer = new CountDownTimer(superfast, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                int seconds = (int) (millisUntilFinished / 1000) % 60;

                int speakingseconds = (int) (millisUntilFinished / 1000);

                textView.setText("" + (int) millisUntilFinished / 1000 / 60 + ":" + seconds);

                if (speakingseconds == 300) {
                    mTts.speak("You have 5 minutes remaining in this section.", TextToSpeech.QUEUE_FLUSH, null);
                    Log.d("TTS", "TIME WORKING1");
                }

                if (speakingseconds == 2100) {
                    mTts.speak("You have 35 minutes remaining in this section.", TextToSpeech.QUEUE_FLUSH, null);
                    Log.d("TTS", "TIME WORKING2");
                }


            }


            @Override
            public void onFinish() {
                textView.setText("STOP");
                mTts.speak("Stop work and put your pencil down.", TextToSpeech.QUEUE_ADD, null);
                mTts.speak("You will now have a 10 minute break.", TextToSpeech.QUEUE_ADD, null);
                mTts.speak("Please close your test booklet and make no use of electronic devices.", TextToSpeech.QUEUE_ADD, null);


                while (mTts.isSpeaking()){

                }

                Intent i = new Intent(getApplicationContext(), BreakOne.class);
                startActivity(i);
            }


        }.start();

    }


}


