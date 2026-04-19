import KvStore.KvStore;
import buyStocks.BuyStocks;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

  public static void main(String[] args) {
//    KvStore kvStore = new KvStore();
//    kvStore.put("greeting", "hello", 1);
//    kvStore.put("greeting", "hi", 10);
//    kvStore.put("greeting", "namaste", 100);
//    kvStore.put("greeting", "gracias", 1000);
//    kvStore.put('a_0', ' ', 101);
//    //kvStore.listVersionTimestamps("greeting");

    BuyStocks buyStocks = new BuyStocks();
    System.out.println(buyStocks.adminAddOrUpdateStock("AAPL", 100));
    System.out.println(buyStocks.adminAddOrUpdateStock("TSLA", 250));
    System.out.println(buyStocks.signUp("u1", "Asha", 1000));
    System.out.println(buyStocks.buyStock("u1", "AAPL", 5));
    System.out.println(buyStocks.getWalletBalance("u1"));
    System.out.println(buyStocks.getPortfolio("u1"));
    System.out.println(buyStocks.sellStock("u1", "AAPL", 2));
    System.out.println(buyStocks.getWalletBalance("u1"));
  }
}
