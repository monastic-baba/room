package InMemoryCache.EvictionPolicy;
import java.util.Set;

public interface EvictionPolicy {

  String getNextEvictionKey(Set<String> cacheKeys);

}
