import com.jeffwhite.shoppingcart.{ItemInventory, SalesItem, SalesItemType, ShoppingCart}
import org.scalatest.{FlatSpec, Matchers, _}

class ShoppingCartTest extends FlatSpec with Matchers with BeforeAndAfterAll with BeforeAndAfterEach {

    override def beforeAll(): Unit = {
      println("Setup:beforeAll Create Fixtures")
      // Create test inventory
      ItemInventory.addItemToInventory(SalesItem(SalesItemType.APPLE, 60))
      ItemInventory.addItemToInventory(SalesItem(SalesItemType.ORANGE, 25))
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

    "A Shopping Cart" should "be created" in {

      println("ShoppingCart test running...")

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

  }