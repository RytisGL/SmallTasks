package generics.task4;

public class Task4 {
    public static void main(String[] args) {
        ListOfNumbers listObject1 = new ListOfNumbers();
        listObject1.add(2.2);
        listObject1.add(2.2);
        listObject1.add(2.2);
        listObject1.add(2.2);
        ListOfNumbers listObject2 = new ListOfNumbers();
        listObject2.add(3.2);
        listObject2.add(3.2);
        listObject2.add(3.2);
        listObject2.add(3.2);
        System.out.println(findWithMaxAverage(listObject1, listObject2));
    }
    public static ListOfNumbers findWithMaxAverage(ListOfNumbers... lists) {
        double highest = lists[0].getAverage();
        ListOfNumbers highestList = lists[0];
        for(int i = 1; i < lists.length; i++) {
            if (lists[i].getAverage() > highest) {
                highest = lists[i].getAverage();
                highestList = lists[i];
            }
        }
        return highestList;
    }
}
