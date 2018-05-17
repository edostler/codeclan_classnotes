class CakeShop:
    def __init__(self, name, cakes):
        self.name = name
        self.cakes = cakes

    def average_cake_rating(self):
        all_ratings = [cake.rating for cake in self.cakes]
        ratings_total = float(sum(all_ratings))
        return ratings_total / len(self.cakes)
