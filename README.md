# Ocean Trader

Ocean Trader, created for CS-2340: Objects and Design is a spinoff of the popular game *Space Trader*, a strategy game originally found on Palm OS and Windows PDAs. Just like *Space Trader,* Ocean Trader seeks to provide the same adventures based on the open seas.


# M3 - New Game Configuration, Use Case, Domain Model
In this iteration, the group worked on the basic structure of the game, creating classes based on the Domain Model and Use Case Diagrams.

The New Game configuration, Use Cases, and Domain Modeling were created during this iteration. 

[Domain Model](https://github.gatech.edu/ileung6/Ocean-Trader/blob/master/src/Models/Domain%20Model.pdf)

[Use Case Model](https://github.gatech.edu/ileung6/Ocean-Trader/blob/master/src/Models/Use%20Case%20Model.pdf)

# M4 - Universe Creation & Sequence Diagram
This milestone covers the creation and display of the universe (in this case, the ocean) with its regions and travel mechanics. The sequence diagram models the interactions between objects in sequence, namely how they interact to make the game feel fluid and update visually.

[Sequence Diagram](https://github.gatech.edu/ileung6/Ocean-Trader/blob/master/src/Models/SequenceDiagram.pdf)

# M5 - Marketplace Trading and Robustness Diagram
For M5, the group created a ship entity with associated type, cargo space, and health. These ships can store cargo in the form of objects of type Item. The GUI now includes a display for items in both the player's inventory and marketplace and update according to region. The item price during purchase is dependent on the base price, time of day, player's trading skill, and local region tax. The robustness diagram models the player's travel and the handling of bandits.

[Robustness Diagram](https://github.gatech.edu/ileung6/Ocean-Trader/blob/master/src/Models/RobustDiagram.pdf)

# M6 - NPC Encounters
For M6, the group created NPCs and their encounter screen popups. There are three types of NPCs with different interactions: Bandits, Traders, and Police. Each interaction has its own unique image, graphical interface, and respective backend logic.
