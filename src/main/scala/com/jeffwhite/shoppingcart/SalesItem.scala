package com.jeffwhite.shoppingcart

case class SalesItem (
                       itemType: SalesItemType.Value,
                       priceIn100s: Int,
                       itemsOnOffer: Int = 1,
                       itemsPaidFor: Int = 1,
                       override var itemCount: Int = 1
                     ) extends ItemTrait




