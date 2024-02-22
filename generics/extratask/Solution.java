package generics.extratask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Solution {
    public static int solution(int[] a) {
        int check = 1;
        HashSet<Integer> removeDup = new HashSet<>();
        for (int num: a) {
            removeDup.add(num);
        }
        List<Integer> sort = new ArrayList<>(removeDup);
        Collections.sort(sort);
        for (Integer integer : sort) {
            if (integer >= 1 && integer != check) {
                return check;
            }
            if (integer < 1) {
                check = 1;
            } else {
                check++;
            }
        }
        return check;
    }
}
