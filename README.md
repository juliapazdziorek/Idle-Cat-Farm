# Idle Cat Farm 🐾

A relaxing 2D farming simulation built in Java with Swing. Guide a team of cats as they autonomously plant crops, fulfill customer orders, and expand their cozy island farm.

## Features

- **Autonomous cats** with A* pathfinding, energy/sleep cycles, and speech-bubble feedback (ZZZ when tired, sun when rested). Four colors: White, Grey, Ginger, Tricolor.
- **Field management:** East and West fields cycling through planting → growing → harvesting states.
- **Farming system:** 20+ crops, fruit trees (apple, pear, peach, orange), and animal products (eggs, specialty milks), unlocked across 5 progression tiers (LVL0 → LVL⭐).
- **Dynamic orders:** randomized customer orders requiring specific resources in varying quantities.
- **Economy:** earn money by fulfilling orders and reinvest into farm progression.
- **Rest & sleep:** tired cats find beds, play sleeping animations, and restore energy on their own.
- **Tabbed UI:** Farm, Cats, Workstation, and Orders tabs, with smooth camera following and rich animations.

**Controls:** WASD to move, F3 for the debug menu, mouse for UI and entity interaction.

## Project Structure

```text
src/
├── Game/         # Core systems and handlers (Farm, Orders, Resources, Fields)
├── Entities/     # ECS entities: cats, objects, buildings, nature, resources
├── Map/          # World generation and text-based tilemaps
├── Pathfinding/  # A* implementation
├── UI/           # Tabbed interface and sections
├── Resources/    # Asset management and animations
└── Debug/        # Development and debugging tools
```

## Credits

- **Pixel art:** [cupnooble.itch.io](https://cupnooble.itch.io) — Sprout Lands asset pack

---

🐾 **Enjoy managing your farm and watching your cat friends work their magic!**
