/*
 * PremiumMember is another subclass(child class) of GymMember
 * This class is for members who have subscribed to Premium Membership in the gym
 */
public class PremiumMember extends GymMember{
    //Attributes for PremiumMember
    private final double premiumCharge;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;

    //Constructor for premium membe
    public PremiumMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate, String personalTrainer){
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.premiumCharge = 50000;
        this.paidAmount = 0;
        this.isFullPayment = false;
        this.personalTrainer = personalTrainer;
        this.discountAmount = 0;
    }

    // Accessor methods for corresponding attributes
    public double getPremiumCharge(){ 
        return this.premiumCharge;
    }
    public String getPersonalTrainer(){
        return this.personalTrainer;
    }
    public boolean getIsFullPayment(){
        return this.isFullPayment;
    }
    public double getPaidAmount(){
        return this.paidAmount;
    }
    public double getDiscountAmount(){
        return this.discountAmount;
    }
    
    /*
     * @Override the markAttendance() abstract method from parent class
     * Increment of attendance value by 1 each time on method invoke
     * Increment of loyaltyPoints by 10 points each on method invoke
     */
    @Override
    public void markAttendance() {
        this.attendance++;
        this.loyaltyPoints += 10;
    }
    
    /*
     * payDueAmount() method is created for members to pay their due amount
     */
    public String payDueAmount(double paidAmount){
        if(this.isFullPayment == true){
            return "Your payment is already completed. Thank You!";
        }
        else{
            if(this.paidAmount > premiumCharge){
            return "!!!Payment amount exceeds the premium charge. The total charge is: " + premiumCharge;
            }
            if(this.paidAmount == premiumCharge){
            this.isFullPayment = true;
            }
            //Adding the received paid amount(from parameter) to the attribute
            this.paidAmount += paidAmount;
            //RemainingAmount is created to check how much due is left to be paid after this payment
            double remainingAmount = premiumCharge - this.paidAmount;
            if(remainingAmount != 0){
            return "Your payment was successful. Your remaining amount is: "+ remainingAmount;
            }
        }
        return "Payment completed. Thank you for your purchase!";
    }
    
    /*
     * calculateDiscount() is created to calculate the discount based on payment status
     */
    public void calculateDiscount(){
        if(isFullPayment == true){
            this.discountAmount = premiumCharge * 0.10;
            System.out.println("Thank you for paying your charge on time. As a loyal member your discount is : " + discountAmount);
        } else{
            System.out.println("You failed to pay your charge on time. No discount available.");
        }
    }

    /*
     * revertPremiumMember is created to revert premium member status
     * This method is made to call the super class method resetMember()
     * It also updates the values of personalTrainer, isFullPayment, paidAmount, and discountAmount 
     */
    public void revertPremiumMember() {
        super.resetMember();
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    //@override display method from parent class to display all attributes
    @Override
    public void display() {
        super.display();
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Paid Amount: " + paidAmount);
        System.out.println("Payment Status: " + (isFullPayment ? "Complete" : "Incomplete"));
        double remainingAmount = premiumCharge - this.paidAmount;
        System.out.println("Remaining Amount: " + remainingAmount);
        if(isFullPayment == true){
            System.out.println("Discount Amount: " + discountAmount);
        }
    }

}
