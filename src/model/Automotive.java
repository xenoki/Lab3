package model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
* Lab 3: CIS35B
* This class provide an Automotive object in which you can store, serialize, and print the model 
* name, base price, and the set of options for a car configuration.
*/
public class Automotive implements Serializable
{
    // Constants --------------------------------------------------------------------------------

    private static final long serialVersionUID = 4070527674933345109L;
    
    // Properties -------------------------------------------------------------------------------
    
    private int _basePrice;
    private String _make;
    private String _model;
    private LinkedHashMap<String, OptionSet> _optionSets;
    
    // Constructors ------------------------------------------------------------------------------
    
    /**
    * Default Automotive Constructor
    */
    public Automotive()
    {
        _optionSets = new LinkedHashMap<String, OptionSet>();
    }
    
    // Constructors ------------------------------------------------------------------------------
    
    public int getBasePrice()
    {
        return _basePrice;
    }
    
    public void setBasePrice(int price)
    {
        _basePrice = price;
    }
    
    public String getMake()
    {
        return _make;
    }
    
    public void setMake(String make)
    {
        _make = make;
    }
    
    public String getModel()
    {
        return _model;
    }
    
    public void setModel(String model)
    {
        _model = model;
    }
    
    public String getName()
    {
        return _make + " " + _model;
    }
    
    /**
     * Update the model name and price 
     */
     public void modifyModelNameAndPrice(String newModelName, int newBasePrice) 
     {
         _model = newModelName;
         _basePrice = newBasePrice;
     }
     
     /**
     * Give the option set to be modify and the new name of that option set.
     * Find that option set, and modify it with the new name
     */
     public void modifyOptionSetName(String optSetName, String newName) 
     {
         try 
         {
             matchOptionSet(optSetName).setName(newName);

         } catch (NullPointerException e) 
         {
             System.out.println("Unable to find Option Set Name: " + optSetName );
         }
     }
     
     /**
     * Given a option set Name, and a option name, update that option 
     */
     public void modifyOptionName(String optSetName, String optName, String newName, int newPrice) 
     {
         try 
         {
             matchOptionSet(optSetName).getOption(optName).update(newName, newPrice);
     
         } catch (NullPointerException e) 
         {
             System.out.println("Unable to find Option: " + optName );

         }
     }
    
    /**
    * Find the optionSet with a specified name in the array list and return that 
    * optionSet, return null if not found. 
    * @param optionSetName the name of the Option set to search for
    * @return the match optionSet object
    */
    public OptionSet matchOptionSet(String optionSetName) 
    {
        boolean found = false;
        int i = 0;
        while(!found && i < _optionSets.size())
         {
            if(_optionSets.get(i).getName().equalsIgnoreCase(optionSetName))
             {
                found = true;
            }
            else 
            {
                i++;
            }
        }
         
        return (found) ? _optionSets.get(i) : null;
    }
     
    /**
    * Return the option set based on the specified index 
    * @param i the location of the element
    * @return a option set object
    */
    public OptionSet matchOptionSet(int i)
    {
        try
        {
            return _optionSets.get(i);
        } 
        catch (IndexOutOfBoundsException e)
        {
            System.out.printf("Error: %s", e);
            return null;
        } 
    }
    
   
    
    /** 
    * Returns the String representation of Automotive for debugging and 
    * logging purposes.
    * @see java.lang.Object#toString()
    */
     public String toString()
     {
         return String.format("Automotive[ _make = %s,  _model = %s,  _basePrice = %d, _optionset = %s]",
                 _make, _model, _basePrice, _optionSets);
     }
     
    /**
    * Print the configuration data of the Automotive object 
    */
    public void print()
    {
        System.out.printf("Maker name: %s\n", _make);
        System.out.printf("Model name: %s\n", _model);
        System.out.printf("Base price: %d\n", _basePrice);
         
        Iterator<OptionSet> it = getOptionSetNamesIterator();
         
        while(it.hasNext())
        {    
            it.next().printOptionSet();
        }
    }
    
    /**
    * Create an option set to hold n number of option 
    * @param setName the name of the option set to be created
    * @param count   the number of options in the option set
    */
    public void addOptionSet(String setName, int count)
    {
         _optionSets.put(setName, new OptionSet(setName, count));
    }
     
    /**
    * Define the name and price of an option given a name of a option set 
    * @param setName    the name of the option set that and option will be define in
    * @param index      where the option to be store in the option set
    * @param optionName the name of the option
    * @param price      the price of the option
    */
    public void setOption(String setName, int index, String optionName, int price)
    {
        _optionSets.get(setName).setOption(index, optionName, price);
    }
    
    /**
    * Given a option set name in the Link Hashed Map, return the option set with containing option data 
    * @param setName the name of the option set to be return
    * @return the option set
    */
    public OptionSet getOptionSet(String setName)
    {
         return _optionSets.get(setName);
    }
    
    /**
    * Given option set name, and option, return that option object back 
    * @param setName    the name of the option set
    * @param optionName the name of the option
    * @return the option object
    */
    public OptionSet.Option getOption(String setName, String optionName)
    {
        return _optionSets.get(setName).getOption(optionName);
    }
    
    /**
    * Return a iterator for the link hashed map to process thru the element 
    * @return iterator object use to find element in Linked Hash Map
    */
    public Iterator<OptionSet> getOptionSetNamesIterator()
    {
       return _optionSets.values().iterator();
    }
    
    /**
    * This method is use for choosing a particular option in an option set
    * @param setName
    * @param optionName
    */
    public void setOptionChoice(String setName, String optionName)
    {
        _optionSets.get(setName).setOptionChoice(optionName);
    }
    
    /**
    * Given an option set name, return the option choice name that user selected. 
    * @param setName
    * @return
    */
    public String getOptionChoice(String setName)
    {
        return _optionSets.get(setName).getOptionChoice().getName();
    }
    
    /**
    * Given an option set name, return the option choice price that user selected. 
    * @param setName
    * @return
    */
    public int getOptionChoicePrice(String setName)
    {
        return _optionSets.get(setName).getOptionChoice().getPrice();
    }
    
    /**
    * Get the total price of the automotive configuration 
    * @return
    */
    public int getTotalPrice()
    {
        int totalPrice = _basePrice;
        Iterator<OptionSet> sets = _optionSets.values().iterator();
        while(sets.hasNext())
        {
            totalPrice += sets.next().getOptionChoice().getPrice();
        }
        return totalPrice;
    }
    
    
     
    
    /**
    * Driver to test out methods of Automotive class 
    * @param args
    */
    public static void main(String args[])
    {
        Automotive a1 = new Automotive();
        a1.setMake("Ford");
        a1.setModel("ZTW");
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
        System.out.println(a1);
        
        a1.setOptionChoice("Color", "Blue");
        System.out.println(a1.getOptionChoice("Color"));
        System.out.println(a1.getOptionChoicePrice("Color"));
        a1.setOptionChoice("Tranmission", "automatic");
        System.out.println(a1.getOptionChoice("Tranmission"));
        System.out.println(a1.getOptionChoicePrice("Tranmission"));
        System.out.println(a1.getTotalPrice());
       
    }
}    
    
    
    
