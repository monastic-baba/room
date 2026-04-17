package pizzaPricing;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SimplePizzaPricing implements PizzaPricing{

  HashMap<String, Integer> toppingPrice = new HashMap<>();
  HashMap<String, Integer> toppingServingsCount = new HashMap<>();
  private int basePrice;
  private int taxPercentage;
  private String size;

  SimplePizzaPricing(int basePrice, int taxPercentage, String size){
    this.basePrice = basePrice;
    this.taxPercentage = taxPercentage;
    this.size = size;
    // init map
    toppingPrice.put("cheeseburst", 100);
    toppingPrice.put("corn", 50);
    toppingPrice.put("onion", 30);
    toppingPrice.put("capsicum", 50);
    toppingPrice.put("pineapple", 60);
    toppingPrice.put("mushroom", 40);
  }

  private boolean toppingValidator(String topping){
    if(Objects.equals(topping, "mushroom")
        && toppingServingsCount.getOrDefault("cheeseburst",0) > 0){
      return false;
    }
    else
      return !Objects.equals(topping, "cheeseburst")
          || toppingServingsCount.getOrDefault("mushroom", 0) <= 0;
  }


  @Override
  public boolean addTopping(String topping, int servingsCount) {
    if(!toppingValidator(topping)){
      return false;
    }
    return true;

  }

  @Override
  public int getFinalPrice() {
    return 0;
  }
}
