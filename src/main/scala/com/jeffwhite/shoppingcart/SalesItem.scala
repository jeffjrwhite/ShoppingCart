package com.jeffwhite.shoppingcart

case class SalesItem (
      itemType: SalesItemType.Value,
      priceIn100s: Int,
      itemCount: Int = 1
)
{
  def incrementItem: SalesItem = this.copy(itemCount = itemCount + 1)
}



