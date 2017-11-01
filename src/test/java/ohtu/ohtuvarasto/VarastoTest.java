package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenTilavuusKonstruktorilleNollataan() {
        Varasto v = new Varasto(-1);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenAlkuSaldoKonstruktorilleNollataan() {
        Varasto v = new Varasto(-1, -1);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkuSaldoSuurempiKuinTilavuusKonstruktorille() {
        double saldo = 2;
        double tilavuus = 1;
        Varasto v = new Varasto(tilavuus, saldo);
        assertEquals(tilavuus, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkuSaldoPienempiKuinTilavuusKonstruktorille() {
        Varasto v = new Varasto(2, 1);
        assertEquals(1, v.getSaldo(), vertailuTarkkuus);
        assertEquals(2, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisäysEiMuutaSaldoa() {
        double vanhaSaldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-1);
        assertEquals(vanhaSaldo, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tilavuudenYlittäväLisäysTasapäistetään() {
        varasto.lisaaVarastoon(20);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttoEiMuutaSaldoa() {
        double vanhaSaldo = varasto.getSaldo();
        varasto.otaVarastosta(-1);
        assertEquals(vanhaSaldo, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaSuurempiMääräKuinSaldo() {
        double vanhaSaldo = varasto.getSaldo();
        double otto = varasto.otaVarastosta(200);
        assertEquals(vanhaSaldo, otto, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tarkistaToString() {
        assertEquals("saldo = 0, vielä tilaa 10.0", varasto.toString());
    }

}