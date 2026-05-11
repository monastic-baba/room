import inMemoryCache.InMemoryCache;
import inMemoryCache.CacheBuilder;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

  public static void main(String[] args) {
//    KvStore kvStore = new KvStore();
//    kvStore.put("greeting", "hello", 1);
//    kvStore.put("greeting", "hi", 10);
//    kvStore.put("greeting", "namaste", 100);
//    kvStore.put("greeting", "gracias", 1000);
//    kvStore.put('a_0', ' ', 101);
//    //kvStore.listVersionTimestamps("greeting");

//    BuyStocks buyStocks = new BuyStocks();
//    System.out.println(buyStocks.adminAddOrUpdateStock("AAPL", 100));
//    System.out.println(buyStocks.adminAddOrUpdateStock("TSLA", 250));
//    System.out.println(buyStocks.signUp("u1", "Asha", 1000));
//    System.out.println(buyStocks.buyStock("u1", "AAPL", 5));
//    System.out.println(buyStocks.getWalletBalance("u1"));
//    System.out.println(buyStocks.getPortfolio("u1"));
//    System.out.println(buyStocks.sellStock("u1", "AAPL", 2));
//    System.out.println(buyStocks.getWalletBalance("u1"));

//    Map<String, List<Integer>> testmap = new HashMap<>();
//    List<Integer> testarray = new ArrayList<>();
//    testarray.add(5);
//    testarray.add(15);
//    testarray.add(25);
//    testarray.add(55);
//    testmap.put("k1", testarray);
//    List<Integer> maplist = testmap.get("k1");
//    maplist.add(100);
//    System.out.println(testmap.get("k1").size());
//    testmap.put("k1", maplist);
//    System.out.println(testmap.get("k1").size());

//    List<String> whs = new ArrayList<>();
//    whs.add("w1");
//    whs.add("w2");
//    WarehouseStoreInventoryUpdate inventoryUpdate = new WarehouseStoreInventoryUpdate(whs);
//    inventoryUpdate.registerStore("s1", "w1");

    // capacity = 2, policy = REMOVE-LARGEST-POLICY
    InMemoryCache cache =  new CacheBuilder(3, "REMOVE_HEAVIEST_POLICY").build();

    System.out.println(cache.nextEvictionKey());

    cache.put("az",   "v1");      // cache: { a }
    cache.put("by",  "v2");      // cache: { a, bb }
    cache.put("cx", "v3");

    System.out.println(cache.nextEvictionKey());
    // among {a(len=1), bb(len=2)} => "bb" is largest length


    // step 1 (insert first): cache becomes { a, bb, ccc }  (size=3)
    // step 2 (size > capacity): evict largest-length key among {a, bb, ccc} => "ccc"
    // final cache: { a, bb }

//    System.out.println(cache.get("ccc")); // -> ""
//    System.out.println(cache.get("bb")); //  -> "v2"


  }
}
