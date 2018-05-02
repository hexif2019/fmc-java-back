package fr.insa.fmc.javaback.controller;

import com.paypal.api.payments.Authorization;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.exception.PayPalException;
import com.paypal.base.rest.PayPalRESTException;
import fr.insa.fmc.javaback.entity.Commande;
import fr.insa.fmc.javaback.repository.CommandeRepository;
import fr.insa.fmc.javaback.service.PaymentNotification;
import fr.insa.fmc.javaback.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private PaypalService paypalService;

    @RequestMapping(method = RequestMethod.POST,value="pay",consumes = "application/json")
    public String pay(@RequestBody Commande commande){
        try {
            String baseUrl = "client.fais-mes-courses.fr/api/pay/";
            Payment payment = paypalService.createPayment((double) commande.getPrixTotal(),"Commande � r�gler",baseUrl+"cancel",baseUrl+"success");
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return "redirect:" + links.getHref();
                }
            }
        }catch (PayPalRESTException e){
            System.err.println(e.getMessage());
        }
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST,value="pay/success",consumes = "application/json")
    public String proceedPayment(@RequestBody PaymentNotification authorize){
        try {
            Authorization authorization = paypalService.executePaymentAndGetAuthorization(authorize.getAuthorizationId(),authorize.getPayerId());
            //todo : update la commande avec l'authorizeId pour pouvoir capturer les fonds plus tard
            if(authorization.getState().equals("success")){
                return "success";
            }
        }catch (PayPalRESTException e){
            System.err.println(e.getMessage());
        }
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST,value="pay/cancel",consumes = "application/json")
    public String cancelPayment(Commande commande){
        return "operation cancelled";
    }
}
