package GameBasis;

import ThreadPool.ThreadPool;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

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
    private JButton mapEditorButton;
    private JButton soundButton;
    private int gameLevel;
    private EventHandler eventHandler = new EventHandler();
    private Clip clip;

    public MainMenu(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        // initializing components
        backgroundSound();
        frame = new JFrame("Wellcome to JTanks");
        mainPanel = new JPanel(new BorderLayout(5,5));
        topPanel = new JPanel();
        bottomPanel = new JPanel(new GridLayout(1,2));
        rightBottomPanel = new JPanel(new GridLayout(5,1));
        leftBottomPanel = new JPanel();


        continueButton = new JButton("Continue Old Game");
        continueButton.addActionListener(eventHandler);
        continueButton.setVisible(false);
        Font font = continueButton.getFont().deriveFont(20f);
        continueButton.setFont(font);
        continueButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                continueButton.setForeground(Color.YELLOW);
            }

            @Override
            public void focusLost(FocusEvent e) {
                continueButton.setForeground(Color.YELLOW);
            }
        });


        easyGameButton = new JButton("Easy");
        easyGameButton.setForeground(Color.WHITE);
        easyGameButton.setOpaque(false);
        easyGameButton.setContentAreaFilled(false);
        easyGameButton.setBorderPainted(false);
        easyGameButton.addActionListener(eventHandler);
        easyGameButton.setFont(font);
        easyGameButton.setPreferredSize(new Dimension((int)easyGameButton.getPreferredSize().getWidth(),(int)easyGameButton.getPreferredSize().getHeight()));
        easyGameButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                selectSound();
                easyGameButton.setForeground(Color.YELLOW);
                mediumGameButton.setForeground(Color.WHITE);
                hardGameButton.setForeground(Color.WHITE);
                mapEditorButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                easyGameButton.setForeground(Color.WHITE);
            }
        });
        easyGameButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                easyGameButton.setForeground(Color.YELLOW);
                mediumGameButton.setForeground(Color.WHITE);
                hardGameButton.setForeground(Color.WHITE);
                mapEditorButton.setForeground(Color.WHITE);
                selectSound();
            }

            @Override
            public void focusLost(FocusEvent e) {
                easyGameButton.setForeground(Color.WHITE);
            }
        });




        mediumGameButton = new JButton("Medium");
        mediumGameButton.setForeground(Color.WHITE);
        mediumGameButton.setOpaque(false);
        mediumGameButton.setContentAreaFilled(false);
        mediumGameButton.setBorderPainted(false);
        mediumGameButton.addActionListener(eventHandler);
        mediumGameButton.setFont(font);
        mediumGameButton.setPreferredSize(new Dimension((int)mediumGameButton.getPreferredSize().getWidth(),(int)mediumGameButton.getPreferredSize().getHeight()));
        mediumGameButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                selectSound();
                easyGameButton.setForeground(Color.WHITE);
                mediumGameButton.setForeground(Color.YELLOW);
                hardGameButton.setForeground(Color.WHITE);
                mapEditorButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mediumGameButton.setForeground(Color.WHITE);
            }
        });
        mediumGameButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                easyGameButton.setForeground(Color.WHITE);
                mediumGameButton.setForeground(Color.YELLOW);
                hardGameButton.setForeground(Color.WHITE);
                mapEditorButton.setForeground(Color.WHITE);
                selectSound();
            }

            @Override
            public void focusLost(FocusEvent e) {
                mediumGameButton.setForeground(Color.WHITE);
            }
        });




        hardGameButton = new JButton("Hard");
        hardGameButton.setForeground(Color.WHITE);
        hardGameButton.setOpaque(false);
        hardGameButton.setContentAreaFilled(false);
        hardGameButton.setBorderPainted(false);
        hardGameButton.setFont(font);
        hardGameButton.setPreferredSize(new Dimension((int)hardGameButton.getPreferredSize().getWidth(),(int)hardGameButton.getPreferredSize().getHeight()));
        hardGameButton.addActionListener(eventHandler);
        hardGameButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                selectSound();
                easyGameButton.setForeground(Color.WHITE);
                mediumGameButton.setForeground(Color.WHITE);
                hardGameButton.setForeground(Color.YELLOW);
                mapEditorButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hardGameButton.setForeground(Color.WHITE);
            }
        });
        hardGameButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                selectSound();
                easyGameButton.setForeground(Color.WHITE);
                mediumGameButton.setForeground(Color.WHITE);
                hardGameButton.setForeground(Color.YELLOW);
                mapEditorButton.setForeground(Color.WHITE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                hardGameButton.setForeground(Color.WHITE);
            }
        });

        mapEditorButton = new JButton("Map Editor");
        mapEditorButton.setForeground(Color.WHITE);
        mapEditorButton.setOpaque(true);
        mapEditorButton.setContentAreaFilled(false);
        mapEditorButton.setBorderPainted(false);
        mapEditorButton.setFont(font);
        mapEditorButton.setPreferredSize(new Dimension((int)hardGameButton.getPreferredSize().getWidth(),(int)hardGameButton.getPreferredSize().getHeight()));
        mapEditorButton.addActionListener(eventHandler);
        mapEditorButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                selectSound();
                easyGameButton.setForeground(Color.WHITE);
                mediumGameButton.setForeground(Color.WHITE);
                hardGameButton.setForeground(Color.WHITE);
                mapEditorButton.setForeground(Color.YELLOW);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mapEditorButton.setForeground(Color.WHITE);
            }
        });
        mapEditorButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                selectSound();
                easyGameButton.setForeground(Color.WHITE);
                mediumGameButton.setForeground(Color.WHITE);
                hardGameButton.setForeground(Color.WHITE);
                mapEditorButton.setForeground(Color.YELLOW);
            }

            @Override
            public void focusLost(FocusEvent e) {
                mapEditorButton.setForeground(Color.WHITE);
            }
        });


        soundButton = new JButton();
        soundButton.addActionListener(eventHandler);
        soundButton.setOpaque(false);
        soundButton.setContentAreaFilled(false);
        soundButton.setBorderPainted(false);
