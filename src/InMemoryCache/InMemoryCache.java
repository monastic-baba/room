package InMemoryCache;

import InMemoryCache.EvictionPolicy.EvictionPolicy;
import java.util.HashMap;
import java.util.Map;

public class InMemoryCache {

  int capacity;
  Map<String, String> cache;
  EvictionPolicy evictionPolicy;

  public InMemoryCache(int capacity, EvictionPolicy policy){
    this.cache = new HashMap<>();
    this.capacity = capacity;
    this.evictionPolicy = policy;
  }

  public String get(String key) {
    return this.cache.get(key) == null ? "" : this.cache.get(key);
  }

  public void put(String key, String value) {
    this.cache.put(key, value);
    if(this.cache.size() > capacity){
      this.cache.remove(
          this.evictionPolicy.getNextEvictionKey(this.cache.keySet())
      );
    }
  }

  public String nextEvictionKey() {
    return this.evictionPolicy.getNextEvictionKey(this.cache.keySet());
  }

  public boolean remove(String key) {
    if(!this.cache.containsKey(key)){
      return false;
    }
    this.cache.remove(key);
    return true;
  }

}
