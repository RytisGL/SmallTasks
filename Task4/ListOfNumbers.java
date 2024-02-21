package Task4;

import java.util.ArrayList;
import java.util.List;

public class ListOfNumbers {
    private List<Double> list = new ArrayList<>();

    public void add(Double number) {
        list.add(number);
    }

    public double getAverage() {
        int count = 0;
        double result = 0;
        for (double d : list) {
            result += d;
            count++;
        }
        return result / count;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("ListOfNumbers{numbers=[");
        for (int i = 0; i < list.size(); i++) {
            result.append(list.get(i));
            if ((i + 1) != list.size()) {
                result.append(", ");
            }
        }
        result.append("]");
        return String.valueOf(result);
    }
}
