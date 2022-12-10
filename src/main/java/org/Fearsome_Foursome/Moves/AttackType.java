/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall2022
 * Instructor: Prof. Brian King
 *
 * Group: Fearsome Foursome
 * Section: 02
 * Date: 11/7/22
 * Time: 1:33 PM
 *
 * Project: csci205_final_project
 * Package: org.Fearsome_Foursome.Moves
 * Class: SupportMove
 *
 * Description: Enumeration for the different types of attacks (strong or weak)
 *
 * *****************************************/

package org.Fearsome_Foursome.Moves;

/** The different types of attack types we can create */
public enum AttackType {

    /** Initialize the Strong accuracy and damage values */
    Strong(0.8, 150),
    Weak(1.0, 100);

    /** Fields to be defined in the constructor */
    private final double accuracy;
    private final int damage;

    /**
     * Constructor (which will be used to create exactly two objects)
     * Set the accuracy and damage
     * @param accuracy - double representing the accuracy of this attack
     * @param damage - integer representing the damage inflicted by this attack
     */
    AttackType(double accuracy, int damage){
        this.accuracy = accuracy;
        this.damage = damage;
    }

    /**
     * Simple getter for the accuracy
     * @return double
     */
    public double getAccuracy() {
        return accuracy;
    }

    /**
     * Simple getter for the damage
     * @return int
     */
    public int getDamage() {
        return damage;
    }
}
