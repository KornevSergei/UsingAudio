package com.example.usingaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    //создаём в классе что бы было видно в методе
    MediaPlayer mediaPlayer;
    Button button;

    //создаём сикбар для звука
    SeekBar volumeSeekBar;
    //создаём для связки с сикбаром
    AudioManager audioManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //связываем с ссистемной службой
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        //устанавливаем максимальный звук
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);




        //связываем и создаём слушатель
        volumeSeekBar = findViewById(R.id.volumeSeekBar);
        volumeSeekBar.setMax(maxVolume);
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //движение полозка
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //смотрим влог на отображение движения
                Log.d("Progress changed: ", "" + progress);

                //устанавливаем звук, прогресс и начальное положение
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

            }
            //когда полозок начал движение
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            //когда полохок закончил движение
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        //связываем и устанавливаем слушатель событий для плей и паузы в одной кнопке
        button = findViewById(R.id.playStop);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //вызываем методы
                if (mediaPlayer.isPlaying()){
                    pause();
                } else {
                    play();
                }
            }
        });




        //создаём плеер и прописываем путь
//        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.minor);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.minor);
    }


    //передаём вью если не в одну кнопку
//    public void play(View view) {
    public void play() {
        mediaPlayer.start();
        button.setText("Pause");
    }

//    public void pause(View view) {
    public void pause() {
        mediaPlayer.pause();
        button.setText("Play");
    }

    //делаем плей и паузу в одну кнопку
    public void playStop(View view) {
    }
}