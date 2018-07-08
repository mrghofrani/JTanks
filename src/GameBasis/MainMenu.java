package GameBasis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainMenu {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel rightBottomPanel;
    private JPanel leftBottomPanel;
    private JLabel gameLogo;
    private JLabel gameImage;
    private JButton continueButton;
    private JButton easyGameButton;
    private JButton mediumGameButton;
    private JButton hardGameButton;
    private JButton soundButton;
    private ImageIcon muteIcon;
    private ImageIcon playIcon;
    private int gameLevel;
    private boolean soundState;
    private EventHandler eventHandler = new EventHandler();

    public MainMenu(){
        // initializing components
        frame = new JFrame("Wellcome to JTanks");
        mainPanel = new JPanel(new BorderLayout(5,5));
        topPanel = new JPanel();
        bottomPanel = new JPanel(new GridLayout(1,2));
        rightBottomPanel = new JPanel(new GridLayout(3,1));
        leftBottomPanel = new JPanel();


        continueButton = new JButton("Continue Old Game");
        continueButton.addActionListener(eventHandler);
        continueButton.setVisible(false);
        easyGameButton = new JButton("Easy");
        easyGameButton.setOpaque(false);
        easyGameButton.setContentAreaFilled(false);
        easyGameButton.setBorderPainted(false);
        easyGameButton.addActionListener(eventHandler);
        mediumGameButton = new JButton("Medium");
        mediumGameButton.setOpaque(false);
        mediumGameButton.setContentAreaFilled(false);
        mediumGameButton.setBorderPainted(false);
        mediumGameButton.addActionListener(eventHandler);
        hardGameButton = new JButton("Hard");
        hardGameButton.setOpaque(false);
        hardGameButton.setContentAreaFilled(false);
        hardGameButton.setBorderPainted(false);
        hardGameButton.addActionListener(eventHandler);
        soundButton = new JButton();
        soundButton.addActionListener(eventHandler);
        soundButton.setOpaque(false);
        soundButton.setContentAreaFilled(false);
        soundButton.setBorderPainted(false);
        muteIcon = new  ImageIcon("files" + File.separator + "Images" + File.separator + "mute.png");
        playIcon = new ImageIcon("files" + File.separator + "Images" + File.separator + "play.png");
        soundButton.setIcon(muteIcon);

        // Buttons part
        rightBottomPanel.add(continueButton);
        rightBottomPanel.add(easyGameButton);
        rightBottomPanel.add(mediumGameButton);
        rightBottomPanel.add(hardGameButton);
        rightBottomPanel.add(soundButton);

        // GameLogo part
        gameLogo = new JLabel();
        gameLogo.setIcon( new ImageIcon("files" + File.separator + "Images" + File.separator + "GameLogo.png"));
        topPanel.add(gameLogo);

        // GameImage part
        gameImage = new JLabel();
        gameImage.setIcon(new ImageIcon("files" + File.separator + "Images" + File.separator + "GameImage.png"));
        leftBottomPanel.add(gameImage);

        mainPanel.add(topPanel,BorderLayout.SOUTH);
        bottomPanel.add(rightBottomPanel);
        bottomPanel.add(leftBottomPanel);
        mainPanel.add(bottomPanel,BorderLayout.CENTER);
        mainPanel.setBackground(Color.BLACK);
        frame.add(mainPanel);

    }

    public void show(){
        frame.pack();
        frame.setVisible(true);
    }

    private class EventHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(continueButton)){
                // TODO we should implement this part later
            } else if(e.getSource().equals(easyGameButton)){
                gameLevel = 1;
                frame.dispose();
            } else if(e.getSource().equals(mediumGameButton)){
                gameLevel = 2;
                frame.dispose();
            } else if(e.getSource().equals(hardGameButton)){
                gameLevel = 3;
                frame.dispose();
            } else if(e.getSource().equals(soundButton)){
                if(soundButton.getIcon().equals(muteIcon)){
                    soundButton.setIcon(playIcon);
                    soundState = true;
                } else if(soundButton.getIcon().equals(playIcon)){
                    soundButton.setIcon(muteIcon);
                    soundState = false;
                }
            }
        }
    }


    public int getGameLevel(){
        return gameLevel;
    }

    public boolean getSoundState(){
        return soundState;
    }


}
