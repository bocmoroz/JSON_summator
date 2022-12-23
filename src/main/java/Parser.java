import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class Parser {

    public static List<String[]> parse(String path) throws FileNotFoundException {
        Gson gson = new Gson();

        Type type = new TypeToken<List<String[]>>() {
        }.getType();

        return gson.fromJson(new FileReader(path), type);
    }

    public static boolean checkSameLength(List<String[]> parsedList) {
        int arrayLength = parsedList.get(0).length;
        return parsedList.stream().filter(a -> a.length == arrayLength).count() == parsedList.size();
    }
}
