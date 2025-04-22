package edu.jundev.donation;

class NewPaymentSystem implements PaymentProcessor {
    @Override
    public void pay(double amount) {
        System.out.println("[NewPaymentSystem] Payment of $" + amount + " completed using NEW system.");
    }
}