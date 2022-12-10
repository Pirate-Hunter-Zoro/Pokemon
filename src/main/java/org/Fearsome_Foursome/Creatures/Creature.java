/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall2022
 * Instructor: Prof. Brian King
 *
 * Team: Fearsome Foursome
 * Section: 02
 * Date: 11/4/22
 * Time: 11:43 AM
 *
 * Project: csci205_final_project
 * Package: org.Fearsome_Foursome.Creatures
 * Class: Creature
 *
 * Description:
 *
 * *****************************************/

package org.Fearsome_Foursome.Creatures;

import javafx.scene.input.MouseEvent;
import org.Fearsome_Foursome.Moves.AttackMove;
import org.Fearsome_Foursome.Moves.Move;
import org.Fearsome_Foursome.Moves.Moves;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static org.Fearsome_Foursome.Battle.Arena.GLOBAL_ARENA;

/**
 * An abstract class which has attributes and methods shared by all different types of creatures
 */
public abstract class Creature {

    /** Default attribute values */
    public static final int DEFAULT_MAX_HEALTH = 500;
    public static final int DEFAULT_HEALTH = 500;
    public static final int DEFAULT_SPEED = 100;

    /** We need a map of functional interfaces denoting 4 moves to a certain {@link Creature} class */
    public static final HashMap<Class, ArrayList<Move>> CREATURE_MOVE_MAP = new HashMap<>();

    /** A map of all the different possible Pokémon names our app will include*/
    public static final HashMap<Class, ArrayList<String>> CREATURE_NAME_MAP = new HashMap<>();

    /** A map of the front and back sprites of each Pokémon by name */
    public static final HashMap<String, String[]> CREATURE_SPRITE_MAP = new HashMap<>();

    // CONCLUDES THE STATIC MEMBERS

    /** The current attribute values of this particular {@link Creature} instance */
    protected int maxHealth;
    protected int health;
    protected int speed;

    /** The name of the {@link Creature} determines what sprite is associate with it */
    protected String name;

    /** Setting this target determines whom aggressive moves attack */
    protected Creature target;

    /**
     * This constructor is mainly initializing the CREATURE_MOVE_MAP
     */
    protected Creature() {
        // FIRST, in terms of what we want to do for this specific creature
        this.maxHealth = DEFAULT_MAX_HEALTH;
        this.health = DEFAULT_HEALTH;
        this.speed = DEFAULT_SPEED;

        // if some creature was created, then this was already initialized, so we're done
        if (!CREATURE_MOVE_MAP.isEmpty())
            return;

        // establish the fireCreature actions and names
        addFireCreatureActions();
        addFireCreatureNames();

        // establish the waterCreature actions and names
        addWaterCreatureActions();
        addWaterCreatureNames();

        // establish the grassCreature actions and names
        addGrassCreatureActions();
        addGrassCreatureNames();

        // establish the normalCreature actions and names
        addNormalCreatureActions();
        addNormalCreatureNames();

        // establish the electricCreature actions and names
        addElectricCreatureActions();
        addElectricCreatureNames();

    }

    /**
     * Private method to add all the moves the {@link FireCreature} class can do into the dictionary
     */
    private void addFireCreatureActions(){
        CREATURE_MOVE_MAP.put(FireCreature.class, new ArrayList<>());
        ArrayList<Move> fireCreatureMoves = CREATURE_MOVE_MAP.get(FireCreature.class);

        // now add all the Moves to the relevant list
        fireCreatureMoves.add(Moves.Ember);
        fireCreatureMoves.add(Moves.Flamethrower);
        fireCreatureMoves.add(Moves.Tackle);
        fireCreatureMoves.add(Moves.Agility);
    }

    /**
     * Private method to add all the names and sprites of the {@link FireCreature} Pokémon
     */
    private void addFireCreatureNames(){
        CREATURE_NAME_MAP.put(FireCreature.class, new ArrayList<>());
        ArrayList<String> fireCreatureNames = CREATURE_NAME_MAP.get(FireCreature.class);

        fireCreatureNames.add("Charizard");
        fireCreatureNames.add("Volcarona");
        fireCreatureNames.add("Magmortar");

        CREATURE_SPRITE_MAP.put(fireCreatureNames.get(0), new String[]{"CharizardAlly.png", "CharizardEnemy.png"});
        CREATURE_SPRITE_MAP.put(fireCreatureNames.get(1), new String[]{"VolcaronaAlly.png", "VolcaronaEnemy.png"});
        CREATURE_SPRITE_MAP.put(fireCreatureNames.get(2), new String[]{"MagmortarAlly.png", "MagmortarEnemy.png"});
    }

