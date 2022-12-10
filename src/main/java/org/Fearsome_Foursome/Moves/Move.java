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
 * Class: FireCreature
 *
 * Description:
 *
 * *****************************************/

package org.Fearsome_Foursome.Moves;

import javafx.scene.input.MouseEvent;
import org.Fearsome_Foursome.Application.HelloPokemon;
import org.Fearsome_Foursome.Creatures.Creature;

import static org.Fearsome_Foursome.Battle.Arena.GLOBAL_ARENA;

/**
 * A functional interface that takes in a target {@link Creature} and does something to it
 */
public interface Move {

    /**
     * Every {@link Move} implementation must have a method to return a {@link String} corresponding to its name
     * @return {@link String}
     */
    String getName();

    /**
     * Every {@link Move} implementation must have a method to return a {@link String} corresponding to its description
     * @return {@link String}
     */
    String getDescription();

    /**
     * Every {@link Move} implementation must have a method to return a {@link String} corresponding to its name
     * @return {@link String}
     */
    String getColor();

    /**
     * Method to actually perform the {@link Move}'s action from the player Pokémon to the enemy Pokémon
     * @param mover - {@link Creature}
     * @param mouseEvent - {@link MouseEvent}
     */
    void act(Creature mover, MouseEvent mouseEvent);

    /**
     * Just simulate the damaging - no {@link String}s or animations - this is necessary for unit testing
     * @param mover - {@link Creature}
     * @param target - {@link Creature}
     */
    void actNoAnimation(Creature mover, Creature target);

    /**
     * Method to perform the damage/attribute changes of a {@link Move} and record the result in a {@link String}
     * @param relevant - {@link Creature} which will be affected
     * @return {@link String}
     */
     String getResultingMessage(Creature relevant);

    /**
     * Method to perform the actual move, and show the updated battle text and image viewing
     * @param mover - {@link Creature} which is performing a {@link Move}
     */
    default void doAndShowResult(Creature mover){
        String battleText = this.getResultingMessage(mover);
        HelloPokemon.arenaController.setUpNameSpriteHealth();
        if (mover == GLOBAL_ARENA.getEnemyCreatureUpFront()){
         HelloPokemon.arenaController.enemyBattleTextLog.setText(battleText);
        } else {
         HelloPokemon.arenaController.userBattleTextLog.setText(battleText);
        }
    }

}
