# MyFirstMod
My first mod for Minecraft 1.17. Doing tutorials to learn the basics of modding.

# Notes
## Setup
- Make a MinecraftMods folder
- Download the [Minecraft Forge MDK](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.17.1.html) for the version you want to mod
- Extract the zip file into a new folder called MyFirstMod
- Import the project in IntelliJ IDEA by selecting the build.gradle file
- After the Gradle background tasks finish, run the `genIntellijRuns` task (in the Gradle tab on the right sidebar)
- Run the `runClient` task to start the game

## Creating a new item
- In ItemInit.java, create a DeferredRegister for Items
    - A DeferredRegister is a way to register items in the game
    - The first argument is the type of item you want to register
    - The second argument is the mod ID
- In the main mod class, create an IEventBus variable
    - An IEventBus is a way to listen for events in the game
    - Then, register the DeferredRegister in the IEventBus
- Back in ItemInit.java, create a new RegistryObject<Item> variable and register it in the DeferredRegister
    - The first argument is the name of the item
    - The second argument is a Supplier that returns a new instance of the item
      - In this Supplier you can chain methods to set properties of the item
        - Some options include:
            - .tab(ItemGroup) - sets the creative tab
            - .addToolType(ToolType, level) - adds a tool type to the item
            - .food(Food) - sets the food properties
            - .stacksTo(int) - sets the max stack size
            - .fireResistant() - makes the item fire resistant
            - .durability(int) - sets the durability of the item
- In the assets/mod/textures/items folder, create a new PNG file for the item (I use Pinta) ## TODO: add link to Pinta and add section on how to use it
- In the assets/mod/models folder, create a new JSON file for the item
- In assets/mod/lang/en_us.json, add a new entry for the item

## Creating a new block
- In BlockInit.java, create a DeferredRegister for Blocks
    - A DeferredRegister is a way to register blocks in the game
    - The first argument is the type of block you want to register
    - The second argument is the mod ID
- In the main mod class, create an IEventBus variable (if not already created) and register the DeferredRegister in the IEventBus
- In BlockInit.java, create a DeferredRegister and a RegistryItem<Block> similar to how we did for items
    - The first argument is the name of the block
    - The second argument is a Supplier that returns a new instance of the block
        - In this Supplier you can chain methods to set properties of the block, by using .of() or .copy() ## TODO: What does this do?
            - Some other properties you can set include:
                - .strength(float, float) - sets the hardness and resistance of the block
                - .requiresCorrectToolForDrops() - makes the block require the correct tool to drop items
- In ItemInit.java, you need to create a RegistryObject for a BlockItem. You will need to supply a Supplier that also allows you to chain methods to set properties of the block item
- In the assets/mod/textures/blocks folder, create a new PNG file for the block
- In the assets/mod/models/block folder, create a new JSON file for the block
- In the assets/mod/models/item folder, create a new JSON file for the block item
- In the assets/mod/blockstates folder, create a new JSON file for the block
- In the assets/data/mod/loot_tables/blocks folder, create a new JSON file for the block ## TODO: add tool
- In the assets/data/minecraft/tags/blocks/minable folder, create a new JSON file for a tool
    - This should include a list of blocks that can be mined with the tool
- In the assets/data/minecraft/tags/blocks folder, create a new JSON file for a list of blocks that require an a tool of a certain material to be mined
- In assets/mod/lang/en_us.json, add a new entry for the block

# AI-generated section (untested)
## Creating a new item (copilot generated)
- Create a new class that extends Item
- Create a new instance of the item in the main mod class
- Register the item in the registry event handler
- Create a new JSON file in the resources/data/myfirstmod/items folder
- Add the item to the JSON file

## Creating a new block (copilot generated)
- Create a new class that extends Block
- Create a new instance of the block in the main mod class
- Register the block in the registry event handler
- Create a new JSON file in the resources/data/myfirstmod/blocks folder
- Add the block to the JSON file
