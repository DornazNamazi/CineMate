package model;

public abstract class UserRegistrationTemplate {

    public final void register() {
        collectUserInfo();
        validateInput();
        createAccount();   // can vary (e.g., Firebase, local DB)
        postRegistrationMessage();
    }

    protected abstract void collectUserInfo();
    protected abstract void validateInput();
    protected abstract void createAccount();

    protected void postRegistrationMessage() {
        System.out.println("Registration successful.");
    }
}

