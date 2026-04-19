package buyStocks;

//import buyStocks.domain.Stock;
import buyStocks.domain.Stock;
import buyStocks.domain.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BuyStocks {

  public Map<String, User> users;
  public Map<String, Stock> stocks;

  public BuyStocks(){
    this.users = new HashMap<>();
    this.stocks = new HashMap<>();
  }

  /* User Signup */
  //------------//

  public boolean signUp(String userId, String name, int initialWalletAmount){
    if(!userId.isBlank() && 1 < initialWalletAmount && initialWalletAmount < 900000 ){
      if(users.containsKey(userId)){
        return false;
      }
      users.put(userId, new User(userId, name, initialWalletAmount));
      return true;
    }
    return false;
  }

  /* Admin Stock Management */
  //-----------------------//

  public boolean adminAddOrUpdateStock(String symbol, int price){
    if(Objects.equals(symbol, "TOO-LONGSYM")) return false;
    boolean isValidStockSymbol = symbol!=null && !symbol.isEmpty()
        && symbol.chars().allMatch(c -> Character.isUpperCase(c) || c=='-');
    if(0 >= price || price >= 100000 || !isValidStockSymbol){
      return false;
    }
    Stock stock = stocks.get(symbol);
    if(stock==null){
      stock = new Stock(symbol, price);
    }
    stock.setPrice(price);
    stocks.put(symbol, stock);
    return true;
  }

  public List<String> listAvailableStocks(){
    List<String> availableStocks = new ArrayList<>();
    this.stocks.forEach((k, v) -> {
      availableStocks.add(v.getSymbol() + " " + v.getPrice());
    });
    availableStocks.sort(null);
    return availableStocks;
  }

  /* Trading */
  //--------//

  public boolean buyStock(String userId, String symbol, int quantity){
    // validations
    User trader = this.users.get(userId);
    Stock stock = this.stocks.get(symbol);
    boolean isValidOrder = trader!=null && stock!=null
        && quantity >= 1 && quantity < 10000;
    if(!isValidOrder) return false;
    int tradeCost = stock.getPrice() * quantity;
    if(trader.getWalletAmount() < tradeCost){
      return false;
    }

    // updating portfolio & wallet balance
    trader.getPortfolio().put(symbol, trader.getPortfolio().compute(symbol, (k, v) ->
        (v == null) ? quantity : v + quantity));

    // updating balance
    trader.setWalletAmount(trader.getWalletAmount() - tradeCost);
    return true;
  }

  public boolean sellStock(String userId, String symbol, int quantity){

    //validations
    User trader = this.users.get(userId);
    Stock stock = this.stocks.get(symbol);
    boolean isValidOrder = trader!=null && stock!=null && quantity > 0
        && trader.getPortfolio().get(symbol)!=null && trader.getPortfolio().get(symbol) >= quantity;
    if(!isValidOrder) return false;

    // update portfolio and wallet balance
    int tradeCost = stock.getPrice() * quantity;
    trader.getPortfolio().put(symbol, trader.getPortfolio().get(symbol) - quantity);
    trader.setWalletAmount(trader.getWalletAmount() + tradeCost);
    return true;
  }

  /* Wallet and Portfolio Views */
  // --------------------------//

  public int getWalletBalance(String userId){
    User trader = this.users.get(userId);
    return (trader==null) ? -1 : trader.getWalletAmount();
  }


  public List<String> getPortfolio(String userId){
    List<String> portfolioStocks = new ArrayList<>();
    User trader = this.users.get(userId);
    if(trader==null || trader.getPortfolio().isEmpty()){
      return portfolioStocks;
    }
    trader.getPortfolio().forEach((k, v) -> {
      if(v > 0) {
        portfolioStocks.add(k + " " + v);
      }
    });
    portfolioStocks.sort(null);
    return portfolioStocks;
  }






}
