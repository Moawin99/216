import java.io.File;
import java.util.HashMap;

public class FileInstance implements Comparable<FileInstance> {
    private File file;
    private HashMap<String, Integer> map;

    public FileInstance(File file, HashMap<String, Integer> map){
        this.file = file;
        this.map = map;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public int compareTo(FileInstance o) {
        return this.file.getName().compareTo(o.file.getName());
    }

}
