/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall2022
 * Instructor: Prof. Brian King
 *
 * Team: Fearsome Foursome
 * Section: 02
 * Date: 12/9/22
 * Time: 7:25 PM
 *
 * Project: csci205_final_project
 * Package: org.Fearsome_Foursome.Battle
 * Class: TurnTracker
 *
 * Description: Singular Instance class which is in charge of keeping track of rounds
 *
 * *****************************************/

package org.Fearsome_Foursome.Battle;

import org.Fearsome_Foursome.Application.Controllers.ArenaController;
import org.Fearsome_Foursome.Application.HelloPokemon;

import java.util.Arrays;

import static org.Fearsome_Foursome.Battle.Arena.GLOBAL_ARENA;

public enum TurnTracker {

    /** The one instance of this class in existence */
    GLOBAL_TURN_TRACKER(Arena.GLOBAL_ARENA);

    /** The three fields of this class */
    private final Player user;
    private final Player enemy;

    /** Keeps track of who has gone */
    private boolean[] moved;

    /**
     * Constructor for this class
     * @param arena - {@link Arena}
     */
    TurnTracker(Arena arena){
        user = arena.getUser();
        enemy = arena.getEnemy();
        moved = new boolean[]{true, true};
    }

    /**
     * Useful for debugging
     */
    public void printRound() {
        System.out.println(Arrays.toString(moved));
    }

    /**
     * Refresh the round
     */
    public void refresh() {
        moved = new boolean[]{true, true};
    }

    /**
     * Return if the round has finished because both {@link Player}s have moved
     * Will start a new round
     * @return boolean
     */
    public boolean willDoNextRound() {
        if (this.canDoNextRound()){
            // round is over so start a new one
            moved[0] = false;
            moved[1] = false;

            // if the enemy is going to switch, it needs to do so NOW
            if (ArenaController.hardMode && GLOBAL_ARENA.smartToSwitchEnemy()){
                // enemy will swap if it's a good idea
                int oldUpFrontIndex = GLOBAL_ARENA.getEnemyCreatureUpFrontIndex();
                if (GLOBAL_ARENA.makeEnemyBestChoice()) {
                    // successful swap
                    if (this.takeTurnIfAllowed(GLOBAL_ARENA.getEnemy())) {
                        // allowed to take a turn
                        HelloPokemon.arenaController.setUpPokemon();

                        // TODO - this text is not showing up and I don't know why
                        HelloPokemon.arenaController.setEnemySwapBattleLog();

                        // that was a turn
                    } else {
                        // undo the swap
                        GLOBAL_ARENA.setEnemyCreatureUpFrontIndex(oldUpFrontIndex);
                    }
                }
            }

            return true;
        }
        // otherwise
        return false;
    }

    /**
     * Return only if the round has finished - no changing anything
     * @return boolean
     */
    public boolean canDoNextRound() {
        return moved[0] && moved[1];
    }

    /**
     * Keep track of when one of the {@link Player}s takes their turn
     * @param player - the {@link Player} who would like to take a turn
     */
    public boolean takeTurnIfAllowed(Player player) {
        // what will we return
        boolean allowed = false;

        // is the user trying to move?
        if (player == user && !moved[0]){
            // they get to move - record that
            moved[0] = true;
            allowed = true;
        }
        // is the enemy trying to move?
        else if (player == enemy && !moved[1]) {
            // is the enemy trying to move?
            // they get to move - record that
            moved[1] = true;
            allowed = true;
        }

        // return the result
        return allowed;
    }

    /**
     * Without changing anything, returns if a {@link Player} would be allowed to take a turn
     * @param player {@link Player}
     * @return boolean
     */
    public boolean wouldAllow(Player player) {
        // is the user trying to move?
        // is the enemy trying to move?
        // they get to move
        if (player == user && !moved[0]){
            // they get to move
            return true;
        }
        // is the enemy trying to move?
        else return player == enemy && !moved[1];

        // otherwise, it would not be allowed
    }

}