package com.hvtroller.mydictionaryfirstui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Khanh Linh
 */
public class AudioPlayer extends Thread {

    private String filePath;

    public AudioPlayer(String audioPath) {
        this.filePath = audioPath;

    }

    @Override
    public void run() {
        try {
            Player player = new Player(new FileInputStream(new File(filePath)));
            player.play();
            player.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());

        } catch (JavaLayerException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
