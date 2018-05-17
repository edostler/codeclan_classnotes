const createUl = function() {
  const ul = document.createElement('ul')
  ul.classList.add('cat')
  return ul
}

const createLi = function(label, text) {
  const li = document.createElement('li')
  li.innerText = `${label}: ${text}`
  return li
}

const createImage = function(url) {
  const li = document.createElement('li')
  const img = document.createElement('img')
  img.src = url
  li.appendChild(img)
  return li
}

const appendItems = function(catSection, listElement, name, favFood, img){
  listElement.appendChild(name);
  listElement.appendChild(favFood);
  listElement.appendChild(img);
  catSection.appendChild(listElement);
}

const addCat = function(name, food, url){
  let ul = createUl();
  let nameli = createLi("Name: ", name);
  let foodli = createLi("Fav Food: ", food);
  let imgli = createImage(url)
  const section = document.querySelector('#cats')
  appendItems(section, ul, nameli, foodli, imgli)
}

const cats = [
  {
    name: "Boba",
    favFood: "sock fluff",
    img:"http://66.media.tumblr.com/d1f01bbe0150fda0c40d2151c5eaeac8/tumblr_odlqqskjj61v9cejwo1_400.jpg"
  },
  {
    name: "Barnaby",
    favFood: "Tuna",
    img: "https://68.media.tumblr.com/88d0fcf2b84a7b098dda839130597569/tumblr_okuo4teiql1uhevdso1_1280.jpg"
  },
  {
    name: "Max",
    favFood: "Whiskas Temptations",
    img: "http://66.media.tumblr.com/7c5784ea89369c780e782bf10c60315a/tumblr_npb0hlYwhV1u63jaco1_1280.jpg"
  },
  {
    name: "Dotty",
    favFood: "Ham",
    img: "https://img.purch.com/w/660/aHR0cDovL3d3dy5saXZlc2NpZW5jZS5jb20vaW1hZ2VzL2kvMDAwLzA5Ny85NTkvb3JpZ2luYWwvc2h1dHRlcnN0b2NrXzYzOTcxNjY1LmpwZw=="
  }
]

const app = function(){
  cats.forEach(function(cat){
    addCat(cat.name, cat.favFood, cat.img)
  })
}

window.onload = app
