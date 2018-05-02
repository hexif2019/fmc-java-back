package fr.insa.fmc.javaback.wrapper;

public class ProduitMagWrapper {
    private ProduitWrapper produit;
    private String idMagasin;

    public ProduitWrapper getProduit() {
        return produit;
    }

    public void setProduit(ProduitWrapper produit) {
        this.produit = produit;
    }

    public String getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(String idMagasin) {
        this.idMagasin = idMagasin;
    }
}
