package com.jeffwhite.shoppingcart

import scala.util.{Failure, Success, Try}

class ShoppingCart(
                    var items: Map[SalesItemType.Value, SalesItem] = Map()
                  )
{
  def addSalesItem(itemType: SalesItemType.Value, count: Int = 1): Unit = {

    items match {
      // If one or more items already in cart - increment count
      case map: Map[SalesItemType.Value, SalesItem] if map.contains(itemType) =>
        val newitem: SalesItem = map(itemType).incrementItem
        items = map.filter(x => x._1 != itemType) + (itemType -> newitem)
      // Else add item to cart
      case map: Map[SalesItemType.Value, SalesItem] =>
        getSalesItemFromInventory(itemType, count) match {
          case Success(item: SalesItem) =>
            items = items + (item.itemType -> item)
          case Failure(ex) =>
            println(ex.getLocalizedMessage)
        }
    }
  }

  private def getSalesItemFromInventory(itemType: SalesItemType.Value, count: Int = 1): Try[SalesItem] = {
    ItemInventory.items match {
      case map: Map[SalesItemType.Value, SalesItem] if map.contains(itemType) =>
        Success(map(itemType))
      case map: Map[SalesItemType.Value, SalesItem] =>
        Failure(new RuntimeException(s"Item [$itemType] not found in Inventory"))
    }
  }

  def totalise():Int = {

    items.map(x => x._2.itemCount * x._2.priceIn100s).foldLeft(0)(_ + _)

  }

  def totalFormattedInPounds(): String = {
    val pounds = totalise()/100
    val pence = totalise()%100
    f"$pounds.$pence%02.0f"
  }

  def itemCount: Int = items.map(x => x._2.itemCount).foldLeft(0)(_ + _)
}
