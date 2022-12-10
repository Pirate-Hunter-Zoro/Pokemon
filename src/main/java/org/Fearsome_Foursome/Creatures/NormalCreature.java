/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall2022
 * Instructor: Prof. Brian King
 *
 * Team: Fearsome Foursome
 * Section: 02
 * Date: 11/4/22
 * Time: 12:48 PM
 *
 * Project: csci205_final_project
 * Package: org.Fearsome_Foursome.Creatures
 * Class: NormalCreature
 *
 * Description: NormalCreature abstract class implementation
 *
 * *****************************************/

package org.Fearsome_Foursome.Creatures;

/**
 * A specific, implementable extension of the {@link Creature} Class - the {@link NormalCreature}
 */
public class NormalCreature extends Creature {

    /**
     * Constructor for the {@link NormalCreature} - this initializes the relevant maps if they have not already been initialized, and sets the name attribute
     * @param nameIndex - which name to select from {@link NormalCreature}'s possible names
     */
    public NormalCreature(int nameIndex){
        // run parent initialization to initialize the maps if need be
        super();
        name = Creature.CREATURE_NAME_MAP.get(this.getClass()).get(nameIndex);
    }

}