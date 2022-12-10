# CSCI 205 - Software Engineering and Design
Bucknell University <br>
Lewisburg, PA

### Course Info
Instructor: Prof. Brian King <br>
Semester: Fall 2022

## Team Information
### Members: <br>
Connor Coles - Sophomore Computer Science Major and Project Owner <br>
Eric Reinhart - Sophomore Computer Science Major and Developer <br>
Jiasong Zhu - Junior Mathematics Major and Developer <br>
Mikey Ferguson - Sophomore Computer Science Major and Scrum Master

## Project Information
*This project is a recreation of the Pokémon game, in which two teams of six creatures battle it out until all creatures of one team are knocked unconscious. The last team standing wins! The user plays against the computer in a Pokémon showdown, and they have the option to make the computer smart and play strategically, or make the computer select moves randomly.<br><br>
Combat works by having two Pokémon up to brawl in an arena. Each of these Pokémon are part of a team of six, and the first team to run out of conscious Pokémon loses. The user controls one team, and the application controls the other. Combat begins with the player getting to go first, and then turns are taken switching between the two opponents. Speed is an important factor in the game! One of the available moves for each Pokémon is the ability to increase speed. This matters when a swapping out of a Pokémon occurs. For instance, when a two Pokémon are taking turns sequentially–as they do–and a new opponent is subbed in before one of the aforementioned Pokémon dies, then that new opponent gets to go first if and only if their speed is greater than the remaining Pokémon Creature which did not sub out. That can make or break a game for a player!<br> It is worth noting that when a Pokémon dies–I'm sorry, falls unconscious...sure–its team must replace it with a new Pokémon. However, regardless of speed, if this new Pokémon is replacing a Pokémon that just... fell unconscious shall we say, then the new Pokémon gets to move first. Once all Pokémon of a given team have been defeated, if that's the user's team, the user is sent to the loser screen. If the application's team loses all its Pokémon, the user  sent to the winner screen.<br><br>
If you are new to Pokémon, and want to use this application, it may be worth noting the strengths and weaknesses of each type of Creature. Each Creature is either a NormalCreature or some type of elemental Creature. Each NormalCreature has four Moves it can perform:<br>Tackle - guaranteed hit for low damage<br>Recover - recover a certain amount of health<br>Hyperbeam - non-guaranteed hit for high damage<br>Agility - increase speed<br>Meanwhile, each elemental Creature has four moves it can perform:<br>Tackle - same<br>Agility - same<br>Strong Attack (unique name) - non-guaranteed hit for high damage<br>Accurate Attack (unique name) - guaranteed hit for lower damage<br> Elemental Strong and Accurate Attacks deal double damage against other elemental types they are strong against, less damage against those they are not strong against, and even less damage against those which they are weak against. Here's a quick list:<br>Fire - strong against Grass<br>Grass - strong against Water<br>Water - strong against Fire<br>Electric - strong against Water<br>All elementals are weak against themselves.<br><br>
We certainly do not take credit for the images present in the game. Except for the Move animations! We made those, thank you very much, via the following link:<br>
https://www.piskelapp.com/p/create/sprite <br>
<br>
Other images came from the following sources:<br>
Background <br>
Menu Background: <br>
https://www.baltana.com/games/pokemon-legends-arceus-hd-desktop-wallpaper-4k-124801.html <br>
Arena Background: <br>
https://www.reddit.com/r/PokemonRMXP/comments/v7hz6t/how_can_i_make_custom_battle_backgrounds/ <br>
Winner background: <br>
https://www.spamchronicles.com/after-25-years-ash-becomes-the-pokemon-world-champion/ <br>

Sprites<br>
Pokémon Sprite Links:<br>
https://pokemondb.net/sprites <br>
Sprite Creation: All animations were made by us except the two below.<br>
https://www.piskelapp.com/p/create/sprite <br>
Agility aura: <br>
https://gifimage.net/aura-dbz-gif-transparent/ <br>
Healing aura:<br>
https://tenor.com/view/vindictus-healing-heal-corona-gif-18337034 <br>

