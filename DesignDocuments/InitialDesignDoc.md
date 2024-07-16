
## UML
```mermaid
        classDiagram
        class Item {
            -id: int
            -title: String
            -thumbnail: String
            -short_description: String
            -game_url: String
            -genre: String
            -platform: String
            -publisher: String
            -developer: String
            -release_date: String
            -freetogame_profile_url: String
        }

        class ItemModel {
            -items: List~Item~
            +getItems(): List~Item~
            +addItem(item: Item): void
            +removeItem(item: Item): void
            +saveList(fileFormat: String, filePath: String): void
            +loadList(filePath: String): void
        }

        class ItemSearchModel {
            +searchItems(keyword: String): List~Item~
            +sortItems(criteria: String): List~Item~
            +filterItems(criteria: String): List~Item~
        }

        class ResultsPanel {
            +displayItems(items: List~Item~): void
            +showError(message: String): void
        }

        class SearchFilterSortPanel {
            +getSearchKeyword(): String
            +getSortCriteria(): String
            +getFilterCriteria(): String
            +showError(message: String): void
        }

        class ImportExportPanel {
            +getImportFilePath(): String
            +getExportFilePath(): String
            +showError(message: String): void
        }

        class ItemDetailPanel {
            +displayItemDetails(item: Item): void
            +getItemInput(): Item
            +showError(message: String): void
        }

        class ItemController {
            -itemModel: ItemModel
            -searchModel: ItemSearchModel
            -resultsPanel: ResultsPanel
            -searchFilterSortPanel: SearchFilterSortPanel
            -importExportPanel: ImportExportPanel
            -itemDetailPanel: ItemDetailPanel
            +ItemController(itemModel: ItemModel, searchModel: ItemSearchModel, resultsPanel: ResultsPanel, searchFilterSortPanel: SearchFilterSortPanel, importExportPanel: ImportExportPanel, itemDetailPanel: ItemDetailPanel)
            +viewItems(): void
            +buildList(selectedItems: List~Item~): void
            +saveList(fileFormat: String, filePath: String): void
            +loadList(filePath: String): void
            +searchItems(): void
            +sortItems(): void
            +filterItems(): void
            +showItemDetails(item: Item): void
            +importList(): void
            +exportList(): void
            +updateItemDetails(): void
        }

        ItemController --> ItemModel
        ItemController --> ItemSearchModel
        ItemController --> ResultsPanel
        ItemController --> SearchFilterSortPanel
        ItemController --> ImportExportPanel
        ItemController --> ItemDetailPanel
        ItemModel --> Item
    ```
    ``````
    ### Function