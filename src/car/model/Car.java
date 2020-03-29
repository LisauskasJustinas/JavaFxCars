package car.model;

public class Car {
    private int id;
    private String make;
    private String model;
    private String feature;
    private String fuel;
    private int door;
    private int userid;

    public Car(int id, String make, String model, String feature, String fuel, int door, int userid) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.feature = feature;
        this.fuel = fuel;
        this.door = door;
        this.userid = userid;
    }

    public Car(String make, String model, String feature, String fuel, int door, int userid) {
        this.make = make;
        this.model = model;
        this.feature = feature;
        this.fuel = fuel;
        this.door = door;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getDoor() {
        return door;
    }

    public void setDoor(int door) {
        this.door = door;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Dakar{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", feature='" + feature + '\'' +
                ", fueal='" + fuel + '\'' +
                ", door=" + door +
                ", userid=" + userid +
                '}';
    }
}
