from django.shortcuts import render
from inventory.models import *

def index(request):
    all_artists = Artist.objects.all()
    context = {
        "all_artists": all_artists
    }
    return render(request, "index.html", context)
