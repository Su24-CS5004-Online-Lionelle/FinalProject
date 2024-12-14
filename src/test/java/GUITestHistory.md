# Testing History

## Below are the happy paths

- **GUI loads:** Make sure GUI loads with a search bar, a list of all pokemons, and 4 buttons (`Add to Team`, `Type`, `Export Team` and `List View`).
![GUILoads](GUITestImages/GUILoads.png)

- **Clicking a Pokemon:** Make sure that clicking a pokemon in the list view prompts the left side of the screen to showcase the basic information of the pokemon, such as its name, image, id, weight, height, etc.
![ClickingAPokemon_1](GUITestImages/IndivdualPokemonPanel_1.png)
![ClickingAPokemon_2](GUITestImages/IndivdualPokemonPanel_2.png)

- **Clicking Moves:** After clicking Moves in the Pokemon panel, another window will pop up with a table of moves. The moves can be filtered in ascending or descending order based on ASCII values for text or by the lowest/highest number.
![ClickingMoves_1](GUITestImages/PokemonMoves_1.png)
![ClickingMoves_2](GUITestImages/PokemonMoves_2.png)
For example when `Power` is clicked, the moves are filtered in descending order.
![ClickingMoves_3](GUITestImages/PokemonMoves_3.png)

- **Search by `name` or `id`:** Ensure that search bar on top allows users to search pokemons by their name or by their id number.
![SearchByNameOrID_1](GUITestImages/SearchNameID_1.png)
By name:
![SearchByNameOrID_2](GUITestImages/SearchNameID_2.png)
By number:
![SearchByNameOrID_3](GUITestImages/SearchNameID_3.png)

- **Filter by `Type`:** Make sure that pokemons can be filtered and sorted by `types`, such as bug, electric, fire, grass, etc.
![FilterByType_1](GUITestImages/TypeFilter_1.png)
Filtering by one type - fire:
![FilterByType_2](GUITestImages/TypeFilter_2.png)
Filtering by multiple types - fire and flying:
![FilterByType_3](GUITestImages/TypeFilter_3.png)

- **Clicking `Export Team`:** After clicking `Export Team`, an export popup shows options to enter file name, choose a folder, select file format, and buttons to create a new folder, cancel, or save. Make sure each button works as intended and file is properly saved and can be found.
![ClickingExportTeam](GUITestImages/ExportTeam_1.png)
Creating a new folder:
![ClickingExportTeam](GUITestImages/ExportTeam_2.png)
Creating a file in the folder and saving it:
![ClickingExportTeam](GUITestImages/ExportTeam_3.png)
Popup alerts user that file has been saved:
![ClickingExportTeam](GUITestImages/ExportTeam_4.png)
Find the saved file in user computer:
![ClickingExportTeam](GUITestImages/ExportTeam_5.png)
Pokemon team information is saved as JSON:
![ClickingExportTeam](GUITestImages/ExportTeam_6.png)


## Below are the edge cases 
These tests make sure that the Pokemon searches and filtering handles the edge cases. These tests alphabet, numerical, and symbol names during search. To test filtering, it tests for selecting all the available types and selecting none of the available types.

![Pokemon Search with an empty space](GUITestImages/EdgeCase_SpaceSearch.png)

![Pokemon Search using symbols](GUITestImages/EdgeCase2_SymbolsSearch.png)

![Pokemon Search using high out of range value number](GUITestImages/EdgeCase3_highvaluenumberID.png)

![Pokemon Search using decimal number](GUITestImages/EdgeCase4_decimalnumberID.png)

![Pokemon Types Filter - Selecting all](GUITestImages/EdgeCase5_SelectAllTypes.png)

![Pokemon Types Filter - Selecting none](GUITestImages/EdgeCase6_SelectNoTypes.png)

![Pokemon Search with non-existent pokemon name](GUITestImages/EdgeCase7_NonExistentPokemon.png)
