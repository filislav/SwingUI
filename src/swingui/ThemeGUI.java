/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingui;

/**
 *
 * @author slava
 */
public class ThemeGUI {
    private String name;

    public ThemeGUI(String name) {
        this.name = name;
    }

    public ThemeGUI() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ThemeGUI{" + "name=" + name + '}';
    }
    
}