//        soundButton.setIcon(muteIcon);

        // Buttons part
        rightBottomPanel.add(continueButton);
        rightBottomPanel.add(easyGameButton);
        rightBottomPanel.add(mediumGameButton);
        rightBottomPanel.add(hardGameButton);
        rightBottomPanel.add(mapEditorButton);
//        rightBottomPanel.add(soundButton);

        // GameLogo part
        gameLogo = new JLabel();
        gameLogo.setIcon( new ImageIcon("files" + File.separator + "Images" + File.separator + "GameLogo.png"));
        topPanel.add(gameLogo);

        // GameImage part
        gameImage = new JLabel();
        gameImage.setIcon(new ImageIcon("files" + File.separator + "Images" + File.separator + "GameImage.png"));
        gameImage.setOpaque(true);
        leftBottomPanel.add(gameImage);

        mainPanel.add(topPanel,BorderLayout.CENTER);
        bottomPanel.add(rightBottomPanel);
        bottomPanel.add(leftBottomPanel);
        mainPanel.add(bottomPanel,BorderLayout.SOUTH);
        mainPanel.setBackground(Color.BLACK);
        frame.add(mainPanel);

        gameImage.setBackground(Color.BLACK);
        gameLogo.setBackground(Color.BLACK);
        rightBottomPanel.setBackground(Color.BLACK);
        bottomPanel.setBackground(Color.BLACK);
        rightBottomPanel.setBackground(Color.BLACK);
        leftBottomPanel.setBackground(Color.BLACK);
        topPanel.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    /**
     * method of showing main frame
     */
    public void show(){
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    /**
     * this is a class to handle selecting levels
     */
    private class EventHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            agreeSound();
            if(e.getSource().equals(continueButton)){
                // TODO we should implement this part later
            } else if(e.getSource().equals(easyGameButton)){
                gameLevel = 1;
                easyGameButton.setForeground(Color.YELLOW);
                soundState();
            } else if(e.getSource().equals(mediumGameButton)){
                gameLevel = 2;
                mediumGameButton.setForeground(Color.YELLOW);
                soundState();
            } else if(e.getSource().equals(hardGameButton)) {
                gameLevel = 3;
                hardGameButton.setForeground(Color.YELLOW);
                soundState();
            }else if(e.getSource().equals(mapEditorButton)){
                frame.dispose();
                clip.stop();
                //(new MapEditor()).show();
            }
        }
    }

    /**
     * this method defines state of sound
     */
    private void soundState(){
        frame.dispose();
        clip.stop();
        startTheGame();
    }

    /**
     * @return the level of hard of the game
     */
    public int getGameLevel(){
        return gameLevel;
    }

    /**
     * This method is used to start the game
     */
    private void startTheGame(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFrame frame = new GameFrame("Normal Tanks");
                frame.setLocationRelativeTo(null); // put frame at center of screen
                frame.setVisible(true);
                frame.initBufferStrategy();
                // Create and execute the game-loop
                GameLoop game = new GameLoop(frame);
                game.init();
                ThreadPool.execute(game);
                // and the game starts ...
            }
        });
    }

    /**
     * This method is used to play the desired clip
     */
    private void backgroundSound(){
        try {
            File soundFile = new File("files" + File.separator + "Sounds" + File.separator + "startup.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.loop(1000);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * is used to select a sound
     */
    private void selectSound(){
        try {
            Clip clip;
            File soundFile = new File("files" + File.separator + "Sounds" + File.separator + "select.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * is use to play agree sound
     */
    private void agreeSound(){
        try {
            Clip clip;
            File soundFile = new File("files" + File.separator + "Sounds" + File.separator + "agree.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
