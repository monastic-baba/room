package buyStocks.domain;

import java.util.HashMap;
import java.util.Map;

public class User {

  String userId;
  String name;
  int walletAmount;
  Map<String, Integer> portfolio;

  public Map<String, Integer> getPortfolio() {
    return portfolio;
  }

  public void setPortfolio(Map<String, Integer> portfolio) {
    this.portfolio = portfolio;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getWalletAmount() {
    return walletAmount;
  }

  public void setWalletAmount(int walletAmount) {
    this.walletAmount = walletAmount;
  }

  public User(String userId, String name, int walletAmount) {
    this.userId = userId;
    this.name = name;
    this.walletAmount = walletAmount;
    this.portfolio = new HashMap<>();
  }
}
