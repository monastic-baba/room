package buyStocks.domain;

public class Stock {
  String symbol;
  int price;

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Stock(String symbol, int price) {
    this.symbol = symbol;
    this.price = price;
  }
}
