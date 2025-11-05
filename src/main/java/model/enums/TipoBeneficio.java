package model.enums;

public enum TipoBeneficio {
    DESCUENTO("Descuento"),
    SERVICIO_GRATIS("Servicio Gratis"), 
    PRODUCTO_GRATIS("Producto Gratis"),
    PUNTOS_EXTRA("Puntos Extra");

    private final String valorEnDB;

    private TipoBeneficio(String pValorEnDB) {
        this.valorEnDB = pValorEnDB;
    }

    public String getValorEnDB() {
        return this.valorEnDB;
    }

    public static TipoBeneficio fromString(String text) {
        for (TipoBeneficio b : TipoBeneficio.values()) {
            if (b.valorEnDB.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("No se encontró un TipoBeneficio para el valor: " + text);
    }
}