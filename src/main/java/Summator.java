import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Summator {

    private final int arrayLength;

    public Summator(int arrayLength) {
        this.arrayLength = arrayLength;
    }

    public List<String[]> resultList(List<String[]> parsedList) {

        List<List<String[]>> devidedArrays = devideByObjectsCountParsedList(parsedList);

        List<String[]> resultList = new ArrayList<>(devidedArrays.get(devidedArrays.size() - 1));

        for (int i = 1; i <= devidedArrays.size() / 2; i++) {
            List<String[]> nextList = devidedArrays.get(i);

            for (int j = i; j < devidedArrays.size() - i; j++) {

                List<List<String[]>> subResult = sumOfDiffObjectCountLists(nextList, devidedArrays.get(j));

                resultList.addAll(subResult.get(0));

                if (!subResult.get(1).isEmpty()) {
                    List<List<String[]>> devidedSubArrays = devideByObjectsCountParsedList(subResult.get(1));
                    resultList.addAll(subResultList(devidedSubArrays, devidedArrays));
                }

            }
        }

        return resultList.stream().map(StringArray::new).distinct().map(StringArray::getArray).collect(Collectors.toList());
    }

    //разделение по количетству не null объектов в массиве
    private List<List<String[]>> devideByObjectsCountParsedList(List<String[]> parsedList) {

        List<List<String[]>> devidedArrays = new ArrayList<>();

        for (int i = 0; i <= arrayLength; i++) {
            devidedArrays.add(new ArrayList<>());
        }

        for (String[] next : parsedList) {
            int countedNull = countNotNull(next);

            devidedArrays.get(countedNull).add(next);
        }

        return devidedArrays;

    }

    private List<String[]> subResultList(List<List<String[]>> devidedNewArrays, List<List<String[]>> devidedOriginalArrays) {

        List<String[]> resultList = new ArrayList<>();

        for (int i = 1; i < devidedNewArrays.size(); i++) {
            List<String[]> nextSubList = devidedNewArrays.get(i);

            for (int j = 1; j < devidedOriginalArrays.size() - i; j++) {

                List<List<String[]>> subResult = sumOfDiffObjectCountLists(nextSubList, devidedOriginalArrays.get(j));

                resultList.addAll(subResult.get(0));

                if (!subResult.get(1).isEmpty()) {
                    List<List<String[]>> devidedSubArrays = devideByObjectsCountParsedList(subResult.get(1));
                    resultList.addAll(subResultList(devidedSubArrays, devidedOriginalArrays));
                }

            }
        }

        return resultList;
    }

    private List<List<String[]>> sumOfDiffObjectCountLists(List<String[]> list1, List<String[]> list2) {

        // в 0 - полные массивы, в 1 - неполные массивы
        List<List<String[]>> sumArrays = new ArrayList<>();
        sumArrays.add(new ArrayList<>());
        sumArrays.add(new ArrayList<>());

        for (String[] next1 : list1) {
            for (String[] next2 : list2) {
                String[] sum = sumArrays(next1, next2);

                if (sum.length == 0) {
                    continue;
                }

                if (countNotNull(sum) == arrayLength) {
                    sumArrays.get(0).add(sum);
                } else {
                    sumArrays.get(1).add(sum);
                }
            }
        }

        return sumArrays;

    }

    private String[] sumArrays(String[] arr1, String[] arr2) {

        if (Arrays.equals(arr1, arr2)) {
            return new String[0];
        }

        String[] sum = new String[arr1.length];

        for (int i = 0; i < arr1.length; i++) {
            String fromArr1 = arr1[i];
            String fromArr2 = arr2[i];

            if (fromArr1 != null && fromArr2 != null) {
                return new String[0];
            }

            if (fromArr1 != null) {
                sum[i] = fromArr1;
            } else if (fromArr2 != null) {
                sum[i] = fromArr2;
            }
        }

        return sum;

    }

    private int countNotNull(String[] array) {
        return (int) Stream.of(array).filter(Objects::nonNull).count();
    }
}
