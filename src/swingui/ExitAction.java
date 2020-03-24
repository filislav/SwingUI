/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author slava
 */
    public class ExitAction extends AbstractAction{
        ExitAction(){
            putValue(NAME,"Exit");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
        
    }

