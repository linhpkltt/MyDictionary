/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hvtroller.mydictionaryfirstui.dictionary;

import com.hvtroller.mydictionaryfirstui.AudioPlayer;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.Clip;

/**
 *
 * @author Khanh Linh
 */
public class Word {

    private String word_target;
    private String word_explain;
    private String audio_path;

    public Word() {
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
        this.audio_path = "audio\\" + word_target + ".mp3";
        getAudio(word_target);
    }

    public String getAudio_path() {
        return audio_path;
    }

    public void setAudio_path(String audio_path) {
        this.audio_path = audio_path;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    private void getAudio(String word_target) {
        String prefix = "https://ssl.gstatic.com/dictionary/static/sounds/oxford/";
        String postfix = "--_gb_1.mp3";
        String url = prefix + this.word_target + postfix;
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(this.audio_path)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            AudioPlayer player = new AudioPlayer(this.audio_path);
        } catch (IOException e) {
            // handle exception
        }
    }

}
