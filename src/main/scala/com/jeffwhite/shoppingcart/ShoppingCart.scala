package com.jeffwhite.shoppingcart

import scala.util.{Failure, Success, Try}

class ShoppingCart(
                    var items: Map[SalesItemType.Value, SalesItem] = Map()
                  ) {
  def addSalesItem(item: SalesItem, count: Int = 1): Unit = {

    items match {
      // If one or more items already in cart - increment count
      case map: Map[SalesItemType.Value, SalesItem] if map.contains(item.itemType) =>
        val newitem: SalesItem = map(item.itemType).incrementItem
        items = map.filter(x => x._1 != item.itemType) + (item.itemType -> newitem)
      // Else add item to cart
      case map: Map[SalesItemType.Value, SalesItem] =>
        getSalesItemFromInventory(item.itemType, count) match {
          case Success(item: SalesItem) =>
            items = items + (item.itemType -> item)
          case Failure(ex) =>
            println(ex.getLocalizedMessage)
        }
    }
  }

  def getSalesItemFromInventory(itemType: SalesItemType.Value, count: Int = 1): Try[SalesItem] = {
    ItemInventory.items match {
      case map: Map[SalesItemType.Value, SalesItem] if map.contains(itemType) =>
        Success(map(itemType))
      case map: Map[SalesItemType.Value, SalesItem] =>
        Failure(new RuntimeException(s"Item [$itemType] not found in Inventory"))
    }
  }
}
