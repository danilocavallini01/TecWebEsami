var multi = true;

function changeView(isMulti = false, elem) {
    var imgContainer = document.querySelector("main")
    var icons = document.querySelectorAll("img.select.icon")

    for ( var icon of icons ) {
        icon.classList.remove("selected")
    }

    elem.classList.add("selected")
    if ( isMulti ) {
        imgContainer.classList.add("multi")
        despawnInfo()
    } else {
        imgContainer.classList.remove("multi")
        despawnInfo()
    }
    multi = isMulti
}

function spawnInfos(elem) {
    if ( isMulti ) {
        var location = elem.getBoundingClientRect();
        var info = document.querySelector("info")
        info.classList.remove("hidden")

        info.style.top = location.top + "px"
        info.style.left = location.left + "px"
        info.style.width = location.width + "px"
        info.style.height = location.height + "px"
        }
}

function despawnInfo() {
    var info = document.querySelector("info")
    info.classList.add("hidden")
}