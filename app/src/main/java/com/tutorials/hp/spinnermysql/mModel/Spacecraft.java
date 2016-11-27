package com.tutorials.hp.spinnermysql.mModel;

/**
 * Created by Oclemy on 9/30/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class Spacecraft {

    /*
    INSTANCE FIELDS
     */
    private int id;
    private String name;
    private String propellant;
    private String destination;

    /*
    GETTERS AND SETTERS
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPropellant() {
        return propellant;
    }

    public void setPropellant(String propellant) {
        this.propellant = propellant;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    /*
    TOSTRING
     */
    @Override
    public String toString() {
        return name;
    }
}
