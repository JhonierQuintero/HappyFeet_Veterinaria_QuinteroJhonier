package model.enums;

public enum EstadoProcedimiento {
    PROGRAMADA("Programado"),
    EN_PROCESO("En Proceso"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado");

    private final String valorEnDB;

    private EstadoProcedimiento(String pValorEnDB) {
        this.valorEnDB = pValorEnDB;
    }

    public String getValorEnDB() {
        return this.valorEnDB;
    }

    public static EstadoProcedimiento fromString(String text) {
        for (EstadoProcedimiento b : EstadoProcedimiento.values()) {
            if (b.valorEnDB.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}