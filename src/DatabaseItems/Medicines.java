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

    // call the deserialisation method
    public Medicines(String... params) {
        deserialise(params);
    }

    // medicine,stock,alert_level
    // interface functions
    public void deserialise(String... params) {
        this.medicine = params[0];
        this.stock = Integer.parseInt(params[1]);
        this.alert_level = Integer.parseInt(params[2]);
    }

    public String serialise() {
        return String.format("%s,%s,%s\n",
                this.medicine,
                this.stock,
                this.alert_level);
    }

    public void printItem() {
        System.out.println(); // add a line break to improve readability
        System.out.println("Medicine: " + this.medicine);
        System.out.println("Stock: " + this.stock);
        System.out.println("Alert Level: " + this.alert_level);
        String status = this.alert_level < this.stock ? "Good" : "LOW STOCK ALERT";
        System.out.println("Status: " + status);
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
