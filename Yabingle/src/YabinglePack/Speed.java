/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YabinglePack;

/**
 *
 * @author Hoshi
 */
public enum Speed 
{
    Slow(1),Normal(2), Fast(4), Insane(12);
    
    private final int speed;
    Speed(int speed) { this.speed = speed;}
    public int getSpeed (){return speed; }
}
