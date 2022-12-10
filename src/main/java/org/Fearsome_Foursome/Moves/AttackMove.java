/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall2022
 * Instructor: Prof. Brian King
 *
 * Group: Fearsome Foursome
 * Section: 02
 * Date: 11/7/22
 * Time: 1:32 PM
 *
 * Project: csci205_final_project
 * Package: org.Fearsome_Foursome.Moves
 * Class: StrongMove
 *
 * Description: Implementation of the StrongMove
 *
 * *****************************************/

package org.Fearsome_Foursome.Moves;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.Fearsome_Foursome.Application.HelloPokemon;
import org.Fearsome_Foursome.Creatures.Creature;

import java.util.List;

import static org.Fearsome_Foursome.Battle.Arena.GLOBAL_ARENA;

public class AttackMove implements Move{

    /** Probability that this attack will hit */
    private final double accuracy;

    /** Damage that this attack will inflict on a hit */
    private final int damage;

    /** Class which takes double damage from this move */
    private final Class strongAgainst;

    /** List of classes which take half damage from this move */
    private final List weakAgainstList;

    /** Name of this {@link AttackMove} */
    private final String name;

    /** Description corresponding to this {@link Move} */
    private final String description;

    /** Color associated with this move */
    private final String color;

    /** The address which contains the .png sprite animation for said {@link SupportMove} */
    private final String imagePath;

    /**
     * Constructor for {@link AttackMove} class, which just initializes all the attributes
     * @param strongAgainst {@link Class}
     * @param weakAgainst {@link List}
     * @param name {@link String}
     */
    protected AttackMove(AttackType type, Class strongAgainst, List weakAgainst, String name, String description, String color){
        this.accuracy = type.getAccuracy();
        this.damage = type.getDamage();
        this.strongAgainst = strongAgainst;
        this.weakAgainstList = weakAgainst;
        this.name = name;
        this.description = description;
        this.color = color;
        this.imagePath = "Sprites/" + name + ".png";
    }

    /**
     * Override for the required 'getName' method which will return the corresponding name of this {@link AttackMove}
     * @return {@link String}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Override for the required 'getDescription' method which will return the corresponding description of this {@link AttackMove}
     * @return {@link String}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Override for the required 'getColor' method which will return the corresponding color of this {@link AttackMove}
     * @return {@link String}
     */
    @Override
    public String getColor() {
        return color;
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
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Simple method to determine if a certain {@link Class} takes reduced damage from this {@link Move}
     * @param className {@link Class}
     * @return boolean
     */
    public boolean isWeakAgainst(Class className){
        if (this.weakAgainstList == null)
            return false;
        return this.weakAgainstList.contains(className);
    }

    /**
     * Simple method to determine if a certain {@link Class} takes extra damage from this {@link Move}
     * @param className - a {@link Class} which this {@link AttackMove} may be strong or weak against
     * @return boolean
     */
    public boolean isStrongAgainst(Class className){
        if (this.strongAgainst == null)
            return false;
        return this.strongAgainst.equals(className);
    }

    /**
     * Testing version of this method
     * @param mover - {@link Creature}
     * @param target - {@link Creature}
     */
    @Override
    public void actNoAnimation(Creature mover, Creature target) {
        if (Math.random() <= accuracy) {
            if (target.getClass().equals(strongAgainst)){
                // target takes double damage
                target.damage(2*damage);
            } else if (weakAgainstList != null && weakAgainstList.contains(target.getClass())){
                // target takes half damage
                target.damage((int)(0.5*damage));
            } else{
                // target takes normal damage
                target.damage(damage);
            }
        }
    }

    /**
     * Override for the required 'playerActOn' method which will do something to the creatures involved
     * @param attacker - {@link Creature}
     * @param mouseEvent - {@link MouseEvent}
     */
    @Override
    public void act(Creature attacker, MouseEvent mouseEvent) {
        // first, get the images which need to be updated
        ImageView creatureImage;
        ImageView moveImage;
        ImageView tackleImage;

        // is the user's Pokémon or the opponent's Pokémon attacking?
        if (attacker == GLOBAL_ARENA.getEnemyCreatureUpFront()) {
            creatureImage = HelloPokemon.arenaController.enemySprite;
            moveImage = HelloPokemon.arenaController.attackMoveEnemy;
            tackleImage = HelloPokemon.arenaController.enemyTackleSprite;
        } else {
            creatureImage = HelloPokemon.arenaController.playerSprite;
            moveImage = HelloPokemon.arenaController.attackMovePlayer;
            tackleImage = HelloPokemon.arenaController.playerTackleSprite;
        }

        if (Math.random() <= accuracy) {
            // show the animation, and THEN update the game
            if (this.name.equals("TACKLE")) {
                // needs to "move" the creature
                this.createTackleTimeline(tackleImage, creatureImage, attacker, mouseEvent);
            } else {
                // not tackling
                this.createAttackTimeline(moveImage, attacker, mouseEvent);
            }
        } else {
            // record that a miss occurred
            this.createMissTimeline(attacker, mouseEvent);
        }
    }

    /**
     * Method to create a timeline that should play in the event of an attack missing
     * @param attacker {@link Creature}
     * @param mouseEvent {@link MouseEvent} for progressing the exchange of moves
     */
    private void createMissTimeline(Creature attacker, MouseEvent mouseEvent) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        event -> {
                            if (attacker == GLOBAL_ARENA.getEnemyCreatureUpFront()) {
                                HelloPokemon.arenaController.enemyBattleTextLog.setText(this.getMissText(attacker));
                            } else {
                                HelloPokemon.arenaController.userBattleTextLog.setText(this.getMissText(attacker));
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(2),
                        event -> HelloPokemon.arenaController.progressExchangeOfMoves(mouseEvent)
                )
        );
        timeline.play();
    }

