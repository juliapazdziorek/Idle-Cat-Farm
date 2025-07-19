# 🗺️ Tilemap Reference Guide

This guide provides a detailed reference for all tile IDs used in the FocusFarm tiles map system.

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
- **Grass Decors**: 121–142
- **Trees**: 143–172
- **Fences**: 173–183
- **Gates**: 184–191
- **Walls**: 192–204
- **Doors**: 205–206
- **Roof**: 207–227
- **Coop**: 228–252
- **Barn Structures**: 253–262
- **Water Tray**: 263–264
- **Mailbox**: 265–266
- **Work Station**: 267–270
- **Water Well**: 271–274
- **Piknik Blanket**: 275–283
- **Piknik Basket**: 284
- **Furniture**: 285–305

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

| ID      | Tile Name       | Notes              |
|---------|-----------------|--------------------|
| 100     | Path up         |                    |
| 101     | Path vertical   |                    |
| 102     | Path bottom     |                    |
| 103     | Path left       |                    |
| 104     | Path horizontal |                    |
| 105     | Path right      |                    |
| 106     | Path arc 1      | Curved path pieces |
| 107     | Path arc 2      | Curved path pieces |
| 108     | Path arc 3      | Curved path pieces |

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

| ID  | Tile Name                | Notes    |
|-----|--------------------------|----------|
| 121 | Small shroom             |          |
| 122 | Two small shrooms        |          |
| 123 | Big shroom               | Obstacle |
| 124 | Poison shroom            | Obstacle |
| 125 | Small stone 1            |          |
| 126 | Small stone 2            |          |
| 127 | Small stone 3            |          |
| 128 | Small stone 4            |          |
| 129 | Stone 1                  | Obstacle |
| 130 | Stone 2                  | Obstacle |
| 131 | Big stone top-left       |          |
| 132 | Big stone top-right      |          |
| 133 | Big stone bottom-left    | Obstacle |
| 134 | Big stone bottom-right   | Obstacle |
| 135 | Large stone top-left     |          |
| 136 | Large stone top-right    |          |
| 137 | Large stone bottom-left  | Obstacle |
| 138 | Large stone bottom-right | Obstacle |
| 139 | Small bush 1             |          |
| 140 | Small bush 2             |          |
| 141 | Small bush 3             |          |
| 142 | Small bush 4             |          |

---

### Trees

**File:** `Nature/trees.png`

| ID  | Tile Name                | Notes              |
|-----|--------------------------|--------------------|
| 143 | Small tree top           |                    |
| 144 | Small tree bottom        | Obstacle           |
| 145 | Bush                     | Animated, obstacle |
| 146 | Large tree top-left      |                    |
| 147 | Large tree top-center    |                    |
| 148 | Large tree top-right     |                    |
| 149 | Large tree middle-left   |                    |
| 150 | Large tree middle-center |                    |
| 151 | Large tree middle-right  |                    |
| 152 | Large tree bottom-left   | Obstacle           |
| 153 | Large tree bottom-center | Obstacle           |
| 154 | Large tree bottom-right  | Obstacle           |
| 155 | Stump 1                  | Obstacle           |
| 156 | Stump 2                  | Obstacle           |
| 157 | Stump 3 left             | Obstacle           |
| 158 | Stump 3 right            | Obstacle           |
| 159 | Stump 4 left             | Obstacle           |
| 160 | Stump 4 right            | Obstacle           |
| 161 | Small log                | Obstacle           |
| 162 | Big log                  | Obstacle           |
| 163 | Big shroomy log          | Obstacle           |

**File:** `Nature/tree.png`

| ID  | Tile Name          | Notes              |
|-----|--------------------|--------------------|
| 164 | Tree top-left      | Animated           |
| 165 | Tree top-center    | Animated           |
| 166 | Tree top-right     | Animated           |
| 167 | Tree middle-left   | Animated           |
| 168 | Tree middle-center | Animated           |
| 169 | Tree middle-right  | Animated           |
| 170 | Tree bottom-left   | Animated           |
| 171 | Tree bottom-center | Animated, obstacle |
| 172 | Tree bottom-right  | Animated           |

---

## Building Parts

---

### Fences

**File:** `BuildingParts/fences.png`

