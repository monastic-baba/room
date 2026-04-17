package pizzaPricing;

public interface PizzaPricing {

  boolean addTopping(String topping, int servingsCount);
  int getFinalPrice();

}