    /**
     * Method to record this {@link AttackMove} missing
     * @param attacker {@link Creature}
     * @return boolean
     */
    private String getMissText(Creature attacker) {
        String message;
        if (attacker == GLOBAL_ARENA.getUserCreatureUpFront()){
            // then the user is attacking
            message = "Your " + attacker.getName() + " used " + this.getName() + ".";
        } else {
            // then the enemy is attacking
            message = "The opponent's " + attacker.getName() + " used " + this.getName() + ".";
        }
        message += "\nIt missed!";
        return message;
    }

    /**
     * Method to animate a tackle, which depends on the creature who's up to bat
     * @param moveImage {@link ImageView}
     * @param creatureImage {@link ImageView}
     * @param attacker {@link Creature}
     * @param mouseEvent {@link MouseEvent}
     */
    private void createTackleTimeline(ImageView moveImage, ImageView creatureImage, Creature attacker, MouseEvent mouseEvent) {
        moveImage.setImage(creatureImage.getImage());
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        event -> this.doAndShowResult(attacker),
                        new KeyValue(moveImage.visibleProperty(), true)),
                new KeyFrame(Duration.ZERO, new KeyValue(creatureImage.visibleProperty(), false)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(moveImage.visibleProperty(), false)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(creatureImage.visibleProperty(), true)),
                new KeyFrame(Duration.seconds(2),
                        // does someone need to go next, or is the round complete?
                        // all of that will be handled with this lambda expression
                        event -> handlePostDamage(attacker, mouseEvent),
                        new KeyValue(creatureImage.visibleProperty(), true)
                )
        );
        timeline.play();
    }

    /**
     * Method to animate a non-tackle {@link AttackMove}
     * Source: <a href="https://stackoverflow.com/questions/23325488/add-timer-for-images-in-javafx">...</a>
     * @param moveImage {@link ImageView}
     * @param attacker {@link Creature}
     * @param mouseEvent {@link MouseEvent}
     */
    private void createAttackTimeline(ImageView moveImage, Creature attacker, MouseEvent mouseEvent) {
        moveImage.setImage(new Image(this.imagePath));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        event -> this.doAndShowResult(attacker),
                        new KeyValue(moveImage.visibleProperty(), true)
                ),
                new KeyFrame(Duration.seconds(2),
                        // does someone need to go next, or is the round complete?
                        // all of that will be handled with this lambda expression
                        event -> handlePostDamage(attacker, mouseEvent),
                        new KeyValue(moveImage.visibleProperty(), false)
                )
        );
        timeline.play();
    }

    /**
     * After an attacking {@link Creature} inflicts some damage, handle the case of the target dying
     * @param attacker - {@link Creature} which just inflicted damage on its target
     * @param mouseEvent - necessary {@link MouseEvent} handler for the next step
     */
    private static void handlePostDamage(Creature attacker, MouseEvent mouseEvent) {
        Creature target = attacker.getTarget();
        if (target.isDead()) {
            if (target == GLOBAL_ARENA.getEnemyCreatureUpFront()){
                // enemy Creature died
                HelloPokemon.arenaController.handleEnemyCreatureDeath(mouseEvent);
            } else {
                // user Creature died
                HelloPokemon.arenaController.handlePlayerCreatureDeath(mouseEvent);
            }
        } else {
            //  then just keep going
            HelloPokemon.arenaController.progressExchangeOfMoves(mouseEvent);
        }
    }

    /**
     * Method to damage the target {@link Creature} and get the resulting message
     * @param mover {@link Creature}
     * @return {@link String}
     */
    @Override
    public String getResultingMessage(Creature mover) {
        Creature target = mover.getTarget();
        String message;
        if (target == GLOBAL_ARENA.getEnemyCreatureUpFront()){
            // then the user is attacking
            message = "Your " + mover.getName() + " used " + this.getName() + ".";
        } else {
            // then the enemy is attacking
            message = "The opponent's " + mover.getName() + " used " + this.getName() + ".";
        }

        if (target.getClass().equals(strongAgainst)){
            // target takes double damage
            target.damage(2*damage);
            message += "\nIt's super effective!";
        } else if (weakAgainstList != null && weakAgainstList.contains(target.getClass())){
            // target takes half damage
            target.damage((int)(0.5*damage));
            message += "\nIt's not very effective...";
        } else{
            // target takes normal damage
            target.damage(damage);
        }
        return message;
    }
}