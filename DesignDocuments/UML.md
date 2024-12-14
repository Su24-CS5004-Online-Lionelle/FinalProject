# UML

``` mermaid

classDiagram

    EventController --> PokedexView : uses
    EventController --> PokedexController : generates
    EventController --> PokemonListPanel : uses
    EventController --> IndivPokemonPanel : uses
    EventController --> PokedexPanel : uses

    PokedexController --> PokemonModel : uses

    PokemonTeam --> PokemonModel : uses

    PokemonModel --> GUIUtil : uses
    PokemonModel --> NetUtils : uses

    ListItem --> PokedexController : generates
    ListItem --> IndivPokemonPanel : uses
    ListItem --> PokemonListPanel : uses
    ListItem --> PokedexPanel : uses

    IndivPokemonPanel --> PokedexController : generates
    IndivPokemonPanel --> GUIUtil : uses

    PokedexPanel --> PokedexController : generates
    PokedexPanel --> CheckableComboBox : generates
    
    PokedexView --> PokedexPanel : generates
    PokedexView --> IndivPokemonPanel : generates
    PokedexView --> PokemonListPanel : generates
    PokedexView --> PokemonTeamPanel : generates
    PokedexView --> PokedexController : Generates
    PokedexView --> IPokedexView : implements

    PokemonListPanel --> PokedexController : generates
    PokemonListPanel --> GridBackground : generates
    PokemonListPanel --> ListItem : generates

    PokemonTeamPanel --> PokedexController : generates
    PokemonTeamPanel --> ListItem : generates
    PokemonTeamPanel --> GridBackground : generates

    PokemonApp --> PokedexView : generates
    PokemonApp --> EventController : generates

    ImageCache --> PokedexController : generates

    CheckableComboBox --> CheckableComboBoxRenderer : uses

    PokeRecord --> PokemonMove : uses
    PokeRecord --> PokemonType : uses
    PokeRecord --> Sprites : 

    PokemonMove --> Move : 
    
    Move --> MoveDetails : uses
    Move --> NetUtilsMoveDetails : uses
    
    PokemonType --> Type : uses


    Sprites --> PokemonSprites : uses


class EventController {
        - PokedexView pokedexView
        - PokedexController controller
        - PokemonListPanel listPanel
        - List~PokeRecord~ currRecords
        + EventController(PokedexView view)
        + addMouseListeners() void
        + handlePokemonClick(PokeRecord pokemonName) void
        + actionPerformed(ActionEvent e) void
        + handleExportTeam() void
        + itemStateChanged(ItemEvent e) void
        + keyTyped(KeyEvent e) void
        + keyPressed(KeyEvent e) void
        + keyReleased(KeyEvent e) void
    }

class PokedexController {
        - PokemonModel model
        + PokedexController()
        + getAllPokemon() List~PokeRecord~
        + filterByID(int id) List~PokeRecord~
        + filterByName(String name) List~PokeRecord~
        + filterByContains(String input) List~PokeRecord~
        + filterByWeight(double minWeight, double maxWeight) List~PokeRecord~
        + filterByHeight(double minHeight, double maxHeight) List~PokeRecord~
        + filterByTypes(List~String~ types) List~PokeRecord~
        + sortByName(boolean ascending) List~PokeRecord~
        + sortByID(boolean ascending) List~PokeRecord~
        + sortByWeight(boolean ascending) List~PokeRecord~
        + sortByType(boolean ascending) List~PokeRecord~
        + getAllPokemonInTeam() List~PokeRecord~
        + isPokemonInTeam(PokeRecord pokemon) boolean
        + addPokemonToTeam(PokeRecord pokemon)
        + removePokemonFromTeam(PokeRecord pokemon)
        + getCryByName(String name) String
        + ExportTeamToFile(String filePath) void
    }

class PokemonTeam {
        - PokemonModel model
        + PokemonTeam()
        + displayAllPokemon() void
        + displayTeam() void
        + addPokemonToTeam(String name) void
        + removePokemonFromTeam(String name) void
    }

class Move {
        - String name
        - String url
        - MoveDetails details
        + Move(String name, String url, MoveDetails details)
        + String getName()
        + void setName(String name)
        + String getUrl()
        + void setUrl(String url)
        + MoveDetails getMoveDetails()
        + void setMoveDetails()
        - MoveDetails fetchMoveDetails() throws Exception, IOException
    }

class PokemonMove {
        -Move move
        +PokemonMove()
        +PokemonMove(Move move)
        +Move getMove()
        +String toString()
    }

class MoveDetails {
        - int accuracy
        - MoveDamageClass damageClass
        - int power
        - int pp
        + MoveDetails()
        + MoveDetails(int, MoveDamageClass, int, int)
        + int getAccuracy()
        + void setAccuracy(int)
        + MoveDamageClass getDamageClass()
        + void setDamageClass(MoveDamageClass)
        + int getPower()
        + void setPower(int)
        + int getPp()
        + void setPp(int)
        + String toString()
    }

class NetUtilsMoveDetails {
        + NetUtilsMoveDetails()
        + static InputStream getUrlContents(String urlStr)
        + static InputStream getMoveDetails(String url)
    }

class PokemonMove {
        - Move move
        + PokemonMove()
        + PokemonMove(Move move)
        + Move getMove()
        + String toString()
    }

class PokemonSprites {
        - Sprites sprites
        + PokemonSprites(Sprites sprites)
        + Sprites getSprites()
        + void setSprites(Sprites sprites)
    }

class Sprites {
        - String backDefault
        - String frontDefault
        + Sprites(String backDefault, String frontDefault)
        + String getBackDefault()
        + void setBackDefault(String backDefault)
        + String getFrontDefault()
        + void setFrontDefault(String frontDefault)
        + String toString()
    }

class PokemonType {
        - Type type
        + PokemonType()
        + PokemonType(Type type)
        + String toString()
    }

class Type {
        - String typeName
        + Type(String typeName)
        + String getTypeName()
    }

class NetUtils {
        <<final>>
        - static final String API_URL_FORMAT
        + NetUtils()
        + static String getApiUrl(String name)
        + static String getApiUrl(int id)
        + static InputStream getUrlContents(String urlStr)
        + static InputStream getIpDetails(String name)
        + static InputStream getIpDetails(int id)
    }

class PokemonModel {
        <<singleton>>
        - static PokemonModel instance
        - static final String DATABASE_FILE
        - static final String DATABASE_TEAM_FILE
        - static ObjectMapper mapper
        - PokemonModel()
        + static PokemonModel getInstance()
        + PokeRecord getRecordFromAPI(String name) throws Exception, UnknownHostException
        + String getCryFromAPI(String name) throws Exception, UnknownHostException
        + PokeRecord getRecordFromAPI(int id) throws Exception, UnknownHostException
        - PokeRecord getRecordHelper(InputStream ipDetailStream) throws Exception, UnknownHostException
        + void populateDatabase(int num1, int num2)
        + void saveRecord(PokeRecord newRecord) throws Exception
        + PokeRecord getPokemonByID(int id) throws IOException
        + PokeRecord getPokemonByName(String pokemonName) throws IOException
        + List<PokeRecord> getAllPokemon() throws IOException
        + boolean isPokemonInTeam(PokeRecord pokemon)
        + void addPokemonToTeam(PokeRecord newRecord)
        + List<PokeRecord> getAllPokemonInTeam() throws IOException
        + void removePokemonFromTeam(PokeRecord pokemon)
        + PokeRecord getPokemonFromTeamByID(int id) throws IOException
        + PokeRecord getPokemonFromTeamByName(String name) throws IOException
        + void writeJsonData(List<PokeRecord> list, String outputFile)
    }

class PokeRecord {
        <<record>>
        + String name
        + int id
        + int order
        + int height
        + int weight
        + List<PokemonMove> moves
        + List<PokemonType> types
        + Sprites sprites
    }

class GridBackground {
        <<class>>
        + GridBackground()
        + void paintComponent(Graphics g)
    }

class ListItem {
        <<class>>
        - static List<ListItem> panels
        - boolean isHighlighted
        - PokeRecord currPokemon
        - PokedexController controller
        - Image backgroundImage
        - boolean isInTeam
        + ListItem(PokeRecord pokemon)
        + Dimension getPreferredSize()
        + void paintComponent(Graphics g)
        + void highlightPanel()
        + void unhighlight()
        + void setCurrPokemon(PokeRecord pokemon)
        + PokeRecord getCurrPokemon()
        + boolean isInTeam()
        - void drawPokeball(Graphics g)
    }

class CheckableComboBox {
        <<class>>
        - final List<String> checkedItems
        - final String placeholder
        - Font pokemonFont
        + CheckableComboBox(String[] items, String placeholder)
        + String[] getItems()
        + List<String> getCheckedItems()
    }

class CheckableComboBoxRenderer {
        <<inner class>>
        - JCheckBox checkBox
        + CheckableComboBoxRenderer()
        + Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus)
    }

class IndivPokemonPanel {
        - PokedexController controller
        - PokeRecord record
        - static IndivPokemonPanel instance
        - JPanel createPanel(String text, float fontSize)
        - JPanel createImagePanel()
        - void playSound()
        - JPanel createInfoPanel()
        - String getTypeFromList()
        - JScrollPane getMoveFromList()
        - void refreshPanel()
        - void initializeComponents()
        - PokeRecord getRecord()
        - void setRecord(PokeRecord record)
        + static synchronized IndivPokemonPanel getInstance()
        + void setRecord(PokeRecord record)
    }

class IPokedexView {
        + void addMouseListenerToListItems(MouseAdapter mouseAdapter)
        + void setListeners(ActionListener clicks)
        + void setItemListeners(ItemListener clicks)
        + void setKeyListeners(KeyListener press)
        + List<String> getTypes()
        + String getSearchbarText()
    }

class PokedexPanel {
        - static PokedexPanel instance
        - JTextField searchbar
        - JLabel placeholderLabel
        - CheckableComboBox typeSelect
        - JButton saveButton
        - JToggleButton addToggleButton
        - JToggleButton viewToggleButton
        - PokedexController controller
        + PokedexPanel()
        + static PokedexPanel getInstance() : PokedexPanel
        + void initializePanel()
        + void setFonts(Font font)
        + JButton getSaveButton() : JButton
        + JToggleButton getAddToggleButton() : JToggleButton
        + void refreshAddToggleButton()
        + JToggleButton getViewToggleButton() : JToggleButton
        + CheckableComboBox getCheckableComboBox() : CheckableComboBox
        + JTextField getSearchbar() : JTextField
        + List<String> getTypes() : List<String>
        + String getSearchbarText() : String
        + void paintComponent(Graphics g)
    }

class PokedexView {
        -PokedexPanel pokedexPanel
        -IndivPokemonPanel indivPokemonPanel
        -PokemonListPanel pokemonListPanel
        -PokemonTeamPanel pokemonTeamPanel
        -Font pokemonFont
        -PokedexController controller
        +PokedexView() throws IOException
        +void viewToggleButtonListener()
        +void addMouseListenerToListItems(MouseAdapter mouseAdapter)
        +void setListeners(ActionListener clicks)
        +void setItemListeners(ItemListener clicks)
        +void setKeyListeners(KeyListener press)
        +List<String> getTypes()
        +String getSearchbarText()
        +static Font getPokemonFont()
    }

class PokemonListPanel {
        -static PokemonListPanel instance
        -PokedexController controller
        -List<ListItem> customRectList
        -JPanel listPanel
        -PokeRecord highlightedPokemon
        -List<PokeRecord> currRecords
        -PokemonListPanel()
        +static synchronized PokemonListPanel getInstance()
        +void addMouseListenerToListItems(MouseAdapter mouseAdapter)
        +void refreshPanel(List<PokeRecord> records)
        +void initializePanel(List<PokeRecord> records)
        +void setIsHighlited(PokeRecord pokemon)
        +PokeRecord getIsHighlited()
        +List<ListItem> getItemList()
        +List<PokeRecord> getCurrRecords()
    }

class PokemonTeamPanel {
        -static PokemonTeamPanel instance
        -PokedexController controller
        -List<PokeRecord> pokemonTeam
        -List<ListItem> customRectTeam
        -JPanel teamPanel
        -PokemonTeamPanel()
        +static PokemonTeamPanel getInstance() throws IOException
        +void initializePanel() throws IOException
        +void refreshPanel() throws IOException
        +void addMouseListenerToListItems(MouseAdapter mouseAdapter)
    }

class GUIUtil {
        +static void showMessage(String content, String title)
    }

class ImageCache {
        -static final String CACHE_DIR
        -static final HashMap<String, String> cache
        +static String getCachedImage(String imageUrl, String pokemonName) throws IOException
        +static void main(String[] args)
    }

class PokemonApp {
        +static void main(String[] args) throws IOException
    }


```