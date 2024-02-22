package generics.task3;

public class Task3 {
    public static void main(String[] args) {
        Map<DnsProvider, DnsServer> map = new Map<>();
        map.add(DnsProvider.GOOGLE, new DnsServer());
        map.add(DnsProvider.NOTGOOGLE, new DnsServer());
        Map<String, String> map1 = new Map<>();
        map1.add("String", "String1");
        System.out.println(map.get(DnsProvider.GOOGLE));
        System.out.println(map.get(DnsProvider.NOTGOOGLE));
        System.out.println(map1.get("String"));
    }
}
