package DatabaseItems;

import Common.DatabaseItems;

/**
 * The {@code Medicines} class represents a medicine item in the inventory.
 * It implements the {@link DatabaseItems} interface and provides methods
 * for serialization, deserialization, and displaying medicine details.
 */
public class Medicines implements DatabaseItems {
    // medicine, stock, alert_level
    private String medicine; // Name of the medicine
    private int stock;       // Current stock level of the medicine
    private int alert_level; // Alert level for low stock

    /**
     * Constructs a {@code Medicines} instance with specified medicine name.
     *
     * @param medicine the name of the medicine
     */
    public Medicines(String medicine) {
        this.medicine = medicine;
        this.stock = 0; // Default stock level
        this.alert_level = 0; // Default alert level
    }

    /**
     * Constructs a {@code Medicines} instance with specified medicine name,
     * stock level, and alert level.
     *
     * @param medicine   the name of the medicine
     * @param stock      the initial stock level of the medicine
     * @param alert_level the alert level for low stock
     */
    public Medicines(String medicine, int stock, int alert_level) {
        this.medicine = medicine;
        this.stock = stock;
        this.alert_level = alert_level;
    }

    /**
     * Constructs a {@code Medicines} instance by deserializing from an array of parameters.
     *
     * @param params an array of strings containing serialized data for the medicine
     */
    public Medicines(String... params) {
        deserialise(params); // Call the deserialization method
    }

    /**
     * Deserializes parameters into a {@code Medicines} object.
     *
     * @param params an array of strings containing serialized data for the medicine
     */
    public void deserialise(String... params) {
        this.medicine = params[0]; // Set medicine name
        this.stock = Integer.parseInt(params[1]); // Set stock level
        this.alert_level = Integer.parseInt(params[2]); // Set alert level
    }

    /**
     * Serializes the {@code Medicines} object into a CSV format string.
     *
     * @return a string representing the serialized data of the medicine
     */
    public String serialise() {
        return String.format("%s,%s,%s\n",
                this.medicine,
                this.stock,
                this.alert_level); // Format as CSV
    }

    /**
     * Prints details of the medicine item to the console.
     */
    public void printItem() {
        System.out.println(); // Add a line break for readability
        System.out.println("Medicine: " + this.medicine);
        System.out.println("Stock: " + this.stock);
        System.out.println("Alert Level: " + this.alert_level);
        String status = this.alert_level < this.stock ? "Good" : "LOW STOCK ALERT"; // Determine status based on stock levels
        System.out.println("Status: " + status);
    }

    /**
     * Returns the name of the medicine.
     *
     * @return the name of the medicine
     */
    public String getMedicine() {
        return medicine;
    }

    /**
     * Returns the current stock level of the medicine.
     *
     * @return the current stock level
     */
    public int getStock() {
        return stock;
    }

    /**
     * Returns the alert level for low stock.
     *
     * @return the alert level for low stock
     */
    public int getAlertLevel() {
        return alert_level;
    }

    /**
     * Sets a new name for the medicine.
     *
     * @param medicine the new name of the medicine
     */
    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    /**
     * Sets a new stock level for the medicine.
     *
     * @param stock the new stock level of the medicine
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Sets a new alert level for low stock.
     *
     * @param alert_level the new alert level for low stock
     */
    public void setAlertLevel(int alert_level) {
        this.alert_level = alert_level;
    }
}