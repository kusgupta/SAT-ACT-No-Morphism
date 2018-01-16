package com.kushangupta.satact;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Locale;

public class WritingInstructions extends AppCompatActivity {
    private TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_writing_instructions);

        final TextView textView = (TextView) findViewById(R.id.instructions);

        final View relative = (View) findViewById(R.id.relatived);

        mTts = new TextToSpeech(WritingInstructions.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                mTts.setLanguage(Locale.US);
                mTts.setSpeechRate((float) .85);
                mTts.setPitch((float) .8);

                if (i == TextToSpeech.SUCCESS) {
                    mTts.setOnUtteranceProgressListener(new UtteranceProgressListener() {

                        @Override
                        public void onDone(String utteranceId) {
                            Intent l = new Intent(getApplicationContext(), SATWriting.class);
                            startActivity(l);
                        }

                        @Override
                        public void onError(String utteranceId) {
                            Log.d("ttserror", "error");

                        }

                        @Override
                        public void onStart(String utteranceId) {
                            Log.d("ttsstart", "started");


                        }


                    });

                } else {
                    Log.e("MainActivity", "Initilization Failed!");
                }


                HashMap<String, String> map = new HashMap<String, String>();
                map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "messageID");
                mTts.speak(textView.getText().toString(), TextToSpeech.QUEUE_FLUSH, map);


            }


        });


        relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTts.stop();
                Intent i = new Intent(getApplicationContext(), SATWriting.class);
                startActivity(i);


            }
        });


    }

}