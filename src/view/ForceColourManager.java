/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import model.City;
import model.Force;

/**
 *
 * @author RuN
 */
public class ForceColourManager
{
    private final List<Force> forceList;
    
    public ForceColourManager(List<Force> forceList)
    {
        this.forceList = forceList;
    }
    
    public void drawPlayerForceColour(Force player, Graphics2D g2)
    {
        g2.setColor(player.getForceColor());
        g2.fillRect(67, 36, 25, 25);
    }
    
    public void drawCity(City city, Color forceColour, Graphics2D g2)
    {
        g2.setStroke(new BasicStroke(2));

        // fill the square
        g2.setPaint(forceColour);
        g2.fill(city.getRect());
        // upper line
        g2.setPaint(forceColour.brighter());
        g2.drawLine(city.x + 1, city.y + 1, city.x + 25, city.y + 1);
        // bottom line
        g2.setPaint(forceColour.darker());
        g2.drawLine(city.x + 1, city.y + 25, city.x + 25 - 1, city.y + 25);
        // left line
        g2.setPaint(forceColour.darker());
        g2.drawLine(city.x + 1, city.y + 1, city.x + 1, city.y + 25);
        // right line
        g2.setPaint(forceColour.brighter());
        g2.drawLine(city.x + 25, city.y + 1, city.x + 25, city.y + 25);
        // making the button more 3D.
        g2.setStroke(new BasicStroke(1));
        g2.setPaint(forceColour.brighter());
        g2.drawLine(city.x + 1, city.y, city.x + 2, city.y);
        g2.setPaint(forceColour.darker());
        g2.drawLine(city.x + 25 - 2, city.y + 25, city.x + 25 - 1, city.y + 25);
    }
    
    public void drawCities(Graphics2D g2)
    {   
        City city;
        Color forceColour;
        
        // draw forces
        for (int i = 0; i < forceList.size(); i++)
        {
            forceColour = forceList.get(i).getForceColor();
            for (int j = 0; j < forceList.get(i).getCityList().size(); j++)
            {
                city = forceList.get(i).getCityList().get(j);
                drawCity(city, forceColour, g2);
            }
        }
    }
    
    public void drawCityPressed(City city, Color forceColour, Graphics2D g2)
    {       
        g2.setStroke(new BasicStroke(2));

        // fill the square
        g2.setPaint(forceColour);
        g2.fill(city.getRect());
        
        // draw 4 lines
        
        // upper line
        g2.setPaint(forceColour.darker().darker());
        g2.drawLine(city.x + 1, city.y + 1, city.x + 25, city.y + 1);
        // bottom line
        g2.setPaint(forceColour.darker().darker());
        g2.drawLine(city.x + 1, city.y + 25, city.x + 25 - 1, city.y + 25);
        // left line
        g2.setPaint(forceColour.darker().darker());
        g2.drawLine(city.x + 1, city.y + 1, city.x + 1, city.y + 25);
        // right line
        g2.setPaint(forceColour.darker().darker());
        g2.drawLine(city.x + 25, city.y + 1, city.x + 25, city.y + 25);
    }
}
