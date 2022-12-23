import java.util.List;
import java.util.stream.Collectors;

public class Sorter {

    public static List<String[]> sortList(List<String[]> list) {
        return list.stream().sorted((o1, o2) -> {
            int result = 0;
            int i = 0;

            while (result == 0 && i < o1.length) {
                result = o1[i].compareTo(o2[i]);
                i++;
            }
            return result;
        }).collect(Collectors.toList());
    }
}
