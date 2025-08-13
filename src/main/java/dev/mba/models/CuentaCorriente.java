package dev.mba.models;

public class CuentaCorriente extends Cuenta {
    private float sobregiro;

    public CuentaCorriente(float saldo, float tasaAnual) {
        super(saldo, tasaAnual);
        this.sobregiro = 0f;
    }

    @Override
    public boolean retirar(float cantidad) {
        if (cantidad <= 0)
            return false;

        if (cantidad <= saldo) {
            return super.retirar(cantidad);
        } else {
            float faltante = cantidad - saldo;
            saldo = 0;
            sobregiro += faltante;
            numeroRetiros++;
            return true;
        }
    }

    @Override
    public boolean consignar(float cantidad) {
        if (cantidad <= 0)
            return false;

        if (sobregiro > 0) {
            if (cantidad >= sobregiro) {
                cantidad -= sobregiro;
                sobregiro = 0;
            } else {
                sobregiro -= cantidad;
                cantidad = 0;
            }
        }
        if (cantidad > 0) {
            return super.consignar(cantidad);
        } else {
            numeroConsignaciones++;
            return true;
        }
    }

    @Override
    public void extractoMensual() {
        if (saldo > 0) {
            saldo -= comisionMensual;
            super.calcularInteresMensual();
        }
        comisionMensual = 0;
    }

    @Override
    public String imprimir() {
        int totalTransacciones = numeroConsignaciones + numeroRetiros;
        return "Saldo: " + saldo +
                " | Comisión mensual: " + comisionMensual +
                " | Transacciones: " + totalTransacciones +
                " | Sobregiro: " + sobregiro;
    }

}
