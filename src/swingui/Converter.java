/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingui;

import com.formdev.flatlaf.FlatDarculaLaf;
import java.awt.Color;
import java.awt.Component;
import static java.awt.Component.TOP_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.beans.EventHandler;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.json.ParseException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 *
 * @author slava
 */
public class Converter extends JFrame {
        public Converter() throws IOException{
        DateTimeFormatter dtf = DateTimeFormatter
                .ofPattern("dd/MMMM/yyyy hh:mm:ss");
        setTitle("Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(500,500,300,180);
        setVisible(true);
        JPanel panel = new JPanel();
        JTextField text = new JTextField("");
        JTextField result = new JTextField(5);
        panel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        add(panel);
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        ActionListener saveResult = e->{           
            try {
                BufferedWriter bw = new BufferedWriter(
                        new FileWriter("/home/slava/NetBeansProjects/"
                        + "SwingUI/src/result/result.txt",true));
                System.out.println(result.getText());
                bw.append(dtf.format(LocalDateTime.now()) + " : " + text.getText()+" = "
                        +result.getText()+"\n");
                JOptionPane.showMessageDialog(null, "Save result success!");
                bw.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Save result error");
            }               
        };
        ActionListener aboutProgramm = e->{
            JOptionPane.showMessageDialog(null,"Converter v 1.0.0");
        };
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(saveResult);
        file.add(save);
        file.addSeparator();
        JMenu about = new JMenu("About programm");
        JMenuItem aboutPr = new JMenuItem("Version");
        aboutPr.addActionListener(aboutProgramm);
        about.add(aboutPr);
        menuBar.add(file);
        menuBar.add(about);
        JMenuItem ex = new JMenuItem(new ExitAction());
        file.add(ex);
        setJMenuBar(menuBar);
        JButton button = new JButton("Start");
        button.setMnemonic('S');
        button.setDisplayedMnemonicIndex(0);
        button.setToolTipText("Press to Convert"); //всплывающая подсказка
        ImageIcon imgStart = new ImageIcon(getClass()
                .getResource("/images/speedometer_PNG49.png"));
        JLabel imagePanel = new JLabel();
        imagePanel.setIcon(new ImageIcon(imgStart.getImage()
                .getScaledInstance(110,110,100))); //последняя цифра повлияет на качество картинки.
        JLabel labelInput = new JLabel("Input your number : ");
        JLabel labelResult = new JLabel("Result :");
        JLabel labelMills = new JLabel("miles/h");
        JLabel labelKmH = new JLabel("km/h");
        JLabel labModel = new JLabel("Theme:");
        JLabel labelTitle = new JLabel("Designed by SONIIR in 2020");
        labelTitle.setFont(new Font("Arial",Font.BOLD,9));
        labelTitle.setForeground(Color.GRAY);
        JComboBox <String> listThemes = new JComboBox<>();
        DefaultComboBoxModel <String> model = new DefaultComboBoxModel<>();
        LookAndFeel hiberbee = IntelliJTheme.createLaf(Converter.class.getResourceAsStream("/Theme/Hiberbee.theme.json"));
//        LookAndFeel solarizedLight = IntelliJTheme.createLaf(Converter.class.getResourceAsStream("/Theme/solarized_light_theme.theme.json"));
//        LookAndFeel solarizedDark = IntelliJTheme.createLaf(Converter.class.getResourceAsStream("/Theme/solarized_dark_theme.theme.json"));
        Map<String,LookAndFeel> styleMap = new HashMap<>();
            System.out.println(hiberbee);
            
        styleMap.put("FlatDark", new FlatDarkLaf());
        styleMap.put("FlatDarcula", new FlatDarculaLaf());
        styleMap.put("FlatLight", new FlatLightLaf());
        styleMap.put(hiberbee.getName(), hiberbee);
        model.setSelectedItem(UIManager.getLookAndFeel().getName());
        listThemes.setModel(model);
        for(Entry<String,LookAndFeel>entry:styleMap.entrySet()){
            model.addElement(entry.getKey());            
        }
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String)listThemes.getSelectedItem();
                LookAndFeel laf = styleMap.get(s);
                if(laf != null){
                        try {
                            UIManager.setLookAndFeel(laf);
                            SwingUtilities.invokeLater(()->{
                               SwingUtilities.updateComponentTreeUI(panel);
                               SwingUtilities.updateComponentTreeUI(menuBar);
                            });
                        } catch (UnsupportedLookAndFeelException ex1) {
                            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex1);
//                        }catch(ClassNotFoundException e1){
//                            
//                        } catch (InstantiationException ex1) {
//                        Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex1);
//                    } catch (IllegalAccessException ex1) {
//                        Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            }
        };
        listThemes.addActionListener(al);
        
        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1.fill = GridBagConstraints.HORIZONTAL;
        constraints1.insets = new Insets(2,2,2,2); //устанавливаются отступы между элементами формы
        constraints1.gridx = 2;
        constraints1.gridy = 0;
        constraints1.gridheight=1;
        constraints1.gridwidth =2;
        panel.add(text,constraints1);
        
        GridBagConstraints constraints2 = new GridBagConstraints();
        constraints2.fill = GridBagConstraints.HORIZONTAL;
        constraints2.insets = new Insets(2,2,2,2);
        constraints2.gridx = 2;
        constraints2.gridy = 1;
        constraints2.gridheight=1;
        constraints2.gridwidth=1;
        panel.add(button,constraints2);
        
        GridBagConstraints constraints3 = new GridBagConstraints();
        constraints3.fill = GridBagConstraints.HORIZONTAL;
        constraints3.insets = new Insets(2,2,2,2);
        constraints3.gridx = 2;
        constraints3.gridy = 2;
        constraints3.gridheight=1;
        constraints3.gridwidth=2;
        result.setEditable(false);
        panel.add(result,constraints3);
        
        GridBagConstraints constraints4 = new GridBagConstraints();
        constraints4.fill = GridBagConstraints.WEST;
        constraints4.insets = new Insets(2,2,2,2);
        constraints4.anchor = GridBagConstraints.EAST;
        constraints4.gridx = 1;
        constraints4.gridy = 0;
        constraints4.gridheight=1;
        constraints4.gridwidth=1;
        panel.add(labelInput,constraints4);
        
        GridBagConstraints constraints5 = new GridBagConstraints();
        constraints5.fill = GridBagConstraints.WEST;
        constraints5.insets = new Insets(2,2,2,2);
        constraints5.anchor = GridBagConstraints.EAST;
        constraints5.gridx = 1;
        constraints5.gridy = 2;
        constraints5.gridheight=1;
        constraints5.gridwidth=1;
        panel.add(labelResult,constraints5);
        
        GridBagConstraints constraints6 = new GridBagConstraints();
        constraints6.fill = GridBagConstraints.EAST;
        constraints6.insets = new Insets(2,2,2,2);
        constraints6.gridx = 4;
        constraints6.gridy = 0;
        constraints6.gridheight=1;
        constraints6.gridwidth=1;
        panel.add(labelMills,constraints6);
        
        GridBagConstraints constraints7 = new GridBagConstraints();
        constraints7.fill = GridBagConstraints.EAST;
        constraints7.insets = new Insets(2,2,2,2);
        constraints7.gridx = 4;
        constraints7.gridy = 2;
        constraints7.gridheight=1;
        constraints7.gridwidth=1;
        panel.add(labelKmH,constraints7);
        
        GridBagConstraints constraints8 = new GridBagConstraints();
        constraints8.gridx = 0;
        constraints8.gridy = 0;
        constraints8.gridheight = 4;
        constraints8.gridwidth=1;
        panel.add(imagePanel,constraints8);
        
        GridBagConstraints constraints9 = new GridBagConstraints();
        constraints9.insets = new Insets(2,2,2,2);
        constraints9.anchor = GridBagConstraints.WEST;
        constraints9.gridx = 0;
        constraints9.gridy = 5;
        constraints9.gridheight=1;
        constraints9.gridwidth =2;
        panel.add(labelTitle,constraints9);
        
        GridBagConstraints constraints10 = new GridBagConstraints();
        constraints10.insets = new Insets(2,2,2,2);
        constraints10.anchor = GridBagConstraints.WEST;
        constraints10.gridx = 2;
        constraints10.gridy = 5;
        constraints10.gridheight=2;
        constraints10.gridwidth =2;
        panel.add(listThemes,constraints10);

        
        GridBagConstraints constraints11 = new GridBagConstraints();
        constraints11.insets = new Insets(2,2,2,2);
        constraints11.anchor = GridBagConstraints.EAST;
        constraints11.gridx = 1;
        constraints11.gridy = 5;
        constraints11.gridheight=1;
        constraints11.gridwidth =1;
        panel.add(labModel,constraints11);
        
        panel.revalidate();
        
        button.addActionListener(e->{
            CompletableFuture.supplyAsync(()->convert(text.getText()))
                    .exceptionally(e1->handleErrorInput(e1))
                    .thenAccept(re->result.setText(re));          
            text.grabFocus(); 
        });
        pack();
        }
        public static String convert(String input){
            double i=Double.parseDouble(input);
            return("" + new DecimalFormat("#0.00").format(1.6*i));
            
        }
        public static String handleErrorInput(Throwable throwable){
            JOptionPane.showMessageDialog(null,"Это было не число");
            return null;
        }
      
    public static void main(String[] args) {
                FlatDarkLaf lf = new FlatDarkLaf();               
            try {
                UIManager.setLookAndFeel(lf);
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (InstantiationException ex) {
//                Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IllegalAccessException ex) {
//                Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(FlatDarkLaf.class.getName());
         SwingUtilities.invokeLater(()->{
                try {
                    new Converter();
                } catch (IOException ex) {
                    Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
                }
         }); 
    }
}