| ID  | Tile Name             | Notes    |
|-----|-----------------------|----------|
| 173 | Fence up              | Obstacle |
| 174 | Fence vertical        | Obstacle |
| 175 | Fence bottom          | Obstacle |
| 176 | Fence left            | Obstacle |
| 177 | Fence horizontal      | Obstacle |
| 178 | Fence right           | Obstacle |
| 179 | Single fence          | Obstacle |
| 180 | Single fence broken 1 | Obstacle |
| 181 | Single fence broken 2 | Obstacle |
| 182 | Left fence broken     | Obstacle |
| 183 | Right fence broken    | Obstacle |

---

### Gates

**File:** `BuildingParts/gates.png`

| ID  | Tile Name                  | Notes                                  |
|-----|----------------------------|----------------------------------------|
| 184 | Gate horizontal left       | Obstacle                               |
| 185 | Gate horizontal left gate  | Animated, depends on state if obstacle |
| 186 | Gate horizontal right gate | Animated, depends on state if obstacle |
| 187 | Gate horizontal right      | Obstacle                               |
| 188 | Gate vertical up           | Obstacle                               |
| 189 | Gate vertical up gate      | Animated, depends on state if obstacle |
| 190 | Gate vertical down gate    | Animated, depends on state if obstacle |
| 191 | Gate vertical down         | Obstacle                               |

---

### Walls

**File:** `BuildingParts/walls.png`

| ID  | Tile Name                      | Notes    |
|-----|--------------------------------|----------|
| 192 | Wall front                     | Obstacle |
| 193 | Wall back                      | Obstacle |
| 194 | Wall left                      | Obstacle |
| 195 | Wall right                     | Obstacle |
| 196 | Wall up-left corner            | Obstacle |
| 197 | Wall bottom-left corner        | Obstacle |
| 198 | Wall up-right corner           | Obstacle |
| 199 | Wall bottom-right corner       | Obstacle |
| 200 | Wall up-left inner corner      | Obstacle |
| 201 | Wall bottom-left inner corner  | Obstacle |
| 202 | Wall up-right inner corner     | Obstacle |
| 203 | Wall bottom-right inner corner | Obstacle |
| 204 | Window                         | Obstacle |

---

### Doors

**File:** `BuildingParts/doors.png`

| ID  | Tile Name    | Notes                                  |
|-----|--------------|----------------------------------------|
| 205 | Single door  | Animated, depends on state if obstacle |
| 206 | Double doors | Animated, depends on state if obstacle |

---

### Roof

**File:** `BuildingParts/roof.png`

| ID  | Tile Name                     | Notes |
|-----|-------------------------------|-------|
| 207 | Roof middle up                |       |
| 208 | Roof middle decor up 1        |       |
| 209 | Roof middle decor up 2        |       |
| 210 | Roof middle down              |       |
| 211 | Roof middle decor down 1      |       |
| 212 | Roof middle decor down 2      |       |
| 213 | Roof edge up                  |       |
| 214 | Roof edge up left             |       |
| 215 | Roof edge up right            |       |
| 216 | Roof side up down right       |       |
| 217 | Roof side up down left        |       |
| 218 | Roof edge down                |       |
| 219 | Roof edge down left           |       |
| 220 | Roof edge down right          |       |
| 221 | Roof side edge down right     |       |
| 222 | Roof side edge down left      |       |
| 223 | Roof side corner left-up      |       |
| 224 | Roof side corner left-bottom  |       |
| 225 | Roof side corner right-up     |       |
| 226 | Roof side corner right-bottom |       |
| 227 | Roof chimney                  |       |

---

### Coop

**File:** `BuildingParts/coop.png`

| ID  | Tile Name    | Notes    |
|-----|--------------|----------|
| 228 | Small coop 1 | Obstacle |
| 229 | Small coop 2 | Obstacle |
| 230 | Small coop 3 | Obstacle |
| 231 | Small coop 4 | Obstacle |
| 232 | Small coop 5 | Obstacle |
| 233 | Small coop 6 | Obstacle |
| 234 | Big coop 1   | Obstacle |
| 235 | Big coop 2   | Obstacle |
| 236 | Big coop 3   | Obstacle |
| 237 | Big coop 4   | Obstacle |
| 238 | Big coop 5   | Obstacle |
| 239 | Big coop 6   | Obstacle |
| 240 | Big coop 7   | Obstacle |
| 241 | Big coop 8   | Obstacle |
| 242 | Big coop 9   | Obstacle |
| 243 | Big coop 10  | Obstacle |
| 244 | Big coop 11  | Obstacle |
| 245 | Big coop 12  | Obstacle |
| 246 | Big coop 13  | Obstacle |
| 247 | Big coop 14  | Obstacle |
| 248 | Big coop 15  | Obstacle |
| 249 | Big coop 16  | Obstacle |
| 250 | Big coop 17  | Obstacle |
| 251 | Big coop 18  | Obstacle |
| 252 | Big coop 19  | Obstacle |

