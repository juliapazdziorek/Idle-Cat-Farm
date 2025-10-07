# Idle Cat Farm 🐾

Idle Cat Farm is a relaxing 2D farming simulation and management game built in Java with Swing. Guide a team of adorable cats as they autonomously plant crops, fulfill customer orders, and expand their cozy island farm. The project showcases sophisticated game architecture with AI, smooth animations, and engaging gameplay mechanics.

## Gameplay Overview

- **Autonomous Cat Management:** Watch cats work independently with realistic behaviors, energy systems, and sleep cycles
- **Dynamic Order System:** Fulfill randomized customer orders requiring specific resources in varying quantities
- **Progressive Farm Development:** Unlock and expand multiple map areas including fields, orchards, animal zones, and living spaces
- **Advanced Cat AI:** Cats exhibit realistic behaviors with pathfinding, energy management, rest cycles, and visual feedback systems
- **Resource Economy:** Manage 20+ different crops, fruits, and animal products with unlockable progression tiers

## Key Features

### Cat AI System

- **Intelligent Pathfinding:** A* algorithm for smart navigation around obstacles and terrain
- **Energy Management:** Cats have stamina that depletes during work and regenerates during sleep
- **Autonomous Task Assignment:** Cats automatically choose and execute farming tasks
- **Visual Communication:** Speech bubble system (ZZZ for tired cats, sun icons for well-rested)
- **Realistic Sleep Cycles:** Cats find and use beds, with sleeping animations and energy restoration
- **Multiple Cat Colors:** White, Grey, Ginger, and Tricolor varieties with unique animations

### Farming System

- **20+ Crop Varieties:** From basic lettuce and tomatoes to advanced star fruits and pumpkins
- **Fruit Trees:** Apple, pear, peach, and orange trees with seasonal growth cycles
- **Animal Products:** Eggs and specialty milk varieties (chocolate, strawberry)
- **Progressive Unlocks:** Crops and resources unlock as your farm levels advance (LVL0 → LVL⭐)
- **Field Management:** East and West fields with planting, growing, and harvesting states

### Architecture

- **Entity Component System:** Modular design for cats, buildings, nature elements, and objects
- **Layered Tile System:** Multi-layer world built from text-based tilemaps with dynamic loading
- **Handler-Based Design:** Separate systems for resources, entities, orders, pathfinding, and UI
- **Custom Animation System:** Smooth sprite animations for all entities and UI elements
- **Memory-Efficient Rendering:** Optimized graphics pipeline with camera culling and layer management

### User Experience

- **Tabbed Interface System:**
  - **Farm Tab:** Field management and crop overview with dynamic labels
  - **Cats Tab:** Individual cat statistics and management
  - **Workstation Tab:** Production and crafting interface
  - **Orders Tab:** Active customer orders and fulfillment tracking
- **Responsive Controls:** WASD/Arrow key movement with smooth camera following
- **Debug Console:** F3 toggles comprehensive debug tools and area management
- **Visual Feedback:** Rich animations, particle effects, and UI state indicators
- **AI-Assisted Development:** User interface components developed with GitHub Copilot integration for enhanced productivity and code quality

## Getting Started

### Prerequisites

- Java 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/juliapazdziorek/Idle-Cat-Farm.git
   ```

2. **Open in your Java IDE**.
3. **Run `Main.java`** to start the game.

### Controls

- **Movement:** WASD
- **Debug Menu:** F3 (toggle area management and debug information)
- **Interaction:** Mouse clicks on UI elements and entities

## Project Structure

```text
src/
├── Game/           # Core game systems and handlers
│   ├── Farm.java           # Main game class and initialization
│   ├── OrdersHandler.java  # Customer order management
│   ├── FarmResourcesHandler.java  # Resource types and inventory
│   └── FieldsHandler.java  # Field management system
├── Entities/       # All game entities with ECS architecture
│   ├── Characters/         # Cat entities and AI systems
│   ├── Objects/           # Interactive objects (beds, signs, etc.)
│   ├── BuildingParts/     # Modular building components
│   ├── Nature/           # Trees, bushes, and natural elements
│   └── FarmResources/    # Crops and harvestable resources
├── Map/            # World generation and tile management
│   ├── Map.java           # Main map system and area management
│   ├── Field.java         # Individual field logic
│   └── TileMaps/         # Text-based map data files
├── Pathfinding/    # A* pathfinding implementation
├── UI/             # User interface components
│   ├── MenuPanel.java     # Main tabbed interface
│   ├── OrdersSection.java # Order management UI
│   └── Various tab implementations
├── Resources/      # Asset management and animations
└── Debug/          # Development and debugging tools
```

## Game Mechanics Deep Dive

### Resource Progression System

The game features a tiered unlock system across 5 levels:

- **Level 0:** Basic crops (Lettuce, Tomato)
- **Level 1:** Expanded crops (Corn, Carrot) + Fruit trees (Apple) + Animal products (Egg, Milk)
- **Level 2:** Advanced crops (Wheat, Cucumber, Radish, Pear) + Specialty milk (Chocolate)
- **Level 3:** Premium crops (Cauliflower, Eggplant, Pumpkin, Peach)
- **Level ⭐:** Ultimate crops (Star Fruit, Orange) + Rare products (Strawberry Milk)

### Cat Behavior System

Each cat operates with sophisticated AI:

- **State Machine:** IDLE → TIRED → GOING_TO_SLEEP → SLEEPING → IDLE
- **Energy Depletion:** Work activities consume energy over time
- **Autonomous Rest:** Cats automatically seek beds when tired
- **Visual Indicators:** Speech bubbles communicate cat status
- **Pathfinding:** Smart navigation around obstacles and terrain features

## Technical Highlights

### Architecture Patterns

- **Entity-Component System:** Modular entity design for easy extension
- **Observer Pattern:** Event-driven updates between systems
- **State Machine:** Clean state management for complex behaviors
- **Factory Pattern:** Centralized animation and entity creation
- **Handler Pattern:** Separation of concerns across game systems
- **Type-Safe Design:** Comprehensive enum usage for game states, resources, and entity types
- **Error Handling:** Robust exception handling and graceful degradation

### Performance Features

- **60 FPS Game Loop:** Consistent frame timing with delta-time calculations
- **Efficient Rendering:** Layer-based rendering with camera culling
- **Memory Management:** Object pooling for frequently created entities
- **Optimized Pathfinding:** A* with cached results and intelligent grid updates

## Contributing

This project showcases advanced game development concepts in Java. Feel free to explore the codebase to learn about:

## Credits

- **Pixel Art Assets:** [cupnooble.itch.io](https://cupnooble.itch.io) - Beautiful Sprout Lands asset pack

---

🐾 **Enjoy managing your farm and watching your cat friends work their magic!**
