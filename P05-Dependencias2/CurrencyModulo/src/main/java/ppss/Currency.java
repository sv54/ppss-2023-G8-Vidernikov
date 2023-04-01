package ppss;

public class Currency {
    private String units;
    private double amount;
    private int cents;
    private ExchangeRate converter;
    public Currency(double amount, String code) {
        this.units = code;
        this.amount = Double.valueOf(amount).intValue();
        this.cents = (int) ((amount * 100.0) % 100);
        converter=null;
    }
    public void setConverter(ExchangeRate converter) {
        this.converter = converter;
    }
    //comprobamos si el objeto es válido
    public boolean checkConverter() {
        throw new UnsupportedOperationException("Not supported yet");
    }
    public Currency toEuros() {
        if (checkConverter()) {
            if ("EUR".equals(units)) {
                return this;
            } else {
                double input = amount + cents / 100.0;
                double rate;
                try {
                    rate = converter.getRate(units, "EUR");
                    double output = input * rate;
                    return new Currency(output, "EUR");
                } catch (IOException ex) {
                    return null;
                }
            }
        } else return null;
    }
}
