package fileReader.service;

import fileReader.pojo.FileDetails;
import fileReader.pojo.VehicleDetails;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  olusola
 * Implementation of service FileReaderService
 */
public class FileReaderServiceImpl implements FileReaderService {

    private List<FileDetails> fileDetailsList;

    @Override
    public FileDetails getFileDetailsFromFile(String fileName) {
        FileDetails fileDetails = fileDetailsList.stream()
                .filter(x -> fileName.equals(x.getName()))
                .findFirst()
                .orElse(null);
        return fileDetails;
    }

    @Override
    public VehicleDetails getVehicleDetailsFromFile(String fileName){
        FileDetails fileDetails = fileDetailsList.stream()
                .filter(x -> fileName.equals(x.getName()))
                .findFirst()
                .orElse(null);
        return fileDetails.getVehicleDetails();
    }

    @Override
    public List<FileDetails> getFileContents(String path) {
        fileDetailsList = new ArrayList<>();
        try {
            readDirectory(path, fileDetailsList);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return fileDetailsList;
    }

    /**
     * Reads the input directory and stores any valid files
     * @param path
     * @param fileDetailsList
     * @throws IOException
     */
    private void readDirectory(String path, List<FileDetails> fileDetailsList) throws IOException{

        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                readDirectory(f.getAbsolutePath(), fileDetailsList );
            } else {
                FileDetails fd = FileReaderUtil.setFileDetails(f);
                if(fd != null) {
                    fileDetailsList.add(fd);
                }
            }
        }
    }

}
