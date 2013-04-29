package driver;
import java.util.Iterator;

import model.Automotive;
import model.OptionSet;
import adapter.AutomotiveBuilder;
import adapter.FileInput;
/**
* Driver Class
*/
public class Main 
{
    public static void main(String[] args) 
    {
       // Lab 2 Driver Test 
       System.out.println("\nTesting out Lab 2 Interface");
       FileInput model = new AutomotiveBuilder();
       model.print(); // Test Print with NullPointerException.
       model.readFile("FordZTW.txt");
       model.print(); 
       
       // Testing modify methods
       model.modifyModelNameAndPrice("Honda Accord", 20000);
       model.modifyOptionSetName("Color", "Paint");
       model.modifyOptionName("Paint", "Fort Knox Gold Clearcoat Metallic", "Pink", 1000);
       model.print();
       
       
       model.readFile("BadFile.txt"); // Unable to find file exception
       model.modifyOptionSetName("Color", "Paint"); // Unable to find option set name exception
       model.modifyOptionName("Default", "Test", "Ddkd", 1000); // Unable to find option name exception
       
       
       // Lab 3 Driver Test
       System.out.println("\nTesting New Methods in Lab3 =====================================================");
       Automotive a1 = new Automotive();
       a1.setMake("Ford");
       a1.setModel("ZTW");
       System.out.println(a1.getName());
       a1.setBasePrice(20000);
       a1.addOptionSet("Color", 2);
       a1.addOptionSet("Tranmission", 2);
       a1.setOption("Tranmission", 0, "automatic", 1000);
       a1.setOption("Tranmission", 1, "manual", 0);
       a1.setOption("Color", 0, "Blue", 500);
       a1.setOption("Color", 1, "Red", 600);
       System.out.println(a1.getOptionSet("Color"));
       System.out.println(a1.getOption("Color", "Blue"));
       Iterator<OptionSet> test = a1.getOptionSetNamesIterator();
       while(test.hasNext())
       {
           test.next().printOptionSet();
       }
       
       a1.setOptionChoice("Color", "Blue");
       System.out.println(a1.getOptionChoice("Color"));
       System.out.println(a1.getOptionChoicePrice("Color"));
       a1.setOptionChoice("Tranmission", "automatic");
       System.out.println(a1.getOptionChoice("Tranmission"));
       System.out.println(a1.getOptionChoicePrice("Tranmission"));
       System.out.println(a1.getTotalPrice());
       
    }
}
