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
                    <a onclick="movieSelected('${movie.id}')" href="/getOne/${movie.id}" class="btn btn-primary">Movie Details</a>
               </div>
            </div>
          `;
    });

    $('#movies').html(output);
}

function movieSelected(id) {
    sessionStorage.setItem('movieId', id);
    // window.location.href("http://localhost:8001/movies");
    return false;
}

function getMovie() {
    let movieId = sessionStorage.getItem('movieId');

    let output =`
    <div class="row">
       <div class="col-md-4">
           <img src="${movie.image}" class="img-thumbnail">
       </div>
       <div class="col-md-8">
            <h2>${movie.title}</h2>
            <ul class="list-group">
                <li class="list-group-item"><strong>Genre:</strong> ${movie.genre}</li>
                <li class="list-group-item"><strong>Duration:</strong> ${movie.duration}</li>
                <li class="list-group-item"><strong>Age Requirement:</strong> ${movie.ageRequirement}</li>
            </ul>
       </div>
    </div>
    <div class="row">
        <div class="well">
            <h3 >Description</h3>
            ${movie.description}
            <hr>
            <a href=""
            <a href="/movies" class="btn btn-secondary">Go Back</a>
        </div>
    </div>`

    $('#movie').html(output);
}