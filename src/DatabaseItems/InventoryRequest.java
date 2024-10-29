package DatabaseItems;

import Common.DatabaseItems;

public class InventoryRequest implements DatabaseItems {
    private String medicine;
    private int request_value;

    public InventoryRequest(String medicine, int request_value) {
        this.medicine = medicine;
        this.request_value = request_value;
    }

    public String getMedicine() {
        return medicine;
    }

    public int getRequestValue() {
        return request_value;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public void setRequestValue(int request_value) {
        this.request_value = request_value;
    }
}
