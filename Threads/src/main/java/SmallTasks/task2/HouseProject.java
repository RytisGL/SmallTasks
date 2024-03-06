package SmallTasks.task2;

import java.time.LocalDate;

public class HouseProject implements Paint{
    private LocalDate yearBuilt;
    private String energyClass;
    private String address;

    public HouseProject(LocalDate yearBuilt, String energyClass, String address) {
        this.yearBuilt = yearBuilt;
        this.energyClass = energyClass;
        this.address = address;
    }

    @Override
    public void paint() {
        System.out.println("House is painted");
    }

    public LocalDate getYearBuilt() {
        return yearBuilt;
    }

    public String getEnergyClass() {
        return energyClass;
    }

    public String getAddress() {
        return address;
    }
}
