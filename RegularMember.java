/*
 * RegularMember is a subclass(child class) of GymMember
 * This class is for members who have subscribed to Regular Membership in the gym
 */
public class RegularMember extends GymMember{
    // Private attributes for RegularMember
    private final int attendanceLimit;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;
    
    // Constructor for RegularMember
    public RegularMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate, String referralSource){
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.isEligibleForUpgrade = false;
        this.attendanceLimit = 30;
        this.plan = "Basic";
        this.price = 6500;
        this.removalReason = "";
        this.referralSource = referralSource;
    }
    
    //Accessor methods for corresponding attributes
    public int getAttendanceLimit(){
        return this.attendanceLimit; 
    }
    public boolean getIsEligibleForUpgrade(){
        return this.isEligibleForUpgrade; 
    }
    public String getRemovalReason(){
        return this.removalReason;
    }
    public String getReferralSource(){
        return this.referralSource;
    }
    public String getPlan(){
        return this.plan;
    }
    public double getPrice(){
        return this.price;
    }
    
    /*
     * @Override the markAttendance() abstract method from parent class
     * Increment of attendance value by 1 each time on method invoke
     * Increment of loyaltyPoints by 5 points each on method invoke
     */
    @Override
    public void markAttendance(){
        attendance++;
        loyaltyPoints +=5;
        if(attendance >= attendanceLimit){
            isEligibleForUpgrade = true;
        }
    }
    
    // Method to get the price of the plan; getPlanPrice()
    public double getPlanPrice(String plan){
        switch(plan.toLowerCase()){
            case "basic":
                return this.price = 6500;
            case "standard":
                return this.price = 12500;
            case "deluxe":
                return this.price = 18500;
            default:
                return -1;
        }
    }
    
    // Upgrade plan method for members to upgrade to a better plan
    public String upgradePlan(String plan) {
        //Checking is the member is eligible for upgrading the plan or not
        if (isEligibleForUpgrade == false) {
            return "You are not eligible for upgrade. Minimum attendance required: " + attendanceLimit;
        }
        //Checking if the member is already subscribed to the plan they want to upgrade to
        if (plan.toLowerCase().equals(this.plan)) {
            return "You are already subscribed to " + plan;
        }
        //Checking if the member selected invalid plan
        if (price == -1) {
            return "You have selected an invalid plan.";
        }
        //Updating price and plan if member is eligible to upgrade
        if(isEligibleForUpgrade == true){
            this.price = getPlanPrice(plan.toLowerCase());
            this.plan = plan.toLowerCase();
        }
        //Returning proper display message if upgrade was succesful
        return "Plan successfully upgraded to " + plan;
    }
    
    /*
     * revertRegularMember() method which accepts removalReason as a parameter
     * super class method - resetMember() is called here
     * isEligibleForUpgrade set to false
     * plan is set to basic and price is set to 6500
     */
    public void revertRegularMember(String removalReason) {
        super.resetMember();
        isEligibleForUpgrade = false;
        plan = "basic";
        price = 6500;
        this.removalReason = removalReason;
    }
    
    //@override display method from parent class to display all attributes
    @Override
    public void display() {
        super.display();
        System.out.println("Plan: " + plan);
        System.out.println("Price: " + price);
        if (removalReason != "") {
            System.out.println("Removal Reason: " + removalReason);
        }
    }        
}