# 🗺️ Tilemap Reference Guide

This guide provides a detailed reference for all tile IDs used in the FocusFarm tiles map system.

## Table of Contents

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
- [Sign](#sign)

---

### ID Ranges Summary

- **Water**: 1
- **Soil**: 2-8
- **Grass**: 9-57
- **Dark Grass**: 58–95
- **Bridges**: 96-99
- **Paths**: 100-108
- **Water Decors**: 109–120
- **Grass Decors**: 121–142
- **Trees**: 150-181
- **Fences**: 182–192
- **Gates**: 193–200
- **Walls**: 201–212
- **Doors**: 213–216
- **Roof**: 217–239
- **Coop**: 240–274
- **Barn Structures**: 275–284
- **Water Tray**: 285–286
- **Mailbox**: 287–288
- **Work Station**: 289–292
- **Water Well**: 293–296
- **Piknik Blanket**: 297–305
- **Piknik Basket**: 306
- **Furniture**: 307–327
- **Sign**: 328

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
| 150 | Small tree top           |                    |
| 151 | Small tree bottom        | Obstacle           |
| 152 | Bush                     | Animated, obstacle |
| 153 | Large tree top-left      |                    |
| 154 | Large tree top-center    |                    |
| 155 | Large tree top-right     |                    |
| 156 | Large tree middle-left   |                    |
| 157 | Large tree middle-center |                    |
| 158 | Large tree middle-right  |                    |
| 159 | Large tree bottom-left   | Obstacle           |
| 160 | Large tree bottom-center | Obstacle           |
| 161 | Large tree bottom-right  | Obstacle           |
| 162 | Small stump 1            | Obstacle           |
| 163 | Small stump 2            | Obstacle           |
| 164 | Stump 1 left             | Obstacle           |
| 165 | Stump 1 right            | Obstacle           |
| 166 | Stump 2 left             | Obstacle           |
| 167 | Stump 2 right            | Obstacle           |
| 168 | Small log                | Obstacle           |
| 169 | Big log left             | Obstacle           |
| 170 | Big log right            | Obstacle           |
| 171 | Big shroomy log left     | Obstacle           |
| 172 | Big shroomy log right    | Obstacle           |

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

| ID  | Tile Name             | Notes    |
|-----|-----------------------|----------|
| 182 | Fence up              | Obstacle |
| 183 | Fence vertical        | Obstacle |
| 184 | Fence bottom          | Obstacle |
| 185 | Fence left            | Obstacle |
| 186 | Fence horizontal      | Obstacle |
| 187 | Fence right           | Obstacle |
| 188 | Single fence          | Obstacle |
| 189 | Single fence broken 1 | Obstacle |
| 190 | Single fence broken 2 | Obstacle |
| 191 | Left fence broken     | Obstacle |
| 192 | Right fence broken    | Obstacle |

---

### Gates

**File:** `BuildingParts/gates.png`

| ID  | Tile Name                  | Notes    |
|-----|----------------------------|----------|
| 193 | Gate horizontal left       | Obstacle |
| 194 | Gate horizontal left gate  | Animated |
| 195 | Gate horizontal right gate | Animated |
| 196 | Gate horizontal right      | Obstacle |
| 197 | Gate vertical up           | Obstacle |
| 198 | Gate vertical up gate      | Animated |
| 199 | Gate vertical down gate    | Animated |
| 200 | Gate vertical down         | Obstacle |

---

### Walls

**File:** `BuildingParts/walls.png`

| ID  | Tile Name                      | Notes    |
|-----|--------------------------------|----------|
| 201 | Wall front                     | Obstacle |
| 202 | Wall left                      | Obstacle |
| 203 | Wall right                     | Obstacle |
| 204 | Wall up-left corner            | Obstacle |
| 205 | Wall bottom-left corner        | Obstacle |
| 206 | Wall bottom-right corner       | Obstacle |
| 207 | Wall up-left inner corner      | Obstacle |
| 208 | Wall bottom-left inner corner  | Obstacle |
| 209 | Wall up-right inner corner     | Obstacle |
| 210 | Wall bottom-right inner corner | Obstacle |
| 211 | Window                         | Obstacle |
| 212 | Floor                          |          |

---

### Doors

**File:** `BuildingParts/doors.png`

| ID  | Tile Name      | Notes    |
|-----|----------------|----------|
| 213 | Single door    | Animated |
| 214 | Double doors 1 | Animated |
| 215 | Double doors 2 | Animated |
| 216 | Double doors 3 | Animated |

---

### Roof

**File:** `BuildingParts/roof.png`

| ID  | Tile Name                         | Notes |
|-----|-----------------------------------|-------|
| 217 | Roof edge corner up-left          |       |
| 218 | Roof edge up                      |       |
| 219 | Roof edge corner up-right         |       |
| 220 | Roof edge up left                 |       |
| 221 | Roof middle up                    |       |
| 222 | Roof middle up light decor        |       |
| 223 | Roof middle up dark decor         |       |
| 224 | Roof edge up right                |       |
| 225 | Roof middle part edge left        |       |
| 226 | Roof middle part                  |       |
| 227 | Roof middle part edge right       |       |
| 228 | Roof edge down left               |       |
| 229 | Roof middle down                  |       |
| 230 | Roof middle down light decor      |       |
| 231 | Roof middle down dark decor       |       |
| 232 | Roof edge down right              |       |
| 233 | Roof edge corner bottom-left      |       |
| 234 | Roof edge down                    |       |
| 235 | Roof edge corner bottom-right     |       |
| 236 | Roof edge inner corner up-left    |       |
| 237 | Roof edge inner corner up-right   |       |
| 238 | Roof edge inner corner down-right |       |
| 239 | Roof chimney                      |       |

---

### Coop

**File:** `BuildingParts/coop.png`

| ID  | Tile Name     | Notes    |
|-----|---------------|----------|
| 240 | Small coop 1  | Obstacle |
| 241 | Small coop 2  | Obstacle |
| 242 | Small coop 3  | Obstacle |
| 243 | Small coop 4  | Obstacle |
| 244 | Small coop 5  | Obstacle |
| 245 | Small coop 6  | Obstacle |
| 246 | Big coop 1    | Obstacle |
| 247 | Big coop 2    | Obstacle |
| 248 | Big coop 3    | Obstacle |
| 249 | Big coop 4    | Obstacle |
| 250 | Big coop 5    | Obstacle |
| 251 | Big coop 6    | Obstacle |
| 252 | Big coop 7    | Obstacle |
| 253 | Big coop 8    | Obstacle |
| 254 | Big coop 9    | Obstacle |
| 255 | Large coop 1  | Obstacle |
| 256 | Large coop 2  | Obstacle |
| 257 | Large coop 3  | Obstacle |
| 258 | Large coop 4  | Obstacle |
| 259 | Large coop 5  | Obstacle |
| 260 | Large coop 6  | Obstacle |
| 261 | Large coop 7  | Obstacle |
| 262 | Large coop 8  | Obstacle |
| 263 | Large coop 9  | Obstacle |
| 264 | Large coop 10 | Obstacle |
| 265 | Large coop 11 | Obstacle |
| 266 | Large coop 12 | Obstacle |
| 267 | Large coop 13 | Obstacle |
| 268 | Large coop 14 | Obstacle |
| 269 | Large coop 15 | Obstacle |
| 270 | Large coop 16 | Obstacle |
| 271 | Large coop 17 | Obstacle |
| 272 | Large coop 18 | Obstacle |
| 273 | Large coop 19 | Obstacle |
| 274 | Large coop 20 | Obstacle |

---

## Objects

---

### Barn Structures

**File:** `Objects/barn_structures.png`

| ID  | Tile Name              | Notes    |
|-----|------------------------|----------|
| 275 | Box                    | Obstacle |
| 276 | Two boxes top-left     | Obstacle |
| 277 | Two boxes top-right    | Obstacle |
| 278 | Two boxes bottom-left  | Obstacle |
| 279 | Two boxes bottom-right | Obstacle |
| 280 | Hay                    | Obstacle |
| 281 | Big hay left           | Obstacle |
| 282 | Big hay right          | Obstacle |
| 283 | Patch of hay           |          |
| 284 | Big patch of hay       |          |

---

### Water Tray

**File:** `Objects/water_tray.png`

| ID  | Tile Name        | Notes    |
|-----|------------------|----------|
| 285 | Water tray left  | Obstacle |
| 286 | Water tray right | Obstacle |

---

### Mailbox

**File:** `Objects/mailbox.png`

| ID  | Tile Name      | Notes              |
|-----|----------------|--------------------|
| 287 | Mailbox up     | Animated           |
| 288 | Mailbox bottom | Animated, obstacle |

---

### Work Station

**File:** `Objects/work_station.png`

| ID  | Tile Name                 | Notes    |
|-----|---------------------------|----------|
| 289 | Work station left-up      |          |
| 290 | Work station left-bottom  | Obstacle |
| 291 | Work station right-up     |          |
| 292 | Work station right-bottom | Obstacle |

---

### Water Well

**File:** `Objects/water_well.png`

| ID  | Tile Name               | Notes    |
|-----|-------------------------|----------|
| 293 | Water well left-up      |          |
| 294 | Water well left-bottom  | Obstacle |
| 295 | Water well right-up     |          |
| 296 | Water well right-bottom | Obstacle |

---

### Piknik Blanket

**File:** `Objects/piknik_blanket.png`

| ID  | Tile Name        | Notes    |
|-----|------------------|----------|
| 297 | Piknik blanket 1 |          |
| 298 | Piknik blanket 2 |          |
| 299 | Piknik blanket 3 |          |
| 300 | Piknik blanket 4 |          |
| 301 | Piknik blanket 5 |          |
| 302 | Piknik blanket 6 |          |
| 303 | Piknik blanket 7 |          |
| 304 | Piknik blanket 8 |          |
| 305 | Piknik blanket 9 |          |

---

### Piknik Basket

**File:** `Objects/piknik_basket.png`

| ID  | Tile Name     | Notes    |
|-----|---------------|----------|
| 306 | Piknik basket |          |

---

### Furniture

**File:** `BuildingParts/basic_furniture.png`

| ID  | Tile Name                    | Notes    |
|-----|------------------------------|----------|
| 307 | Painting                     |          |
| 308 | Pot flower                   | Obstacle |
| 309 | Bed top                      | Obstacle |
| 310 | Pink bed bottom              | Obstacle |
| 311 | Blue bed bottom              | Obstacle |
| 312 | Green bed bottom             | Obstacle |
| 313 | Green upside down bed top    | Obstacle |
| 314 | Green upside down bed bottom | Obstacle |
| 315 | Chair left                   | Obstacle |
| 316 | Chair down                   | Obstacle |
| 317 | Dresser                      | Obstacle |
| 318 | Table                        | Obstacle |
| 319 | Pink big carpet left         |          |
| 320 | Pink big carpet right        |          |
| 321 | Blue big carpet left         |          |
| 322 | Blue big carpet right        |          |
| 323 | Green big carpet left        |          |
| 324 | Green big carpet right       |          |
| 325 | Pink small carpet            |          |
| 326 | Blue small carpet            |          |
| 327 | Green small carpet           |          |

---

### Sign

**File:** `BuildingParts/sign.png`

| ID  | Tile Name | Notes                         |
|-----|-----------|-------------------------------|
| 328 | Sign      | Obstacle, changing appearance |
