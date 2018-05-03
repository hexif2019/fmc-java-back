package fr.insa.fmc.javaback.configuration;

public class GlobalURLs {

    public static final String BASE = "/api";
    public static final String MOCK = "/mock";

    public static final String CASIER_OPEN = BASE+"/casier/open";
    public static final String MAGASIN_GETPRODUITS = BASE+"/getProduits/{id}";
    public static final String MAGASIN_GETPRODUITS_BYMAGASIN = BASE+"/getProduit/{marchandid}/{produitid}";
    public static final String MAGASIN_REGISTER = BASE+"/registerMarchand";
    public static final String MAGASIN_AUTHMARCHAND = BASE+"/authenticateMarchand";
    public static final String MAGASIN_UPDATEPRODUIT = BASE+"/updateproduit";
    public static final String MAGASIN_DELETEPRODUIT_BYMAGASIN = BASE+"/deleteProduit/{marchandid}/{produitid}";

    public static final String CLIENT_REGISTER = BASE+"/register";
    public static final String CLIENT_AUTHENTICATE_TOKEN = BASE+"/authenticateToken";
    public static final String COMMANDE_GETPANIER = BASE+"/authenticate";

    public static final String COMMANDE_GETCOMMANDESENCOURS = BASE+"/getCommandesEnCour/{id}";

    public static final String COMMANDE_GETCOMMANDESARCHIVEES = BASE+"/getCommandesArchiver/{id}";

    public static final String COMMANDE_GETLASTCOMMANDES = BASE+"/getLastCommandes/{id}";

    public static final String COMMANDE_UPDATEPANIER = BASE+"/updatePanier";

    public static final String COURSIER_AUTHENTICATE = BASE+"/livreur/authenticate";

    public static final String COURSIER_TERMINERLIVRAISON = BASE+"/livreur/terminerlivraison/{commandeId}";

    public static final String COURSIER_GETCOMMANDESENCOURS = BASE+"/livreur/getCommandesEnCour/{coursierId}";

    public static final String COURSIER_GETCOMMANDESARCHIVEES = BASE+"/livreur/getCommandesArchiver/{userid}";

    public static final String PAYPAL_PAY = BASE+"/pay";
    public static final String PAYPAL_PAYSUCCESS = BASE+"/pay/success";

    public static final String PAYPAL_PAYCANCEL = BASE+"/pay/cancel";

    public static final String a = BASE+"";

    public static final String MOCK_PAYPAL_PAY = MOCK+"/pay";

    public static final String MOCK_CLIENT_SEARCH = MOCK+"/searchclient/{email}";

    public static final String MOCK_CLIENT_AUTHENTICATE = MOCK+"/authenticate";



}
