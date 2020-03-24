/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingui;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.json.ParseException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author slava
 */
public class ItemChangeListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
       if(e.getStateChange() == ItemEvent.SELECTED){
           Object item = e.getItem();
         
                
       }
    }
    
}
