package InMemoryCache.EvictionPolicy;

import java.util.Comparator;
import java.util.Set;

public class RemoveLargestEvictionPolicy implements EvictionPolicy {

  @Override
  public String getNextEvictionKey(Set<String> cacheKeys) {
    return cacheKeys.stream().max(Comparator.comparingInt(String::length)
        .thenComparing(Comparator.reverseOrder())).orElse(null);
  }
}