Music <br>
Menu screen: <br>
https://downloads.khinsider.com/game-soundtracks/album/pokemon-diamond-and-pearl-super-music-collection <br>
Battle screen:<br>
https://www.gamethemesongs.com/Pokemon_HeartGold_-_SoulSilver_-_Champion_Lance_-_Trainer_Red_Battle.html <br>
Losing screen:<br>
https://downloads.khinsider.com/game-soundtracks/album/pokemon-black-and-white <br>
Victory screen:<br>
https://downloads.khinsider.com/game-soundtracks/album/pokemon-diamond-and-pearl-super-music-collection <br>
<br><br>
Finally, various images used in the different screens were taken from the following sources:<br>
background.jpg – https://free4kwallpapers.com/others/created-a-3d-render-of-a-pokemon-trophy-in-the-grass-wallpaper--jVWY <br>
bird.png – https://www.clipartmax.com/middle/m2i8H7A0d3A0A0G6_its-alolas-early-bird-the-little-woodpecker-is-cute-pokemon-pikipek/ <br>
charized_mega – https://pokemondb.net/pokedex/charizard <br>
dragonite .png – https://www.pngitem.com/middle/wxohib_pokemon-icon-dragonite-hd-png-download/ <br>
forestBackGround.jpg – https://twitter.com/namatnieks/status/960133231185203201 <br>
game_over.png – https://creativemarket.com/b-s-d/4620488-Game-over-glyph-icon <br>
grass1.png – https://yumetwins.com/blog/top-10-cutest-grass-type-pokemon <br>
grass2.png – https://legendsofthemultiuniverse.fandom.com/wiki/Bulbasaur <br>
grass3.png – https://www.clipartmax.com/middle/m2i8b1N4Z5H7G6d3_pokemon-black-white-which-cute-pokemon-pokemon-gen-5-grass-type/ <br>
pikachu.png – https://vsbattles.fandom.com/wiki/Pikachu_%28Anime%29 <br>
sad_pikachu – https://www.syfy.com/syfy-wire/the-10-saddest-moments-in-pokemon-history-ranked <br>
Ash_Ketchum_World_Champion.jpg – https://www.theverge.com/2022/11/11/23451915/pokemon-ash-ketchum-ultimate-journeys-champion <br>
<br>
We made extensive use of the JavaFX library to create our application. Other than that, no third party libraries not already included in the Java API were used.
<br>
<br>
The package structure of this project breaks down the code into different components of the project. The four main packages under the main package org.Fearsome_Foursome are Application, Battle, Creatures, and Moves.<br><br>
Application contains a subpackage Controllers, as well as two classes GameModel and HelloPokemon. HelloPokemon is responsible for launching the application, GameModel is responsible for bookkeeping the status of each team in the battle, and inside Controllers are 5 different classes, each responsible for controlling one of the 5 scenes of the application.<br><br>
The Battle package contains the Arena and Player classes. Each Player possesses six Pokémon, or Creatures, and each Arena has two Players. The arena is responsible for setting the two players' teams against each other, each having one Pokémon up to bat at a time.<br><br>
The Creatures package is responsible for the creation of all Pokémon creatures. With an abstract Creature class, which itself has a bunch of static maps that store information on the moves, appearances, and names of different specific types of creatures, this package provides the means of creating Pokémon. Its scalability also makes adding new types of Pokémon into the game manageable.<br><br>
Finally, the Moves package contains the means of creating new Moves which Pokémon creatures can perform. Each type of Move implements the Move interface, and the two implementations are AttackMove and SupportMove. AttackMoves performed by a Creature damage the Creature's target, and SupportMoves boost a certain attribute of whichever Creature uses said SupportMove.*

## How to run it
*Open terminal, navigate to a location in which you are comfortable copying files, and type in the following command:<br>*
git clone git@gitlab.bucknell.edu:mtf009/csci205_final_project.git<br>
*A folder named 'csci205_final_project' should have been created. Navigate into this folder and run the following command:<br>*
gradle wrapper<br>
*Once the latter operation completes, run the command:<br>*
./gradlew run<br>
*The application should launch!*