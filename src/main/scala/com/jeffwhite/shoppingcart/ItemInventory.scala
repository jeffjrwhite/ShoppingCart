package com.jeffwhite.shoppingcart

object ItemInventory
{
  var items: Map[SalesItemType.Value, SalesItem] = Map()

  def addItemToInventory(item: SalesItem): Unit = {

    items match {
      // If item already in inventory do nothing
      case map: Map[SalesItemType.Value, SalesItem] if map.contains(item.itemType) =>
        println(s"Item [${item.itemType}] already in Inventory")
      // Else add item to cart
      case map: Map[SalesItemType.Value, SalesItem] =>
        items = items + (item.itemType -> item)
        println(s"Item [${item.itemType}] added to Inventory")
    }

  }
}
