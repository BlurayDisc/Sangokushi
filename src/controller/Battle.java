/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Arrays;
import model.Army;
import model.Dice;

/**
 *
 * @author RuN

 This class implements Risk Battle Logics. 3 Dice are rolled each time, 
 dice logics are different due to which side is attacking.
 Added dice logic support for field fieldBattle situations.
 This will be used as the new fieldBattle logics system.
 */
public class Battle {

    private final int[] playerResult, enemyResult;
    private final Dice playerDice, enemyDice;
    private int playerSoldiers, enemySoldiers;
    private int playerLife, enemyLife;
    private int roundCounter;
    private Mode mode;
    
    public Battle() {
        playerDice = new Dice(1, 6);
        enemyDice = new Dice(1, 6);
        playerResult = new int[3];
        enemyResult = new int[3];
        mode = Mode.BATTLE;
        playerLife = 3; 
        enemyLife = 3;
    }
    
    public void startBattle() {
        increaseRound();
        rollDice();
        calcAttack();
        updateSoldiers();
        resetValues();
    }
    
    private void updateSoldiers() {
        playerSoldiers -= (3 - playerLife);
        enemySoldiers -= (3 - enemyLife);
    }
    
    private void calcAttack() {
        switch(mode) {
            case PLAYER_SEIGE: playerSeige(); 
                               break;
            case ENEMY_SEIGE: enemySeige(); 
                              break;
            case BATTLE: fieldBattle(); 
                         break;
        }
    }
    
    private void rollDice() {        
        for (int i = 0; i < 3; i++) {
            playerDice.rollDice();
            enemyDice.rollDice();
            playerResult[i] = playerDice.getDice();
            enemyResult[i] = enemyDice.getDice();
        }
        Arrays.sort(playerResult);
        Arrays.sort(enemyResult);
    }
    
    
    // player = enemy -> both dies
    private void fieldBattle() {
        while (playerLife >= 1 && enemyLife >= 1) {
            if (playerResult[playerLife - 1] > enemyResult[enemyLife - 1]) {
                enemyLife --;
            } else if (enemyResult[playerLife - 1] > playerResult[enemyLife - 1]){
                playerLife --;
            } else if (playerResult[playerLife - 1] == enemyResult[enemyLife - 1]) {
                enemyLife --;
                playerLife --;
            }
        }
    }
    
    // player = enemy -> player dies
    private void playerSeige() {
        while (playerLife >= 1 && enemyLife >= 1) {
            if (playerResult[playerLife - 1] > enemyResult[enemyLife - 1]) {
                enemyLife --;
            } else {
                playerLife --;
            }
        }
    }

    // player = enemy -> enemy dies
    private void enemySeige() {
        while (playerLife >= 1 && enemyLife >= 1) {
            if (enemyResult[enemyLife - 1] > playerResult[playerLife - 1]) {
                playerLife --;
            } else {
                enemyLife --;
            }
        }
    }
    
    private void resetValues() {
        playerLife = 3;
        enemyLife = 3;
    }
    
    public void increaseRound() {
        roundCounter++;
    }    
        
    public void setMode(Mode mode) {
        this.mode = mode;
    }
    
    public void setArmies(Army playerArmy, Army enemyArmy) {
        playerSoldiers = playerArmy.getSoldiers() / 100;
        enemySoldiers = enemyArmy.getSoldiers() / 100;
    }
    
    public void setSoldiers(int player, int enemy) {
        playerSoldiers = player / 100;
        enemySoldiers = enemy / 100;
    }
    
    public int getPlayerSoldiers() {
        return playerSoldiers;
    }
    
    public int getEnemySoldiers() {
        return enemySoldiers;
    }
    
    public int getPlayerCasualty() {
        return 3 - playerLife;
    }
    
    public int getEnemyCasualty() {
        return 3 - enemyLife;
    }
     
    public void testBattleLogics() { 
        setMode(Mode.BATTLE);
        increaseRound();
        rollDice();
        calcAttack();
        resetValues();
        
        setMode(Mode.PLAYER_SEIGE);
        increaseRound();
        rollDice();
        calcAttack();
        resetValues();
        
        setMode(Mode.ENEMY_SEIGE);
        increaseRound();
        rollDice();
        calcAttack();
        resetValues();
    }
}
