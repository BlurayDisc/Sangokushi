/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import model.Dice;
import model.Legion;

/**
 *
 * @author RuN
 */
public class Battle
{
    private final Legion playerLegion, enemyLegion;
    private final Dice playerDice;
    private final Dice enemyDice;
    private int roundCounter;
    private int playerHp;
    private final int playerDmg;
    private final int playerDef, playerRange;
    private int enemyHp;
    private final int enemyDmg;
    private final int enemyDef, enemyRange;
    private int p, e;
    private double diceResult;
    private int damageToEnemy, damageToPlayer;
    private int playerUnitNumber, enemyUnitNumber;
    private int playerUnitCasualty, enemyUnitCasualty;
    private int playerUnitHp, enemyUnitHp;
    
    private int[] player = new int[3], enemy = new int[3];
    private int playerIndex = 0, enemyIndex = 0;

    public Battle(Legion player, Legion enemy) {  
        roundCounter = 0;
        playerLegion = player;
        enemyLegion = enemy;
        playerHp = playerLegion.getHP();
        playerDmg = playerLegion.getDmg();
        playerDef = playerLegion.getDef();
        playerRange = playerLegion.getRange();
        enemyHp = enemyLegion.getHP();
        enemyDmg = enemyLegion.getDmg();
        enemyDef = enemyLegion.getDef();
        enemyRange = enemyLegion.getRange();

        // create 2 6-sided dice
        playerDice = new Dice(1, 6);
        enemyDice = new Dice(1, 6);
    }
   
    // These methods should be executed in the following orders:
    // 1. playerAttack()
    // 2. enemyAttack()
    // 3. endRound()
    
    public void playerAttack()
    {
        applyDiceLogic();
        playerActions();
    }
    
    public void enemyAttack()
    {
        applyDiceLogic();
        enemyActions();
    }
    
    public void incrementRoundCounter()
    {
        roundCounter++;
        //printResult();
    }
    
    private void playerActions()
    {      
        damageToEnemy = (int) (playerDmg * diceResult - enemyDef);
        if (damageLessThanDefence())
        {
            damageToEnemy = playerDmg / 5;
            if (diceResult == 0)
                damageToEnemy = 0;
            enemyHp = enemyHp - damageToEnemy;
        }
        else
        {
            enemyHp = enemyHp - damageToEnemy;
        }
    }
    
    private void enemyActions()
    {
        damageToPlayer = (int) (enemyDmg * diceResult - playerDef);     // DiceResult = 1.0 - 1.6 for normal condition and 0 or 2 for miss or crit.
        if (damageLessThanDefence())
        {
            damageToPlayer = enemyDmg / 5;
            if (diceResult == 0)
                damageToPlayer = 0;
            playerHp = playerHp - damageToPlayer;
        }
        else
        {
            playerHp = playerHp - damageToPlayer;
        }
    }
    
    private void newDiceLogics(int mode) { 
        rollDice();
        if (mode == 1) {
            playerAttacksCastle();
        } else if (mode == 2) {
            enemyAttacksCastle();
        }
    }
    
    private void rollDice() {
        for (int i = 0; i < 3; i++) {
            playerDice.rollDice();
            enemyDice.rollDice();
            player[i] = playerDice.getDice();
            enemy[i] = enemyDice.getDice();
        }
    }
    
    private void playerAttacksCastle() {
        // attack
        while (playerIndex < 2 || enemyIndex < 2) {
            if (player[playerIndex] > enemy[enemyIndex]) {
                enemyIndex++;
            } else {
                playerIndex++;
            }
        }
    }
    
    private void enemyAttacksCastle() {
        // defend
        while (playerIndex < 2 || enemyIndex < 2) {
            if (enemy[enemyIndex] > player[playerIndex]) {
                playerIndex++;
            } else {
                enemyIndex++;
            }
        }
    }
    
    // Generates variable diceResult
    // diceResult = MIN: 0, MAX: 2, NORM: 1.0 - 1.6
    
    private void applyDiceLogic()
    {
        // roll both dice
        
        playerDice.rollDice();
        enemyDice.rollDice();
        
        p = playerDice.getDice();
        e = enemyDice.getDice();
        
        if ((p == 1  && e >= 5) || (p == 2 && e == 6))         // Chance: 1/12 = 8.33%, miss and deal no dmg.
        {
            diceResult = 0;
        }
        else if ((p == 6 && e <= 2) || (p == 5 && e == 1))      // Chance: 1/12 = 8.33%, critical strike and deal 2x dmg.
        {
            diceResult = 2;
        }
        else                                                       // Chance: 10/12 = 83.33%, normal attack with dmg modification.
        {
            diceResult = p - e + 3;                                 // Range: (0) to (+6)
            diceResult =  (1 + (diceResult / 10));                     // Range: 100% to 160% Dmg
        }
    }
    
    public void calcUnitCasualty()
    {
        playerUnitCasualty = damageToPlayer % playerUnitHp;
        playerUnitNumber = playerUnitNumber - playerUnitCasualty;
    }
    
    public boolean damageLessThanDefence()
    {
        boolean isLess = false;
        if ( ( damageToEnemy < (playerDmg/5) ) || ( damageToPlayer < (enemyDmg/5) ) )
            isLess = true;
        return isLess;
    }
    
    private void printResult()
    {
        System.out.println("**************************");
        System.out.println("Round: " + roundCounter);
        System.out.println(enemyLegion.getCommander().getName() + "对" + playerLegion.getCommander().getName() + 
                        "造成了" + damageToPlayer + "点伤害！");
        System.out.println(playerLegion.getCommander().getName() + " HP: " + playerHp);
        System.out.println(playerLegion.getCommander().getName() + "对" + enemyLegion.getCommander().getName() + 
                        "造成了" + damageToEnemy + "点伤害！");
        System.out.println(enemyLegion.getCommander().getName() + " HP: " + enemyHp);
    }
    
    // Setters
    
    public void setRoundCounter(int inNumber)
    {
        roundCounter = inNumber;
    }
    
    // Getters
    public int getRoundCounter()
    {
        return roundCounter;
    }

    public int getPlayerHp()
    {
        if (playerHp < 0)
            playerHp = 0;
        return playerHp;
    }

    public int getPlayerDmg()
    {
        return playerDmg;
    }

    public int getPlayerDef()
    {
        return playerDef;
    }

    public int getPlayerRange()
    {
        return playerRange;
    }

    public int getEnemyHp()
    {
        if (enemyHp <0)
            enemyHp = 0;
        return enemyHp;
    }

    public int getEnemyDmg()
    {
        return enemyDmg;
    }

    public int getEnemyDef()
    {
        return enemyDef;
    }

    public int getEnemyRange()
    {
        return enemyRange;
    }

    public double getDiceResult()
    {
        return diceResult;
    }
    
    public int getDmgToPlayer()
    {
        return damageToPlayer;
    }
    
    public int getDmgToEnemy()
    {
        return damageToEnemy;
    }
    
    public String getPlayerName()
    {
        return playerLegion.getCommander().getName();
    }
    
    public String getEnemyName()
    {
        return enemyLegion.getCommander().getName();
    }
}
