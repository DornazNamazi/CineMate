package model;

public class CustomerRegistration extends UserRegistrationTemplate {
    @Override
    protected void collectUserInfo() {
        System.out.println("Collecting name, email, and password for customer.");
    }

    @Override
    protected void validateInput() {
        System.out.println("Validating customer email format...");
    }

    @Override
    protected void createAccount() {
        System.out.println("Creating Firebase account for customer...");
    }
}
