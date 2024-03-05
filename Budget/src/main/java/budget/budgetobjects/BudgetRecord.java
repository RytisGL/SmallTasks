package budget.budgetobjects;

import com.fasterxml.jackson.annotation.*;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(Expense.class),
        @JsonSubTypes.Type(Income.class)}
)
public abstract class BudgetRecord {
    private int amount;
    private int id;
    private String additionalInfo;
    private LocalDate date;

    BudgetRecord() {
    }

    BudgetRecord(int amount, String additionalInfo) {
        this.amount = amount;
        this.additionalInfo = additionalInfo;
        this.date = LocalDate.now();
        this.id = Budget.id;
        Budget.id++;
    }

    //Used for loading from csv only, doesn't need id++.
    BudgetRecord(int amount, int id, LocalDate date, String additionalInfo) {
        this.amount = amount;
        this.id = id;
        this.date = date;
        this.additionalInfo = additionalInfo;
    }

    public int getAmount() {
        return amount;
    }

    public abstract String getStringForCsvFile();

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
}
