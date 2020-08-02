Welcome to Escape the Dungeon!

You are a wizard who has been trapped in a dungeon full of enemies. Can you find your way out?

To build this code, open up a command prompt in the main Escape The Dungeon folder and type cd src, and then type javac EscapeTheDungeon.java. You can also open and build it in IntelliJ
If you build this code, you need to copy the "data" folder into the "out/production/Escape The Dungeon" folder, otherwise, the code will not work from the command line
To run this code, open up a command prompt in the main Escape The Dungeon folder and type cd out/production/Escape The Dungeon, and then type java EscapeTheDungeon

If you want to extend this code, open it as a project in IntelliJ.

MAIN MENU :

When starting the game, type "N", "NEW" or "NEW GAME" to start a new game
Type "L", "LOAD", "C" or "CONTINUE" to continue the saved game

CHARACTER CREATOR MENU :

Your character will have 5 different stats:

- HP is the amount of health you have
- Magic is the amount of magical power you have. Every time you use a spell, you use up magical power
- Attack is how strong your magic is
- Defense is how resilient you are to attacks
- Luck is how lucky you are. The luckier you are, the more likely it is for your spells to succeed and for your opponent's attacks to miss!

The character creator will ask you what type of wizard you are :
- A Healthy Wizard gains a boost in HP
- A Wise Wizard gains a boost in Magic
- A Strong Wizard gains a boost in Attack
- A Careful Wizard gains a boost in Defense
- A Lucky Wizard gains a boost in Luck

You will then be asked to choose 2 out of 3 possible offensive spells and 2 out of 3 possible support spells
Your offensive spells are:
- Neutral Attack : A regular attack, with decent strength and magic cost
- Powerful Attack : A more powerful attack that costs more magic
- Risky Attack : A risky attack that is very tsrong and costs little magic power but has a high chance to fail
and 2 supporting spells:
- Magic Healing :
- Magic Sword (doubles your attack power, 2 MP cost)
- Magic Shield (doubles your defense power, 2 MP cost)

Supporting spells cannot fail.

You will start with 10 health potions and 10 stamina potions. Use them wisely!

EXPLORATION MENU :

You characters character is represented by the letter 'P' Chests are represented by the letter 'C'. Walls are represented by the '#' symbol, while empty spaces are represented by a '.'

When exploring the dungeon, you can use commands to move or access menus:
- Type "^", "U", "UP", "N" or "NORTH" to move up
- Type "v", "D", "DOWN", "S" or "SOUTH" to move down
- Type "<", "L", "LEFT", "W" or "WEST" to move left
- Type ">", "R", "RIGHT", "E" or "EAST" to move right
- Type "M" or "MAP" to view the map
- Type "B" or BAG" to see how many items you have
- type "SAVE" to save your game. Be careful! There is only one save file!
- Type "P" or "PLAYER" to see the status of your character
When viewing the map, the bag, or your player info, press any key to cancel and access the map again.
You can also use items or spells in the overworld by typing them out.

BATTLE MENU :

When in battle, type out the spell or item you want to use.

Type "R" or "RUN" if you want to try to run away.

Be careful! If you lose all your health or magic, you will die and must reload your previous save!

If you wnat to close the game, type "X" or "EXIT" in the exploration menu or the overworld!

None of the commands are case sensitive, so don't worry about that!

Here are some tips to help you through your journey :

- Treasure chests in the dungeon are well-guarded! The closer you get to a treasure chest, the more likely you are to have wild encounters!
- Your wizard instincts tell you that the exit to the dungeon is to the north, however, the side paths may give you valuable treasures!
- You can run away from wild encounters, but you have around a 50% chance to fail, and the luckier you are, the more likely you are to succeed!
- This adventure has multiple endings and a secret easier egg! See if you can find them all!