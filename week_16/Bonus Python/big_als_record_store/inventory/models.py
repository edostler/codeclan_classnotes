from django.db import models

class Artist(models.Model):
    name = models.CharField(max_length=255)

    def __str__(self):
        return self.name


class Album(models.Model):
    title = models.CharField(max_length=255)
    artist = models.ForeignKey(Artist, on_delete=models.CASCADE)
    stock_level = models.IntegerField()

    def __str__(self):
        return self.title
