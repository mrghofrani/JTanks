package GameBasis;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

public class MapEditor {
    private final int mapWidth = 20;
    private final int mapHeight = 20;
    private JFrame frame;
    private JPanel mapViewPanel;
    private ArrayList<JLabel> texts;
    private ArrayList<JLabel> images;
    private ArrayList<JLabel> mapItems;
    private int selectedImageIndex = -1;
    private int i = 0;


    public MapEditor(){

        mapItems = new ArrayList<JLabel>();

        frame = new JFrame("Map Editor");
        frame.setSize(1500, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BorderLayout mainLayout = new BorderLayout(10 , 10);
        JPanel contentPane = new JPanel(mainLayout);

        frame.setContentPane(contentPane);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));


        JPanel itemPanel = new JPanel(new GridLayout(1,2));
        JScrollPane scrollPane = new JScrollPane(itemPanel ,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        File folderPath = new File("files\\Images\\MapEditorImages");
        images = new ArrayList<JLabel>();
        texts = new ArrayList<JLabel>();
        File[] files = folderPath.listFiles();
        GridLayout layout = new GridLayout(files.length, 2);
        itemPanel.setLayout(layout);
        for(File file : files){
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon("files\\Images\\MapEditorImages\\" + file.getName()));
            label.setPreferredSize(new Dimension(100,50));
            images.add(label);
            JLabel label1 = new JLabel(file.getName().substring(0,file.getName().indexOf('.')));
            label1.setPreferredSize(new Dimension(100,50));
            itemPanel.add(label1);
            itemPanel.add(label);
            mouseHandler handler = new mouseHandler(label1);
            label1.setOpaque(true);
            texts.add(label1);
        }

        scrollPane.setPreferredSize(itemPanel.getPreferredSize());
        rightPanel.add(scrollPane,BorderLayout.NORTH);

        contentPane.add(rightPanel , BorderLayout.EAST);

        mapViewPanel = new JPanel(new GridLayout(mapWidth, mapHeight , 1, 1));

        resetMap();

        JButton btnSave = new JButton("Save");
        JButton btnLoad = new JButton("Load");
        JButton btnClear = new JButton("Clear");

        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel inCenterPanel = new JPanel(new GridLayout(1 , 3));

        inCenterPanel.add(btnSave);
        inCenterPanel.add(btnLoad);
        inCenterPanel.add(btnClear);
        centerPanel.add(inCenterPanel , BorderLayout.NORTH);

        contentPane.add(centerPanel , BorderLayout.SOUTH);

        contentPane.add(mapViewPanel, BorderLayout.CENTER);

        btnSave.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify file name and path to save");

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    saveMap(fileToSave.getPath());

                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                }
            }
        });


        btnLoad.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int r = jfc.showOpenDialog(new JPanel());

                if (r == JFileChooser.APPROVE_OPTION) {
                    String fileName = jfc.getSelectedFile().getPath();
                    loadMap(fileName);
                }
            }
        });

        btnClear.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearMap();
                resetMap();
            }
        });

