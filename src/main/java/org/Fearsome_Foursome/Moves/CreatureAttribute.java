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
 * Description: Enumeration for the different attributes of creatures which could be increased with a support move
 *
 * *****************************************/

package org.Fearsome_Foursome.Moves;

/**
 * An enumeration which will be useful for the {@link SupportMove} class
 */
public enum CreatureAttribute {

    // maybe we care about the max health of a Creature
    MaxHealth,

    // maybe we care about the current health of a Creature
    Health,

    // maybe we care about the speed of a Creature
    Speed

}
