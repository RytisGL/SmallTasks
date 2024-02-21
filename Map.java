import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Pair> pairList = new ArrayList<>();

    public <K, V> void add(K key, V value) {
        Pair<K, V> pair = new Pair(key, value);
        this.pairList.add(pair);
    }

    public <K> String get(K key) {
        for (Pair pair : pairList) {
            if (pair.getKey().equals(key)) {
                return pair.getValue().toString();
            }
        }
        return null;
    }
}
