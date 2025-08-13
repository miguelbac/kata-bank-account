package dev.mba.models;

public class CuentaAhorros extends Cuenta {
    private boolean activa;

    public CuentaAhorros(float saldo, float tasaAnual) {
        super(saldo, tasaAnual);
        activa = saldo >= 10000;
    }

    @Override
    public boolean consignar(float cantidad) {
        if (activa) {
            return super.consignar(cantidad);
        } else {
            System.out.println("La cuenta no está activa. No se puede consignar.");
            return false;
        }
    }

    @Override
    public boolean retirar(float cantidad) {
        if (activa) {
            return super.retirar(cantidad);
        } else {
            System.out.println("La cuenta no está activa. No se puede retirar.");
            return false;
        }
    }

    @Override
    public void extractoMensual() {
        if (numeroRetiros > 4) {
            int retirosExtra = numeroRetiros - 4;
            comisionMensual += retirosExtra * 1000;
        }
        super.extractoMensual();
        activa = saldo >= 10000;
    }

    @Override
    public String imprimir() {
        int totalTransacciones = numeroConsignaciones + numeroRetiros;
        return "Saldo: " + saldo +
                " | Comisión mensual: " + comisionMensual +
                " | Transacciones: " + totalTransacciones;
    }

}
