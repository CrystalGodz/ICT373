package ict373_a2_clarencelim_35008001;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MagazineService{
    private Map<String, Magazine> magazineMap;
    private AlertGUI alert = new AlertGUI();
    
    public MagazineService(){
        this.magazineMap = new HashMap<>();
    }
    
    
    //create a new magazine service
    public void addService(Magazine magazine){
        this.magazineMap.put(magazine.getMagazineName(), magazine);
    }
    
    
    //retrieves mapped magazine
    public Magazine getMagazineService(String magazineName){
        return this.magazineMap.get(magazineName);
    }
    
    
    //compare magazine by name
    public boolean compareMagazineName(String magazineName){
        return this.magazineMap.containsKey(magazineName);
    }
    
    
    //get arraylist of magazine
    public ArrayList<String> getMagazineName(){
        return new ArrayList<>(this.magazineMap.keySet());
    }
    
    
    //loads magazine from .ser file
    public void loadMagazineFromFile(String magazineName){
        try(FileInputStream inputFile = new FileInputStream(magazineName + ".ser"); ObjectInputStream objectIn = new ObjectInputStream(inputFile)){
            this.magazineMap.put(magazineName, (Magazine) objectIn.readObject());
            this.alert.displayAlert(magazineName + " was loaded successfully");

        }
        catch(IOException | ClassNotFoundException ex){
            this.alert.displayAlert("Error, unable to load selected file");
        }
    }
    
    
    //saves magazine to a '.ser' file
    public void saveMagazine(String magazineName){
        try{
            File file = new File(magazineName + ".ser");
            //check if file already exists
            if(file.exists()){
                //overrides the existing file
                if(!file.delete()){
                    this.alert.displayAlert("Failed to override existing file.");
                }
            }
            try(FileOutputStream outputFile = new FileOutputStream(file);
                    ObjectOutputStream output = new ObjectOutputStream(outputFile)){
                output.writeObject(this.magazineMap.get(magazineName));
                this.alert.displayAlert(magazineName + " has been saved to file 'ICT373_A2_ClarenceLim_35008001'.");

            }
        }
        catch(IOException ex){
            this.alert.displayAlert("Error, File not saved.");
        }
    }
}
