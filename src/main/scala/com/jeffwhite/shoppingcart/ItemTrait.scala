package com.jeffwhite.shoppingcart

trait ItemTrait {
  val itemType: SalesItemType.Value
  val priceIn100s: Int
  var itemCount: Int

  def incrementItem: Int = {
    itemCount = itemCount + 1
    itemCount
  }

  def calculateItemTotal: Int = {
    itemCount * priceIn100s
  }

}



