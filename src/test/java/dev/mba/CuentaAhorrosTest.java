package dev.mba;

import dev.mba.models.CuentaAhorros;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CuentaAhorrosTest {

    private CuentaAhorros cuentaAhorros;

    @BeforeEach
    public void setUp() {
        cuentaAhorros = new CuentaAhorros(15000f, 12f);
    }

    @Test
    public void testCuentaInicialmenteActiva() {
        assertThat(cuentaAhorros.consignar(1000f), is(true));
    }

    @Test
    public void testConsignarMientrasActiva() {
        boolean resultado = cuentaAhorros.consignar(2000f);
        assertThat(resultado, is(true));
        assertThat((double) cuentaAhorros.getSaldo(), is(closeTo(17000.0, 0.001)));
    }

    @Test
    public void testRetirarMientrasActiva() {
        boolean resultado = cuentaAhorros.retirar(6000f);
        assertThat(resultado, is(true));
        assertThat((double) cuentaAhorros.getSaldo(), is(closeTo(9000.0, 0.001)));
    }

    @Test
    public void testInactivarCuenta() {
        cuentaAhorros.retirar(6000f);
        boolean resultado = cuentaAhorros.consignar(500f);
        assertThat(resultado, is(false));
        assertThat((double) cuentaAhorros.getSaldo(), is(closeTo(9000.0, 0.001)));
    }

    @Test
    public void testExtractoMensualConRetirosExtra() {
        cuentaAhorros.retirar(1000f);
        cuentaAhorros.retirar(1000f);
        cuentaAhorros.retirar(1000f);
        cuentaAhorros.retirar(1000f);
        cuentaAhorros.retirar(1000f);
        cuentaAhorros.retirar(1000f);

        cuentaAhorros.extractoMensual();
        assertThat((double) cuentaAhorros.getComisionMensual(), is(closeTo(2000.0, 0.001)));
    }

    @Test
    public void testImprimir() {
        cuentaAhorros.retirar(1000f);
        cuentaAhorros.consignar(500f);
        String output = cuentaAhorros.imprimir();
        assertThat(output, containsString("Saldo:"));
        assertThat(output, containsString("Comisión mensual:"));
        assertThat(output, containsString("Transacciones:"));
    }
}
