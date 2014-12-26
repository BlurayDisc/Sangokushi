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
public class ResearchCentre extends Building{
    public ResearchCentre(){
        setName("研究所");
        setCost(2000);
        setLevel(1);
    }
    
    @Override
    public void getInfo() {
        
    }
}
