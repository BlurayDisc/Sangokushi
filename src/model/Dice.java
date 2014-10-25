/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Random;

/**
 *
 * @author RuN
 */
public class Dice
{
    private int randomNumber;
    private Random random;
    private int lowerBound, upperBound;
    
    public Dice(int lowerBound, int upperBound)
    {
        randomNumber = 0;
        random = new Random();
        setBounds(lowerBound, upperBound);

    }
    
    // Roll the dice to generate a random number.
    public void rollDice()
    {
        randomNumber = random.nextInt((upperBound - lowerBound) + 1) + lowerBound;
    }
    
    
    // Setter for boundary values.
    private void setBounds(int lowerBound, int upperBound)
    {
        if ((upperBound <= 0) || (upperBound - lowerBound) <= 1)
        {
            System.out.println("Invalid Bound Values!");
            this.lowerBound = 0;
            this.upperBound = 6;
            // sets to a default 6 sided dice.
        }
        else
        {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
    }
    
    // Retreives the result of the dice. (Must roll first)
    public int getDice()
    {
        return randomNumber;
    }
}
