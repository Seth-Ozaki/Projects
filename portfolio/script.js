const IDS = ['name', 'about', 'projects', 'contact'];
let currentId = 0;

function getNextPage() {
    let currentPage = document.getElementById(IDS[currentId]);
    //hide page
    currentPage.hidden = true;
    if (currentId >= 3) {
        currentId = 0;
    } else {
        currentId++;
    }

    currentPage = document.getElementById(IDS[currentId]);
    currentPage.hidden = false;
}

function getPrevPage(text) {
    let currentPage = document.getElementById(IDS[currentId]);
    //hide page
    currentPage.hidden = true;
    if (currentId <= 0) {
        currentId = 3;
    } else {
        currentId--;
    }
    currentPage = document.getElementById(IDS[currentId]);
    currentPage.hidden = false;
}
