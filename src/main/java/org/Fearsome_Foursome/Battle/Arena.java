/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2022
 * Instructor: Pro. Brian King
 * Name: Connor Coles
 * Date: 11/13/22
 * Time: 1:49 PM
 *
 * Project: csci205_final_project
 * Class: Arena
 * Description:
 *
 *
 ****************************************
 */
package org.Fearsome_Foursome.Battle;

import javafx.scene.input.MouseEvent;
import org.Fearsome_Foursome.Application.Controllers.ArenaController;
import org.Fearsome_Foursome.Creatures.Creature;
import org.Fearsome_Foursome.Creatures.NormalCreature;
import org.Fearsome_Foursome.Moves.AttackMove;
import org.Fearsome_Foursome.Moves.Move;
import org.Fearsome_Foursome.Moves.Moves;

import java.util.*;

public enum Arena {
    /** There is one {@link Arena} in existence; here it is */
    GLOBAL_ARENA;

    /** The player you control */
    private Player user;

    /** An AI enemy*/
    private Player enemy;

    /** Current Creature in play for player */
    private Creature userCreatureUpFront;

    /** Current Enemy Creature in play */
    private Creature enemyCreatureUpFront;

    /** Corresponding indices of the two {@link Creature}s for the player who are up front to move */
    private int userCreatureUpFrontIndex;

    /** Corresponding indices of the two {@link Creature}s for the enemy who are up front to move */
    private int enemyCreatureUpFrontIndex;

    /**
     * Keep track of the {@link Move} for the enemy Pokémon
     */
    private int enemyMoveIndex;

    /**
     * Keep track of the {@link Move} for the friendly Pokémon
     */
    private int userMoveIndex;

    /** Initializes the players*/
    Arena(){
        this.pickNewPlayers();
    }

    /**
     *  Refresh both the Player and Enemy teams
     */
    public void pickNewPlayers() {
        this.user = new Player();
        this.enemy = new Player();

        this.userCreatureUpFrontIndex = (int)(Math.random() * this.user.getCreatureArray().length);
        this.enemyCreatureUpFrontIndex = (int)(Math.random() * this.enemy.getCreatureArray().length);

        this.userCreatureUpFront = this.user.getPokeCreature(this.userCreatureUpFrontIndex);
        this.enemyCreatureUpFront = this.enemy.getPokeCreature(this.enemyCreatureUpFrontIndex);
    }

    /** Simple getter for the player with the team of friendly Pokémon */
    public Player getUser() { return user; }

    /** Simple getter for a certain friendly {@link Creature} */
    public Creature getUserCreatureUpFront() { return userCreatureUpFront; }

    /** Simple getter for the enemy with the team of enemy Pokémon */
    public Player getEnemy() { return enemy; }

    /** Simple getter for a certain enemy {@link Creature} */
    public Creature getEnemyCreatureUpFront() { return enemyCreatureUpFront; }

    /**
     * Getter for the player move index
     * @return int
     */
    public int getUserMoveIndex() {
        return userMoveIndex;
    }

    /**
     * Getter for the enemy move index
     * @return int
     */
    public int getEnemyMoveIndex() {
        return enemyMoveIndex;
    }

    /**
     * Setter for the player move index
     * @param playerMoveIndex - the index of the {@link Move}
     */
    public void setUserMoveIndex(int playerMoveIndex) {
        this.userMoveIndex = playerMoveIndex;
    }

    /**
     * Simple getter for the player's up front {@link Creature} index
     * @return int
     */
    public int getUserCreatureUpFrontIndex() {
        return userCreatureUpFrontIndex;
    }

    /**
     * Getter for the enemy {@link Creature} index up to bat
     * @return int
     */
    public int getEnemyCreatureUpFrontIndex() {
        return enemyCreatureUpFrontIndex;
    }

    /**
     * Method to determine if this {@link Arena} CAN set the user's {@link Creature} to be the respective {@link Creature}
     * @param i - respective index
     * @return boolean
     */
    public boolean canSetUserCreatureUpFrontIndex(int i) {
        return i != userCreatureUpFrontIndex && !user.getPokeCreature(i).isDead();
    }

