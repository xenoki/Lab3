package exceptions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



import model.Automotive;

public class AutoException extends Exception
{
    // Constants -----------------------------------------------------------------------------------
    
    private static final long serialVersionUID = -8239592865109816171L;
    public static final int NO_MODEL_NAME = 1;
    public static final int NO_BASE_PRICE = 2;
    public static final int NO_OPTIONSET_NAME = 3;
    public static final int NO_OPTION_NUM = 4;
    
    // Properties ----------------------------------------------------------------------------------

    private int errorno;
    private String message;
    
    // Constructors --------------------------------------------------------------------------------

    public AutoException() 
    { 
        
    }

    public AutoException(String msg) 
    {
        super(msg);
    }
    
    public AutoException(String msg, int errorno) 
    {
        this.message = msg;
        this.errorno = errorno;
        writeexceptiontoFile();
    }
    
    
    // Getters/Setters -----------------------------------------------------------------------------

    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public void setErrorNO(int i)
    {
        this.errorno = i;
    }
    
    public int getErrorNo()
    {
        return errorno;
    }
   
   
    // Methods ---------------------------------------------------------------------------------------
   public void addDefaultModelName(Automotive model) 
   {
       System.out.println("Temporary Fix: Adding Model Name as DEFAULT MODEL");
       model.setModel("Default Model");
   }
   
   public void addDefaultBasePrice(Automotive model) 
   { 
       System.out.println("Temporary Fix: Adding Default Base Price of 0");
       model.setBasePrice(0);
   }
   
   public void addDefaultOptionSetName(Automotive model) 
   { 
       System.out.println("Temporary Fix: Adding Default Option Set name as DEFAULT OPTION SET");
       model.addOptionSet("Default Option Set", 1);
       
   }
   
   public void fixOptionSet(Automotive model)
   {
       System.out.println("Temporary Fix: Adding ");
   }
   
   public void fixNumberOfOption(Automotive model)
   {
       System.out.println("Tempory Fix: Setting Option Set with 1 default option");
       
   }
   
   public Automotive fixUnableToFindFile()
   {
       System.out.println("Building a empty automotive object");
       Automotive model = new Automotive();
       model.setModel("Default Name");
       model.setBasePrice(0);
       model.addOptionSet("Default", 1);
       return model;
   }
   
   void writeexceptiontoFile() 
   { 
       try
       {
           String data = this.message;
           File file =new File("autoFileExceptions.txt");

           if(!file.exists())
           {
               file.createNewFile();
           }
           
           FileWriter fileWritter = new FileWriter(file.getName(),true); //true = append file
           BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
           bufferWritter.write(data);
           bufferWritter.close();
       }
       catch(IOException e)
       {
           e.printStackTrace();
       }
   }
}








