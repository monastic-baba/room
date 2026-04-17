package KvStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KvStore {

  Map<String, List<VersionedValue> > store;

  public KvStore(){
    this.store = new HashMap<>();
  }

  private static class VersionedValue {
    long timestamp;
    String value;

    public VersionedValue(Long timestamp, String val){
      this.timestamp = timestamp;
      this.value = val;
    }
  }

  /* Stores the given value for key at the store's current timestamp. Each successful call creates a new version for that key. First put is version 0.
  Returns true if the write is stored successfully.
      Returns false if key or value is null or blank.
  currentTimestamp will always be a positive integer and monotonically increasing .
  If there are multiple writes for the same key at the same timestamp then they will be versioned in the order in which they occurred.
  */
  public boolean put(String key, String value, long currentTimestamp){
    if(key == null || value == null || key.isBlank() || value.isBlank()){
      return false;
    }
    List<VersionedValue> val = store.get(key);
    if(val == null){
      val = new ArrayList<>();
    }
    val.add(new VersionedValue(currentTimestamp, value));
    this.store.put(key, val);
    return true;
  }

  /* Returns the latest value stored for key.
 Returns the latest stored value if present.
 Returns an empty string "" if the key has no stored value.
  */
  public String get(String key){
    List<VersionedValue> val = this.store.get(key);
    if(val==null){
      return "";
    }
    return val.get(val.size() - 1).value;
  }

  /* Returns the value for key as of the provided timestamp.
  If there exists at least one write for the key with writeTimestamp ≤ timestamp, return the value from the write with the maximum such writeTimestamp.
  Returns an empty string "" if:
  the key has no stored value, or
  timestamp is earlier than the first write for that key.
   */
  public String getWithTimestamp(String key, long timestamp){
    List<VersionedValue> val = this.store.get(key);
    if(val==null){
      return "";
    }
    // for comparator
    VersionedValue targetObj = new VersionedValue(timestamp, "noop");
    int idx = Collections.binarySearch(val, targetObj,
        Comparator.comparingLong(o -> o.timestamp)
    );
    if (idx < 0) {
      idx = -(idx + 1) - 1; // Move back one from insertion point
    }
    return (idx >= 0) ? val.get(idx).value : "";
  }

  /* Returns the value for key at the given version (0-based).
  Returns the stored value for that exact version if it exists.
  Returns an empty string "" if:
  the key has no stored value, or
  version is out of range (e.g., version < 0 or version > currentVersion).
   */
  public String getByVersion(String key, int version){
    List<VersionedValue> val = this.store.get(key);
    if(val==null){
      return "";
    }
    int numberOfVersions = val.size();
    return (version < 0 || version >= numberOfVersions) ? "" : val.get(version).value;
  }

  /* Returns all versions and their timestamps for the stored versions of key in increasing order.
  Each row is formatted as "version,timestamp" e.g. "2,28738734"
  Returns an empty list if the key has no stored value.
   */
  public List<String> listVersionTimestamps(String key){
    List<String> result = new ArrayList<>();
    List<VersionedValue> val = this.store.get(key);
    if(val==null){
      return result;
    }
    for (int i = 0; i < val.size(); i++) {
      result.add( i + "," + val.get(i).timestamp);
      System.out.println(result.get(result.size() - 1));
    }
    return result;
  }


}