    /**
     * Private method to add all the moves the {@link WaterCreature} class can do into the dictionary
     */
    private void addWaterCreatureActions(){
        CREATURE_MOVE_MAP.put(WaterCreature.class, new ArrayList<>());
        ArrayList<Move> waterCreatureMoves = CREATURE_MOVE_MAP.get(WaterCreature.class);

        // now add all the Moves to the relevant list
        waterCreatureMoves.add(Moves.Watergun);
        waterCreatureMoves.add(Moves.Surf);
        waterCreatureMoves.add(Moves.Tackle);
        waterCreatureMoves.add(Moves.Agility);
    }

    /**
     * Private method to add all the names and sprites of the {@link WaterCreature} Pokémon
     */
    private void addWaterCreatureNames(){
        CREATURE_NAME_MAP.put(WaterCreature.class, new ArrayList<>());
        ArrayList<String> waterCreatureNames = CREATURE_NAME_MAP.get(WaterCreature.class);

        waterCreatureNames.add("Blastoise");
        waterCreatureNames.add("Gyarados");
        waterCreatureNames.add("Milotic");

        CREATURE_SPRITE_MAP.put(waterCreatureNames.get(0), new String[]{"BlastoiseAlly.png", "BlastoiseEnemy.png"});
        CREATURE_SPRITE_MAP.put(waterCreatureNames.get(1), new String[]{"GyaradosAlly.png", "GyaradosEnemy.png"});
        CREATURE_SPRITE_MAP.put(waterCreatureNames.get(2), new String[]{"MiloticAlly.png", "MiloticEnemy.png"});
    }

    /**
     * Private method to add all the moves the {@link GrassCreature} class can do into the dictionary
     */
    private void addGrassCreatureActions(){
        CREATURE_MOVE_MAP.put(GrassCreature.class, new ArrayList<>());
        ArrayList<Move> grassCreatureMoves = CREATURE_MOVE_MAP.get(GrassCreature.class);

        // now add all the Moves to the relevant list
        grassCreatureMoves.add(Moves.Vinewhip);
        grassCreatureMoves.add(Moves.Leafblade);
        grassCreatureMoves.add(Moves.Tackle);
        grassCreatureMoves.add(Moves.Agility);
    }

    /**
     * Private method to add all the names and sprites of the {@link GrassCreature} Pokémon
     */
    private void addGrassCreatureNames(){
        CREATURE_NAME_MAP.put(GrassCreature.class, new ArrayList<>());
        ArrayList<String> grassCreatureNames = CREATURE_NAME_MAP.get(GrassCreature.class);

        grassCreatureNames.add("Venusaur");
        grassCreatureNames.add("Sceptile");

        CREATURE_SPRITE_MAP.put(grassCreatureNames.get(0), new String[]{"VenusaurAlly.png", "VenusaurEnemy.png"});
        CREATURE_SPRITE_MAP.put(grassCreatureNames.get(1), new String[]{"SceptileAlly.png", "SceptileEnemy.png"});
    }

    /**
     * Private method to add all the moves the {@link NormalCreature} class can do into the dictionary
     */
    private void addNormalCreatureActions(){
        CREATURE_MOVE_MAP.put(NormalCreature.class, new ArrayList<>());
        ArrayList<Move> normalCreatureMoves = CREATURE_MOVE_MAP.get(NormalCreature.class);

        // now add all the Moves to the relevant list
        normalCreatureMoves.add(Moves.Tackle);
        normalCreatureMoves.add(Moves.Hyperbeam);
        normalCreatureMoves.add(Moves.Recover);
        normalCreatureMoves.add(Moves.Agility);
    }

    /**
     * Private method to add all the names and sprites of the {@link NormalCreature} Pokémon
     */
    private void addNormalCreatureNames(){
        CREATURE_NAME_MAP.put(NormalCreature.class, new ArrayList<>());
        ArrayList<String> normalCreatureNames = CREATURE_NAME_MAP.get(NormalCreature.class);

        normalCreatureNames.add("Snorlax");
        normalCreatureNames.add("Staraptor");

        CREATURE_SPRITE_MAP.put(normalCreatureNames.get(0), new String[]{"SnorlaxAlly.png", "SnorlaxEnemy.png"});
        CREATURE_SPRITE_MAP.put(normalCreatureNames.get(1), new String[]{"StaraptorAlly.png", "StaraptorEnemy.png"});
    }

    /**
     * Private method to add all the moves the {@link ElectricCreature} class can do into the dictionary
     */
    private void addElectricCreatureActions(){
        CREATURE_MOVE_MAP.put(ElectricCreature.class, new ArrayList<>());
        ArrayList<Move> electricCreatureMoves = CREATURE_MOVE_MAP.get(ElectricCreature.class);

        // now add all the Moves to the relevant list
        electricCreatureMoves.add(Moves.Electroball);
        electricCreatureMoves.add(Moves.Thunderbolt);
        electricCreatureMoves.add(Moves.Tackle);
        electricCreatureMoves.add(Moves.Agility);
    }

