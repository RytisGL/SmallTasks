package extratask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Solution {
    public static int solution(int[] a) {
        int check = 1;
        HashSet<Integer> removeDup = new HashSet<>();
        List<Integer> sort = new ArrayList<>();
        for (int num: a) {
            removeDup.add(num);
        }
        sort.addAll(removeDup);
        Collections.sort(sort);
        for (int i = 0; i < sort.size(); i++) {
            if (sort.get(i) >= 1 && sort.get(i) != check) {
                return check;
            }
            if (sort.get(i) < 1) {
                check = 1;
            } else {
                check++;
            }
        }
        return check;
    }
}
