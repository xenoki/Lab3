package util;

import model.Automotive;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import exceptions.AutoException;

/** Lab 2
* This class is use to read in a text file and build an automotive object 
* from the data contain in the file. It also provide provide the 
* functionality of serializing an object to file, and deSerializing an object 
* file from disk into memory. 
*/
public class FileIO
{
    // Constants -----------------------------------------------------------------------------------
    
    private final int MODEL_NAME = 0;
    private final int BASE_PRICE = 0;
    private final int OPTION_SET = 1;
    private final int NUM_OF_OPTIONS =2;
    private final int OPTION = 0;
    private final int NAME = 1;
    private final int PRICE = 1;
    private final int OPTION_NAME = 0;
    private final int OPTION_PRICE = 1;
    
    
    // Properties ----------------------------------------------------------------------------------
    
    private String fileName;
  
    // Constructors --------------------------------------------------------------------------------
    
    /**
    * Constructs a default FILEIO object 
    */
    public FileIO() { }
    
    /**
    * Constructs a FILEIO object and initialize the name of the file 
    * @param fileName the name and path of the file
    */
    public FileIO(String fileName)
    {
        this.fileName = fileName;
    }
    
    // Getters/Setters -----------------------------------------------------------------------------
    
    public String getfileName()
    {
        return fileName;
    }

    public void setfileName(String fileName)
    {
        this.fileName = fileName;
    }
    
    // Methods ---------------------------------------------------------------------------------------
    
    /**
    * Build an Automotive object by reading in a text file and using the Automotive object methods
    * to set in the value from the text file
    * @return
    */
    public Automotive buildAutoFromFile() throws AutoException
    {
        Automotive model;
        try 
        {
            FileReader file = new FileReader(fileName);
            BufferedReader buffer = new BufferedReader(file);
            model = new Automotive();
            boolean eof = false;
            while (!eof)
            {
                String line = buffer.readLine();
                if (line == null)
                {    
                   eof = true;
                }   
                else
                {   
                    build(model,  line.split(":"), buffer);
                }  
            }
            buffer.close();
        } 
        catch (Exception e) 
        {
            throw new AutoException("Unable to find " + fileName, 5);
        }
        return model;
    }
    
    public boolean readFile(FileReader file, BufferedReader buffer) throws AutoException
    {
        boolean flag = false;
        try 
        {
            file = new FileReader(fileName);
            buffer = new BufferedReader(file);
            flag = true;
        } 
        catch (FileNotFoundException e) 
        {
            throw new AutoException();
        }
        return flag;
    }
    
    /**
    * Read a line from the buffer and determine where to put into the Automotive object
    * @param model  the Automotive object to be build from file
    * @param data   a line in a text file to be process
    * @param buffer the buffer object use to read the line
    */
    private void build(Automotive model, String[] data, BufferedReader buffer)
    {
        if(data[MODEL_NAME].equalsIgnoreCase("Model Name"))
        {  
            try 
            {
                readModelName(model, data);
            } 
            catch (AutoException e) 
            {
                System.out.printf("%s",e);
                e.addDefaultModelName(model);
            } 
        }
        else if (data[BASE_PRICE].equalsIgnoreCase("Base Price"))
        {
            try 
            {
                readBasePrice(model, data);
            } 
            catch (AutoException e) 
            {
                System.out.printf("%s",e);
                e.addDefaultBasePrice(model);
            }
        }
        else if(data[OPTION].equalsIgnoreCase("Option"))
        {
            try 
            {
                readOptionSet(model, data, buffer);
            } 
            catch (AutoException e) 
            {
                System.out.printf("%s",e);
                e.addDefaultOptionSetName(model);
            }
        }
    }
    
    /**
    * Read the model name from a text configuration file
    * @param model The automotive object 
    * @param data  The buffer data
    * @throws AutoException
    */
    private void readModelName(Automotive model, String[] data) throws AutoException
    {
       try 
       {
           model.setModel(data[NAME]);
       } 
       catch (ArrayIndexOutOfBoundsException e) 
       {
            throw new AutoException("Unable to find model name in file\n", 
                    AutoException.NO_MODEL_NAME);
       }
    }
    
    /**
    * Read in the base price of the car model to build the automotive object
    * @param model
    * @param data
    * @throws AutoException
    */
    private void readBasePrice(Automotive model, String[] data) throws AutoException
    {
        try 
        {
            model.setBasePrice(Integer.parseInt(data[PRICE].trim()));
        } 
        catch (ArrayIndexOutOfBoundsException e) 
        {
            throw new AutoException("File does not contain a base price\n", 
                    AutoException.NO_BASE_PRICE);
        }
    }
    
    /**
    * Read in the options to build the automotive object
    * @param model
    * @param data
    * @param buffer
    * @throws AutoFileException
    */
    private void readOptionSet(Automotive model, String[] data, BufferedReader buffer) throws AutoException
    {
        int numberOfOption;
        try 
        {
            numberOfOption = Integer.parseInt(data[NUM_OF_OPTIONS].trim());
            model.addOptionSet(data[OPTION_SET], numberOfOption);

            for(int i = 0; i < numberOfOption; i++) 
            {   
                try 
                {   
                    String [] option = buffer.readLine().split(":");
                    model.setOption(data[OPTION_SET], 
                                    i, 
                                    option[OPTION_NAME].trim(),
                                    Integer.parseInt(option[OPTION_PRICE].trim()));
                } 
                catch (Exception e) 
                {
                    System.out.println("Error 101 -- " + e.toString());
                }
            }
        } 
        catch (ArrayIndexOutOfBoundsException e) 
        {
            throw new AutoException("File does not contain the number of option", 
                    AutoException.NO_OPTION_NUM);
        }
    }
    
    /**
    * Serialize automotive object to file 
    * @param model the automotive object to be serialize
    */
    public void serializeAutomotive(String fileName, Automotive model)
    {
        System.out.printf("Serializing Automotive Object..........\n");
        try
        {  
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(model);
            out.close();
        } 
        catch (Exception e)
        {
            System.out.print("Serialization Error: " + e);
            System.exit(1);
        }
    }
     
    /**
    * deSerialize automotive object file from disk into memory and return a new automotive
    * object 
    * @param fileName the name of the serialize object file
    * @return a deSerialize automotive object
    */
    public Automotive deserializeAutomotive(String fileName)
    {
        System.out.printf("DeSerializing Automotive Object........\n");
        try
        {
            ObjectInputStream input =  new ObjectInputStream(new FileInputStream(fileName));
            Automotive model = (Automotive) input.readObject();
            input.close();
            return model;
        } 
        catch (Exception e)
        {
            System.out.print("DeSerilization Error: " + e);
            return null;
        }
    }
}
