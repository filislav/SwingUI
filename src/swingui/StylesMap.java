/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingui;

import java.util.Map;

/**
 *
 * @author slava
 */
public class StylesMap{
    private Map<String,Object> map;
    

    public StylesMap(Map<String, Object> map) {
        this.map = map;
    }

    public StylesMap() {
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "StylesMap{" + "map=" + map + '}';
    }
    
    
}
