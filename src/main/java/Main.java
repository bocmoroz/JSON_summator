import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            String path = "src/main/resources/file.json";
            //String path = "src/main/resources/file1.json";
            List<String[]> parsedList = Parser.parse(path);

            if (!Parser.checkSameLength(parsedList)) {
                System.out.println("Все массивы должны содержать одинаковое количество элементов!");
                return;
            }

            Summator summator = new Summator(parsedList.get(0).length);

            List<String[]> result = summator.resultList(parsedList);
            result.forEach(a -> System.out.println(Arrays.toString(a)));
            System.out.println("----------");

            List<String[]> sortedResult = Sorter.sortList(result);
            sortedResult.forEach(a -> System.out.println(Arrays.toString(a)));
            System.out.println("----------");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