    /**
     * Set the new {@link Creature} who will be up to bat for the user, IF AND ONLY IF said {@link Creature} is not dead
     * @param i - respective index
     * @return boolean - valid {@link Creature} corresponding to this index? (i.e. - not dead) - note, also return false if the switch is not to a new {@link Creature} for the user
     */
    public boolean setUserCreatureUpFrontIndex(int i) {
        if (this.canSetUserCreatureUpFrontIndex(i)) {
            userCreatureUpFrontIndex = i;
            userCreatureUpFront = user.getPokeCreature(i);
            return true;
        }
        return false;
    }

    /**
     * Set the new {@link Creature} who will be up to bat for the enemy
     * This will only be called with indices that make sense
     * @param i - respective index
     */
    public void setEnemyCreatureUpFrontIndex(int i) {
        enemyCreatureUpFrontIndex = i;
        enemyCreatureUpFront = enemy.getPokeCreature(i);
    }

    /**
     * Simple getter for the value of combatOver
     * @return boolean
     */
    public boolean isCombatOver() {
        return user.getDeadCount() == 6 || enemy.getDeadCount() == 6;
    }

    /**
     * Method that sets up the combatants. It takes in an index to select a Creature to place
     * into battle. It sets up the target to the opposite Pokémon as well.
     */
    public void setUpCombatants(){
        // setting the targeting
        this.enemyCreatureUpFront.setTarget(this.userCreatureUpFront);
        this.userCreatureUpFront.setTarget(this.enemyCreatureUpFront);
    }

    /**
     * Allows controller to bring up two Pokémon and pit them against each other.
     * @param mouseEvent - {@link MouseEvent} necessary for handling the result of the animation
     */
    public void doPlayerTurn(MouseEvent mouseEvent){
        userCreatureUpFront.doMove(mouseEvent);
    }

    /**
     * Method to perform an enemy move - maybe attacking or switching Pokémon
     * @param mouseEvent - {@link MouseEvent} necessary because team switching may occur
     */
    public void doEnemyTurn(MouseEvent mouseEvent) {
        // while the enemy Pokémon up to bat is dead, this method will not be called
        this.pickAndDoEnemyMove(mouseEvent);
    }

    /**
     * Make the enemy attack the player - potentially necessary whenever a swapping occurs
     * @param mouseEvent - {@link MouseEvent}
     */
    public void pickAndDoEnemyMove(MouseEvent mouseEvent) {
        this.enemyMoveIndex = this.pickEnemyMoveIndex();
        enemyCreatureUpFront.doMove(mouseEvent);
    }

    /**
     * Method to switch out the enemy Pokémon
     */
    public void switchEnemyPokemon() {
        if (ArenaController.hardMode) {
            this.makeEnemyBestChoice();
        } else {
            this.enemyCreatureUpFront = this.enemy.getPokeCreature(this.getRandomNotDeadFromEnemy());
            this.enemyCreatureUpFront.setTarget(this.userCreatureUpFront);
            this.userCreatureUpFront.setTarget(this.enemyCreatureUpFront);
        }
    }