---

## Objects

---

### Barn Structures

**File:** `Objects/barn_structures.png`

| ID  | Tile Name              | Notes    |
|-----|------------------------|----------|
| 253 | Box                    | Obstacle |
| 254 | Two boxes top-left     | Obstacle |
| 255 | Two boxes top-right    | Obstacle |
| 256 | Two boxes bottom-left  | Obstacle |
| 257 | Two boxes bottom-right | Obstacle |
| 258 | Hay                    | Obstacle |
| 259 | Big hay left           | Obstacle |
| 260 | Big hay right          | Obstacle |
| 261 | Patch of hay           |          |
| 262 | Big patch of hay       |          |

---

### Water Tray

**File:** `Objects/water_tray.png`

| ID  | Tile Name        | Notes    |
|-----|------------------|----------|
| 263 | Water tray left  | Obstacle |
| 264 | Water tray right | Obstacle |

---

### Mailbox

**File:** `Objects/mailbox.png`

| ID  | Tile Name      | Notes              |
|-----|----------------|--------------------|
| 265 | Mailbox up     | Animated           |
| 266 | Mailbox bottom | Animated, obstacle |

---

### Work Station

**File:** `Objects/work_station.png`

| ID  | Tile Name                 | Notes    |
|-----|---------------------------|----------|
| 267 | Work station left-up      |          |
| 268 | Work station left-bottom  | Obstacle |
| 269 | Work station right-up     |          |
| 270 | Work station right-bottom | Obstacle |

---

### Water Well

**File:** `Objects/water_well.png`

| ID  | Tile Name               | Notes    |
|-----|-------------------------|----------|
| 271 | Water well left-up      |          |
| 272 | Water well left-bottom  | Obstacle |
| 273 | Water well right-up     |          |
| 274 | Water well right-bottom | Obstacle |

---

### Piknik Blanket

**File:** `Objects/piknik_blanket.png`

| ID  | Tile Name        | Notes    |
|-----|------------------|----------|
| 275 | Piknik blanket 1 |          |
| 276 | Piknik blanket 2 |          |
| 277 | Piknik blanket 3 |          |
| 278 | Piknik blanket 4 |          |
| 279 | Piknik blanket 5 |          |
| 280 | Piknik blanket 6 |          |
| 281 | Piknik blanket 7 |          |
| 282 | Piknik blanket 8 |          |
| 283 | Piknik blanket 9 |          |

---

### Piknik Basket

**File:** `Objects/piknik_basket.png`

| ID  | Tile Name     | Notes    |
|-----|---------------|----------|
| 284 | Piknik basket |          |

---

### Furniture

**File:** `BuildingParts/basic_furniture.png`

| ID  | Tile Name                    | Notes |
|-----|------------------------------|-------|
| 285 | Painting                     |       |
| 286 | Pot flower                   |       |
| 287 | Bed top                      |       |
| 288 | Pink bed bottom              |       |
| 289 | Blue bed bottom              |       |
| 290 | Green bed bottom             |       |
| 291 | Green upside down bed top    |       |
| 292 | Green upside down bed bottom |       |
| 293 | Chair left                   |       |
| 294 | Chair down                   |       |
| 295 | Dresser                      |       |
| 296 | Table                        |       |
| 297 | Pink big carpet left         |       |
| 298 | Pink big carpet right        |       |
| 299 | Blue big carpet left         |       |
| 300 | Blue big carpet right        |       |
| 301 | Green big carpet left        |       |
| 302 | Green big carpet right       |       |
| 303 | Pink small carpet            |       |
| 304 | Blue small carpet            |       |
| 305 | Green small carpet           |       |
