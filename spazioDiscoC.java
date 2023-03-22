import java.io.File;
import java.text.NumberFormat;


public class spazioDiscoC{
    //constant for the number of bytes
    private static final long BYTES_PER_GB = 1073741824;

    public static void main(String[] args) {
        //total disk space
        File file = new File("c:/");
        long spazioTotale = file.getTotalSpace();
        long spazioTotaleGB = spazioTotale / BYTES_PER_GB ;
        System.out.println("total space on the disk: " + spazioTotaleGB + " gb");

        //free disk space
        long spazioLibero = file.getFreeSpace();
        spazioLibero = spazioLibero / BYTES_PER_GB;
        System.out.println("free space on the disk: " + spazioLibero + " gb");

        //disk space used
        long spazioUtilizzato = spazioTotaleGB - spazioLibero;
        System.out.println("space used on the disk: " + spazioUtilizzato + " gb");

        //used disk space in percentage
        double percentuale = (double) spazioUtilizzato / spazioTotaleGB * 100;
        percentuale = Math.round(percentuale * 10) / 10;
        System.out.println("percentage of space used on the disk: " + percentuale + "%");

        //Get an array of files that represents all files and subdirectories in the root directory of the system
        File[] rootFiles = new File("c:/").listFiles();

        //Iterate on each element of the file array
        for (File f : rootFiles) {
            //If it is a directory, call the method checkDirectory() on the current directory
            if (f.isDirectory()) {
                checkDirectory(f, spazioTotaleGB);
            }
            //Otherwise, if it is a file, check if it has a size greater than 1 GB
            else if (f.isFile() && f.length() > BYTES_PER_GB) {
                //Save the directory address of the file in a string
                String filePath = f.getAbsolutePath();
                //format the whole and print the weight of the file in GB
                double fileSize = (double) f.length() / BYTES_PER_GB;
                NumberFormat nf = NumberFormat.getInstance();
                nf.setMaximumFractionDigits(2);
                String fileSizeString = nf.format(fileSize);
                //Calculate the percentage of space occupied by files  
                double percentualeOccupata = fileSize  / (double) spazioTotaleGB * 100;
                percentualeOccupata = Math.round(percentualeOccupata * 10) / 10.0;
                
                System.out.println("weight of the file in  " + filePath + ": " + fileSizeString + " GB, percentage: " + percentualeOccupata   + "%");
            }
        }
        System.out.println("\n\nPress ENTER or RETURN to exit");
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}
    }
    public static void checkDirectory(File directory, long spazioTotaleGB) {
        //Get an array of files that represents all files and subdirectories in the specified directory
        File[] directoryFiles = directory.listFiles();
        
            
        //Verify that the file array is different from null
        if (directoryFiles != null) {
            //Iterate on each element of the file array
            for (File f : directoryFiles) {
                //If it is a directory, call the method checkDirectory() on the current directory
                if (f.isDirectory()) {
                    checkDirectory(f, spazioTotaleGB);
                }
                //Otherwise, if it is a file, check if it has a size greater than 1 GB
                else if (f.isFile() && f.length() > BYTES_PER_GB) {
                    //Save the directory address of the file in a string
                    String filePath = f.getAbsolutePath();
                    //format the whole and print the weight of the file in GB
                    double fileSize = (double) f.length() / BYTES_PER_GB;
                    NumberFormat nf = NumberFormat.getInstance();
                    nf.setMaximumFractionDigits(2);
                    String fileSizeString = nf.format(fileSize);

                    //Calculate the percentage of space occupied by files
                    double percentualeOccupata = fileSize  / (double) spazioTotaleGB * 100;
                    percentualeOccupata = Math.round(percentualeOccupata * 10) / 10.0;
                    
                    System.out.println("weight of the file in " + filePath + ": " + fileSizeString + " GB, percentage: " + percentualeOccupata   + "%");
                
                }   
            }
        }
    }       
}