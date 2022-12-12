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
 * Description: Implementation of a SupportMove
 *
 * *****************************************/

package org.Fearsome_Foursome.Moves;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.Fearsome_Foursome.Application.HelloPokemon;
import org.Fearsome_Foursome.Creatures.Creature;

import java.io.File;
import java.io.IOException;

import static org.Fearsome_Foursome.Battle.Arena.GLOBAL_ARENA;

public class SupportMove implements Move {

    /** What attribute will this Move modify? */
    private final CreatureAttribute attributeToChange;

    /** What bonus will this Move give to the relevant attribute? */
    private final int bonus;

    /** Name corresponding to this {@link Move} */
    private final String name;

    /** Description corresponding to this {@link Move} */
    private final String description;

    /** {@link String} corresponding to this {@link Move} */
    private final String color;

    /** The address which contains the .png sprite animation for said {@link SupportMove} */
    private final String imagePath;

    /**
     * Constructor for the {@link SupportMove} class, which initializes attributeToChange and bonus
     * @param type {@link SupportType}
     * @param name {@link String}
     */
    public SupportMove(SupportType type, String name, String description, String color){
        this.attributeToChange = type.getAttributeToModify();
        this.bonus = type.getAmountToIncrease();
        this.name = name;
        this.description = description;
        this.color = color;
        this.imagePath = "src/main/java/resources/Sprites/" + name + ".png";
    }

    /**
     * Overridden method to return the name of this {@link SupportMove}
     * @return {@link String}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Override for the required 'getDescription' method which will return the corresponding description of this {@link SupportMove}
     * @return {@link String}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Overridden method to return the {@link String} of this {@link SupportMove}
     * @return {@link String}
     */
    @Override
    public String getColor() { return color; }

    /**
     * What does this move DO to all parties involved? This is the method for when the player moves
     * @param mover - {@link Creature}
     * @param mouseEvent - {@link MouseEvent}
     */
    @Override
    public void act(Creature mover, MouseEvent mouseEvent) {
        // we need to get our hands on the supporting move image
        ImageView moveImage;
        if (mover == GLOBAL_ARENA.getEnemyCreatureUpFront()) {
            moveImage = HelloPokemon.arenaController.supportMoveEnemy;
        } else {
            moveImage = HelloPokemon.arenaController.supportMovePlayer;
        }

        // just make the supporting move visible for a certain amount of time
        try {
            File fileForMoveSprite = new File(this.imagePath);
            moveImage.setImage(new Image(fileForMoveSprite.toURI().toURL().toExternalForm()));
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            event -> this.doAndShowResult(mover),
                            new KeyValue(moveImage.visibleProperty(), true)),
                    new KeyFrame(Duration.seconds(1),
                            // does someone need to go next, or is the round complete?
                            // all of that will be handled with this lambda expression
                            event -> HelloPokemon.arenaController.progressExchangeOfMoves(mouseEvent),
                            new KeyValue(moveImage.visibleProperty(), false)
                    )
            );
            timeline.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs the changes on the attributes and returns a {@link String} recording the result
     * @param mover - {@link Creature} which will be affected
     * @return {@link String}
     */
    @Override
    public String getResultingMessage(Creature mover) {
        String moveAction;
        if (mover == GLOBAL_ARENA.getUserCreatureUpFront()){
            moveAction = "Your ";
        } else {
            moveAction = "The opponent's ";
        }
        moveAction += mover.getName() +  " used " + this.getName() + "!";

        // simply increase the relevant attribute of mover
        switch (attributeToChange) {
            case Health -> {
                mover.increaseHealth(bonus);
                moveAction += "\nRecovered " + SupportType.Healing.amountToIncrease + " health!";
            }
            case MaxHealth -> mover.increaseMaxHealth(bonus);
            case Speed -> {
                mover.increaseSpeed(bonus);
                moveAction += "\nIncreased speed by " + SupportType.Speeding.amountToIncrease + "!";
            }
        }
        return moveAction;
    }

    /**
     * Testing version of this method
     * @param mover - {@link Creature}
     * @param target - {@link Creature}
     */
    @Override
    public void actNoAnimation(Creature mover, Creature target) {
        switch (attributeToChange) {
            case Health -> mover.increaseHealth(bonus);
            case MaxHealth -> mover.increaseMaxHealth(bonus);
            case Speed -> mover.increaseSpeed(bonus);
        }
    }

    /**
     * Simple getter for the attribute which is changed
     * @return {@link CreatureAttribute}
     */
    public CreatureAttribute getAttributeToChange() {
        return attributeToChange;
    }

    /**
     * Simple getter for the bonus amount
     * @return int
     */
    public int getBonus() {
        return bonus;
    }

}