package infra.shortcutsDatabase;
import java.util.ArrayList;

import adapters.interfaces.IShortcutsDatabase;
import adapters.shortcutDataAdapter.ShortcutData;
import adapters.shortcutDataAdapter.ShortcutsDataAdapter;
import entities.shortcut.Shortcut;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ShortcutsDatabase implements IShortcutsDatabase {

    public ShortcutsDatabase() {}

    // TODO: Seria legal fazer com que esse cara não conhecesse a entidade Shortcut;
    // Daí eu poderia criar um model no adapters q faz isso, e integra o DB. 
    public ArrayList<Shortcut> get() {
        ShortcutData[] shortcutsData = this.getShortcutsJsonFile();
        ArrayList<Shortcut> shortcuts = ShortcutsDataAdapter.toShortcuts(shortcutsData);
        return shortcuts;
    }

    public Boolean update(ArrayList<Shortcut> shortcuts) {
        String jsonFileName = "shortcuts.config.json";
        try {
            ShortcutData[] shortcutsData = ShortcutsDataAdapter.toShortcutsData(shortcuts);
            GsonBuilder builder = new GsonBuilder();
            Gson gsonWriter = builder.setPrettyPrinting().create();
            String jsonString = gsonWriter.toJson(shortcutsData);
            FileWriter writer = new FileWriter(jsonFileName);
            writer.write(jsonString.replaceAll("\\\\u0027", "\'"));
            writer.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private ShortcutData[] getShortcutsJsonFile() {
        String jsonFileName = "shortcuts.config.json";
        try {
            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader(jsonFileName));
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            ShortcutData[] shortcuts = new ShortcutData[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                ShortcutData shortcut = gson.fromJson(jsonObject, ShortcutData.class);
                shortcuts[i] = shortcut;
            }
            reader.close();
            return shortcuts;
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            System.err.println("There was a problem to read shortcuts.config.json file. Check invalid atributes.");
            System.err.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }
}
