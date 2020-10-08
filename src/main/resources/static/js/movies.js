$(document).ready(() => {
    getMovies("w/e");

    // $('#searchForm').on('submit', (e) => {
    //     let searchText = $('#searchText').val();
    //     getMovies(searchText);
    //     e.preventDefault();
    // })
});

function getMovies() {

    let output = '';
    $.each(movies, (index, movie) => {
        output += `
            <div class="col-md-3" >
               <div class="well text-center jumbotron">
                   <img src="${movie.image}">
                   <h5> ${movie.title}</h5>
               <a onclick="movieSelected('${movie.id}')" class="btn btn-primary" href="#">Movie Details</a>
               </div>
            </div>
          `;
    });

    $('#movies').html(output);
}

function movieSelected(id) {
    sessionStorage.setItem('movieId', id);
    window.location.href='movie.html';
    return false;
}