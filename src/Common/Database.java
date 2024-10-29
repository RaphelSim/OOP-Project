package Common;

import java.util.ArrayList;

public abstract class Database {
    protected String csvPath;

    public void setcsvPath(String path) {
        this.csvPath = path;
    }

    abstract public void extractFromCSV();

    abstract public void storeToCSV();

    abstract public void printItems();

    public void addItem(DatabaseItems item) {
        records.add(item);
    };

    public boolean removeItem() {
        System.out.println("Please indicate the item to remove");
        return false;
    }

    protected ArrayList<DatabaseItems> records = new ArrayList<DatabaseItems>();
}
