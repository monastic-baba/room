package inMemoryCache;

import inMemoryCache.EvictionPolicy.EvictionPolicy;
import inMemoryCache.EvictionPolicy.RemoveHeaviestEvictionPolicy;
import inMemoryCache.EvictionPolicy.RemoveLargestEvictionPolicy;

public class EvictionPolicyFactory {

  enum EvictionPolicies {
    REMOVE_HEAVIEST_POLICY,
    REMOVE_LARGEST_POLICY
  }

  public static EvictionPolicy create(String policyName){
    EvictionPolicies policy = EvictionPolicies.valueOf(policyName);
    return switch (policy) {
      case REMOVE_LARGEST_POLICY -> new RemoveLargestEvictionPolicy();
      case REMOVE_HEAVIEST_POLICY -> new RemoveHeaviestEvictionPolicy();
      default -> throw new IllegalArgumentException("Invalid eviction policy!");
    };
  }
}
