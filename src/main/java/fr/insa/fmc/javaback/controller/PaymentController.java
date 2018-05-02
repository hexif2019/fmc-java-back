package fr.insa.fmc.javaback.controller;

import com.paypal.api.payments.Authorization;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import fr.insa.fmc.javaback.entity.Commande;
import fr.insa.fmc.javaback.repository.CommandeRepository;
import fr.insa.fmc.javaback.service.PaymentCreationNotification;
import fr.insa.fmc.javaback.service.PaymentExecuteNotification;
import fr.insa.fmc.javaback.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PaymentController {
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private PaypalService paypalService;

    @RequestMapping(method = RequestMethod.POST,value="api/pay",consumes = "application/json")
    public PaymentCreationNotification pay(@RequestBody Commande commande){
        PaymentCreationNotification res = new PaymentCreationNotification();
        try {
            String baseUrl = "client.fais-mes-courses.fr/api/pay/";
            Payment payment = paypalService.createPayment((double) commande.getPrixTotal(),"Commande à régler",baseUrl+"cancel",baseUrl+"success");
            res.setPaymentID(payment.getId());
        }catch (PayPalRESTException e){
            System.err.println(e.getMessage());
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.POST,value="mock/pay",consumes = "application/json")
    public PaymentCreationNotification payMock(){
        PaymentCreationNotification res = new PaymentCreationNotification();
        try {
            String baseUrl = "client.fais-mes-courses.fr/api/pay/";
            Payment payment = paypalService.createPayment(31.11,"Commande à régler",baseUrl+"cancel",baseUrl+"success");
            res.setPaymentID(payment.getId());
        }catch (PayPalRESTException e){
            System.err.println(e.getMessage());
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.POST,value="api/pay/success",consumes = "application/json")
    public String proceedPayment(@RequestBody PaymentExecuteNotification authorize){
        try {
            Authorization authorization = paypalService.executePaymentAndGetAuthorization(authorize.getAuthorizationId(),authorize.getPayerId());
            if(authorization.getState().equals("success")){
                Optional<Commande> commande = commandeRepository.findById(authorize.getCommandeId());
                if(commande.isPresent()){
                    Commande in = commande.get();
                    in.setAuthorizationId(authorization.getId());
                    commandeRepository.save(in);
                }
                return "success";
            }
        }catch (PayPalRESTException e){
            System.err.println(e.getMessage());
        }
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST,value="api/pay/cancel",consumes = "application/json")
    public String cancelPayment(Commande commande){
        return "operation cancelled";
    }
}
