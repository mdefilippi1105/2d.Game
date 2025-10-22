package com.mike_d.game.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {

        soundURL[0] = getClass().getResource("/sound/background.wav");
        soundURL[1] = getClass().getResource("/sound/getkey.wav");
        soundURL[2] = getClass().getResource("/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/sound/fanfare.wav");
    }
    public void setFile (int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        }catch (Exception e){
        }
    }
    public void playSound() {
        clip.start();
    }
    public void loopAudio () {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopAudio () {
        clip.stop();
    }






}
