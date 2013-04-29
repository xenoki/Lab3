package model;

import java.io.Serializable;
import java.util.ArrayList;
/**
* This class create a option set object and provide the functionality to add
* in option to it's array list of options 
*/
public class OptionSet implements Serializable 
{
    // Constants --------------------------------------------------------------------------------

    private static final long serialVersionUID = 8694479852974342642L;
    
    // Properties -------------------------------------------------------------------------------

    private String _name;
    private ArrayList<Option> _options; // Contain all the options in an option set
    private Option choice;
    
    // Constructors ------------------------------------------------------------------------------

    public OptionSet() 
    {
        _options = new ArrayList<OptionSet.Option>();
    }
    
    public OptionSet(String name) 
    {
        _name = name;
        _options = new ArrayList<OptionSet.Option>();
    }
    
    public OptionSet(String name, int count) 
    {
        _name = name;
        _options = new ArrayList<OptionSet.Option>(count);
    }
    
    // Constructors ------------------------------------------------------------------------------

    public String getName() 
    {
        return _name;
    }
    
    public void setName(String name)
    {
        _name = name;
    }
    
    public ArrayList<Option> getOptions() 
    {
        return _options;
    }
    
    public void setOptions(ArrayList<Option> options)
    {
        _options = options;
    }
    
    public void setOption(int i, String name, int price) 
    {
        //_options.set(i, new Option(name, price));
        _options.add(i,  new Option(name, price));
    }
    
    public void setOption(String name, int price)
    {
        _options.add(new Option(name, price));
    }
    
    // Methods ---------------------------------------------------------------------------------------

    /**
    * Given the name of an option, would save that choice inside the option set. 
    * @param optionName
    */
    public void setOptionChoice(String optionName)
    {
        int found = findOption(optionName);
        if(found != -1)
        {
            choice = _options.get(found);
        }
    }
    
    /**
    * This method would return the option chosen, if any, otherwise it should return null 
    * @return
    */
    public OptionSet.Option getOptionChoice()
    {
        return choice;
    }
    
    
    /**
    * A a new option to this option set array list of optoins 
    * @param name the name of the option
    * @param price the price of the option
    */
    public void addNewOption(String name, int price) 
    {
        _options.add(new Option(name, price));
    }
    
    /**
    * Find the option in the array list based on it's name 
    * @param name the name of the option
    * @return the location of the option found
    */
    private int findOption(String name) 
    {
        boolean found = false;
        int i = 0;
        while(!found && i < _options.size())
        {
            if(_options.get(i).getName().equalsIgnoreCase(name))
            {
                found = true;
            }
            else 
            {
                i++;
            }
        }
        return(found) ? i : -1;
    }
    
    /**
    * Print all the options in the Option set array list of option
    */
    public void printOptionSet() 
    {
        System.out.printf("%s Option:\n", _name);
        for(int i = 0; i < _options.size(); i++)
        {
            System.out.printf("\t%s: %d\n", _options.get(i)._name, _options.get(i)._price);
        }
    }
    
    /** 
    * Returns the String representation of OptionSet for debugging and 
    * logging purposes.
    * @see java.lang.Object#toString()
    */
    public String toString() 
    {
        return String.format("OptionSet[ _name = %s, _options = %s ]", _name, _options);
    }
    
    // CRUD Methods =====================================================
    
    /**
    * Create and return a new option object 
    * @param name the name of the option
    * @param price the price of the option
    * @return
    */
    public Option createOption(String name, int price)
    {
        return new Option(name, price);
    }
    
    /**
    * Add new option in the option set 
    * @param name the name of the option
    * @param price the price of the option
    */
    public Option getOption(String name) 
    {
        int index;
        if((index = findOption(name)) != -1) 
        {
            return _options.get(index);
        }
        else
        {
            return null;
        }
    }
    
    /**
    * Find the option and return it's price
    * @param name the name of the option
    * @return the price of the option
    */
    public int getOptionPrice(String name) 
    {
        int index;
        if((index = findOption(name)) != -1) 
        {
            return _options.get(index).getPrice();
        }
        else 
        {
            return -1;
        }
    }
    
    /**
    * Modify the option base on the specify location in the arraylist
    * @param i the location to rename
    * @param newName the name of the option
    * @param newPrice the price of the option
    */
    public void renameOpt(int i, String newName, int newPrice) 
    {
        try
        {
            _options.set(i, new Option(newName, newPrice));
        } 
        catch (Exception e)
        {
            System.out.printf("Error: %s", e);
        }
    }
    
    /**
    * Delete the option base on the specified index location. 
    * @param i
    */
    public void deleteOpt(int i) 
    {
        try
        {
            _options.remove(i);
        } 
        catch (Exception e)
        {
            System.out.printf("Error: %s", e);
        }
    }
    
    /**
    * Delete the option based on the name 
    * @param name
    */
    public void deleteOption(String name)
    {
        
        try
        {
            _options.remove(findOption(name));
        } 
        catch (Exception e)
        {
            System.out.printf("Error: %s", e);
        }
    }
    
    /**
    * This class create a option object to store the name and price of the option
    */
    public class Option implements Serializable 
    {
        // Constants ========================================================

        private static final long serialVersionUID = 6169084247187832308L;
        
        // Properties =======================================================

        private String _name;
        private int _price;
        
        // Constructors =====================================================

        public Option()
        {
            
        }
        
        public Option(String name)
        {
            _name = name;
        }
        
        public Option(int price)
        {
            _price = price;
        }
        
        public Option(String name, int price)
        {
            _name = name;
            _price = price;
        }
        
        // Getters/Setters ==================================================

        public String getName() 
        {
            return _name;
        }
        
        public void setName(String name) 
        {
            _name = name;
        }
        
        public int getPrice() 
        {
            return _price;
        }
        
        public void setPrice(int price) 
        {
            _price = price;
        }
        
       
        // Methods ==========================================================

        /** 
        * Returns the String representation of Option for debugging and 
        * logging purposes.
        * @see java.lang.Object#toString()
        */
        public String toString() 
        {
            return String.format("Option[ _name = %s, _price = %d ]", _name, _price);
        }
        
        /**
        * Print the name and price of the option 
        */
        public void printOption() 
        {
            System.out.printf("%s: %d\n", _name, _price);
        }
        
        // CRUD Methods =====================================================
        
        /**
        * Create and return a option object
        * @param name the name of the option
        * @param price the priceo f the option
        * @return
        */
        public Option create(String name, int price) 
        {
            return new Option(name, price);
        }
        
        /**
        * Return this option object
        * @return the option object
        */
        public Option read() 
        {
            return this;
        }
        
        /**
        * Update the name and price of the optin object
        * @param name the name of the option
        * @param price the price of the option
        */
        public void update (String name, int price) 
        {
            this.setName(name);
            this.setPrice(price);
        }
        
        /**
        * Set the value price to 0 and name to null
        */
        public void delete() 
        {
            this.setName(null);
            this.setPrice(0);
        }
    }
    
}
