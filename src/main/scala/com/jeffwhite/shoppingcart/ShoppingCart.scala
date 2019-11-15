package com.jeffwhite.shoppingcart

import scala.util.{Failure, Success, Try}

class ShoppingCart(
                    val itemInventory: ItemInventory,
                    var items: Map[SalesItemType.Value, ItemTrait] = Map()
                  )
{
  def addSalesItem(itemType: SalesItemType.Value, count: Int = 1): Unit = {

    items match {
      // If one or more promotional items already in cart - increment count
      case map: Map[SalesItemType.Value, ItemTrait] if map.contains(itemType) =>
        map(itemType).incrementItem
      // Else add regular item to cart
      case map: Map[SalesItemType.Value, ItemTrait] =>
        getSalesItemFromInventory(itemInventory, itemType, count) match {
          case Success(item: ItemTrait) =>
            items = items + (item.itemType -> item)
          case Failure(ex) =>
            println(ex.getLocalizedMessage)
        }
    }
  }

  private def getSalesItemFromInventory(itemInventory: ItemInventory, itemType: SalesItemType.Value, count: Int = 1): Try[ItemTrait] = {
    itemInventory.items match {
      case map: Map[SalesItemType.Value, ItemTrait] if map.contains(itemType) =>
        Success(map(itemType))
      case map: Map[SalesItemType.Value, ItemTrait] =>
        Failure(new RuntimeException(s"Item [$itemType] not found in Inventory"))
    }
  }

  def totalise():Int = {

    items.map(x => x._2.calculateItemTotal).foldLeft(0)(_ + _)

  }

  def totalFormattedInPounds(): String = {
    val pounds = totalise()/100
    val pence = totalise()%100
    f"$pounds.$pence%02.0f"
  }

  def itemCount: Int = items.map(x => x._2.itemCount).foldLeft(0)(_ + _)

}
