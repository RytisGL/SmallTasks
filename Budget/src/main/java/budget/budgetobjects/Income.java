package budget.budgetobjects;


import java.time.LocalDate;

public class Income extends BudgetRecord {
    private boolean isMoneyReceived;

    public Income() {
    }

    public Income(int amount, String additionalInfo, boolean isMoneyReceived) {
        super(amount, additionalInfo);
        this.isMoneyReceived = isMoneyReceived;
    }

    public Income(int amount, int id, LocalDate date, boolean isMoneyReceived, String additionalInfo) {
        super(amount, id, date, additionalInfo);
        this.isMoneyReceived = isMoneyReceived;
    }

    public void setMoneyReceived(boolean moneyReceived) {
        this.isMoneyReceived = moneyReceived;
    }

    public boolean isMoneyReceived() {
        return isMoneyReceived;
    }

    @Override
    public String getStringForCsvFile() {
        return String.valueOf(super.getAmount()) + ';' + super.getId() + ';' + super.getDate() + ';' + this.isMoneyReceived + ';' + super.getAdditionalInfo() + ';';
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + super.getId() +
                ", amount=" + super.getAmount() +
                ", date=" + super.getDate() +
                ", moneyReceivedConfirmation=" + this.isMoneyReceived +
                ", additionalInfo='" + super.getAdditionalInfo() + '\'' +
                '}';
    }
}
