package org.Fearsome_Foursome.Battle;/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2022
 * Instructor: Pro. Brian King
 * Group: Fearsome Foursome
 * Date: 11/8/22
 * Time: 2:47 PM
 *
 * Project: csci205_final_project
 * Class: Battle.Player
 * Description:
 *
 * Responsible for creating and managing the team.
 *
 ****************************************
 */

import org.Fearsome_Foursome.Creatures.*;
import org.Fearsome_Foursome.Moves.AttackMove;
import org.Fearsome_Foursome.Moves.Move;

import java.util.HashSet;


public class Player {
    /**
     * An array of Creatures
     */
    private final Creature[] creatureArray = new Creature[6];

    /**
     * Keeps track of how many of the {@link Player}'s {@link Creature}s are dead
     */
    private int deadCount = 0;

    /**
     * Creates a team of random Pokémon
     */
    Player(){
        this.randomizeCreatures();
    }

    /**
     * Method to randomize the {@link Creature}s in the creatureArray field
     */
    private void randomizeCreatures() {
        // Create a HashSet of the indices of Pokémon have already been chosen
        HashSet<Integer> picked = new HashSet<>();

        // Create some new (full-health) creatures
        Creature[] creatureOptions = new Creature[]{new FireCreature(0), new FireCreature(1), new FireCreature(2), new WaterCreature(0),
                new WaterCreature(1),  new WaterCreature(2), new GrassCreature(0), new GrassCreature(1),
                new NormalCreature(0), new NormalCreature(1), new ElectricCreature(0), new ElectricCreature(1)};

        // keep track of where are picking from and at what position we are inserting into
        int randIndex;
        int currentCreatureArrayIndex = 0;

        // While the array of the player's Pokémon still has an empty slot, obtain a random Pokémon's index
        // and add it to the player's team but do not allow repeats
        while (picked.size() < creatureArray.length) {
            randIndex = (int) (Math.random() * creatureOptions.length);
            if (!picked.contains(randIndex)) {
                picked.add(randIndex);
                creatureArray[currentCreatureArrayIndex] = creatureOptions[randIndex];
                currentCreatureArrayIndex++;
            }
        }
    }

    /** Allows user to get a Pokémon at an index in the array */
    public Creature getPokeCreature(int i){
        return creatureArray[i];
    }

    /** Allows incrementation of the number of dead {@link Creature}s */
    public void incrementDead(){
        deadCount++;
    }

    /** Getter for the amount of recorded dead {@link Creature}s */
    public int getDeadCount() { return deadCount; }

    /** Getter for the array of {@link Creature}s */
    public Creature[] getCreatureArray() {
        return creatureArray;
    }

    /**
     * Method to determine if this {@link Player} has a living {@link Creature} which is not weak against an attack of the opponent class
     * @param opponentClass - the class of the opponent {@link Creature}
     * @return boolean
     */
    public boolean hasNonWeakAgainstCreature(Class opponentClass) {
        for (Creature creature : this.creatureArray) {
            int countOfMovesChecked;
            // look at all the living Creatures
            if (!creature.isDead()) {
                countOfMovesChecked = 0;
                for (Move move : Creature.CREATURE_MOVE_MAP.get(opponentClass)) {
                    if (move instanceof AttackMove && ((AttackMove) move).isStrongAgainst(creature.getClass())) {
                        // the opponent definitely has a strong move against this creature
                        break;
                    } else {
                        countOfMovesChecked++;
                    }
                }
                if (countOfMovesChecked == 4) {
                    // then we found a creature against which the opponent has no strong attack!
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to determine if this {@link Player} has a living {@link Creature} which has moves that are not weak against an attack of the opponent class
     * @param opponentClass - the class of an opponent {@link Creature}
     * @return boolean
     */
    public boolean hasNonWeakMoveAgainstCreature(Class opponentClass) {
        for (Creature creature : this.creatureArray) {
            if (!creature.isDead()) {
                int countOfNonWeakMoves = 0;
                // look at all the living Creatures
                for (Move move : Creature.CREATURE_MOVE_MAP.get(creature.getClass())) {
                    if (!(move instanceof AttackMove) || !((AttackMove) move).isWeakAgainst(opponentClass)) {
                        // then the move is not weak against the opponent
                        countOfNonWeakMoves++;
                    }
                }
                if (countOfNonWeakMoves == 4) {
                    // then we found a creature which does not have weak attacks against the opponent!
                    return true;
                }
            }
        }
        // all living creature have a weak attack against the opponent
        return false;
    }

}
   