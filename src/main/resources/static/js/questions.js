function search() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("search");
    filter = input.value.toUpperCase();
    ul = document.getElementById("lista");

    li = document.getElementsByTagName("li");
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName("a")[0];
        if (a.text.toUpperCase().indexOf(filter) > -1) {

            li[i].style.display = "block";
        } else {
            li[i].style.display = "none";

        }
    }
}

function logout( ) {
    window.location.href="/logout";


}