/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sangokushi.old;

import sangokushi.battle.Dice;
import sangokushi.battle.Army;

/**
 *
 * @author RuN
 */
public class BattleOld
{
    private final Army playerArmy, enemyArmy;
    private final Dice playerDice, enemyDice;
    private int roundCounter;
    private int playerHp, enemyHp;
    private final int playerDmg, playerDef, playerRange, enemyDmg, enemyDef, enemyRange;

    private double diceResult;
    private int damageToEnemy, damageToPlayer;
    private int playerUnitNumber, enemyUnitNumber;
    private int playerUnitCasualty, enemyUnitCasualty;
    private int playerUnitHp, enemyUnitHp;
    
    private int p, e;
    

    public BattleOld(Army player, Army enemy) {  
        roundCounter = 0;
        playerArmy = player;
        enemyArmy = enemy;
        playerHp = playerArmy.getHP();
        playerDmg = playerArmy.getDmg();
        playerDef = playerArmy.getDef();
        playerRange = playerArmy.getRange();
        enemyHp = enemyArmy.getHP();
        enemyDmg = enemyArmy.getDmg();
        enemyDef = enemyArmy.getDef();
        enemyRange = enemyArmy.getRange();

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
    
    public void increaseRound()
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
        else                                                       // Chance: 10/12 = 83.33%, normal calcAttack with dmg modification.
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
        System.out.println(enemyArmy.getCommander().getName() + "对" + playerArmy.getCommander().getName() + 
                        "造成了" + damageToPlayer + "点伤害！");
        System.out.println(playerArmy.getCommander().getName() + " HP: " + playerHp);
        System.out.println(playerArmy.getCommander().getName() + "对" + enemyArmy.getCommander().getName() + 
                        "造成了" + damageToEnemy + "点伤害！");
        System.out.println(enemyArmy.getCommander().getName() + " HP: " + enemyHp);
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
        return playerArmy.getCommander().getName();
    }
    
    public String getEnemyName()
    {
        return enemyArmy.getCommander().getName();
    }

}
