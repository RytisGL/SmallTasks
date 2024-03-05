package budget.budgetobjects;

import java.time.LocalDate;

public class Expense extends BudgetRecord {
    private String cardUsed;

    public Expense() {
    }

    public Expense(int amount, String additionalInfo, String cardUsed) {
        super(amount, additionalInfo);
        System.out.println("Enter card number for the expense: ");
        this.cardUsed = cardUsed;
    }

    public Expense(int amount, int id, LocalDate date, String cardUsed, String additionalInfo) {
        super(amount, id, date, additionalInfo);
        this.cardUsed = cardUsed;
    }

    public void setCardUsed(String cardUsed) {
        this.cardUsed = cardUsed;
    }

    public String getCardUsed() {
        return cardUsed;
    }

    @Override
    public String getStringForCsvFile() {
        return String.valueOf(super.getAmount()) + ';' + super.getId() + ';' + super.getDate() + ';' + this.cardUsed + ';' + super.getAdditionalInfo() + ';';
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + super.getId() +
                ", amount=" + super.getAmount() +
                ", date=" + super.getDate() +
                ", cardUsed='" + cardUsed + '\'' +
                ", additionalInfo='" + super.getAdditionalInfo() + '\'' +
                '}';
    }
}
