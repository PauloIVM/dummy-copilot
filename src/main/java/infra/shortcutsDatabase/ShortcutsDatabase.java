package infra.shortcutsDatabase;

import adapters.interfaces.IShortcutData;
import adapters.interfaces.IShortcutDataAction;
import adapters.interfaces.IShortcutsDatabase;

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
 
    public IShortcutData[] get() {
        ShortcutData[] shortcutsData = this.getShortcutsJsonFile();
        return shortcutsData;
    }

    public Boolean update(IShortcutData[] s) {
        ShortcutData[] shortcutsData = new ShortcutData[s.length];
        shortcutsData = (ShortcutData[]) s;
        String jsonFileName = "shortcuts.config.json";
        try {
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

    public IShortcutData createShortcutData(String trigger, IShortcutDataAction[] actions) {
        return new ShortcutData(trigger, (ShortcutDataAction[]) actions);
    }

    public IShortcutData[] createShortcutDataArray(Integer size) {
        return new ShortcutData[size];
    }

    public IShortcutDataAction createShortcutDataAction(String type, String content) {
        return new ShortcutDataAction(type, content);
    }

    public IShortcutDataAction createShortcutDataAction(String type, Integer repeat, String keys) {
        return new ShortcutDataAction(type, repeat, keys);
    }

    public IShortcutDataAction[] createShortcutDataActionArray(Integer size) {
        return new ShortcutDataAction[size];
    }

    // TODO: Dependendo de como eu defino a implementação das interfaces e dos casts,
    // o gson pode dar uma error aqui, pois ele não aceita serializar uma interface.
    // Criar testes unitários que garantam que nenhuma interface foi passada de forma
    // errada.
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
