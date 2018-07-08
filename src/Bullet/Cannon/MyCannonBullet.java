package Bullet.Cannon;

import ThreadPool.ThreadPool;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
//import Bullet.Explosive;

public abstract class MyCannonBullet extends CannonBullet /*implements Explosive*/ {

    protected String Explode_Sound_PATH = "files" + File.separator + "Sounds" + File.separator;
    protected String Recosh_Sound_PATH = "files" + File.separator + "Sounds" + File.separator;

    /**
     * This method initializes the variables
     * declared in the parent class
     * @param locationX the X location of where the bullet was shot
     * @param locationY the X location of where the bullet was shot
     * @param degree degree to rotate the bullet and determine the speed of bullet in both X and Y directions
     */
    protected abstract void initialize(int locationX, int locationY, int degree);

    /**
     * Every moment the bullet is moving in this method
     */
    public void move() {

        ThreadPool.execute(new MoveThread());
//        while(Board.isEmpty()){

       /* }
        if(Board.getObject() instanceof Explosive){
            // ACT
        }
        else {
            try {
                File soundFile = new File(Recosh_Sound_PATH);
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }*/
        }

        private class MoveThread implements Runnable{

            @Override
            public void run() {

                Timer timer = new Timer(30, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        locationX += speed * Math.cos(Math.toRadians(degree));
                        locationY += speed * Math.sin(Math.toRadians(degree));
                    }
                });
                timer.start();
            }
        }
    }

