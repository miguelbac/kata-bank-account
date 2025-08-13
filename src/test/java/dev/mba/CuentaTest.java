package dev.mba;

import dev.mba.models.Cuenta;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CuentaTest {

    private Cuenta cuenta;

    @BeforeEach
    public void setUp() {
        cuenta = new Cuenta(10000f, 12f);
    }

    @Test
    public void testConsignarValido() {
        boolean resultado = cuenta.consignar(2000f);
        assertThat(resultado, is(true));
        assertThat((double) cuenta.getSaldo(), is(closeTo(12000.0, 0.001)));
    }

    @Test
    public void testConsignarInvalido() {
        boolean resultado = cuenta.consignar(-500f);
        assertThat(resultado, is(false));
        assertThat((double) cuenta.getSaldo(), is(closeTo(10000.0, 0.001)));
    }

    @Test
    public void testRetirarValido() {
        boolean resultado = cuenta.retirar(3000f);
        assertThat(resultado, is(true));
        assertThat((double) cuenta.getSaldo(), is(closeTo(7000.0, 0.001)));
    }

    @Test
    public void testRetirarInvalidoMayorSaldo() {
        boolean resultado = cuenta.retirar(15000f);
        assertThat(resultado, is(false));
        assertThat((double) cuenta.getSaldo(), is(closeTo(10000.0, 0.001)));
    }

    @Test
    public void testRetirarInvalidoNegativo() {
        boolean resultado = cuenta.retirar(-100f);
        assertThat(resultado, is(false));
        assertThat((double) cuenta.getSaldo(), is(closeTo(10000.0, 0.001)));
    }

    @Test
    public void testCalcularInteresMensual() {
        float interes = cuenta.calcularInteresMensual();
        assertThat((double) interes, is(closeTo(100.0, 0.001)));
        assertThat((double) cuenta.getSaldo(), is(closeTo(10100.0, 0.001)));
    }

    @Test
    public void testExtractoMensual() {
        cuenta.setComisionMensual(50f);
        cuenta.extractoMensual();
        assertThat((double) cuenta.getSaldo(), is(closeTo(10049.5, 0.001)));
    }
}