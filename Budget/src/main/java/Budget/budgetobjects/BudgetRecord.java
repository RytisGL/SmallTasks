package Budget.budgetobjects;

import com.fasterxml.jackson.annotation.*;
import Budget.exceptions.WrongInputException;
import Budget.utils.Util;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(Expense.class),
        @JsonSubTypes.Type(Income.class) }
)
public abstract class BudgetRecord {
    private int amount;
    private int id;
    private String additionalInfo;
    private LocalDate date;
    BudgetRecord() {
    }

    BudgetRecord(Scanner scanner) {
        System.out.println("Enter amount: ");
        this.amount = Util.stringToIntRecursive(scanner);
        System.out.println("Additional info: ");
        this.additionalInfo = scanner.nextLine();
        this.date = LocalDate.now();
        this.id = Budget.id;
        Budget.id++;
    }
    //Used for loading from csv only, doesn't need id++.
    BudgetRecord(int amount, int id, LocalDate date,String additionalInfo) {
        this.amount = amount;
        this.id = id;
        this.date = date;
        this.additionalInfo = additionalInfo;
    }

    public void editDate(Scanner scanner) throws WrongInputException {
        try {
            Util.printTypeNewValueMessage();
            this.setDate(LocalDate.parse(scanner.nextLine()));
        } catch (Exception e) {
            throw new WrongInputException("Wrong input");
        }
    }

    public void editAmount(Scanner scanner) throws WrongInputException {
        Util.printTypeNewValueMessage();
        this.setAmount(Util.stringToIntEx(scanner));
    }

    public void editAdditionalInfo(Scanner scanner) {
        Util.printTypeNewValueMessage();
        this.setAdditionalInfo(scanner.nextLine());
    }

    public int getAmount() {
        return amount;
    }

    public abstract String getStingForFile();

    public int getId() {
        return id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAdditionalInfo(String info) {
        this.additionalInfo = info;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BudgetRecord that = (BudgetRecord) object;
        return id == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
