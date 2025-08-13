package dev.mba;

import dev.mba.models.CuentaCorriente;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CuentaCorrienteTest {

    private CuentaCorriente cuentaCorriente;

    @BeforeEach
    public void setUp() {
        cuentaCorriente = new CuentaCorriente(5000f, 12f);
    }

    @Test
    public void testRetirarDentroDelSaldo() {
        boolean resultado = cuentaCorriente.retirar(3000f);
        assertThat(resultado, is(true));
        assertThat((double) cuentaCorriente.getSaldo(), is(closeTo(2000.0, 0.001)));
        assertThat(cuentaCorriente.imprimir(), containsString("Sobregiro: 0.0"));
    }

    @Test
    public void testRetirarConSobregiro() {
        boolean resultado = cuentaCorriente.retirar(6000f);
        assertThat(resultado, is(true));
        assertThat((double) cuentaCorriente.getSaldo(), is(closeTo(0.0, 0.001)));
        assertThat(cuentaCorriente.imprimir(), containsString("Sobregiro: 1000.0"));
    }

    @Test
    public void testConsignarReduciendoSobregiro() {
        cuentaCorriente.retirar(6000f);
        boolean resultado = cuentaCorriente.consignar(700f);
        assertThat(resultado, is(true));
        assertThat(cuentaCorriente.imprimir(), containsString("Sobregiro: 300.0"));
    }

    @Test
    public void testConsignarCubriendoSobregiroYSaldo() {
        cuentaCorriente.retirar(6000f);
        boolean resultado = cuentaCorriente.consignar(1500f);
        assertThat(resultado, is(true));
        assertThat((double) cuentaCorriente.getSaldo(), is(closeTo(500.0, 0.001)));
        assertThat(cuentaCorriente.imprimir(), containsString("Sobregiro: 0.0"));
    }

    @Test
    public void testExtractoMensualNoAfectaSobregiro() {
        cuentaCorriente.retirar(6000f); // genera sobregiro 1000
        cuentaCorriente.setComisionMensual(50f);
        cuentaCorriente.extractoMensual();
        assertThat((double) cuentaCorriente.getSaldo(), is(closeTo(0.0, 0.001))); // saldo = 0 + interés (0)
        assertThat(cuentaCorriente.imprimir(), containsString("Sobregiro: 1000.0"));
    }

    @Test
    public void testImprimir() {
        cuentaCorriente.retirar(2000f);
        cuentaCorriente.consignar(500f);
        String output = cuentaCorriente.imprimir();
        assertThat(output, containsString("Saldo:"));
        assertThat(output, containsString("Comisión mensual:"));
        assertThat(output, containsString("Transacciones:"));
        assertThat(output, containsString("Sobregiro:"));
    }
}
