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
 * Description: Enumeration for the different types of support moves
 *
 * *****************************************/

package org.Fearsome_Foursome.Moves;

/** The different types of supportive moves we can create */
public enum SupportType {

    /** Create the only {@link SupportType} objects in existence */
    Healing(CreatureAttribute.Health, 75),
    Speeding(CreatureAttribute.Speed, 100);

    /** Which attribute will this {@link SupportType} correspond to increasing, and by how much? */
    public final CreatureAttribute attributeToModify;
    public final int amountToIncrease;

    /**
     * Initialize the attributes based off the arguments of the Constructor
     * This constructor will be called only in the instances where it is called above
     * @param attribute - {@link CreatureAttribute} to modify
     * @param amount - the amount by which to modify said attribute
     */
    SupportType(CreatureAttribute attribute, int amount){
        this.attributeToModify = attribute;
        this.amountToIncrease = amount;
    }

    /**
     * Simple getter for the attribute to modify
     * @return {@link CreatureAttribute}
     */
    public CreatureAttribute getAttributeToModify() {
        return attributeToModify;
    }

    /**
     * Simple getter for the amount by which the attribute will increase
     * @return int
     */
    public int getAmountToIncrease() {
        return amountToIncrease;
    }

}
