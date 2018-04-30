package fr.insa.fmc.javaback.entity.enums;

public enum enumEtatMagasinCommande {
    EDITION,
    PAYEMENT_EFFECTUE,
    ATTENTE_CONFIRMATION_MAGASIN,
    ANNULATION_CLIENT,
    ANNULATION_SYSTEM,
    ANNULATION_MAGASIN,
    VALIDE_MAGASINS_MAIS_MODIF,    //Si le magasin valide mais n'a pas tout les produits, seul etat qui n est pas un c/c de la commande en soit ?
    ATTRIBUE_A_COURSIER,
    EN_COURS_DE_LIVRAISON,
    DANS_CASIER,
    RECUPERE_CLIENT,
    CASIER_TIMEOUT
    //RESTE: evacuer(la commande a étais suprimer du casier)
}
