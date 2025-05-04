package model;

public abstract class AdminActionTemplate {

    public final void executeAction() {
        authenticateAdmin();
        performAction();           // abstract
        logAction();               // optional
    }

    protected void authenticateAdmin() {
        System.out.println("Admin authenticated.");
    }

    protected abstract void performAction();

    protected void logAction() {
        System.out.println("Action logged.");
    }
}
