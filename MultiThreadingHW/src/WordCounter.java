import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class WordCounter {
    public static final Path FOLDER_OF_TEXT_FILES  = Paths.get("C:\\Users\\Mark\\Documents\\216Hw\\216MultiThreadHW\\MultiThreadingHW\\textfiles"); // path to the folder where input text files are located
    public static final Path WORD_COUNT_TABLE_FILE = Paths.get("C:\\Users\\Mark\\Documents\\216Hw\\216MultiThreadHW\\MultiThreadingHW"); // path to the output plain-text (.txt) file
    public static final int  NUMBER_OF_THREADS     = 5;                // max. number of threads to spawn


    public File[] getTextFiles(){
        return new File(FOLDER_OF_TEXT_FILES.toString()).listFiles();
    }

    public HashMap<String, Integer> readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        HashMap<String, Integer> count = new HashMap<>();
        String fileText = reader.readLine();
        String s = fileText.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        String[] words = s.split("\\s+");
        for (String word : words) {
            if (!count.containsKey(word)) {
                count.put(word, 1);
            } else {
                count.replace(word, count.get(word) + 1);
            }
        }
        reader.close();
        return count;
    }

    public HashMap<String, Integer> getTotal(List<FileInstance> instances){
        HashMap<String, Integer> total = new HashMap<>();
        for (FileInstance instance : instances) {
            for (int j = 0; j < instance.getMap().size(); j++) {
                String key = (String) instance.getMap()
                                              .keySet()
                                              .toArray()[j];
                int value = instance.getMap()
                                    .get(key);
                total.merge(key, value, Integer::sum);
            }
        }
        return total;
    }

    public static void main(String[] args){
        ExecutorService pool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        WordCounter counter = new WordCounter();
        File[] files =  counter.getTextFiles();
        ArrayList<Future<FileInstance>> futures = new ArrayList<>();

        for(File f : files) {
            futures.add(pool.submit(() -> new FileInstance(f, counter.readFile(f))));
        }

        List<FileInstance> instances = new ArrayList<>();
        futures.forEach(fileInstanceFuture -> {
            try {
                instances.add(fileInstanceFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        pool.shutdown();

        HashMap<String, Integer> total = counter.getTotal(instances);       //Total hashmap created
        List<String> sorted = total.keySet().stream().sorted().collect(Collectors.toList());

        int max = 0;
        for(int i = 0; i < sorted.size(); i++){
            if(max < sorted.get(i).length()){
                max = sorted.get(i).length();
            }
        }
        max += 1;

        try{
           FileWriter output = new FileWriter(WORD_COUNT_TABLE_FILE + "\\output.txt");
           String firstLine = String.format("%" + max + "s", "");
           for(int i = 0; i < instances.size(); i++){
               firstLine += String.format("%s    ", instances.get(i).getFile().getName().substring(0, instances.get(i).getFile().getName().length() - 4).toLowerCase());
           }
           firstLine += "total\n";
           output.write(firstLine);

           String line = "";
           for(int i = 0; i < sorted.size(); i++){
               int textSpace = max - sorted.get(i).length();
               line += sorted.get(i) + String.format("%" + textSpace + "s", " ");
               for(int j = 0; j < instances.size(); j++){
                   int numSpace = instances.get(j).getFile().getName().length() - String.valueOf(instances.get(j).getMap().getOrDefault(sorted.get(i), 0)).length();
                   line+= instances.get(j).getMap().getOrDefault(sorted.get(i), 0) + String.format("%" + numSpace + "s", " ");
               }
               line += total.get(sorted.get(i)) + "\n";
           }
           output.write(line);
           output.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