    /**
     * Private method to add all the names and sprites of the {@link ElectricCreature} Pokémon
     */
    private void addElectricCreatureNames(){
        CREATURE_NAME_MAP.put(ElectricCreature.class, new ArrayList<>());
        ArrayList<String> electricCreatureNames = CREATURE_NAME_MAP.get(ElectricCreature.class);

        electricCreatureNames.add("Electivire");
        electricCreatureNames.add("Galvantula");

        CREATURE_SPRITE_MAP.put(electricCreatureNames.get(0), new String[]{"ElectivireAlly.png", "ElectivireEnemy.png"});
        CREATURE_SPRITE_MAP.put(electricCreatureNames.get(1), new String[]{"GalvantulaAlly.png", "GalvantulaEnemy.png"});
    }

    /**
     * Given the index in the list of moves available to this {@link Creature}, perform that move
     * @param mouseEvent - {@link MouseEvent} - the {@link Move} needs this
     */
    public void doMove(MouseEvent mouseEvent){
        // let's make this VERY clear - first get the Move from the list of moves this particular Creatures most specific class is allowed
        Move move;
        if (this == GLOBAL_ARENA.getUserCreatureUpFront()) {
            // the player is moving
            move = CREATURE_MOVE_MAP.get(this.getClass()).get(GLOBAL_ARENA.getUserMoveIndex());
        } else {
            // the enemy is moving
            move = CREATURE_MOVE_MAP.get(this.getClass()).get(GLOBAL_ARENA.getEnemyMoveIndex());
        }
        move.act(this, mouseEvent);
    }

    /**
     * Simple method to damage a {@link Creature} by a certain amount
     */
    public void damage(int amount){
        this.health = Math.max(this.health - amount, 0);
    }

    /**
     * Simple method to boost the speed of a {@link Creature}
     */
    public void increaseSpeed(int amount){
        this.speed += amount;
    }

    /**
     * Simple method to increase the health of a {@link Creature}
     */
    public void increaseHealth(int amount){
        this.health += amount;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
    }

    /**
     * Simple method to increase the maximum health of a {@link Creature}
     */
    public void increaseMaxHealth(int amount){
        this.maxHealth += amount;
    }

    /**
     * A simple method to select the {@link Creature}'s target for when they perform aggressive moves
     */
    public void setTarget(Creature target){
        this.target = target;
    }

    /**
     * Simple getter for the {@link Creature}'s name
     * @return {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Simple getter for the current health attribute
     * @return int
     */
    public int getHealth() {
        return health;
    }

    /**
     * Simple getter for the current speed attribute
     * @return int
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Simple method to determine if a {@link Creature} is alive
     * @return boolean
     */
    public boolean isDead() { return !(health > 0); }

    /**
     * Simple getter for the maxHealth attribute
     * @return int
     */
    public int getMaxHealth() { return maxHealth; }

    /**
     * Simple getter for this {@link Creature}'s target {@link Creature}
     * @return {@link Creature}
     */
    public Creature getTarget() {
        return target;
    }

    /**
     * Method to return the {@link Creature}'s {@link Move} at the indicated index
     * @param index - the index of the move which we want to retrieve
     * @return {@link Move}
     */
    public Move getMove(int index) { return CREATURE_MOVE_MAP.get(this.getClass()).get(index); }

    /**
     * Method to determine if this {@link Creature} has a {@link Move} which is strong against the target class
     * @param targetClass - the {@link Class} against which we are curious if this {@link Creature} has a strong {@link Move}
     * @return boolean
     */
    public boolean hasStrongMoveAgainst(Class targetClass) {
        for (Move move : Creature.CREATURE_MOVE_MAP.get(this.getClass())){
            if (move instanceof AttackMove && ((AttackMove) move).isStrongAgainst(targetClass)){
                return true;
            }
        }
        return false;
    }

    /**
     * Method to determine if this {@link Creature} has a {@link Move} which is weak against the target class
     * @param targetClass - the {@link Class} against which we are curious if this {@link Creature} has a weak {@link Move}
     * @return boolean
     */
    public boolean hasWeakMoveAgainst(Class targetClass) {
        for (Move move : Creature.CREATURE_MOVE_MAP.get(this.getClass())){
            if (move instanceof AttackMove && ((AttackMove) move).isWeakAgainst(targetClass)){
                // some of the enemy's attacks are weak against the player - worth changing
                return true;
            }
        }
        return false;
    }

    /**
     * So that {@link Creature}s can be keys in maps
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}