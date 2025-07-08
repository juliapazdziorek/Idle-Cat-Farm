# 🌱 FocusFarm

An indie farming simulation game helping you to focus currently in development, based on a university project. Experience the tranquil life of managing your own farm while exploring a beautifully crafted 2D [Sprout Lands](https://cupnooble.itch.io/sprout-lands-asset-pack) world.

## 🎮 About

FocusFarm is a 2D farming simulation game built from the ground up using Java and Swing. What started as a university project has evolved into an little indie game development endeavor, focusing on creating an immersive and relaxing farming experience.

## ✨ Features

### Currently Implemented
- **2D Tile-based World**: Explore a detailed environment with multiple terrain types
- **Animated Water System**: Dynamic water animations using sprite-based rendering
- **Layer-based Map System**: Sophisticated map rendering with multiple visual layers
- **Camera System**: Smooth camera movement and world navigation
- **Resource Management**: Comprehensive sprite and animation loading system
- **Modular Architecture**: Clean, extensible codebase with proper separation of concerns

### In Development
- Crop planting and harvesting mechanics
- Character movement and interaction
- Inventory and crafting systems
- Weather and seasonal changes
- Farm animals and livestock management

## 🛠️ Technical Details

### Built With
- **Language**: Java
- **GUI Framework**: Swing
- **Graphics**: Java 2D Graphics API
- **Architecture**: Entity-Component pattern with layer-based rendering

### Project Structure
```
src/
├── Entities/           # Game entities and components
│   ├── Map/           # Map and layer management
│   ├── Entity.java    # Base entity class
│   ├── AnimatedEntity.java
│   └── StaticEntity.java
├── Resources/         # Resource management and animations
│   ├── Animation.java
│   ├── ResourceHandler.java
│   └── SproutLands/   # Game assets
├── Camera.java        # Camera and viewport management
├── Loop.java          # Game loop and timing
├── Panel.java         # Main rendering panel
└── FocusFarm.java     # Main game class
```

## 🎨 Assets

The game uses beautiful pixel art assets from the SproutLands tileset, featuring:
- Detailed terrain tiles (water, grass, soil, paths)
- Animated water effects
- Decorative elements (stones, plants, bridges)
- Multiple grass and terrain variations

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)

### Running the Game
1. Clone the repository
2. Open the project in your preferred Java IDE
3. Run the `Main.java` file
4. Use arrow keys to navigate the camera around the world

### Controls
- **Arrow Keys**: Move camera
- **ESC**: Exit game (when implemented)

## 📋 Development Roadmap

### Phase 1: Core Systems ✅
- [x] Basic game loop and rendering
- [x] Camera system
- [x] Map loading and display
- [x] Animation system
- [x] Resource management

### Phase 2: Player Interaction 🔄
- [ ] Player character and movement
- [ ] Basic farming mechanics
- [ ] Simple UI elements
- [ ] Input handling improvements

### Phase 3: Game Mechanics 📋
- [ ] Crop growth system
- [ ] Inventory management
- [ ] Tool system
- [ ] Day/night cycle

### Phase 4: Advanced Features 🎯
- [ ] Weather system
- [ ] Animal husbandry
- [ ] Market and economy
- [ ] Save/load functionality

## 🤝 Contributing

This is primarily a solo development project for learning purposes, but feedback and suggestions are always welcome! If you'd like to contribute:

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request with a clear description

## 📝 License

This project is developed for educational purposes. Asset credits go to their respective creators.

## 📧 Contact

- **Developer**: Julia Pazdziorek
- **Project Type**: University Project → Indie Game
- **Status**: In Active Development

---

*"Growing more than just crops - growing as a developer through the journey of game creation."*