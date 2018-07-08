package za.co.simonmohoalali.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CountryLookUpUtil {

    public static String getFile(String fileName) {
        return getFileHelper(fileName).toString();
    }

    public static Map<String, String> createHashMapFromFileEntry(String fileName) throws FileNotFoundException {
        Map<String, String> map = new HashMap<>();
        try (Scanner scanner = new Scanner(getFileHelper(fileName))){
            String ipEntries[];
            while (scanner.hasNextLine()) {
                ipEntries = scanner.nextLine().split(",");
                map.put(ipEntries[0], ipEntries[1]);
            }
        }
        return map;
    }

    public static Map<String, String> sortHashMapByValue(Map<String, String> mapToSort) {
        Map<String, String> sortedMap =
                mapToSort.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (m1, m2) -> m1, LinkedHashMap::new));
        return sortedMap;
    }

    private static File getFileHelper(String fileName) {
        CountryLookUpUtil util = new CountryLookUpUtil();
        File file = new File(util.getClassLoader().getResource(fileName).getFile());
        return file;
    }

    private ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }
}
