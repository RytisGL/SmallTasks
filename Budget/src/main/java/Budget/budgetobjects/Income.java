package Budget.budgetobjects;


import com.fasterxml.jackson.annotation.JsonProperty;
import Budget.exceptions.WrongInputException;

import java.time.LocalDate;
import java.util.Scanner;

public class Income extends BudgetRecord {
    @JsonProperty("isMoneyReceived")
    private boolean isMoneyReceived;
    public Income() {
    }

    public Income(Scanner scanner) {
        super(scanner);
        System.out.println("Type Y if money has been received: ");
        this.isMoneyReceived = scanner.nextLine().equalsIgnoreCase("y");
    }
    public Income(int amount, int id, LocalDate date, boolean isMoneyReceived ,String additionalInfo) {
        super(amount, id, date, additionalInfo);
        this.isMoneyReceived = isMoneyReceived;
    }

    public void editMoneyReceived(Scanner scanner) throws WrongInputException {
        System.out.println("Type F for false, T for true");
        String input = scanner.nextLine().toUpperCase();
        switch (input) {
            case "F" -> this.setMoneyReceived(false);
            case "T" -> this.setMoneyReceived(true);
            case null, default -> throw new WrongInputException("Wrong input");
        }
    }

    public void setMoneyReceived(boolean moneyReceived) {
        this.isMoneyReceived = moneyReceived;
    }

    public boolean isMoneyReceived() {
        return isMoneyReceived;
    }

    @Override
    public String getStingForFile() {
        return String.valueOf(super.getAmount()) + ';' + super.getId() + ';' + super.getDate() + ';' + this.isMoneyReceived + ';' + super.getAdditionalInfo() + ';';
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + super.getId() +
                ", amount=" + super.getAmount() +
                ", date=" + super.getDate() +
                ", moneyReceivedConfirmation=" + isMoneyReceived +
                ", additionalInfo='" + super.getAdditionalInfo() + '\'' +
                '}';
    }
}
