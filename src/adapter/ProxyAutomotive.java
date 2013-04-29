package adapter;
import model.Automotive;

/** 
* Abstract class ProxyAutomtive, this class will provide the abstract method that will 
* be exported as API
*/
public abstract class ProxyAutomotive 
{	
    protected Automotive auto;
    
    // Abstract methods -----------------------------------------------------------------------------
    public abstract void buildAutomotive(String fileName);
    public abstract void modifyModelNameAndPrice(String newModelName, int newBasePrice);
    public abstract void modifyOptionSetName(String optSetName, String newName);
    public abstract void modifyOptionName(String optSetName, String optName, String newName, int newPrice);
}
