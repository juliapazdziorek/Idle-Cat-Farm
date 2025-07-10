# 🌱 FocusFarm

An indie farming simulation game designed to help you cultivate focus while you play. Currently in development as an evolution of a university project, FocusFarm invites you to experience the peaceful rhythm of farm life while exploring a beautifully crafted 2D [Sprout Lands](https://cupnooble.itch.io/sprout-lands-asset-pack) world.

## 🚜 About

FocusFarm is a 2D farming simulation game built using Java and Swing. Drawing inspiration from focus apps like [Forest](https://www.forestapp.cc) and combining them with classics like [Stardew Valley](https://www.stardewvalley.net), the game creates a unique experience where virtual farming activities help reinforce real-world focus habits.

## 🌾 Features

### Currently Implemented

- **🏗️ Modular Architecture**: Clean, extensible codebase with separation of concepts
- **🎒 Resource Management**: Comprehensive sprite and animation loading system
- **🎬 Animation System**: Smooth character animations with direction change cooldown
- **🗺️ 2D Tile-based Map**: Cozy farm island world built with layered rendering and text-based map data
- **📷 Camera System**: Smooth camera movement and world navigation
- **🐝 Entities System**: Including static and animated entities
- **😼 Farm Cat Character**: Animated player character with multiple states (standing, walking, running)
- **🧭 AStar Pathfinding**: Intelligent pathfinding system for smooth character movement

### In Development

- 🗺️ Map obstacle detection for pathfinding

### In the Future

- 🌱 Basic farming mechanics
- 🖥️ Simple UI elements

## ⚙️ Technical Details

### Built With

- **Language**: Java
- **GUI Framework**: Swing
- **Graphics**: Java 2D Graphics API
- **Architecture**: Entity-Component pattern with layer-based rendering

### 📁 Project Structure

``` text
src/
├── Entities/              # Game entities and components
│   ├── Entity.java        
│   ├── AnimatedEntity.java
│   ├── StaticEntity.java
│   └── Characters/        # Character entities
│       └── FarmCat.java   
├── Map/                   # Map and layer management
│   ├── Map.java           # Main map class
│   ├── MapLayer.java      
│   └── TextMapLayers/     # Map data files
├── Pathfinding/           # A* pathfinding system
│   ├── AStar.java         
│   └── Node.java          
├── Resources/             # Assets management
│   ├── Animation.java     
│   ├── Colors.java        # Color definitions
│   ├── ResourceHandler.java # Resource management
│   └── SproutLands/       # Game assets
├── Game/                  # Core game systems
│   ├── Camera.java        
│   ├── EntitiesHandler.java # Entity management
│   ├── FocusFarm.java     # Main game class
│   ├── KeyHandler.java    
│   ├── Loop.java          
│   ├── MouseHandler.java  
│   └── Panel.java         # Main rendering panel
└── Main.java              # Application entry point
```

## 🎨 Assets

The game uses pixel art assets from the [SproutLands](https://cupnooble.itch.io/sprout-lands-asset-pack) tileset.

## 🌟 Getting Started

### 📋 Prerequisites

- Java 11 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)

### 🚀 Running the Game

1. Clone the repository
2. Open the project in your preferred Java IDE
3. Run the `Main.java` file

### 🎮 Controls

- **Arrow Keys/WASD**: Move camera

## Development Roadmap

### Phase 1: Core Systems

- [x] 🔄 Basic game loop and rendering
- [x] 📷 Camera system
- [x] 🗺️ Map loading and display
- [x] 🎬 Animation system
- [x] 🎒 Resource management

### Phase 2: Character and Movement

- [x] � Farm cat character with animations
- [x] 🧭 A* pathfinding algorithm
- [x] 🚀 Dynamic movement system (walk/run)
- [x] 🎯 Smooth character movement with direction cooldown
- [ ] 🚧 Map obstacle detection

### Phase 3: Basic Game Mechanics

- [ ] 🌱 Basic farming mechanics
- [ ] 🖥️ Simple UI elements

### Phase 4: Advanced Game Mechanics

- [ ] 🌾 Crop growth system
- [ ] 🎒 Inventory management
- [ ] 🔨 Tool system
- [ ] 🌙 Day/night cycle

### Phase 5: Focus System

- [ ] 🎯 Focus features

### Phase 6: Advanced Features

- [ ] 💅 Fancy UI
- [ ] 🐄 Farming animals
- [ ] 💰 Market and money system
- [ ] 🏘️ Developing buildings

## 🤝 Contributing

This is primarily a solo development project for learning purposes, but feedback and suggestions are always welcome! If you'd like to contribute:

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request with a clear description

## License

This project is developed for educational purposes. Asset credits go to their respective creators.

---
