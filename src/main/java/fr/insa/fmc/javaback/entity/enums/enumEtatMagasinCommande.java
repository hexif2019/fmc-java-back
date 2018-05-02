package fr.insa.fmc.javaback.entity.enums;

public enum enumEtatMagasinCommande {
    EDITION,
    PAYEMENT_EFFECTUE,
    ANNULATION_CLIENT,
    ANNULATION_SYSTEM,
    ANNULATION_MAGASIN,
    VALIDE_MAGASINS_MAIS_MODIF,    //Si le magasin valide mais n'a pas tout les produits, seul etat qui n est pas un c/c de la commande en soit ?
    VALIDE_MAGASIN,                 //Si le magasin valide tout les produits sans soucis
    ATTRIBUE_A_COURSIER,
    EN_COURS_DE_LIVRAISON,
    DANS_CASIER,
    RECUPERE_CLIENT,
    CASIER_TIMEOUT,
    VIDE_PAR_NOUS
}
