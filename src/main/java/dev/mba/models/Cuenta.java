package dev.mba.models;

public class Cuenta {
    protected float saldo;
    protected int numeroConsignaciones = 0;
    protected int numeroRetiros = 0;
    protected float tasaAnual;
    protected float comisionMensual = 0f;

    public Cuenta(float saldo, float tasaAnual) {
        this.saldo = saldo;
        this.tasaAnual = tasaAnual;
    }

    public boolean consignar(float cantidad) {
        if (cantidad <= 0f)
            return false;
        saldo += cantidad;
        numeroConsignaciones++;
        return true;
    }

    public boolean retirar(float cantidad) {
        if (cantidad <= 0f || cantidad > saldo)
            return false;
        saldo -= cantidad;
        numeroRetiros++;
        return true;
    }

    public float calcularInteresMensual() {
        float tasaMensual = (tasaAnual / 12f) / 100f;
        float interes = saldo * tasaMensual;
        saldo += interes;
        return interes;
    }

    public void extractoMensual() {
        saldo -= comisionMensual;
        calcularInteresMensual();
    }

    public String imprimir() {
        return "Saldo: " + saldo +
                " | Consignaciones: " + numeroConsignaciones +
                " | Retiros: " + numeroRetiros +
                " | Tasa anual (%): " + tasaAnual +
                " | Comisión mensual: " + comisionMensual;
    }

    public float getSaldo() {
        return saldo;
    }

    public float getComisionMensual() {
        return comisionMensual;
    }

    public void setComisionMensual(float comisionMensual) {
        this.comisionMensual = comisionMensual;
    }
}
