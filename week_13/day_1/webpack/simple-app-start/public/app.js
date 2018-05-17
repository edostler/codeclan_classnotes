var app = function() {
  var header = document.getElementById("header");
  header.textContent = _.join(["Hello", "Webpack"], " ");
}

window.addEventListener("load", app);
