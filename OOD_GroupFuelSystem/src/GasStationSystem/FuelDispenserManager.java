package GasStationSystem;

public interface FuelDispenserManager {

    //All the abstract methods in the fuel dispenser manager
    public abstract boolean dispenseFuel(double fuelAmount);
    public abstract void restockFuel();
    public abstract void stopFuelDispense();

}
