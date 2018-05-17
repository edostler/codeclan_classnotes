import unittest
from Cake import Cake

class TestCake(unittest.TestCase):
    def setUp(self):
        ingredients = ["Sugar", "Flour", "Chocolate"]
        self.cake = Cake("Brownie", ingredients, 5)

    def test_cake_has_name(self):
        self.assertEquals("Brownie", self.cake.name)

    def test_cake_has_ingredients(self):
        self.assertEquals(3, len(self.cake.ingredients))

    def test_cake_has_rating(self):
        self.assertEqual(5, self.cake.rating)

    def test_cake_can_bake(self):
        self.assertEqual("The cake is baking!", self.cake.bake())

if __name__ == "__main__":
    unittest.main()
