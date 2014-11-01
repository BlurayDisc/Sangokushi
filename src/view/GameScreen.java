/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GameController;
import static controller.GameParameters.MENU_BUTTON_UNSELECTED;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import model.City;

/**
 *
 * @author RuN
 */
public class GameScreen {
    private static final GameScreen instance = new GameScreen();
    private final GameController controller;
    private final Rectangle[] menuButtons;
    private Graphics2D g;
    private Image menuImage;
    private Image hideMenuImage;
    private Image backgroundImage;
    
    private GameScreen() {
        controller = GameController.getInstance();
        menuButtons = new Rectangle[6];
        initMenuRectangles();
        initBackground();
    }
    
    public void setGraphics(Graphics g) {
        this.g = (Graphics2D) g;
    }
    
    public Graphics getGraphics() {
        return g;
    }

    private void initBackground() {
        try{
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/resources/gameBackground.png"));
            menuImage = ImageIO.read(getClass().getResourceAsStream("/resources/optionPanelBackground.png"));
            hideMenuImage = ImageIO.read(getClass().getResourceAsStream("/resources/hideOptionPanel.png"));
        } catch (Exception e){}
    }
    
    private void initMenuRectangles() {
        menuButtons[0] = new Rectangle(19 + 20, 29 + 90, 80, 40);
        menuButtons[1] = new Rectangle(19 + 20, 76 + 90, 80, 40);
        menuButtons[2] = new Rectangle(137 + 20, 29 + 90, 80, 40);
        menuButtons[3] = new Rectangle(137 + 20, 76+ 90, 80, 40);
        menuButtons[4] = new Rectangle(251 + 20, 28 + 90, 80, 40);
        menuButtons[5] = new Rectangle(251 + 20, 75 + 90, 80, 40);
    }
    
    public int getSelectedButton(MouseEvent evt) {
        for (int i = 0; i < menuButtons.length; i++) {
            if (menuButtons[i].contains(evt.getPoint())) {
                return i + 1;
            }
        }
        return MENU_BUTTON_UNSELECTED;
    }
    
    public City getSelectedCity(MouseEvent evt) {
        City city;
        for (int i = 1; i < 41; i ++) {
            city = controller.getCity(i);
            if (city.getRect().contains(evt.getPoint())) {
                return city;
            }
        }
        return null;
    }
    
    public void setMenuVisible() {        
        g.drawImage(menuImage, 20, 90, null);
        for (int i = 0; i < menuButtons.length; i++) {
            menuButtons[i].setSize(80, 40);
        }
    }
    
    public void setMenuHidden() {
        g.drawImage(hideMenuImage, 20, 90, null);
        for (int i = 0; i < menuButtons.length; i++) {
            menuButtons[i].setSize(0, 0);
        }
    }
    
    public void drawBackground() {
        g.drawImage(backgroundImage, 0, 0, null);
    }
    
    public void drawPlayerForceColour() {
        g.setColor(controller.getPlayer().getForceColor());
        g.fillRect(67, 36, 25, 25);
    }
    
    public void restoreCitySquare() {
        drawCity(controller.getSelectedCity(), controller.getSelectedCity().getOwner().getForceColor());          // change city back
    }
    
    public void makeCitySquarePressed() {
        drawCityPressed(controller.getSelectedCity(), controller.getSelectedCity().getOwner().getForceColor());   // make city pressed
    }
    
    public void drawCity(City city, Color forceColour) {
        g.setStroke(new BasicStroke(2));

        // fill the square
        g.setPaint(forceColour);
        g.fill(city.getRect());
        // upper line
        g.setPaint(forceColour.brighter());
        g.drawLine(city.x + 1, city.y + 1, city.x + 25, city.y + 1);
        // bottom line
        g.setPaint(forceColour.darker());
        g.drawLine(city.x + 1, city.y + 25, city.x + 25 - 1, city.y + 25);
        // left line
        g.setPaint(forceColour.darker());
        g.drawLine(city.x + 1, city.y + 1, city.x + 1, city.y + 25);
        // right line
        g.setPaint(forceColour.brighter());
        g.drawLine(city.x + 25, city.y + 1, city.x + 25, city.y + 25);
        // making the button more 3D.
        g.setStroke(new BasicStroke(1));
        g.setPaint(forceColour.brighter());
        g.drawLine(city.x + 1, city.y, city.x + 2, city.y);
        g.setPaint(forceColour.darker());
        g.drawLine(city.x + 25 - 2, city.y + 25, city.x + 25 - 1, city.y + 25);
    }
    
    public void drawCities() {   
        City city;
        Color forceColour;
        
        // draw forces
        for (int i = 0; i < controller.getNumForces(); i++) {
            forceColour = controller.getForce(i).getForceColor();
            for (int j = 0; j < controller.getForce(i).getCityList().size(); j++) {
                city = controller.getForce(i).getCityList().get(j);
                drawCity(city, forceColour);
            }
        }
    }
    
    public void drawCityPressed(City city, Color forceColour) {       
        g.setStroke(new BasicStroke(2));

        // fill the square
        g.setPaint(forceColour);
        g.fill(city.getRect());
        
        // draw 4 lines
        
        // upper line
        g.setPaint(forceColour.darker().darker());
        g.drawLine(city.x + 1, city.y + 1, city.x + 25, city.y + 1);
        // bottom line
        g.setPaint(forceColour.darker().darker());
        g.drawLine(city.x + 1, city.y + 25, city.x + 25 - 1, city.y + 25);
        // left line
        g.setPaint(forceColour.darker().darker());
        g.drawLine(city.x + 1, city.y + 1, city.x + 1, city.y + 25);
        // right line
        g.setPaint(forceColour.darker().darker());
        g.drawLine(city.x + 25, city.y + 1, city.x + 25, city.y + 25);
    }
    
    public static GameScreen getInstance() {
        return instance;
    }
}
