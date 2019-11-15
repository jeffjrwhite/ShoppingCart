import com.jeffwhite.shoppingcart.{ItemInventory, PromotionalSalesItem, SalesItem, SalesItemType, ShoppingCart}
import org.scalatest.{FlatSpec, Matchers, _}

class ShoppingCartTest extends FlatSpec with Matchers with BeforeAndAfterAll with BeforeAndAfterEach {

    override def beforeAll(): Unit = {
      println("Setup:beforeAll Create Fixtures")
    }

    override def afterAll(): Unit = {
      println("Setup:afterAll")
    }

    override def beforeEach {
      println("Setup:beforeEach")
      println(s"ItemInventory ${ItemInventory.items}")
    }

    override def afterEach {
      println("Teardown:afterEach")
    }

    "A basic Shopping Cart" should "be created with items and totalised" in {

      println("Basic ShoppingCart test running...")
      // Create test inventory
      ItemInventory.addItemToInventory(SalesItem(SalesItemType.APPLE, 60))
      ItemInventory.addItemToInventory(SalesItem(SalesItemType.ORANGE, 25))

      println(s"ItemInventory ${ItemInventory.items}")
      // Create a cart
      val cart = new ShoppingCart
      // Add an apple
      cart.addSalesItem(SalesItemType.APPLE)
      // Add an orange
      cart.addSalesItem(SalesItemType.ORANGE)
      // Add the rest of the products
      cart.addSalesItem(SalesItemType.APPLE)
      cart.addSalesItem(SalesItemType.APPLE)

      // Check and print result
      println(s"Cart ${cart.items}")
      cart.itemCount should be (4)
      println(s"Cart item count ${cart.itemCount}")
      cart.totalise should be (205)
      println(s"Cart total (pence) ${cart.totalise}")
      cart.totalFormattedInPounds should be ("2.05")
      println(f"Cart total (pounds) ${cart.totalFormattedInPounds}")
    }

  "Some promotional offers" should "should be totalised correctly" in {

    val apple = PromotionalSalesItem(SalesItemType.APPLE, 60, 2, 1, 5)
    val twoFor1on5Apples = apple.calculateItemTotal
    twoFor1on5Apples should be (180)
    println(s"Promotion (2 for 1) total for 5 apples (pence) $twoFor1on5Apples")

    val orange = PromotionalSalesItem(SalesItemType.ORANGE, 25, 3, 2, 10)
    val threeFor2on10Orange = orange.calculateItemTotal
    threeFor2on10Orange should be (175)
    println(s"Promotion (3 for 2) total for 10 orange (pence) $threeFor2on10Orange")
  }

  "A Promotional Shopping Cart No. 1" should "be created with items and totalised" in {

    println("Promotional ShoppingCart test running...")
    // Create test inventory
    ItemInventory.addItemToInventory(PromotionalSalesItem(SalesItemType.APPLE, 60, 2, 1))
    ItemInventory.addItemToInventory(PromotionalSalesItem(SalesItemType.ORANGE, 25, 3, 2))

    println(s"ItemInventory ${ItemInventory.items}")
    // Create a cart
    val cart = new ShoppingCart
    // Add an apple
    cart.addSalesItem(SalesItemType.APPLE)
    // Add an orange
    cart.addSalesItem(SalesItemType.ORANGE)
    // Add the rest of the products
    cart.addSalesItem(SalesItemType.APPLE)
    cart.addSalesItem(SalesItemType.APPLE)

    // 3 Apples at 60p each for 2 for 1 = 120p
    // 1 Orange = 25
    // Total = 145p

    // Check and print result
    println(s"Cart ${cart.items}")
    cart.itemCount should be (4)
    println(s"Cart item count ${cart.itemCount}")
    cart.totalise should be (145)
    println(s"Promotional cart total (pence) ${cart.totalise}")
    cart.totalFormattedInPounds should be ("1.45")
    println(f"Promotional cart total (pounds) ${cart.totalFormattedInPounds}")

  }

  "A Promotional Shopping Cart No. 2" should "be created with items and totalised" in {

    println("Promotional ShoppingCart test running...")
    // Create test inventory
    ItemInventory.addItemToInventory(PromotionalSalesItem(SalesItemType.APPLE, 60, 2, 1))
    ItemInventory.addItemToInventory(PromotionalSalesItem(SalesItemType.ORANGE, 25, 3, 2))

    println(s"ItemInventory ${ItemInventory.items}")
    // Create a cart
    val cart = new ShoppingCart
    // Add an apple
    cart.addSalesItem(SalesItemType.APPLE)
    // Add an orange
    cart.addSalesItem(SalesItemType.ORANGE)
    // Add the rest of the products
    cart.addSalesItem(SalesItemType.APPLE)
    cart.addSalesItem(SalesItemType.APPLE)
    // Add another apple
    cart.addSalesItem(SalesItemType.APPLE)
    // Add another 2 oranges
    cart.addSalesItem(SalesItemType.ORANGE)
    cart.addSalesItem(SalesItemType.ORANGE)

    // 4 Apples at 60p each for 2 for 1 = 120p
    // 2 Oranges at 25 each for 3 for 2 =  50p
    // Total = 170p

    // Check and print result
    println(s"Cart ${cart.items}")
    cart.itemCount should be (7)
    println(s"Cart item count ${cart.itemCount}")
    cart.totalise should be (170)
    println(s"Promotional cart total (pence) ${cart.totalise}")
    cart.totalFormattedInPounds should be ("1.70")
    println(f"Promotional cart total (pounds) ${cart.totalFormattedInPounds}")

  }

}