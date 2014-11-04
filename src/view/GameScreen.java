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
import model.Player;

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
    private Image backgroundImage;
    private final int MENU_LENGTH, MENU_WIDTH, MENU_X, MENU_Y;
    private final int BUTTON_LENGTH, BUTTON_WIDTH;
    
    private GameScreen() {
        controller = GameController.getInstance();
        menuButtons = new Rectangle[6];
        initMenuRectangles();
        initBackground();
        
        BUTTON_LENGTH = 80;
        BUTTON_WIDTH = 40;
        MENU_X = 20;
        MENU_Y = 90;
        MENU_LENGTH = 358;
        MENU_WIDTH = 144;
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
        } catch (Exception e){}
    }
    
    private void initMenuRectangles() {
        menuButtons[0] = new Rectangle(19 + MENU_X, 29 + MENU_Y, BUTTON_LENGTH, BUTTON_WIDTH);
        menuButtons[1] = new Rectangle(19 + MENU_X, 76 + MENU_Y, BUTTON_LENGTH, BUTTON_WIDTH);
        menuButtons[2] = new Rectangle(137 + MENU_X, 29 + MENU_Y, BUTTON_LENGTH, BUTTON_WIDTH);
        menuButtons[3] = new Rectangle(137 + MENU_X, 76+ MENU_Y, BUTTON_LENGTH, BUTTON_WIDTH);
        menuButtons[4] = new Rectangle(251 + MENU_X, 29 + MENU_Y, BUTTON_LENGTH, BUTTON_WIDTH);
        menuButtons[5] = new Rectangle(251 + MENU_X, 76 + MENU_Y, BUTTON_LENGTH, BUTTON_WIDTH);
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
        g.drawImage(menuImage, MENU_X, MENU_Y, null);
        for (int i = 0; i < menuButtons.length; i++) {
            menuButtons[i].setSize(BUTTON_LENGTH, BUTTON_WIDTH);
        }
    }
    
    public void setMenuHidden() {
        g.drawImage(backgroundImage, MENU_X, MENU_Y, MENU_X + MENU_LENGTH, MENU_Y + MENU_WIDTH, 
                                     MENU_X, MENU_Y, MENU_X + MENU_LENGTH, MENU_Y + MENU_WIDTH, null);
        for (int i = 0; i < menuButtons.length; i++) {
            menuButtons[i].setSize(0, 0);
        }
    }
    
    public void drawBackground() {
        g.drawImage(backgroundImage, 0, 0, null);
    }
    
    public void drawPlayerForceColour() {
        Player player = Player.getInstance();
        g.setColor(player.getPlayerForce().getForceColor());
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
