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
 *
 * This class implements Risk Battle Logics. 3 Dice are rolled each time, 
 * dice logics are different due to which side is attacking.
 * Added dice logic support for field fieldBattle situations.
 * This will be used as the new fieldBattle logics system.
 */
public class Battle {

    private final int[] playerDiceList, enemyDiceList;
    private final Dice playerDice, enemyDice;
    private int playerCasualty, enemyCasualty;
    private int playerSoldiers, enemySoldiers;
    private int roundCounter;
    private Mode mode;
    
    public Battle() {
        playerDice = new Dice(1, 6);
        enemyDice = new Dice(1, 6);
        playerDiceList = new int[GameController.NUMBER_OF_DICE];
        enemyDiceList = new int[GameController.NUMBER_OF_DICE];
        Arrays.fill(playerDiceList, 0);
        Arrays.fill(enemyDiceList, 0);
        
        mode = Mode.FIELD_BATTLE;
        
        playerSoldiers = 0;
        enemySoldiers = 0;
        playerCasualty = 0;
        enemyCasualty = 0;
        roundCounter = 0;
    }
    
    public void start() {
        increaseRound();
        resetValues();
        rollPlayerDice();
        rollEnemyDice();
        calcAttack();
        updateSoldiers();
    }
    
    private void updateSoldiers() {
        playerSoldiers -= playerCasualty;
        enemySoldiers -= enemyCasualty;
    }
    
    private void calcAttack() {
        switch(mode) {
            case PLAYER_SEIGE: playerSeige(); break;
            case ENEMY_SEIGE: enemySeige(); break;
            case FIELD_BATTLE: fieldBattle(); break;
            default: break;
        }
    }
    
    // Attackers have their number of dice reduced.
    private void rollPlayerDice() {
        int numDice = GameController.NUMBER_OF_DICE;
        if (mode == Mode.PLAYER_SEIGE) { numDice = GameController.PENALISED_NUMBER_OF_DICE; }
        for (int i = 0; i < numDice; i++) {
            playerDice.rollDice();
            playerDiceList[i] = playerDice.getDice();
        }
        sort(playerDiceList);
    }
    
    private void rollEnemyDice() {
        int numDice = GameController.NUMBER_OF_DICE;
        if (mode == Mode.ENEMY_SEIGE) { numDice = GameController.PENALISED_NUMBER_OF_DICE; }
        for (int i = 0; i < numDice; i++) {
            enemyDice.rollDice();
            enemyDiceList[i] = enemyDice.getDice();
        }
        sort(enemyDiceList);
    }
    
    // Bubble Sort Algorithm
    private void sort(int[] A) {
        int i, j, temp;
 
        for (i = 0; i < A.length - 1; i++) {
            for(j = A.length -1; j > i; j--) {
                if (A[j] > A[j-1]) {
                    temp = A[j-1];
                    A[j-1] = A[j];
                    A[j] = temp;
                }
            }
	}
    }

    // player = enemy -> both dies
    private void fieldBattle() {
        for (int i = 0; i < playerDiceList.length; i++) {
            if (playerDiceList[i] > enemyDiceList[i]) {
                enemyCasualty++;
            } else if (enemyDiceList[i] > playerDiceList[i]) {
                playerCasualty++;
            } else {
                enemyCasualty++;
                playerCasualty++;
            }
        }
    }
    
    // player = enemy -> player dies
    private void playerSeige() {
        for (int i = 0; i < playerDiceList.length; i++) {
            if (playerDiceList[i] != 0) {
                if (playerDiceList[i] > enemyDiceList[i]) {
                    enemyCasualty++;
                } else {
                    playerCasualty++;
                }
            }
        }
    }

    // player = enemy -> enemy dies
    private void enemySeige() {
        for (int i = 0; i < playerDiceList.length; i++) {
            if (enemyDiceList[i] != 0) {
                if (enemyDiceList[i] > playerDiceList[i]) {
                    playerCasualty++;
                } else {
                    enemyCasualty++;
                }
            }
        }
    }
    
    private void resetValues() {
        Arrays.fill(playerDiceList, 0);
        Arrays.fill(enemyDiceList, 0);
        enemyCasualty = 0;
        playerCasualty = 0;
    }
    
    public void increaseRound() {
        roundCounter++;
    }    
        
    public void setMode(Mode mode) {
        this.mode = mode;
    }
    
    public void setSoldiers(Army playerArmy, Army enemyArmy) {
        playerSoldiers = playerArmy.getSoldiers() / GameController.SOLDIER_NUMBER_PER_UNIT;
        enemySoldiers = enemyArmy.getSoldiers() / GameController.SOLDIER_NUMBER_PER_UNIT;
    }
    
    public void setSoldiers(int player, int enemy) {
        playerSoldiers = player / GameController.SOLDIER_NUMBER_PER_UNIT;
        enemySoldiers = enemy / GameController.SOLDIER_NUMBER_PER_UNIT;
    }
    
    public int getPlayerSoldiers() {
        return playerSoldiers;
    }
    
    public int getEnemySoldiers() {
        return enemySoldiers;
    }
    
    public int getPlayerCasualty() {
        return playerCasualty;
    }
    
    public int getEnemyCasualty() {
        return enemyCasualty;
    }
    
    public int getRound() {
        return roundCounter;
    }
}
