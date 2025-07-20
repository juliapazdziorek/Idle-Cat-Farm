# 🗺️ Tilemap Reference Guide - Fixed Sequential

This guide provides a detailed reference for all tile IDs used in the FocusFarm tiles map system with sequential numbering from 0 to 324.

## Table of Contents

- [Quick Reference](#quick-reference)
- [Water Tiles](#water-tiles)
- [Soil Tiles](#soil-tiles)
- [Grass Tiles](#grass-tiles)
- [Dark Grass Tiles](#dark-grass-tiles)
- [Bridge Tiles](#bridge-tiles)
- [Path Tiles](#path-tiles)
- [Water Decorations](#water-decorations)
- [Grass Decorations](#grass-decorations)
- [Trees](#trees)
- [Fences](#fences)
- [Gates](#gates)
- [Walls](#walls)
- [Doors](#doors)
- [Roof](#roof)
- [Coop](#coop)
- [Barn Structures](#barn-structures)
- [Water Tray](#water-tray)
- [Mailbox](#mailbox)
- [Work Station](#work-station)
- [Water Well](#water-well)
- [Piknik Blanket](#piknik-blanket)
- [Piknik Basket](#piknik-basket)
- [Furniture](#furniture)

---

## Quick Reference

### Files Overview

- **water.txt**: Water tiles and obstacles
- **soil.txt**: Ground/soil base layer
- **grass.txt**: Main grass layer with transitions
- **darkGrass.txt**: Dark grass variants and borders
- **bridges.txt**: Bridge tiles (override water obstacles)
- **groundDecor.txt**: Decorative elements and paths

### ID Ranges Summary

- **Water**: 1
- **Soil**: 2-8
- **Grass**: 9-57
- **Dark Grass**: 58–95
- **Bridges**: 96-99
- **Paths**: 100-108
- **Water Decors**: 109–120
- **Grass Decors**: 121–149
- **Trees**: 150–184
- **Fences**: 185–195
- **Gates**: 196–203
- **Walls**: 204–215
- **Doors**: 216–219
- **Roof**: 220–242
- **Coop**: 243–285
- **Barn Structures**: 286–295
- **Water Tray**: 296–297
- **Mailbox**: 298–299
- **Work Station**: 300–303
- **Water Well**: 304–307
- **Piknik Blanket**: 308–316
- **Piknik Basket**: 317
- **Furniture**: 318–324

### Animation Notes

- ???

### Obstacle Notes

- ???

---

## No tiles

| ID | Tile Name | Notes    |
|----|-----------|----------|
| 0  | Empty     | No tiles |

---

## Map Tiles

---

### Water Tiles

**File:** `MapTiles/water.png`

| ID | Tile Name | Notes              |
|----|-----------|--------------------|
| 1  | Water     | Animated, obstacle |

---

### Soil Tiles

**File:** `MapTiles/soil.png`

| ID | Tile Name               | Notes |
|----|-------------------------|-------|
| 2  | Plain soil              |       |
| 3  | Dark sand soil          |       |
| 4  | Two dark stones soil    |       |
| 5  | Three dark stones soil  |       |
| 6  | Light sand soil         |       |
| 7  | Two light stones soil   |       |
| 8  | Three light stones soil |       |

---

### Grass Tiles

**File:** `MapTiles/grass_water.png`

| ID    | Tile Name                        | Notes                     |
|-------|----------------------------------|---------------------------|
| 9     | Plain grass                      |                           |
| 10    | Up grass water                   | Top border                |
| 11    | Left grass water                 | Left border               |
| 12    | Right grass water                | Right border              |
| 13    | Bottom grass water               | Bottom border             |
| 14    | Left-up corner grass water       | Outer corner              |
| 15    | Left-bottom corner grass water   | Outer corner              |
| 16    | Right-up corner grass water      | Outer corner              |
| 17    | Right-bottom corner grass water  | Outer corner              |
| 18    | Left-up inner grass water        | Inner corner              |
| 19    | Left-bottom inner grass water    | Inner corner              |
| 20    | Right-up inner grass water       | Inner corner              |
| 21    | Right-bottom inner grass water   | Inner corner              |
| 22    | Special transition grass water 1 | Special transition pieces |
| 23    | Special transition grass water 2 | Special transition pieces |
| 24    | Special transition grass water 3 | Special transition pieces |
| 25    | Special transition grass water 4 | Special transition pieces |
| 26    | Special transition grass water 5 | Special transition pieces |
| 27    | Special transition grass water 6 | Special transition pieces |
| 28    | Special transition grass water 7 | Special transition pieces |
| 29    | Green decor grass                |                           |
| 30    | Green two flowers grass          |                           |
| 31    | Green three flowers grass        |                           |
| 32    | Light decor grass                |                           |
| 33    | Light decor one flower grass     |                           |
| 34    | Light decor two flowers grass    |                           |
| 35    | Two dark rocks grass             |                           |
| 36    | Three dark rocks grass           |                           |
| 37    | Two light rocks grass            |                           |
| 38    | Three light rocks grass          |                           |
| 39    | Three flowers grass              |                           |
| 40    | Two flowers grass                |                           |

**File:** `MapTiles/grass_layer.png`

| ID    | Tile Name                        | Notes                     |
|-------|----------------------------------|---------------------------|
| 41    | Bottom grass layer               | Bottom border             |
| 42    | Left grass layer                 | Left border               |
| 43    | Right grass layer                | Right border              |
| 44    | Left-up corner grass layer       | Outer corner              |
| 45    | Left-bottom corner grass layer   | Outer corner              |
| 46    | Right-up corner grass layer      | Outer corner              |
| 47    | Right-bottom corner grass layer  | Outer corner              |
| 48    | Left-up inner grass layer        | Inner corner              |
| 49    | Left-bottom inner grass layer    | Inner corner              |
| 50    | Right-up inner grass layer       | Inner corner              |
| 51    | Right-bottom inner grass layer   | Inner corner              |
| 52    | Special transition grass layer 1 | Special transition pieces |
| 53    | Special transition grass layer 2 | Special transition pieces |
| 54    | Special transition grass layer 3 | Special transition pieces |
| 55    | Special transition grass layer 4 | Special transition pieces |
| 56    | Special transition grass layer 5 | Special transition pieces |
| 57    | Special transition grass layer 6 | Special transition pieces |

---

### Dark Grass Tiles

**File:** `MapTiles/dark_grass.png`

| ID | Tile Name                          | Notes                     |
|----|------------------------------------|---------------------------|
| 58 | Plain dark grass                   |                           |
| 59 | Up dark grass                      | Top border                |
| 60 | Left dark grass                    | Left border               |
| 61 | Bottom dark grass                  | Bottom border             |
| 62 | Right dark grass                   | Right border              |
| 63 | Left-up corner dark grass          | Outer corner              |
| 64 | Left-bottom corner dark grass      | Outer corner              |
| 65 | Right-up corner dark grass         | Outer corner              |
| 66 | Right-bottom corner dark grass     | Outer corner              |
| 67 | Left-up inner dark grass           | Inner corner              |
| 68 | Left-bottom inner dark grass       | Inner corner              |
| 69 | Right-up inner dark grass          | Inner corner              |
| 70 | Right-bottom inner dark grass      | Inner corner              |
| 71 | Special transition dark grass 1    | Special transition pieces |
| 72 | Special transition dark grass 2    | Special transition pieces |
| 73 | Special transition dark grass 3    | Special transition pieces |
| 74 | Special transition dark grass 4    | Special transition pieces |
| 75 | Special transition dark grass 5    | Special transition pieces |
| 76 | Special transition dark grass 6    | Special transition pieces |
| 77 | Special transition dark grass 7    | Special transition pieces |
| 78 | Special transition dark grass 8    | Special transition pieces |
| 79 | Special transition dark grass 9    | Special transition pieces |
| 80 | Special transition dark grass 10   | Special transition pieces |
| 81 | Special transition dark grass 11   | Special transition pieces |
| 82 | Special transition dark grass 12   | Special transition pieces |
| 83 | Special transition dark grass 13   | Special transition pieces |
| 84 | Special transition dark grass 14   | Special transition pieces |
| 85 | Special transition dark grass 15   | Special transition pieces |
| 86 | Special transition dark grass 16   | Special transition pieces |
| 87 | Special transition dark grass 17   | Special transition pieces |
| 88 | Special transition dark grass 18   | Special transition pieces |
| 89 | Special transition dark grass 19   | Special transition pieces |
| 90 | Special transition dark grass 20   | Special transition pieces |
| 91 | Special transition dark grass 21   | Special transition pieces |
| 92 | Green decor dark grass             |                           |
| 93 | Green three flowers dark grass     |                           |
| 94 | Three dark stones dark grass       |                           |
| 95 | Two dark stones dark grass         |                           |

---

### Bridge Tiles

**File:** `MapTiles/bridges.png`

| ID | Tile Name         | Notes |
|----|-------------------|-------|
| 96 | Left bridge       |       |
| 97 | Horizontal bridge |       |
| 98 | Right bridge      |       |
| 99 | Water left bridge |       |

---

### Path Tiles

**File:** `MapTiles/paths.png`

| ID      | Tile Name       | Notes             |
|---------|-----------------|-------------------|
| 100     | Path up         |                   |
| 101     | Path vertical   |                   |
| 102     | Path bottom     |                   |
| 103     | Path left       |                   |
| 104     | Path horizontal |                   |
| 105     | Path right      |                   |
| 106     | Path arc 1      | Curved path piece |
| 107     | Path arc 2      | Curved path piece |
| 108     | Path arc 3      | Curved path piece |

---

## Nature

---

### Water Decorations

**File:** `Nature/water_decor.png`

| ID  | Tile Name          | Notes |
|-----|--------------------|-------|
| 109 | Water stone 1      |       |
| 110 | Water stone 2      |       |
| 111 | Water stone 3      |       |
| 112 | Water stone 4      |       |
| 113 | Big water stone 1  |       |
| 114 | Big water stone 2  |       |
| 115 | Three water sticks |       |
| 116 | Four water sticks  |       |
| 117 | Water lily 1       |       |
| 118 | Water lily 2       |       |
| 119 | Water lily 3       |       |
| 120 | Water lily 4       |       |

---

### Grass Decorations

**File:** `Nature/grass_decor.png`

| ID  | Tile Name                | Notes           |
|-----|--------------------------|-----------------|
| 121 | Small shroom             |                 |
| 122 | Two small shrooms        |                 |
| 123 | Big shroom               | Obstacle        |
| 124 | Poison shroom            | Obstacle        |
| 125 | Small stone 1            |                 |
| 126 | Small stone 2            |                 |
| 127 | Small stone 3            |                 |
| 128 | Small stone 4            |                 |
| 129 | Stone 1                  | Obstacle        |
| 130 | Stone 2                  | Obstacle        |
| 131 | Big stone top-left       |                 |
| 132 | Big stone top-right      |                 |
| 133 | Big stone bottom-left    | Obstacle        |
| 134 | Big stone bottom-right   | Obstacle        |
| 135 | Large stone top-left     |                 |
| 136 | Large stone top-right    |                 |
| 137 | Large stone bottom-left  | Obstacle        |
| 138 | Large stone bottom-right | Obstacle        |
| 139 | Small bush 1             |                 |
| 140 | Small bush 2             |                 |
| 141 | Small bush 3             |                 |
| 142 | Small bush 4             |                 |
| 143 | Pink flower              | Farmable entity |x
| 144 | Blue flower              | Farmable entity |x
| 145 | Violet flower            | Farmable entity |x
| 146 | Yellow flower top        | Farmable entity |x
| 147 | Yellow flower bottom     | Farmable entity |x
| 148 | White flower             | Farmable entity |x
| 149 | Dark blue flower         | Farmable entity |x

---

### Trees

**File:** `Nature/trees.png`

| ID    | Tile Name                | Notes              |
|-------|--------------------------|--------------------|
| 150   | Small tree top           |                    |
| 151   | Small tree bottom        | Obstacle           |
| 152   | Bush                     | Animated, obstacle |
| 153   | Large tree top-left      |                    |
| 154   | Large tree top-center    |                    |
| 155   | Large tree top-right     |                    |
| 156   | Large tree middle-left   |                    |
| 157   | Large tree middle-center |                    |
| 158   | Large tree middle-right  |                    |
| 159   | Large tree bottom-left   | Obstacle           |
| 160   | Large tree bottom-center | Obstacle           |
| 161   | Large tree bottom-right  | Obstacle           |
| 162   | Stump 1                  | Obstacle           |
| 163   | Stump 2                  | Obstacle           |
| 164   | Stump 3 left             | Obstacle           |
| 165   | Stump 3 right            | Obstacle           |
| 166   | Stump 4 left             | Obstacle           |
| 167   | Stump 4 right            | Obstacle           |
| 168   | Small log                | Obstacle           |
| 169   | Big log left             | Obstacle           |
| 170   | Big log right            | Obstacle           |
| 171   | Big shroomy log left     | Obstacle           |
| 172   | Big shroomy log right    | Obstacle           |

**File:** `Nature/tree.png`

| ID  | Tile Name          | Notes              |
|-----|--------------------|--------------------|
| 173 | Tree top-left      | Animated           |
| 174 | Tree top-center    | Animated           |
| 175 | Tree top-right     | Animated           |
| 176 | Tree middle-left   | Animated           |
| 177 | Tree middle-center | Animated           |
| 178 | Tree middle-right  | Animated           |
| 179 | Tree bottom-left   | Animated           |
| 180 | Tree bottom-center | Animated, obstacle |
| 181 | Tree bottom-right  | Animated           |

---

## Building Parts

---

### Fences

**File:** `BuildingParts/fences.png`

| ID  | Tile Name                 | Notes    |
|-----|---------------------------|----------|
| 182 | Fence up                  | Obstacle |
| 183 | Fence vertical            | Obstacle |
| 184 | Fence bottom              | Obstacle |
| 185 | Fence left                | Obstacle |
| 186 | Fence horizontal          | Obstacle |
| 187 | Fence right               | Obstacle |
| 188 | Single fence              | Obstacle |
| 189 | Single fence broken small | Obstacle |
| 190 | Single fence broken       | Obstacle |
| 191 | Left fence broken         | Obstacle |
| 192 | Right fence broken        | Obstacle |

---

### Gates

**File:** `BuildingParts/gates.png`

| ID  | Tile Name                  | Notes                                  |
|-----|----------------------------|----------------------------------------|
| 193 | Gate horizontal left       | Obstacle                               |
| 194 | Gate horizontal left gate  | Animated, depends on state if obstacle |
| 195 | Gate horizontal right gate | Animated, depends on state if obstacle |
| 196 | Gate horizontal right      | Obstacle                               |
| 197 | Gate vertical up           | Obstacle                               |
| 198 | Gate vertical up gate      | Animated, depends on state if obstacle |
| 199 | Gate vertical down gate    | Animated, depends on state if obstacle |
| 200 | Gate vertical down         | Obstacle                               |

---

### Walls

**File:** `BuildingParts/walls.png`

| ID    | Tile Name                    | Notes    |
|-------|------------------------------|----------|
| 201   | Wall front                   | Obstacle |
| 202   | Wall left                    | Obstacle |
| 203   | Wall right                   | Obstacle |
| 204   | Wall up-left corner          | Obstacle |
| 205   | Wall up-left big corner      | Obstacle |
| 206   | Wall bottom-left big corner  | Obstacle |
| 207   | Wall up-right big corner     | Obstacle |
| 208   | Wall bottom-right big corner | Obstacle |
| 209   | Window                       | Obstacle |
| 210   | Floor                        | Obstacle |

---

### Doors

**File:** `BuildingParts/doors.png`

| ID    | Tile Name           | Notes                                  |
|-------|---------------------|----------------------------------------|
| 211   | Single door         | Animated, depends on state if obstacle |
| 212   | Double doors left   | Animated, depends on state if obstacle |
| 213   | Double doors middle | Animated, depends on state if obstacle |
| 214   | Double doors right  | Animated, depends on state if obstacle |

---

### Roof

**File:** `BuildingParts/roof.png`

| ID    | Tile Name                     | Notes |
|-------|-------------------------------|-------|
| 215   | Roof middle up                |       |
| 216   | Roof middle decor up dark     |       |
| 217   | Roof middle decor up light    |       |
| 218   | Roof middle down              |       |
| 219   | Roof middle decor down dark   |       |
| 220   | Roof middle decor down light  |       |
| 221   | Roof edge up                  |       |
| 222   | Roof edge up left             |       |
| 223   | Roof edge up right            |       |
| 224   | Roof side up down left        |       |
| 225   | Roof side up down right       |       |
| 226   | Roof edge down                |       |
| 227   | Roof edge down left           |       |
| 228   | Roof edge down right          |       |
| 229   | Roof side down left           |       |
| 230   | Roof side down right          |       |
| 231   | Roof side corner left-up      |       |
| 232   | Roof side corner right-up     |       |
| 233   | Roof chimney                  |       |
| 234   | Roof middle part              |       |
| 235   | Roof middle part left         |       |
| 236   | Roof middle part right        |       |

---

### Coop

**File:** `BuildingParts/coop.png`

| ID    | Tile Name     | Notes    |
|-------|---------------|----------|
| 237   | Small coop 1  | Obstacle |
| 238   | Small coop 2  | Obstacle |
| 239   | Small coop 3  | Obstacle |
| 240   | Small coop 4  | Obstacle |
| 241   | Small coop 5  | Obstacle |
| 242   | Small coop 6  | Obstacle |
| 243   | Big coop 1    | Obstacle |
| 244   | Big coop 2    | Obstacle |
| 245   | Big coop 3    | Obstacle |
| 246   | Big coop 4    | Obstacle |
| 247   | Big coop 5    | Obstacle |
| 248   | Big coop 6    | Obstacle |
| 249   | Big coop 7    | Obstacle |
| 250   | Big coop 8    | Obstacle |
| 251   | Big coop 9    | Obstacle |
| 252   | Large coop 1  | Obstacle |
| 253   | Large coop 2  | Obstacle |
| 254   | Large coop 3  | Obstacle |
| 255   | Large coop 4  | Obstacle |
| 256   | Large coop 5  | Obstacle |
| 257   | Large coop 6  | Obstacle |
| 258   | Large coop 7  | Obstacle |
| 259   | Large coop 8  | Obstacle |
| 260   | Large coop 9  | Obstacle |
| 261   | Large coop 10 | Obstacle |
| 262   | Large coop 11 | Obstacle |
| 263   | Large coop 12 | Obstacle |
| 264   | Large coop 13 | Obstacle |
| 265   | Large coop 14 | Obstacle |
| 266   | Large coop 15 | Obstacle |
| 267   | Large coop 16 | Obstacle |
| 268   | Large coop 17 | Obstacle |
| 269   | Large coop 18 | Obstacle |
| 270   | Large coop 19 | Obstacle |
| 271   | Large coop 20 | Obstacle |

---

## Objects

---

### Barn Structures

**File:** `Objects/barn_structures.png`

| ID  | Tile Name              | Notes    |
|-----|------------------------|----------|
| 272 | Box                    | Obstacle |
| 273 | Two boxes top-left     | Obstacle |
| 274 | Two boxes top-right    | Obstacle |
| 275 | Two boxes bottom-left  | Obstacle |
| 276 | Two boxes bottom-right | Obstacle |
| 277 | Hay                    | Obstacle |
| 278 | Big hay left           | Obstacle |
| 279 | Big hay right          | Obstacle |
| 280 | Patch of hay           |          |
| 281 | Big patch of hay       |          |

---

### Water Tray

**File:** `Objects/water_tray.png`

| ID  | Tile Name        | Notes    |
|-----|------------------|----------|
| 282 | Water tray left  | Obstacle |
| 283 | Water tray right | Obstacle |

---

### Mailbox

**File:** `Objects/mailbox.png`

| ID  | Tile Name      | Notes              |
|-----|----------------|--------------------|
| 284 | Mailbox up     | Animated           |
| 285 | Mailbox bottom | Animated, obstacle |

---

### Work Station

**File:** `Objects/work_station.png`

| ID  | Tile Name                 | Notes    |
|-----|---------------------------|----------|
| 286 | Work station left-up      |          |
| 287 | Work station left-bottom  | Obstacle |
| 288 | Work station right-up     |          |
| 289 | Work station right-bottom | Obstacle |

---

### Water Well

**File:** `Objects/water_well.png`

| ID  | Tile Name               | Notes    |
|-----|-------------------------|----------|
| 290 | Water well left-up      |          |
| 291 | Water well left-bottom  | Obstacle |
| 292 | Water well right-up     |          |
| 293 | Water well right-bottom | Obstacle |

---

### Piknik Blanket

**File:** `Objects/piknik_blanket.png`

| ID  | Tile Name        | Notes    |
|-----|------------------|----------|
| 294 | Piknik blanket 1 |          |
| 295 | Piknik blanket 2 |          |
| 296 | Piknik blanket 3 |          |
| 297 | Piknik blanket 4 |          |
| 298 | Piknik blanket 5 |          |
| 299 | Piknik blanket 6 |          |
| 300 | Piknik blanket 7 |          |
| 301 | Piknik blanket 8 |          |
| 302 | Piknik blanket 9 |          |

---

### Piknik Basket

**File:** `Objects/piknik_basket.png`

| ID  | Tile Name     | Notes    |
|-----|---------------|----------|
| 303 | Piknik basket |          |

---

### Furniture

**File:** `BuildingParts/basic_furniture.png`

| ID  | Tile Name                    | Notes |
|-----|------------------------------|-------|
| 304 | Painting                     |       |
| 305 | Pot flower                   |       |
| 306 | Bed top                      |       |
| 307 | Pink bed bottom              |       |
| 308 | Blue bed bottom              |       |
| 309 | Green bed bottom             |       |
| 310 | Green upside down bed top    |       |
| 311 | Green upside down bed bottom |       |
| 312 | Chair left                   |       |
| 313 | Chair down                   |       |
| 314 | Dresser                      |       |
| 315 | Table                        |       |
| 316 | Pink big carpet left         |       |
| 317 | Pink big carpet right        |       |
| 318 | Blue big carpet left         |       |
| 319 | Blue big carpet right        |       |
| 320 | Green big carpet left        |       |
| 321 | Green big carpet right       |       |
| 322 | Pink small carpet            |       |
| 323 | Blue small carpet            |       |
| 324 | Green small carpet           |       |
