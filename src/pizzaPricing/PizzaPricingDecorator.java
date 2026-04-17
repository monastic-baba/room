package pizzaPricing;

public abstract class PizzaPricingDecorator implements PizzaPricing {

  protected PizzaPricing pizzaPricing;

  public PizzaPricingDecorator(PizzaPricing pizzaPricing){
    this.pizzaPricing = pizzaPricing;
  }
}
