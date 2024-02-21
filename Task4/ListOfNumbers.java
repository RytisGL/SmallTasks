package Task4;

import java.util.ArrayList;
import java.util.List;

public class ListOfNumbers {
    private List<Double> list = new ArrayList<>();

    public void add(Double number) {
        list.add(number);
    }

    public double getAverage() {
        double result = 0;
        for (int i = 0; i < list.size(); i++) {
            result += list.get(i);
            if ((i + 1) == list.size()) {
                result = result / i;
            }
        }
        return result;
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
        result.append("]}");
        return String.valueOf(result);
    }
}
