public class Bicycle {
    public int cadence;
    public int gear;
    public int speed;

// the bicycle class has one constructor
    public Bicycle(int startCadence, int startSpeed, int startGear) {
    gear = startGear;
    cadence = startCadence;
    speed = startSpeed;
    }

//  the bicycle class has four methods
    public void setCadence (int newValue){
        cadence = newValue;
    }

    public void setGear(int newValue) {
        gear = newValue;
    }
    public void applyBrake(int decrement) {
        speed -= decrement;
    }
    public void speedUp (int increment) {
        speed += increment;
    }
}

