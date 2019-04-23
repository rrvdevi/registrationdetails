package fileReader.pojo;

/**
 *   
 * Class to hold vehicle details
 */
public class VehicleDetails {

    /**
     * Registration number (plate) of vehicle
     */
    private String regNo;
    /**
     * Make of vehicle
     */
    private String make;
    /**
     * Colour of vehicle
     */
    private String colour;

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleDetails that = (VehicleDetails) o;

        if (getRegNo() != null ? !getRegNo().equals(that.getRegNo()) : that.getRegNo() != null) return false;
        if (getMake() != null ? !getMake().equals(that.getMake()) : that.getMake() != null) return false;
        return getColour() != null ? getColour().equals(that.getColour()) : that.getColour() == null;
    }

    @Override
    public int hashCode() {
        int result = getRegNo() != null ? getRegNo().hashCode() : 0;
        result = 31 * result + (getMake() != null ? getMake().hashCode() : 0);
        result = 31 * result + (getColour() != null ? getColour().hashCode() : 0);
        return result;
    }
}
