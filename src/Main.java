import KvStore.KvStore;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

  public static void main(String[] args) {
    KvStore kvStore = new KvStore();
    kvStore.put("greeting", "hello", 1);
    kvStore.put("greeting", "hi", 10);
    kvStore.put("greeting", "namaste", 100);
    kvStore.put("greeting", "gracias", 1000);
    kvStore.put('a_0', ' ', 101);
    //kvStore.listVersionTimestamps("greeting");
  }
}
