/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingui;

import java.awt.Color;
import static java.awt.Component.TOP_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author slava
 */
public class SwingUI {

    /**
     * @param args the command line arguments
     */
        
        
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        JFrame jFrame = getFrame();
        JPanel jPanel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        jPanel.setLayout(layout);
        jFrame.add(jPanel);
        JTextField text = new JTextField(""+0);
        JTextField result = new JTextField(5);
        JButton button = new JButton("Start");
        JLabel labelInput = new JLabel("Input your number : ");
        JLabel labelResult = new JLabel("Result :");
        JLabel labelMills = new JLabel("miles");
        JLabel labelKmH = new JLabel("km/h");
        
        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1.fill = GridBagConstraints.BOTH;
        constraints1.gridx = 1;
        constraints1.gridy = 0;
        constraints1.gridheight=1;
        constraints1.gridwidth =2;
        jPanel.add(text,constraints1);
        
        GridBagConstraints constraints2 = new GridBagConstraints();
        constraints2.fill = GridBagConstraints.BOTH;
        constraints2.gridx = 1;
        constraints2.gridy = 1;
        constraints2.gridheight=1;
        constraints2.gridwidth=1;
        jPanel.add(button,constraints2);
        
        GridBagConstraints constraints3 = new GridBagConstraints();
        constraints3.fill = GridBagConstraints.BOTH;
        constraints3.gridx = 1;
        constraints3.gridy = 2;
        constraints3.gridheight=1;
        constraints3.gridwidth=2;
        jPanel.add(result,constraints3);
        
        GridBagConstraints constraints4 = new GridBagConstraints();
        constraints4.fill = GridBagConstraints.WEST;
        constraints4.gridx = 0;
        constraints4.gridy = 0;
        constraints4.gridheight=1;
        constraints4.gridwidth=1;
        jPanel.add(labelInput,constraints4);
        
        GridBagConstraints constraints5 = new GridBagConstraints();
        constraints5.fill = GridBagConstraints.WEST;
        constraints5.gridx = 0;
        constraints5.gridy = 2;
        constraints5.gridheight=1;
        constraints5.gridwidth=1;
        jPanel.add(labelResult,constraints5);
        
        GridBagConstraints constraints6 = new GridBagConstraints();
        constraints6.fill = GridBagConstraints.EAST;
        constraints6.gridx = 3;
        constraints6.gridy = 0;
        constraints6.gridheight=1;
        constraints6.gridwidth=1;
        jPanel.add(labelMills,constraints6);
        
        GridBagConstraints constraints7 = new GridBagConstraints();
        constraints7.fill = GridBagConstraints.EAST;
        constraints7.gridx = 3;
        constraints7.gridy = 2;
        constraints7.gridheight=1;
        constraints7.gridwidth=1;
        jPanel.add(labelKmH,constraints7);
        
        jPanel.revalidate();
        button.addActionListener(e->{
            double i = Double.parseDouble(text.getText());
            result.setText("");
            Runnable convert = ()->{
            result.setText(""+new DecimalFormat("#0.00").format(1.6*i));
            };
            executor.submit(convert);
            System.out.println(Thread.currentThread().getName());
        });
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            executor.shutdown();
            System.out.println("Converting thread is closed");
            }           
        });
    }
       
    static JFrame getFrame(){
    JFrame jFrame = new JFrame(){};    
    jFrame.setVisible(true);//чтобы была видима
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //закрывалка формы
    jFrame.setBounds(500,500,280,150); // вместо setSize и SetLocation сначала location потом size
    jFrame.setTitle("Converter");
    return jFrame;
    }
}
