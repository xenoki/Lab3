package adapter;
import exceptions.AutoException;
import util.FileIO;
import model.Automotive;

/** Lab 2
* This class inherit and override ProxyAutomotive abstract method and implement 
* the ReadTextFile interface to build an automotive object from a given text 
* file
*/
public class AutomotiveBuilder extends ProxyAutomotive implements FileInput 
{
    // Constructors --------------------------------------------------------------------------------

    /**
    * Default AutomotiveBuilder Constructor 
    */
    public AutomotiveBuilder()
    {
        super();
    }
    
    // Getters/Setters -----------------------------------------------------------------------------
 
    public Automotive getAuto() 
    {
        return auto;
    }

    public Automotive setAuto(Automotive auto) 
    {
        this.auto = auto;
        return auto;
    }
    
    // Methods ---------------------------------------------------------------------------------------
    @Override
    /**
    * Build an automotive object from a given text file 
    */
    public void readFile(String fileName) 
    {
        buildAutomotive(fileName);
    }

    @Override
    /**
    * Using the Automotive object to print the data of it's properties. 
    */
    public void print()
    {  
        try {
            auto.print();

        } catch (NullPointerException e) {
           System.out.println(e);
        }
    }
    
    @Override
    /**
    * Modify the name and base price of the Automotive Object 
    */
    public void modifyModelNameAndPrice(String newModelName, int newBasePrice) 
    {
        auto.modifyModelNameAndPrice(newModelName, newBasePrice);
    }

    @Override
    /**
    * Modify the name of the option set 
    */
    public void modifyOptionSetName(String optSetName, String newName) 
    {
        auto.modifyOptionSetName(optSetName, newName);

    }

    @Override
    /**
    * Modify the name and price of the option in a given option set 
    */
    public void  modifyOptionName(String optSetName, String optName, String newName, int newPrice) 
    {
        auto.modifyOptionName(optSetName, optName, newName, newPrice);
    }

    @Override
    public void buildAutomotive(String fileName) 
    {
        try 
        {
            auto = new FileIO(fileName).buildAutoFromFile();

        } catch (AutoException e) 
        {
            System.out.println(e);
            auto = e.fixUnableToFindFile(); // Return a automotive object with default value
        }   
    }
}
