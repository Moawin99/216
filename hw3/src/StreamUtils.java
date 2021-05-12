import java.util.*;
import java.util.stream.Collectors;

public class StreamUtils {
    public static void main(String[] args) {
        List<Integer> s = Arrays.asList(5, 2, 7, 8, 2, 13);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Mark", 1);
        map.put("Sherzod", 2);
        map.put("Nik", 3);
        map.put("Alex", 4);
//        System.out.println(capitalized(s));
//        System.out.println(longest(s, true));
//        System.out.println(least(s, true));
//        List<String> list = flatten(map);
//        list.forEach(System.out::println);
    }

    public static Collection<String> capitalized(Collection<String> strings)  {
        return strings
                .stream()
                .filter(s -> Character.isUpperCase(s.charAt(0)))
                .collect(Collectors.toList());
    }

    public static String longest(Collection<String> strings, boolean from_start) {
        return
                strings
                .stream()
                .reduce((s1 ,s2) -> {
                    if(s1.length() > s2.length()){
                        return s1;
                    }
                    else if(s2.length() > s1.length()){
                        return s2;
                    }
                    else{
                        return from_start ? s1 : s2;
                    }
                })
                .get();
//                from_start ?
//                strings.stream()
//                       .max(Comparator.comparingInt(String::length))
//                       .get()
//                :
//                strings.stream()
//                       .filter(string -> string.length() == strings.stream()
//                                                                   .max(Comparator.comparingInt(String::length))
//                                                                   .get()
//                                                                   .length())
//                       .collect(Collectors.toList())
//                       .get(strings.stream()
//                                   .filter(string -> string.length() == strings.stream()
//                                                                               .max(Comparator.comparingInt(String::length))
//                                                                               .get()
//                                                                               .length())
//                                   .collect(Collectors.toList())
//                                   .size() - 1);
    }

    public static <T extends Comparable<T>> T least(Collection<T> items, boolean from_start){
        return items
                .stream()
                .reduce((item1, item2) -> {
                    if(item1.compareTo(item2) < 0){
                        return item1;
                    }
                    else if(item1.compareTo(item2) > 0){
                        return item2;
                    }
                    else{
                        return from_start ? item1 : item2;
                    }
                })
                .get();
    }

    public static <K, V> List<String> flatten(Map<K, V> aMap){
        return aMap.entrySet().stream().map(kvEntry -> kvEntry.getKey().toString() + " -> " + kvEntry.getValue().toString()).collect(Collectors.toList());
    }
}
