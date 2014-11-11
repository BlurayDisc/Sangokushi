/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GameController;
import static controller.GameParameters.MENU_BUTTON_UNSELECTED;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.SwingWorker;
import model.City;
import model.Player;

/**
 *
 * @author RuN
 */
public class GameScreen {
    
    private static final GameScreen instance = new GameScreen();
    private final GameController controller;
    private final Player player;
    private final Rectangle[] menuButtons;
    private Graphics2D g;
    private Image menuImage;
    private Image backgroundImage;
    private Color forceColor;
    public final int menu_height, menu_width, menu_xPos, menu_yPos;
    public final int button_xPos, button_yPos;
    
    private SwingWorker<Image, Void> worker;
    
    private GameScreen() {

        // Constants
        button_xPos = 80;
        button_yPos = 40;
        menu_xPos = 20;
        menu_yPos = 90;
        menu_height = 358;
        menu_width = 144;
        
        // Fields
        controller = GameController.getInstance();
        player = Player.getInstance(); 
        forceColor = null;
        
        // Button Array
        menuButtons = new Rectangle[6];
        
        // initSwingWorker();
        initMenuRectangles();
        initImages();
    }
    
    public void setGraphics(Graphics g) {
        this.g = (Graphics2D) g;
    }
    
    private void initSwingWorker() {
        worker = new SwingWorker<Image, Void>() {
            @Override
            public Image doInBackground() {
                Image image = loadImage();
                return image;
            }
        };
        
        storeImage();
    }
    
    private void storeImage() {
        try {
            backgroundImage = worker.get(3000, TimeUnit.MICROSECONDS);
        } catch (Exception e){}
    }
    
    private Image loadImage() {

        Image tempImage = null;

        try{
            tempImage = ImageIO.read(getClass().getResourceAsStream("/resources/gameBackground.png"));
        } catch (Exception e){}
        
        return tempImage;
    }

    private void initImages() {
        try{
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/resources/gameBackground.png"));
            menuImage = ImageIO.read(getClass().getResourceAsStream("/resources/optionPanelBackground.png"));
        } catch (Exception e){}
    }
    
    private void initMenuRectangles() {
        menuButtons[0] = new Rectangle(19 + menu_xPos, 29 + menu_yPos, button_xPos, button_yPos);
        menuButtons[1] = new Rectangle(19 + menu_xPos, 76 + menu_yPos, button_xPos, button_yPos);
        menuButtons[2] = new Rectangle(137 + menu_xPos, 29 + menu_yPos, button_xPos, button_yPos);
        menuButtons[3] = new Rectangle(137 + menu_xPos, 76+ menu_yPos, button_xPos, button_yPos);
        menuButtons[4] = new Rectangle(251 + menu_xPos, 29 + menu_yPos, button_xPos, button_yPos);
        menuButtons[5] = new Rectangle(251 + menu_xPos, 76 + menu_yPos, button_xPos, button_yPos);
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
        g.drawImage(menuImage, menu_xPos, menu_yPos, null);
        for (int i = 0; i < menuButtons.length; i++) {
            menuButtons[i].setSize(button_xPos, button_yPos);
        }
    }
    
    public void setMenuHidden() {
        for (int i = 0; i < menuButtons.length; i++) {
            menuButtons[i].setSize(0, 0);
        }
    }
    
    public void drawBackground() {
        g.drawImage(backgroundImage, 0, 0, null);
    }
    
    public void drawPlayerForceColour() {
        g.setColor(player.getPlayerForce().getForceColor());
        g.fillRect(67, 36, 25, 25);
    }
    
    // Draws all cities
    public void drawCities() {
        for (int i = 0; i < controller.getForceList().size(); i++) {
            controller.getForceList().get(i).drawCities(g);
        }
    }
    
    // Make city pressed
    public void drawCityPressed() {
        setForceColor();
        player.getSelectedCity().drawPressed(g, forceColor);
    }
    
    // Change city back
    public void drawCityReleased() {
        setForceColor();
        player.getSelectedCity().draw(g, forceColor);
    }
    
    // Sets the force color to work with
    private void setForceColor() {
        forceColor = player.getSelectedCity().getOwner().getForceColor();
    }
    
    public static GameScreen getInstance() {
        return instance;
    }
}
