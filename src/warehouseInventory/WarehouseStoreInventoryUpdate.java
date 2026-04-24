package warehouseInventory;

import common.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class WarehouseStoreInventoryUpdate {

  List<String> warehouseIds; // warehouseIds
  List<String> storeIds; // storeIds

  Map<String, String> storeToWarehouseMap;
  Map<String, Set<String>> warehouseToStoresMap;
  Map<String, List<Pair<String, Long>>> warehouseInventory;

  public WarehouseStoreInventoryUpdate(List<String> warehouseIds) {
    this.warehouseIds = warehouseIds;
    this.storeIds = new ArrayList<>();
    this.storeToWarehouseMap = new HashMap<>();
    this.warehouseToStoresMap = new HashMap<>();
    this.warehouseInventory = new HashMap<>();
    for(String whid: warehouseIds){
      this.warehouseInventory.put(whid, new ArrayList<>());
      this.warehouseToStoresMap.put(whid, new HashSet<>());
    }

  }

  public boolean registerStore(String storeId, String warehouseId){
    if(!warehouseIds.contains(warehouseId) || storeIds.contains(storeId)){
      return false;
    }
    storeIds.add(storeId);
    storeToWarehouseMap.put(storeId, warehouseId);
    warehouseToStoresMap.get(warehouseId).add(storeId);
    return true;
  }

  public boolean addInventory(String warehouseId, String productId, int quantity){
    if(!warehouseIds.contains(warehouseId) || quantity <= 0){
      return false;
    }
    List<Pair<String, Long>> inventoryList = warehouseInventory.get(warehouseId);
    boolean quantityUpdated = false;
    for(Pair<String, Long> inventory: inventoryList){
      if(Objects.equals(inventory.first, productId)){
        inventory.second += quantity;
        quantityUpdated = true;
      }
    }
    if(!quantityUpdated){
      inventoryList.add(new Pair<>(productId, (long) quantity));
    }
    warehouseInventory.put(warehouseId, inventoryList); // IS THIS OPTIONAL??
    return true;
  }

  public List<String> getWarehouseInventory(String warehouseId){
    List<String> warehouseInventories = new ArrayList<>();
    if(!warehouseIds.contains(warehouseId) || warehouseInventory.get(warehouseId).isEmpty()){
      return warehouseInventories;
    }
    for(Pair<String, Long> p: warehouseInventory.get(warehouseId)){
      warehouseInventories.add("productId="+p.first+", quantity="+p.second);
    }
    Collections.sort(warehouseInventories);
    return warehouseInventories;
  }

  public List<String> getStoreInventory(String storeId){
    List<String> storeInventories = new ArrayList<>();
    if(!storeIds.contains(storeId)){
      return storeInventories;
    }
    String warehouseId = storeToWarehouseMap.get(storeId);
    if(!warehouseIds.contains(warehouseId) || warehouseInventory.get(warehouseId).isEmpty()){
      return storeInventories;
    }
    for(Pair<String, Long> p: warehouseInventory.get(warehouseId)){
      storeInventories.add("productId="+p.first+", quantity="+p.second);
    }
    Collections.sort(storeInventories);
    return storeInventories;
  }

  public List<String> getStoresForWarehouse(String warehouseId){
    List<String> stores = new ArrayList<>();
    if(!warehouseIds.contains(warehouseId)){
      return stores;
    }
    stores.addAll(warehouseToStoresMap.get(warehouseId));
    Collections.sort(stores);
    return stores;
  }



}
