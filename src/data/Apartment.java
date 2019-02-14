/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author MajimeSoftware-AlbertCerfeda
 * @version 1
 */
public class Apartment{
    
    private String owner="";
    private float sm;
    private int floor;
    private int n_tenant;
    private boolean empty=true;
    /**
     * Returns the owner of the apartment
     * @return 
     */
    public String getOwner() {
        return owner;
    }
    /**
     * Sets the String of the name of the owner of the apartment
     * @param owner The new owner of the apartment
     */
    public void setOwner(String owner){
        this.owner = owner;
    }
    /**
     * Returns the surface of the apartment
     * @return 
     */
    public float getSm(){
        return sm;
    }
    /**
     * Sets the surface of the Apartment
     * @param sm The new surface of the apartment
     */
    public void setSm(float sm) {
        this.sm = sm;
    }
    /**
     * Returns the floor of the apartment
     * @return 
     */
    public int getFloor() {
        return floor;
    }
    /**
     * Sets the floor of the apartment
     * @param floor The new floor number of the apartment
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }
    /**
     * Returns the number of tenants of the apartment
     * @return 
     */
    public int getN_tenant() {
        return n_tenant;
    }
    /**
     * Sets the number of tenants of the apartment
     * @param n_tenant The new number of tenants of the apartment
     */
    public void setN_tenant(int n_tenant) {
        this.n_tenant = n_tenant;
    }
    /**
     * Prints the info of the apartments in a <b>Long Format</b>
     */
    public boolean isEmpty(){
        return empty;
    }
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
    /**
     * Constructs and inizializes the attributes of the apartments using the inputs
     * @param sm Square meters of the apartment
     * @param owner Owner of the apartment
     * @param floor Floor number of the apartment
     * @param n_tenant Number of tenants of the apartment
     */
    public Apartment(float sm,String owner,int floor,int n_tenant){
        this.sm=sm;
        this.owner=owner;
        this.floor=floor;
        this.n_tenant=n_tenant;
        this.empty=false;
    }
    /**
     * Constructs an empty apartment
     * @param sm Square meters of the apartment
     * @param floor Floor number of the apartment
     */
    public Apartment(float sm,int floor){
        this.sm=sm;
        this.owner=owner;
        this.floor=floor;
        this.n_tenant=0;
        this.empty=true;
    }
    /**
     * Sets the apartment empty and brings al the values back to when it was empty
     */
    public void evict(){
        empty=true;
        owner="";
        n_tenant=0;
    }
}