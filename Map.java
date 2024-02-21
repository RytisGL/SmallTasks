import java.util.ArrayList;
import java.util.List;

public class Map<K, V> {
    private List<Pair<K, V>> pairList = new ArrayList<>();

    public void add(K key, V value) {
        Pair<K, V> pair = new Pair<>(key, value);
        this.pairList.add(pair);
    }

    public V get(Object key) {
        for (Pair<K, V> pair : pairList) {
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
    }
}
