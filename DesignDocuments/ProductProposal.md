# Product Proposal

## Initial Design

Update UML here

### Generic UML

```mermaid
---
title: Initial Diagram
---
classDiagram
  note "Generic UML to get started"
  IView <|-- View
  IModel <|-- Model
  note for Model "Data logic, communicates with a local database"
  NetUtils
  note for NetUtils "Concerns with API calls if used"
  IController <|-- Controller
  Controller --> NetUtils : uses
  Controller --> Model : uses
  View --> Controller : uses
  note for View "Displays data, communicates with the Controller"
  class IModel {
    <<Interface>>

  }
```

```mermaid
---
title: Initial Diagram
---
classDiagram
  class IModel {
    <<Interface>>
    getPokemonID(String pokemonName) : int
    getPokemon(String pokemonName) : List<Pokemon>
    getAllPokemon() : List<Pokemon>
    filterByType(String type) : List<Pokemon>
  }
  class Pokemon {
    - id : int
    - name : String
    - type : String
    - weight : int
    - height : int
    - imgUrl : String
    - evolutions : List<Pokemon>
    - moves : List<String>
    + getID() : int
    + getName() : String
    + getType() : String
    + getWeight() : int
    + getHeight() : int
    + getImgUrl() : String
    + getEvolutions() : List<Pokemon>
    + getMoves() : List<String>
  }
```
## About
* What are you building?
PokeDex app.

* What are the initial features for the application?
Search for individual pokemon and list all pokemon.

* What are the *minimum* additional features you plan to implement?
Click on each pokemon in the list displayed, have an option to go to a view page for each individual pokemon.

* What are your stretch goals (features beyond the minimum)?
* Go over your initial design.

  * Special emphasis should be placed on how you plan to break it up

  * MVC, presenter, file management, different input validation, testing, documentation, etc.

* How do you plan to break up the work?

* What is your teams timeline and major check-in points?


### Professor's notes:
* Pick which feature you want to implement
* Create a branch for that feature/aspect of the project
* Work on that aspect.
* Do a pull request when done (while you were working in your branch)
* Have someone else go over your code to review and merge back into main
* Delete the branch
* Repeat for another feature/aspect as you grow the assignment
