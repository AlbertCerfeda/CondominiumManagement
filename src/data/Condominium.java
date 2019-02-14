package data;

/**
 *
 * @author MajimeSoftware-AlbertCerfeda
 * @version 1
 */
public class Condominium {
    
    public Apartment[] a;
    private String name;
    private int nFloors;
    private int nApartEachFloor;
    /**
     * Returns the name of the condominium
     * @return 
     */
    public String getName(){
        return name;
    }
    /**
     * Returns the number of floors of the condominium
     * @return 
     */
    public int getnFloors(){
        return nFloors;
    }
    /**
     * Returns the number of <b>apartments each floor</b> of the condominium
     * @return 
     */
    public int getnApartEachFloor() {
        return nApartEachFloor;
    }
    /**
     * Returns the index of an apartment given the floor he's on and positioning in that floor
     * @param floor The floor the apartment is on
     * @param n The position of the apartment on his floor
     * @return the corresponding apartment index
     */
    public int getApartIndex(int floor, int n) {
        return (floor * nApartEachFloor) + n;
    }
    /**
     * Returns the size of the apartments array
     * @return array lenght
     */
    public int getApSize(){
        return a.length;
    }
    /**
     * Counts how many of the apartment in the array are empty
     * @return number of empty apartments
     */
    public int countEmptyApartment(){
        int empty=0;
        for(int n=0;n<a.length;n++){
            if(a[n].isEmpty()){
                empty++;
            }
        }
        return empty;
    }
    /**
     * Counts how many of the apartments in the array are occupied
     * @return number of occupied apartments
     */
    public int countOccupiedApartment(){
        int occupied=0;
        for(int n=0;n<a.length;n++){
            if(!a[n].isEmpty()){
                occupied++;
            }
        }
        return occupied;
    }
    /**
     * Counts how many tenants there are in the whole apartments array
     * @return number of tenants in the condominium
     */
    public int countTenants(){
        int tenants=0;
        for(int n=0;n<a.length;n++){
            if(!a[n].isEmpty()){
                tenants+=a[n].getN_tenant();
            }
        }
        return tenants;
    }
    /**
     * Returns the reference to the Apartment array of the Condominium
     * @return apartment array
     */
    public Apartment[] getArray(){
        return a;
    }
    /**
     * Constructs and initializes the Condominium using input data
     * @param sm Array containing the float values of the surface area of every apartment on the same floor
     * @param nFloors Number of floors
     * @param nApartEachFloor Number of apartments each floor
     * @param name Name of the condominium
     */
    public Condominium(Float sm[], int nFloors, int nApartEachFloor, String name) {
        this.name = name;
        this.nFloors = nFloors;
        this.nApartEachFloor = nApartEachFloor;

        a = new Apartment[nFloors * nApartEachFloor];
        for (int floor = 0; floor < nFloors; floor++) {
            for (int n = 0; n < nApartEachFloor; n++) {
                a[getApartIndex(floor, n)] = new Apartment(sm[n],floor);
            }
        }
    }
    /**
     * This method defines the owner and the number of tenants of an Apartment in the condominium
     * @param index Index of the apartment to operate on
     * @param owner Name of the new owner
     * @param n_tenant Number of the Tenants in the apartment
     */
    public void defineApartment(int index, String owner, int n_tenant,boolean empty) {
        a[index].setOwner(owner);
        a[index].setN_tenant(n_tenant);
        a[index].setEmpty(empty);
    }
    /**
     * Empties an apartment in a certain position
     * @param index Indicates which Apartment to empty
     */
    public void evict(int index){
        a[index].evict();
    }
}