    /**
     * Look through the enemy's Pokémon - depending on the player's Pokémon one of them is a better choice
     */
    public boolean makeEnemyBestChoice() {
        String oldEnemyCreatureName = enemyCreatureUpFront.getName();

        // this method will only run if the enemy has Pokémon left which are alive
        Comparator<Creature> creatureComparator = (creature1, creature2) -> (creature2.getHealth() - creature1.getHealth());
        TreeSet<Creature> strongAgainstCreatures = new TreeSet<>(creatureComparator);
        TreeSet<Creature> nonVulnerableCreatures = new TreeSet<>(creatureComparator);
        TreeSet<Creature> allCreatures = new TreeSet<>(creatureComparator);
        HashMap<Creature, Integer> indices = new HashMap<>();

        // look through the Pokémon
        for (int i=0; i<enemy.getCreatureArray().length; i++){
            Creature creature = this.enemy.getPokeCreature(i);
            indices.put(creature, i);
            if (!creature.isDead()) {
                // does the enemy have something strong against the player Pokémon?
                if (creature.hasStrongMoveAgainst(userCreatureUpFront.getClass())) {
                    strongAgainstCreatures.add(creature);
                }
                // does the enemy have something that is not weak against the player Pokémon?
                if (!this.userCreatureUpFront.hasStrongMoveAgainst(creature.getClass())) {
                    nonVulnerableCreatures.add(creature);
                }
                // it's a living creature - if nothing else is available it will have to do
                allCreatures.add(creature);
            }
        }

        // look through the sorted Pokémon
        if (!strongAgainstCreatures.isEmpty()){
            enemyCreatureUpFront = strongAgainstCreatures.iterator().next();
        } else if (!nonVulnerableCreatures.isEmpty()){
            enemyCreatureUpFront = nonVulnerableCreatures.iterator().next();
        } else {
            enemyCreatureUpFront = allCreatures.iterator().next();
        }
        enemyCreatureUpFront.setTarget(userCreatureUpFront);
        userCreatureUpFront.setTarget(enemyCreatureUpFront);
        enemyCreatureUpFrontIndex = indices.get(enemyCreatureUpFront);

        return !enemyCreatureUpFront.getName().equals(oldEnemyCreatureName);
    }

    /**
     * Determine if it would be a good idea for the enemy to switch out its Pokémon up to bat
     * @return boolean
     */
    public boolean smartToSwitchEnemy() {
        if (userCreatureUpFront.hasStrongMoveAgainst(enemyCreatureUpFront.getClass()) && enemy.hasNonWeakAgainstCreature(userCreatureUpFront.getClass())){
            // then holy shit the enemy had better switch Pokémon
            return true;
        }
        // otherwise, if the enemy does not have effective attacks against the player - worth switching for the enemy
        return enemyCreatureUpFront.hasWeakMoveAgainst(userCreatureUpFront.getClass()) && enemy.hasNonWeakMoveAgainstCreature(userCreatureUpFront.getClass());
        // else
    }

    /**
     * Return a random index of a Pokémon from the Enemy which is alive
     * @return int
     */
    public int getRandomNotDeadFromEnemy() {
        Creature[] creatures = enemy.getCreatureArray();
        return getAliveIndex(creatures);
    }

    /**
     * Given an array of creatures, return the index of a randomly alive one
     * @param creatures - an array of {@link Creature}s
     * @return int
     */
    private int getAliveIndex(Creature[] creatures) {
        // we do not need to worry about selecting the Pokémon which was already up to bat for the enemy, because this method is only called when an enemy Pokémon dies and must be replaced
        ArrayList<Integer> alive = new ArrayList<>();
        for (int i=0; i<creatures.length; i++){
            if (!creatures[i].isDead()){
                alive.add(i);
            }
        }
        if (alive.size() == 0){
            return -1;
        } else {
            return alive.get((int)(Math.random()*alive.size()));
        }
    }

    /**
     * Depending on if the enemy is smart or random, an index will be returned to represent the enemy's move
     *
     * @return int - the enemy's Move index
     */
    private int pickEnemyMoveIndex() {
        if (!ArenaController.hardMode) {
            Random rand = new Random();
            return rand.nextInt(4);
        } else {
            return this.makeDecisionForEnemy();
        }
    }

