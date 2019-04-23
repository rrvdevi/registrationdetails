package fileReader.pojo;

import fileReader.enums.MimeType;

/**
 * @author  olusola
 * Class for containing the details of a file
 **/
public class FileDetails {

    /**
     * Name of file
     */
    private String name;
    /**
     * Size of file
     */
    private Long fileSize;
    /**
     * Mimetype of file
     */
    private MimeType mimeType;
    /**
     * File extension
     */
    private String fileExtension;
    /**
     * Vehicle details contained in the file
     */
    private VehicleDetails vehicleDetails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public MimeType getMimeType() {
        return mimeType;
    }

    public void setMimeType(MimeType mimeType) {
        this.mimeType = mimeType;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public VehicleDetails getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(VehicleDetails vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileDetails that = (FileDetails) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getFileSize() != null ? !getFileSize().equals(that.getFileSize()) : that.getFileSize() != null)
            return false;
        if (getMimeType() != null ? !getMimeType().equals(that.getMimeType()) : that.getMimeType() != null)
            return false;
        if (getFileExtension() != null ? !getFileExtension().equals(that.getFileExtension()) : that.getFileExtension() != null)
            return false;
        return getVehicleDetails() != null ? getVehicleDetails().equals(that.getVehicleDetails()) : that.getVehicleDetails() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getFileSize() != null ? getFileSize().hashCode() : 0);
        result = 31 * result + (getMimeType() != null ? getMimeType().hashCode() : 0);
        result = 31 * result + (getFileExtension() != null ? getFileExtension().hashCode() : 0);
        result = 31 * result + (getVehicleDetails() != null ? getVehicleDetails().hashCode() : 0);
        return result;
    }
}
