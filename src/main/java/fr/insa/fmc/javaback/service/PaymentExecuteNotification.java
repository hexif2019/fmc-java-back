package fr.insa.fmc.javaback.service;

public class PaymentExecuteNotification {

    private String commandeId;
    private String paymentID;
    private String payerID;

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String authorizationId) {
        this.paymentID = authorizationId;
    }

    public String getPayerID() {
        return payerID;
    }

    public void setPayerID(String payerId) {
        this.payerID = payerId;
    }

    public String getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(String commandeId) {
        this.commandeId = commandeId;
    }
}