    /**
     * When an enemy is smart, this is the decision process they will go through to select a move
     * @return int - the enemy's Move index
     */
    private int makeDecisionForEnemy() {
        if (this.enemyCreatureUpFront instanceof NormalCreature) {
            // map the different indices
            int heal = -1;
            int speed = -1;
            int tackle = -1;
            int hyperbeam = -1;
            for (int i = 0; i < 4; i++) {
                Move move = this.enemyCreatureUpFront.getMove(i);
                if (move == Moves.Recover) {
                    heal = i;
                } else if (move == Moves.Agility) {
                    speed = i;
                } else if (move == Moves.Tackle) {
                    tackle = i;
                } else {
                    hyperbeam = i;
                }
            }
            return makeNormalCreatureDecision(heal, speed, tackle, hyperbeam);
        } else {
            // map the different indices
            int strong = -1;
            int accurate = -1;
            int tackle = -1;
            int agility = -1;
            AttackMove strongAttack = (AttackMove) Moves.Tackle;
            // actually get a hold of the move because we need to test what it is strong and weak against (which will be the same for the accurate attack)
            for (int i = 0; i < 4; i++) {
                Move move = this.enemyCreatureUpFront.getMove(i);
                if (move instanceof AttackMove && ((AttackMove) move).getAccuracy() < 1) {
                    strong = i;
                    strongAttack = (AttackMove) move;
                } else if (move instanceof AttackMove && move != Moves.Tackle) {
                    accurate = i;
                } else if (move == Moves.Tackle) {
                    tackle = i;
                } else {
                    agility = i;
                }
            }
            return makeElementalCreatureDecision(strong, accurate, tackle, agility, strongAttack);
        }
    }

    /**
     * Method to determine which choice would be smart for the enemy {@link Creature} to make when said {@link Creature} is NOT a {@link NormalCreature}.
     * @param strong - int
     * @param accurate - int
     * @param tackle - int
     * @param agility - int
     * @param strongAttack - {@link AttackMove}
     * @return int
     */
    private int makeElementalCreatureDecision(int strong, int accurate, int tackle, int agility, AttackMove strongAttack) {
        // is the target weak against the strong/accurateAttacks?
        if (strongAttack.isStrongAgainst(this.userCreatureUpFront.getClass()) && this.userCreatureUpFront.getHealth() >= this.userCreatureUpFront.getMaxHealth() / 2) {
            // if target it at more than half health, go for the big damage with the elemental strong attack
            return strong;
        } else if (strongAttack.isStrongAgainst(this.userCreatureUpFront.getClass())) {
            // if target is not at more than half health, go with guaranteed hits with the elemental accurate attack
            return accurate;
        } else if (this.enemyCreatureUpFront.getSpeed() == Creature.DEFAULT_SPEED) {
            // if this happens, the creature has not bumped up its speed yet and attacks are not urgent - may be worth doing
            return agility;
        } else if (!strongAttack.isWeakAgainst(this.userCreatureUpFront.getClass()) && this.userCreatureUpFront.getHealth() >= this.userCreatureUpFront.getMaxHealth() / 2) {
            // it will at least be neutrally effective
            return strong;
        } else if (!strongAttack.isWeakAgainst(this.userCreatureUpFront.getClass())) {
            // still, the accurate attack will at least be neutrally effective
            return accurate;
        } else {
            // if we are here, any elemental attacks are weak against the target, so we should tackle
            return tackle;
        }
    }

    /**
     * Method to determine which choice would be smart for the enemy {@link Creature} to make when said {@link Creature} IS a {@link NormalCreature}.
     * @param heal - int
     * @param speed - int
     * @param tackle - int
     * @param hyperbeam - int
     * @return int
     */
    private int makeNormalCreatureDecision(int heal, int speed, int tackle, int hyperbeam) {
        if (this.userCreatureUpFront.getHealth() >= this.userCreatureUpFront.getMaxHealth() / 2) {
            // if the enemy is high on health, go for a hyperbeam attack
            return hyperbeam;
        } else if (this.enemyCreatureUpFront.getMaxHealth() / 3 <= this.enemyCreatureUpFront.getHealth() && this.enemyCreatureUpFront.getHealth() <= this.enemyCreatureUpFront.getMaxHealth() / 2) {
            // if we are in a productive window to heal, then heal
            // too low? then we're going to be dead soon so just deal some damage
            // too high? then we don't need to heal so do something else instead
            return heal;
        } else if (this.enemyCreatureUpFront.getSpeed() == Creature.DEFAULT_SPEED) {
            // the enemy has been procrastinating speed - it should get more speed
            return speed;
        } else {
            // final move decision
            return tackle;
        }
    }
}