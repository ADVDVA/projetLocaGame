package adrien.faouzi.utility;

import org.primefaces.model.file.UploadedFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManaging {

    //location of folder for file.
    private static final String FOLDER_PATH_ABSOLUT = "http://localhost:8080/projetLocaGame_war_exploded/images/download/";
    private static final String DEFAULT_FILE = "default.png";
    private static final String FOLDER_PATH_RELATIVE = "images/download/";
    private static final String FOLDER_PATH_DOWNLOAD = "C:/Users/faouz/IdeaProjects/projetLocaGame/src/main/webapp/images/download/";



    //save a file (from input file).
    public static boolean saveNewFile(UploadedFile uploadingFile){
        boolean out = false;

        //instance file and path.
        File file = new File(FOLDER_PATH_DOWNLOAD+uploadingFile.getFileName());
        //File file = new File(FOLDER_LOCAL+uploadingFile.getFileName());
        Path filePath = file.toPath();

        //delete previous file if existing.
        if(Files.exists(filePath)){
            file.deleteOnExit();
        }

        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            outputStream.write(uploadingFile.getContent());
            out = true;
        } catch (IOException error) {
            UtilityProcessing.debug("Error from write in file (FileManaging.java)");
            UtilityProcessing.debug(error.getMessage());
        }

        return out;
    }



    //get seed of file in folder download.
    public static String getUrlFile(UploadedFile uploadingFile){
        return FOLDER_PATH_RELATIVE+uploadingFile.getFileName();
        //return FOLDER_LOCAL+uploadingFile.getFileName();
    }



    //get seed of default file in folder download.
    public static String getDefaultUrlFile(){
        return FOLDER_PATH_ABSOLUT+DEFAULT_FILE;
        //return FOLDER_LOCAL+DEFAULT_FILE;
    }
    public static String getUrlFileAbsolute(String url){
        return FOLDER_PATH_ABSOLUT+url;
    }



    //re build url from db, for img html.
    public static String urlFromDB(String fileName){
        //return FOLDER_PATH_RELATIVE+fileName;
        return FOLDER_PATH_ABSOLUT+fileName;
    }

}
