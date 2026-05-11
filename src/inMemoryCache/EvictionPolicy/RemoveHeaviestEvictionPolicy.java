package inMemoryCache.EvictionPolicy;

import java.util.Comparator;
import java.util.Set;

public class RemoveHeaviestEvictionPolicy implements EvictionPolicy {


  @Override
  public String getNextEvictionKey(Set<String> cacheKeys) {
    return cacheKeys.stream().max(
            Comparator.comparingInt(RemoveHeaviestEvictionPolicy::computeWeight)
                .thenComparing(Comparator.reverseOrder())
        )
        .orElse("");
  }

  private static int computeWeight(String s){
    int wt = 0;
    for (int i = 0; i < s.length(); i++) {
      wt += s.charAt(i) - 'a' + 1;
    }
    return wt;
  }
}
