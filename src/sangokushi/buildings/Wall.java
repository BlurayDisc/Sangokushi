/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sangokushi.buildings;

/**
 *
 * @author RuN
 */
public class Wall extends Building{
    public Wall(){
        setName("城墙");
        setCost(1000);
        setLevel(1);
    }

    
    @Override
    public void getInfo() {
        
    }
}
