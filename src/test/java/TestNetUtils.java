import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Model.IModel;
import Model.Net.NetUtils;
import org.junit.jupiter.api.Test;

class TestNetUtils {

  @Test
  void fetchSeasonAverages() {
  }

  @Test
  void fetchPlayers() {
    // Initialize List of players
    List<IModel.PlayerBackground> backgroundList= new ArrayList<>();

    try {
      backgroundList = NetUtils.fetchPlayers();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertNotEquals(null, backgroundList);

  }

  @Test
  void fetchAPlayers() {
    // Initialize List of players
    IModel.PlayerBackground backgroundList;

//    try {
//      backgroundList = NetUtils.getAPlayer("Stephen", "Curry");
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }

//    assertNotEquals(null, backgroundList);

  }

  @Test
  void TestFetchAverages() {
    // Initialize List of players
    List<IModel.PlayerAverages> averagesList= new ArrayList<>();

    try {
      averagesList = NetUtils.fetchSeasonAverages("19");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }


    assertNotEquals(null, averagesList);
  }
}