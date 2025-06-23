    /*
 * GymMember is the parent class (abstract)
 * This class works as the base class for the gym membership project
 */
public abstract class GymMember{
    //The following are protected attributes
    protected int id;
    protected String name;
    protected String location;
    protected String phone;
    protected String email;
    protected String gender;
    protected String DOB;
    protected String membershipStartDate;
    protected int attendance;
    protected double loyaltyPoints;
    protected boolean activeStatus;
    
    //Parameterized constructors
    public GymMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate){
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.DOB = DOB;
        this.membershipStartDate = membershipStartDate;
        this.attendance = 0;
        this.loyaltyPoints = 0;
        this.activeStatus = false;
    }
    
    //Accessor method for the attributes
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getLocation(){
        return this.location;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getEmail(){
        return this.email;
    }
    public String getGender(){
        return this.gender;
    }
    public String getDOB(){
        return this.DOB;
    }
    public String getMembershipStartDate(){
        return this.membershipStartDate;
    }
    public int getAttendance(){
        return this.attendance;
    }
    public double getLoyaltyPoints(){
        return this.loyaltyPoints;
    }
    public boolean getActiveStatus(){
        return this.activeStatus;
    }
    
    //Abstract method for marking attendance; markAttendance()
    public abstract void markAttendance();
    
    /*
     * Methods to activate and deactivate the membership of members
     * 1. Creating a method called activateMembership() that sets activeStatus to true when membership needs to be activated
     * 2. Creating a method called deactivateMembership() that sets activeStatus to false if membership has to be deactivated
     */
    public void activateMembership(){
        this.activeStatus = true; // Activate membership
    }
    public void deactivateMembership(){
        if(this.activeStatus == false){
            System.out.println("You are not a member yet!");
        }
        else{
        this.activeStatus = false;
        }
    }
    
    /*
     * Method to reset member details i.e. resetMember()
     * This method sets the activeStatus to false, and attendance and loyaltyPoints to zero
     */
    public void resetMember(){
        this.activeStatus = false;
        this.attendance = 0;
        this.loyaltyPoints = 0;
    }
    
    /*
     * A method display() to display/output the member details
     */
    public void display() {
        System.out.println("Member ID: " + id);
        System.out.println("Full Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Gender: " + gender);
        System.out.println("Date of Birth: " + DOB);
        System.out.println("Membership Start Date: " + membershipStartDate);
        System.out.println("Attendance: " + attendance);
        System.out.println("Loyalty Points: " + loyaltyPoints);
        System.out.println("Active Status: " + (activeStatus? "Active" : "Inactive"));
    }
}