package Budget.budgetobjects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Scanner;

public class Expense extends BudgetRecord {
    @JsonProperty("cardUsed")
    private String cardUsed;
    public Expense() {
    }

    public Expense(Scanner scanner) {
        super(scanner);
        System.out.println("Enter card number for the expense: ");
        this.cardUsed = scanner.nextLine();
    }

    public Expense(int amount, int id, LocalDate date, String cardUsed, String additionalInfo) {
        super(amount, id, date, additionalInfo);
        this.cardUsed = cardUsed;
    }

    public void editCardUsed(Scanner scanner) {
        this.setCardUsed(scanner.nextLine());
    }

    public void setCardUsed(String cardUsed) {
        this.cardUsed = cardUsed;
    }

    public String getCardUsed() {
        return cardUsed;
    }

    @Override
    public String getStingForFile() {
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
