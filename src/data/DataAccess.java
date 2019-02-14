/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;

/**
 *
 * @author cerfe
 */
public class DataAccess{

    public static ArrayList<Condominium> c = new ArrayList<>();

    public DataAccess(){
        
    }
    /**
     * Adds a new slot on the condominium Array List and istantiates a new condominium in it
     * @param sm Array containing the float values of the surface area of every apartment on the same floor
     * @param nFloors Number of floors
     * @param nApartEachFloor Number of apartments each floor
     * @param name Name of the condominium
     */
    public static void newCondominium(Float sm[], int nFloors, int nApartEachFloor, String name) {
        c.add(new Condominium(sm, nFloors, nApartEachFloor, name));
    }
    /**
     * Deletes the condominium in a certain position in the condominium's Array List
     * @param index The index of the condominium to delete
     */
    public static void delCondominium(int index) {
        c.remove(index);
    }
}
