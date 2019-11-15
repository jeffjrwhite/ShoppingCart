package com.jeffwhite.shoppingcart

case class PromotionalSalesItem(
                                 itemType: SalesItemType.Value,
                                 priceIn100s: Int,
                                 itemsOnOffer: Int = 1,
                                 itemsPaidFor: Int = 1,
                                 override var itemCount: Int = 1
                               ) extends ItemTrait
{
  override def calculateItemTotal:Int = {
    ((itemCount / itemsOnOffer) * itemsPaidFor  + itemCount % itemsOnOffer) * priceIn100s
  }

}
