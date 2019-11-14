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
    }

    override def afterEach {
      println("Teardown:afterEach")
    }

    "A Shopping Cart" should "be created" in {

      println("ShoppingCart test running...")
    }

  }