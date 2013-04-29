package adapter;
/**
* @author hquach
* API for FileInput
* Enforce a contract between classes for methods
* Enable Polymorphism 
* Define API
* 
* Class can implement multiply interfaces
* Neither Interface and Abstract Class can not be instantiated
*/
public interface FileInput 
{   
    public void readFile(String fileName);
    public void print();
    public void modifyModelNameAndPrice(String newModelName, int newBasePrice);
    public void modifyOptionSetName(String optSetName, String newName);
    public void modifyOptionName(String optSetName, String optName, String newName, int newPrice);
}
