/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sangokushi.old;

import java.util.LinkedList;
import sangokushi.model.City;
import sangokushi.model.Force;
import sangokushi.view.GamePanel;
import javax.swing.ImageIcon;

/**
 *
 * @author RuN
 */
public class ButtonManager
{
    private final GamePanel gp;
    private String cityImageLocation;
    private String cityImagePressedLocation;
    private String forceImageLocation;
    
    public ButtonManager(GamePanel gp)
    {
        this.gp = gp;
    }
    
    public void updateCityColour(String forceName, String cityName)
    {
        setCityImageLocation(forceName);        // Gets the city image path for inForce.
        switch (cityName)                      // Sets the city image for inCity.
        {
            case "许昌": //gp.getXuchangButton().setIcon(new ImageIcon(getClass().getResource(cityImageLocation)));
                        //gp.getXuchangButton().setPressedIcon(new ImageIcon(getClass().getResource(cityImagePressedLocation)));
                        break;
            case "江陵": //gp.getJianglingButton().setIcon(new ImageIcon(getClass().getResource(cityImageLocation)));
                        //gp.getJianglingButton().setPressedIcon(new ImageIcon(getClass().getResource(cityImagePressedLocation)));
                        break;
            case "柴桑": //gp.getChaisangButton().setIcon(new ImageIcon(getClass().getResource(cityImageLocation)));
                        //gp.getChaisangButton().setPressedIcon(new ImageIcon(getClass().getResource(cityImagePressedLocation)));
                        break;
            case "襄阳": //gp.getXiangyangButton().setIcon(new ImageIcon(getClass().getResource(cityImageLocation)));
                        //gp.getXiangyangButton().setPressedIcon(new ImageIcon(getClass().getResource(cityImagePressedLocation)));
                        break;
            case "庐江": //gp.getLujiangButton().setIcon(new ImageIcon(getClass().getResource(cityImageLocation)));
                        //gp.getLujiangButton().setPressedIcon(new ImageIcon(getClass().getResource(cityImagePressedLocation)));
                        break;
            default: break;
        }
    }
    
    /**
     *
     * @param forceList List of forces.
     */
    public void initCityColour(LinkedList<Force> forceList)
    {
        int i;
        for (i = 0; i < forceList.size(); i++)
        {
            // prevent the programm from crashing
            if (forceList.get(i) == null)
                break;
            // Get the Force name and City Names for each force.
            updateCityColour(forceList.get(i).getForceName(), forceList.get(i).getCityList().get(0).getCityName());
        }
    }
    
    public void initPlayerForceColour()
    {
        //gp.getPlayerColourLabel().setIcon(new ImageIcon(getClass().getResource(forceImageLocation)));
    }
    
    public void setForceImageLocation(String forceName)
    {
        switch (forceName)
        {
            case "曹操":  forceImageLocation = "/resources/caocaoForceColour.png";
                        break;
            case "刘备":  forceImageLocation = "/resources/liubeiForceColour.png";
                        break;
            case "孙权":  forceImageLocation = "/resources/sunquanForceColour.png";
                        break;
            case "刘表":  forceImageLocation = "/resources/liubiaoForceColour.png";
                        break;
            default:      forceImageLocation = "/resources/caocaoForceColour.png";
                         break;
        }
    }
    
    public void setCityImageLocation(String forceName)
    {
        switch (forceName)
        {
            case "曹操":  cityImageLocation = "/resources/caocaoCityColour.png";
                        cityImagePressedLocation = "/resources/caocaoCityPressedColour.png";
                        break;
            case "刘备":  cityImageLocation = "/resources/liubeiCityColour.png";
                        cityImagePressedLocation = "/resources/liubeiCityPressedColour.png";
                        break;
            case "孙权":  cityImageLocation = "/resources/sunquanCityColour.png";
                        cityImagePressedLocation = "/resources/sunquanCityPressedColour.png";
                        break;
            case "刘表":  cityImageLocation = "/resources/liubiaoCityColour.png";
                        cityImagePressedLocation = "/resources/liubiaoCityPressedColour.png";
                        break;
            default:      cityImageLocation = "/resources/caocaoCityColour.png";
                         cityImagePressedLocation = "/resources/caocaoCityPressedColour.png";
                         break;
        }
    }
}
