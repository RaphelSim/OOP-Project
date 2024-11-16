package DatabaseItems;

import Common.DatabaseItems;

/**
 * The {@code InventoryRequest} class represents a request for inventory items.
 * It implements the {@link DatabaseItems} interface and provides methods for 
 * serialization, deserialization, and displaying request details.
 */
public class InventoryRequest implements DatabaseItems {
    private String medicine; // Name of the requested medicine
    private int request_value; // Quantity requested

    /**
     * Constructs an {@code InventoryRequest} instance with specified medicine name and request value.
     *
     * @param medicine      the name of the requested medicine
     * @param request_value the quantity of the requested medicine
     */
    public InventoryRequest(String medicine, int request_value) {
        this.medicine = medicine;
        this.request_value = request_value;
    }

    /**
     * Constructs an {@code InventoryRequest} instance by deserializing from an array of parameters.
     *
     * @param params an array of strings containing serialized data for the inventory request
     */
    public InventoryRequest(String... params) {
        deserialise(params); // Call the deserialization method
    }

    /**
     * Deserializes parameters into an {@code InventoryRequest} object.
     *
     * @param params an array of strings containing serialized data for the inventory request
     */
    public void deserialise(String... params) {
        // medicine, request_value
        this.medicine = params[0]; // Set medicine name from params
        this.request_value = Integer.parseInt(params[1]); // Set request value from params
    }

    /**
     * Serializes the {@code InventoryRequest} object into a CSV format string.
     *
     * @return a string representing the serialized data of the inventory request
     */
    public String serialise() {
        return String.format("%s,%s\n",
                this.medicine,
                this.request_value); // Format as CSV
    }

    /**
     * Prints details of the inventory request to the console.
     */
    public void printItem() {
        System.out.println(); // Print a new line for better readability
        System.out.println("Medicine: " + this.medicine);
        System.out.println("Request Value: " + this.request_value);
    }

    /**
     * Returns the name of the requested medicine.
     *
     * @return the name of the medicine
     */
    public String getMedicine() {
        return medicine;
    }

    /**
     * Returns the quantity requested for the medicine.
     *
     * @return the requested quantity
     */
    public int getRequestValue() {
        return request_value;
    }

    /**
     * Sets a new name for the requested medicine.
     *
     * @param medicine the new name of the requested medicine
     */
    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    /**
     * Sets a new request value for the quantity of requested medicine.
     *
     * @param request_value the new quantity to set
     */
    public void setRequestValue(int request_value) {
        this.request_value = request_value;
    }
}