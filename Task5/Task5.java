package Task5;

import java.util.*;

public class Task5 {
    public static void main(String[] args) {
        SuperPair<String, String, Integer> superPair = new SuperPair<>("String", "String", 123);
//        SuperPair<String, String, Integer> superPair1 = new SuperPair<>("String", "String", "String");
        int[] ex =  {1,3,6,4,1,2};
        System.out.println(solution(ex));
    }
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
