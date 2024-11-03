package DatabaseItems;

import Common.DatabaseItems;

public class InventoryRequest implements DatabaseItems {
    private String medicine;
    private int request_value;

    public InventoryRequest(String medicine, int request_value) {
        this.medicine = medicine;
        this.request_value = request_value;
    }

    //call the deserialisation method
    public InventoryRequest(String ...params){
        deserialise(params);
    }

    //interface functions
    public void deserialise(String ...params){
        // medicine,request_value
        this.medicine = params[0];
        this.request_value = Integer.parseInt(params[1]);
    }

    public String serialise(){
        return String.format("%s,%s\n",
        this.medicine,
        this.request_value
        );
    }

    public void printItem(){
        System.out.println("Medicine: " + this.medicine);
        System.out.println("Request Value: " + this.request_value);
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
