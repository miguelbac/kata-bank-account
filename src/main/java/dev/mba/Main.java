package dev.mba;

import dev.mba.models.CuentaAhorros;
import dev.mba.models.CuentaCorriente;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== CUENTA DE AHORROS ===");
        CuentaAhorros ca = new CuentaAhorros(15000, 12);
        System.out.println(ca.imprimir());
        ca.consignar(2000);
        ca.retirar(3000);
        ca.retirar(2000);
        ca.retirar(1500);
        ca.retirar(1000);
        ca.retirar(500);
        System.out.println(ca.imprimir());
        ca.extractoMensual();
        System.out.println("Después de extracto:");
        System.out.println(ca.imprimir());

        System.out.println("\n=== CUENTA CORRIENTE ===");
        CuentaCorriente cc = new CuentaCorriente(5000, 12);
        cc.retirar(7000);
        System.out.println(cc.imprimir());
        cc.consignar(1000);
        System.out.println(cc.imprimir());
        cc.consignar(2000);
        System.out.println(cc.imprimir());
        cc.extractoMensual();
        System.out.println("Después de extracto:");
        System.out.println(cc.imprimir());
    }
}
