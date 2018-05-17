import unittest
from Cake import Cake
from CakeShop import CakeShop

class TestCakeShop(unittest.TestCase):
    def setUp(self):
        ingredients = ["Sugar", "Flour", "Chocolate"]
        self.cake1 = Cake("Brownie", ingredients, 4)

        ingredients2 = ["Sugar", "Flour", "Lemon"]
        self.cake2 = Cake("Lemon Drizzle", ingredients2, 5)

        self.cake_shop = CakeShop("Jake The Snake's Baked Cakes", [self.cake1, self.cake2])

    def test_cake_shop_name(self):
        self.assertEqual(self.cake_shop.name, "Jake The Snake's Baked Cakes")

    def test_average_cake_rating(self):
        self.assertEqual(self.cake_shop.average_cake_rating(), 4.5)

if __name__ == "__main__":
    unittest.main()
