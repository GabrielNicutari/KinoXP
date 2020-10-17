$(document).ready(() => {
    getMovies();

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
                    <a onclick="movieSelected('${movie.id}')" href="/movies/getOne/${movie.id}" class="btn btn-primary">Movie Details</a>
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
        <div class="well">
            <a href="/movies" class="btn btn-secondary"><i class="fa fa-arrow-left"></i></a>
            <h2>${movie.title}</h2>
            ${movie.description}
            <hr>
        </div>
    </div>
   <div class="row">
       <div class="col-md-8">
            <ul class="list-group">
                <li class="list-group-item"><strong>Genre:</strong> ${movie.genre}</li>
                <li class="list-group-item"><strong>Duration:</strong> ${movie.duration}</li>
                <li class="list-group-item"><strong>Age Requirement:</strong> ${movie.ageRequirement}</li>
                <li class="list-group-item"><strong>Actors:</strong> ${movie.actors}</li>
            </ul>
            
            <a href="/movies/viewOne/?id=${movie.id}" class="btn btn-warning editButton">Update Movie</a>
            <a href="/movies/delete/?id=${movie.id}" class="btn btn-danger deleteButton">Delete Movie</a>
            
            <div class="form-group" style="display: inline-block" role="group">
                <select id="showtime-select" class="custom-select">
                    <option selected="">Pick A Date</option>
                </select>
            </div>
       </div>
    </div>`;

    let jumbotron = document.getElementsByClassName("jumbotron")[0];
    jumbotron.style.backgroundImage = "url("+ movie.cover +")";

    $('#movie').html(output);
}

function fetchShowtimes() {
    let output = '';
    var select = document.getElementById("my-select");
    $.each(showtimes, (index, showtime) => {
        console.log(showtime.id);
        output += `
        <div class="option">
            <input type="radio" class="radio" id="date${showtime.id}" name="category" value="${showtime.id}" onclick="displaySelected(id)"/>
            <label for="date${showtime.id}">${showtime.dateTime}</label>
        </div>
        `;
    });
    select.innerHTML = output;
}

function displaySelected(name) {
    console.log(name);
}