package ppss;

import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;


public class CurrencyTest {

    IMocksControl ctrl;
    Currency c;

    ExchangeRate e;

    Currency esperado;

    @BeforeEach
    public void setup(){
        ctrl = createStrictControl();
        c = partialMockBuilder(Currency.class).withConstructor(3.75, "EUR").addMockedMethod("checkConverter").mock(ctrl);
        e = ctrl.mock(ExchangeRate.class);
    }

    @Test
    public void toEurosC1(){
        expect(c.checkConverter()).andReturn(true);
        assertDoesNotThrow(() -> expect(e.getRate("USD", "EUR")).andReturn(1.5));

        ctrl.replay();
        c.setConverter(e);
        Currency resul = c.toEuros();

        assertEquals(new Currency(3.75, "EUR"), resul);

        ctrl.verify();


    }



}
