package InMemoryCache;

import InMemoryCache.EvictionPolicy.EvictionPolicy;
import java.util.Map;

public class CacheBuilder {

  Integer capacity;
  Map<String ,String> cache;
  EvictionPolicy evictionPolicy;

  public CacheBuilder(int capacity, String policyName){
    if(capacity >=0){
      this.capacity = capacity;
    } else {
      throw new IllegalArgumentException("Invalid capacity!");
    }
    this.evictionPolicy = EvictionPolicyFactory.create(policyName);
  }

  public InMemoryCache build(){
    return new InMemoryCache(
        this.capacity,
        this.evictionPolicy
    );
  }



}
