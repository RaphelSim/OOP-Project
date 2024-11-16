package Common;

/**
 * The {@code DatabaseItems} interface defines the methods that 
 * any database item should implement. This includes serialization,
 * deserialization, and printing item details.
 */
public interface DatabaseItems {
    
    /**
     * Serializes the database item into a string representation.
     *
     * @return a string that represents the serialized form of the database item
     */
    public String serialise();

    /**
     * Deserializes the database item from a set of parameters.
     *
     * @param params an array of strings containing the data needed to reconstruct
     *               the database item. The exact format and number of parameters
     *               depend on the specific implementation of this interface.
     */
    public void deserialise(String ...params);

    /**
     * Prints the details of the database item to standard output.
     * This method is expected to provide a human-readable representation
     * of the item's attributes.
     */
    public void printItem();
}