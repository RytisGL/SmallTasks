package Task5;

public class SuperPair<K, V, N extends Number> {
    K key;
    V value;
    N number;

    public SuperPair(K key, V value, N number) {
        this.key = key;
        this.value = value;
        this.number = number;
    }
}
