package fr.insa.fmc.javaback.entity.enums;

public enum enumEtatCommande {
    EDITION,
    PAYEMENT_EFFECTUE,
    ANNULATION_CLIENT,
    ANNULATION_SYSTEM,
    ANNULATION_MAGASIN,             //Si tout les magasins ont annule ou timeout
    VALIDE_MAGASINS_MAIS_MODIF,    //Si un des magasins n a pas valide ou indique ne pas avoir un article, cas le plus chiant a traite
    VALIDE_MAGASIN,                //valide par tout les magasins sans soucis
    ATTRIBUE_A_COURSIER,
    EN_COURS_DE_LIVRAISON,
    DANS_CASIER,
    RECUPERE_CLIENT,
    CASIER_TIMEOUT,             //Le client n'a pas validé avoir récupéré la commande, et le delai de recuperation de la commande est depasse
    VIDE_PAR_NOUS               //apres un timeout, lorsque l on a tout enleve
}
