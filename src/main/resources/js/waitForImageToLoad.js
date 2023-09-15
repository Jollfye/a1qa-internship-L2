var callback = arguments[arguments.length - 1];
var image = document.createElement('img');
image.src = arguments[0];
image.onload = function() {
    callback();
};