//        setItem(20,20);
    }



    private void saveMap(String path){
        if(!path.equals("")) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path)))) {
                for (int i = 1; i <= mapWidth; i++) {
                    for (int j = 1; j <= mapHeight; j++) {
                        int index = (i - 1) * mapWidth + j - 1;
                        String iconFilename = mapItems.get(index).getIcon().toString();
                        System.out.println(iconFilename);
                        switch (iconFilename) {
                            case "files\\Images\\MapEditorImages\\Ground.png":
                                writer.append(" |");
                                break;
                            case "files\\Images\\MapEditorImages\\grass.png":
                                writer.append(" :*|");
                                break;
                            case "files\\Images\\MapEditorImages\\grassOnWall.jpg":
                                writer.append("#:*|");
                                break;
                            case "files\\Images\\MapEditorImages\\hardWall.png":
                                writer.append("#|");
                                break;
                            case "files\\Images\\MapEditorImages\\softWall.png":
                                writer.append(" :&|");
                                break;
                            case "files\\Images\\MapEditorImages\\EnemyTank1.jpg":
                                writer.append(" :E1|");
                                break;
                            case "files\\Images\\MapEditorImages\\EnemyTank2.jpg":
                                writer.append(" :E2|");
                                break;
                            case "files\\Images\\MapEditorImages\\EnemyTank3.jpg":
                                writer.append(" :E3|");
                                break;
                            case "files\\Images\\MapEditorImages\\EnemyTank4.png":
                                writer.append(" :E4|");
                                break;
                            case "files\\Images\\MapEditorImages\\EnemyTank5.jpg":
                                writer.append(" :E5|");
                                break;
                            case "files\\Images\\MapEditorImages\\RepairItem.png":
                                writer.append(" :RI|");
                                break;
                            case "files\\Images\\MapEditorImages\\MachineGunCartridgeItem.png":
                                writer.append(" :MI|");
                                break;
                            case "files\\Images\\MapEditorImages\\UpgradeGunItem.png":
                                writer.append(" :UI|");
                                break;
                            case "files\\Images\\MapEditorImages\\CannonBulletCartridgeItem.png":
                                writer.append(" :CI|");
                                break;
                            case "files\\Images\\MapEditorImages\\EndPoint.png":
                                writer.append(" :F|");
                                break;
                        }
                    }
                    writer.newLine();
                }
            } catch (IOException e) {

            }
        }else{
            JOptionPane.showMessageDialog(null, "select a path and write a name for your map!","Save Error",JOptionPane.WARNING_MESSAGE);
        }
    }
    private void loadMap(String path){
        i =0;
        File file = new File(path);
        try {
            String line;
            String[] objects;
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                objects = line.split("[|]");
                for (String object : objects) {
                    drawObject(object);
                    i++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(mapItems.size());
        mapViewPanel = new JPanel(new GridLayout(mapWidth,mapHeight));
        for (JLabel label: mapItems) {
            mapViewPanel.add(label);
        }
        SwingUtilities.updateComponentTreeUI(mapViewPanel);
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public void drawObject(String objectName) {
        switch (objectName) {
            case " ":
                mapItems.get(i).setIcon(new  ImageIcon("files\\Images\\MapEditorImages\\Ground.png"));
                break;
            case "#":
                mapItems.get(i).setIcon(new ImageIcon("files\\Images\\MapEditorImages\\hardWall.png"));
                break;
            case " :&":
                mapItems.get(i).setIcon((new ImageIcon("files\\Images\\MapEditorImages\\softWall.png")));
                break;
            case "#:*":
                mapItems.get(i).setIcon(new ImageIcon("files\\Images\\MapEditorImages\\grassOnWall.jpg"));
                break;
            case " :*":
                mapItems.get(i).setIcon((new ImageIcon("files\\Images\\MapEditorImages\\grass.png")));
                break;
            case " :MI":
                mapItems.get(i).setIcon(new ImageIcon("files\\Images\\MapEditorImages\\MachineGunCartridgeItem.png"));
                break;
            case " :RI":
                mapItems.get(i).setIcon(new ImageIcon("files\\Images\\MapEditorImages\\RepairItem.png"));
                break;
            case " :CI":
                mapItems.get(i).setIcon(new ImageIcon("files\\Images\\MapEditorImages\\CannonBulletCartridgeItem.png"));
                break;
            case " :F":
                mapItems.get(i).setIcon(new ImageIcon("files\\Images\\MapEditorImages\\EndPoint.png"));
                break;
            case " :E1":
                mapItems.get(i).setIcon(new ImageIcon("files\\Images\\MapEditorImages\\EnemyTank1.jpg"));
                break;
            case " :E2":
                mapItems.get(i).setIcon(new ImageIcon("files\\Images\\MapEditorImages\\EnemyTank2.jpg"));
                break;
            case " :E3":
                mapItems.get(i).setIcon(new ImageIcon("files\\Images\\MapEditorImages\\EnemyTank3.jpg"));
                break;
            case " :E4":
                mapItems.get(i).setIcon(new ImageIcon("files\\Images\\MapEditorImages\\EnemyTank4.jpg"));
                break;
            case " :E5":
                mapItems.get(i).setIcon(new ImageIcon("files\\Images\\MapEditorImages\\EnemyTank5.jpg"));
                break;
        }
        SwingUtilities.updateComponentTreeUI(mapViewPanel);
    }

    public void setItem(int index){
//        int index = (row - 1) * mapWidth + col - 1;
        if(selectedImageIndex != -1)
            mapItems.get(index).setIcon(images.get(selectedImageIndex).getIcon());
        else
            JOptionPane.showMessageDialog(frame, "nothing has been selected!!!","Select Error",  JOptionPane.INFORMATION_MESSAGE);
    }

    public void resetMap(){
        for(int i = 0 ; i < 20 ;i++) {
            for (int j = 0; j < 20; j++) {
                JLabel label = new JLabel(new ImageIcon("files\\Images\\MapEditorImages\\Ground.png"));
                label.setPreferredSize(new Dimension(50, 50));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                mapViewPanel.add(label);
                mapItems.add(label);
                TileHandler handler = new TileHandler(label);
                label.addMouseListener(handler);
            }
        }
            SwingUtilities.updateComponentTreeUI(mapViewPanel);
    }
    public void clearMap(){
        mapItems = new ArrayList<JLabel>();
        mapViewPanel = new JPanel(new GridLayout(mapHeight, mapHeight));
        SwingUtilities.updateComponentTreeUI(mapViewPanel);
    }



    public void show(){
        frame.setVisible(true);
    }

    public class TileHandler extends mouseHandler{

        public TileHandler(JLabel label) {
            super(label);
        }
        @Override
        public void mouseClicked(MouseEvent e){
            if(!mapItems.isEmpty())
                setItem(mapItems.indexOf(label));
        }
    }

    public class mouseHandler extends MouseInputAdapter{

        protected JLabel label;
        private boolean isClicked = false;
        public mouseHandler(JLabel label){
            label.addMouseListener(this);
            this.label = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent e){
            int lastSeleceted = selectedImageIndex;
            if(!isClicked && selectedImageIndex != texts.indexOf(label)){
                isClicked = true;
                label.setBackground(Color.BLUE);
                selectedImageIndex = texts.indexOf(label);
                for(int i = 0 ; i < texts.size() ; i++) {
                    if(i == selectedImageIndex)
                        continue;
                    texts.get(i).setBackground(Color.WHITE);
                }
                System.out.println(selectedImageIndex + 1);
            }else{
                isClicked = false;
                label.setBackground(Color.WHITE);
                selectedImageIndex = lastSeleceted;
            }


        }

        @Override
        public void mousePressed(MouseEvent e){

        }

        @Override
        public void mouseEntered(MouseEvent e){

        }
        @Override
        public void mouseExited(MouseEvent e){

        }



    }
}
