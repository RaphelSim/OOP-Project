package Common;

import java.util.ArrayList;

public abstract class Database {
    protected String csvPath;

    public void setcsvPath(String path) {
        this.csvPath = path;
    }

    abstract public void abstractFromCSV();

    abstract public void storeToCSV();

    public void addRecord(DatabaseItems item) {
        records.add(item);
    };

    protected ArrayList<DatabaseItems> records = new ArrayList<>();
}
