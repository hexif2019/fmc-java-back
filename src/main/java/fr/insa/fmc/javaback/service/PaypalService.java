package fr.insa.fmc.javaback.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalService {

    private String method = "paypal";
    private String intent = "authorize";
    private String currency = "EUR";

    @Autowired
    private APIContext apiContext;

    public Payment createPayment(
            Double total,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException{
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public Authorization executePaymentAndGetAuthorization(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        Payment createdAuthPayment = payment.execute(apiContext, paymentExecute);
        return createdAuthPayment.getTransactions().get(0).getRelatedResources().get(0).getAuthorization();
    }

    public void captureAuthorization(String authorizationId, Double total) throws PayPalRESTException {
        Authorization authorization = Authorization.get(apiContext,authorizationId);
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format("%.2f", total));
        Capture capture = new Capture();
        capture.setAmount(amount);
        capture.setIsFinalCapture(true);
        Capture responseCapture = authorization.capture(apiContext, capture);
        System.out.println("Capture id = " + responseCapture.getId() + " and status = " + responseCapture.getState());
    }

    public static double ConvertIntToDouble(int prix){
        double prixDouble = (double) prix;
        return prixDouble/100.0;
    }

}
