package DatabaseItems;

import Common.DatabaseItems;

public class Medicines implements DatabaseItems {
    // medicine,stock,alert_level
    private String medicine;
    private int stock;
    private int alert_level;

    public Medicines(String medicine) {
        this.medicine = medicine;
        this.stock = 0;
        this.alert_level = 0;
    }

    public Medicines(String medicine, int stock, int alert_level) {
        this.medicine = medicine;
        this.stock = stock;
        this.alert_level = alert_level;
    }

    public String getMedicine() {
        return medicine;
    }

    public int getStock() {
        return stock;
    }

    public int getAlertLevel() {
        return alert_level;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setAlertLevel(int alert_level) {
        this.alert_level = alert_level;
    }
}
