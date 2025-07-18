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
- [Walls](#walls)
- [Doors](#doors)
- [Roof](#roof)
- [Fences](#fences)
- [Gates](#gates)
- [Coop](#coop)
- [Barn Structures](#barn-structures)
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
- **Grass Decors**: 121–163
- **Trees**: 164–193
- **Walls**: 194–206
- **Doors**: 207–208
- **Roof**: 209–233
- **Fences**: 234–256
- **Gates**: 257–264
- **Coop**: 265–284
- **Barn Structures**: 285–294
- **Furniture**: 295–315

### Obstacle Notes

- ???

---

## No tiles

| ID | Tile Name | Notes    |
|----|-----------|----------|
| 0  | Empty     | No tiles |

---

## Water Tiles

**File:** `water.txt`

| ID | Tile Name | Notes              |
|----|-----------|--------------------|
| 1  | Water     | Animated, obstacle |

**Implementation Notes:**

- Obtained through `createWaterAnimation()` in `ResourceHandler` class

---

## Soil Tiles

**File:** `soil.png`

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

## Grass Tiles

**File:** `grass_water.png`

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

**File:** `grass_layer.png`

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

## Dark Grass Tiles

**File:** `dark_grass.png`

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

## Bridge Tiles

**File:** `bridges.png`

| ID | Tile Name         | Notes |
|----|-------------------|-------|
| 96 | Left bridge       |       |
| 97 | Horizontal bridge |       |
| 98 | Right bridge      |       |
| 99 | Water left bridge |       |

---

## Path Tiles

**File:** `paths.png`

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

## Water Decorations

**File:** `water_decor.png`

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

## Grass Decorations

**File:** `grass_decor.png`

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
| 143 | Yellow flower 1          |          |
| 144 | Yellow flower 2          |          |
| 145 | Yellow flower 3          |          |
| 146 | Yellow flower 4 top      |          |
| 147 | Yellow flower 4 bottom   | Obstacle |
| 148 | Violet flower 1          |          |
| 149 | Violet flower 2          |          |
| 150 | Violet flower 3          |          |
| 151 | Pink flower 1            |          |
| 152 | Pink flower 2            |          |
| 153 | Pink flower 3            |          |
| 154 | Pink flower 4            | Obstacle |
| 155 | Blue flower 1            |          |
| 156 | Blue flower 2            |          |
| 157 | Blue flower 3            |          |
| 158 | Blue flower 4            | Obstacle |
| 159 | White flower 1           |          |
| 160 | White flower 2           |          |
| 161 | White flower 3           |          |
| 162 | Dark blue flower 1       |          |
| 163 | Dark blue flower 2       |          |

---

## Trees

**File:** `trees.png`

| ID  | Tile Name                | Notes              |
|-----|--------------------------|--------------------|
| 164 | Small tree top           |                    |
| 165 | Small tree bottom        | Obstacle           |
| 166 | Bush                     | Animated, obstacle |
| 167 | Large tree top-left      |                    |
| 168 | Large tree top-center    |                    |
| 169 | Large tree top-right     |                    |
| 170 | Large tree middle-left   |                    |
| 171 | Large tree middle-center |                    |
| 172 | Large tree middle-right  |                    |
| 173 | Large tree bottom-left   | Obstacle           |
| 174 | Large tree bottom-center | Obstacle           |
| 175 | Large tree bottom-right  | Obstacle           |
| 176 | Stump 1                  | Obstacle           |
| 177 | Stump 2                  | Obstacle           |
| 178 | Stump 3 left             | Obstacle           |
| 179 | Stump 3 right            | Obstacle           |
| 180 | Stump 4 left             | Obstacle           |
| 181 | Stump 4 right            | Obstacle           |
| 182 | Small log                | Obstacle           |
| 183 | Big log                  | Obstacle           |
| 184 | Big shroomy log          | Obstacle           |

**File:** `Trees/tree.png`

| ID  | Tile Name          | Notes              |
|-----|--------------------|--------------------|
| 185 | Tree top-left      | Animated           |
| 186 | Tree top-center    | Animated           |
| 187 | Tree top-right     | Animated           |
| 188 | Tree middle-left   | Animated           |
| 189 | Tree middle-center | Animated           |
| 190 | Tree middle-right  | Animated           |
| 191 | Tree bottom-left   | Animated           |
| 192 | Tree bottom-center | Animated, obstacle |
| 193 | Tree bottom-right  | Animated           |

---

## Walls

**File:** `BuildingParts/walls.png`

| ID  | Tile Name                      | Notes    |
|-----|--------------------------------|----------|
| 194 | Wall front                     | Obstacle |
| 195 | Wall back                      | Obstacle |
| 196 | Wall left                      | Obstacle |
| 197 | Wall right                     | Obstacle |
| 198 | Wall up-left corner            | Obstacle |
| 199 | Wall bottom-left corner        | Obstacle |
| 200 | Wall up-right corner           | Obstacle |
| 201 | Wall bottom-right corner       | Obstacle |
| 202 | Wall up-left inner corner      | Obstacle |
| 203 | Wall bottom-left inner corner  | Obstacle |
| 204 | Wall up-right inner corner     | Obstacle |
| 205 | Wall bottom-right inner corner | Obstacle |
| 206 | Window                         | Obstacle |

---

## Doors

**File:** `BuildingParts/doors.png`

| ID  | Tile Name    | Notes                                  |
|-----|--------------|----------------------------------------|
| 207 | Single door  | Animated, depends on state if obstacle |
| 208 | Double doors | Animated, depends on state if obstacle |

---

## Roof

**File:** `BuildingParts/roof.png`

| ID  | Tile Name                     | Notes |
|-----|-------------------------------|-------|
| 209 | Roof middle up                |       |
| 210 | Roof middle decor up 1        |       |
| 211 | Roof middle decor up 2        |       |
| 212 | Roof middle decor up 3        |       |
| 213 | Roof middle decor up 4        |       |
| 214 | Roof middle down              |       |
| 215 | Roof middle decor down 1      |       |
| 216 | Roof middle decor down 2      |       |
| 217 | Roof middle decor down 3      |       |
| 218 | Roof middle decor down 4      |       |
| 219 | Roof edge up                  |       |
| 220 | Roof edge up left             |       |
| 221 | Roof edge up right            |       |
| 222 | Roof side up down right       |       |
| 223 | Roof side up down left        |       |
| 224 | Roof edge down                |       |
| 225 | Roof edge down left           |       |
| 226 | Roof edge down right          |       |
| 227 | Roof side edge down right     |       |
| 228 | Roof side edge down left      |       |
| 229 | Roof side corner left-up      |       |
| 230 | Roof side corner left-bottom  |       |
| 231 | Roof side corner right-up     |       |
| 232 | Roof side corner right-bottom |       |
| 233 | Roof chimney                  |       |

---

## Fences

**File:** `BuildingParts/fences.png`

| ID  | Tile Name              | Notes    |
|-----|------------------------|----------|
| 234 | Fence vertical         | Obstacle |
| 235 | Fence horizontal       | Obstacle |
| 236 | Fence corner up left   | Obstacle |
| 237 | Fence corner up right  | Obstacle |
| 238 | Fence corner down left | Obstacle |
| 239 | Fence corner down right| Obstacle |
| 240 | Fence T up             | Obstacle |
| 241 | Fence T down           | Obstacle |
| 242 | Fence T left           | Obstacle |
| 243 | Fence T right          | Obstacle |
| 244 | Fence cross            | Obstacle |
| 245 | Fence end up           | Obstacle |
| 246 | Fence end down         | Obstacle |
| 247 | Fence end left         | Obstacle |
| 248 | Fence end right        | Obstacle |
| 249 | Fence post vertical    | Obstacle |
| 250 | Fence post horizontal  | Obstacle |
| 251 | Fence post corner up left | Obstacle |
| 252 | Fence post corner up right | Obstacle |
| 253 | Fence post corner down left | Obstacle |
| 254 | Fence post corner down right | Obstacle |
| 255 | Fence post T up        | Obstacle |
| 256 | Fence post T down      | Obstacle |

---

## Gates

**File:** `BuildingParts/gates.png`

| ID  | Tile Name                  | Notes                                  |
|-----|----------------------------|----------------------------------------|
| 257 | Gate horizontal left       | Obstacle                               |
| 258 | Gate horizontal left gate  | Animated, depends on state if obstacle |
| 259 | Gate horizontal right gate | Animated, depends on state if obstacle |
| 260 | Gate horizontal right      | Obstacle                               |
| 261 | Gate vertical up           | Obstacle                               |
| 262 | Gate vertical up gate      | Animated, depends on state if obstacle |
| 263 | Gate vertical down gate    | Animated, depends on state if obstacle |
| 264 | Gate vertical down         | Obstacle                               |

---

## Coop

**File:** `BuildingParts/coop.png`

| ID  | Tile Name     | Notes    |
|-----|---------------|----------|
| 265 | Small coop 1  | Obstacle |
| 266 | Small coop 2  | Obstacle |
| 267 | Small coop 3  | Obstacle |
| 268 | Small coop 4  | Obstacle |
| 269 | Big coop 1    | Obstacle |
| 270 | Big coop 2    | Obstacle |
| 271 | Big coop 3    | Obstacle |
| 272 | Big coop 4    | Obstacle |
| 273 | Big coop 5    | Obstacle |
| 274 | Big coop 6    | Obstacle |
| 275 | Big coop 7    | Obstacle |
| 276 | Big coop 8    | Obstacle |
| 277 | Big coop 9    | Obstacle |
| 278 | Big coop 10   | Obstacle |
| 279 | Big coop 11   | Obstacle |
| 280 | Big coop 12   | Obstacle |
| 281 | Big coop 13   | Obstacle |
| 282 | Big coop 14   | Obstacle |
| 283 | Big coop 15   | Obstacle |
| 284 | Big coop 16   | Obstacle |

---

## Barn Structures

**File:** `BuildingParts/barn_structures.png`

| ID  | Tile Name              | Notes    |
|-----|------------------------|----------|
| 285 | Box                    | Obstacle |
| 286 | Two boxes top-left     | Obstacle |
| 287 | Two boxes top-right    | Obstacle |
| 288 | Two boxes bottom-left  | Obstacle |
| 289 | Two boxes bottom-right | Obstacle |
| 290 | Hay                    | Obstacle |
| 291 | Big hay left           | Obstacle |
| 292 | Big hay right          | Obstacle |
| 293 | Patch of hay           |          |
| 294 | Big patch of hay       |          |

## Furniture

**File:** `BuildingParts/basic_furniture.png`

| ID  | Tile Name                    | Notes |
|-----|------------------------------|-------|
| 295 | Painting                     |       |
| 296 | Pot flower                   |       |
| 297 | Bed top                      |       |
| 298 | Pink bed bottom              |       |
| 299 | Blue bed bottom              |       |
| 300 | Green bed bottom             |       |
| 301 | Green upside down bed top    |       |
| 302 | Green upside down bed bottom |       |
| 303 | Chair left                   |       |
| 304 | Chair down                   |       |
| 305 | Dresser                      |       |
| 306 | Table                        |       |
| 307 | Pink big carpet left         |       |
| 308 | Pink big carpet right        |       |
| 309 | Blue big carpet left         |       |
| 310 | Blue big carpet right        |       |
| 311 | Green big carpet left        |       |
| 312 | Green big carpet right       |       |
| 313 | Pink small carpet            |       |
| 314 | Blue small carpet            |       |
| 315 | Green small carpet           |       |
