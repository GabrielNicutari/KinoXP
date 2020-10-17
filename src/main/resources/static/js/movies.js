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
    var dateSelect = document.getElementById("date-select");
    var timeSelect = document.getElementById("time-select");

    const selected = document.querySelector(".selected");
    const optionsContainer = document.querySelector(".options-container");
    const optionsList = document.querySelectorAll(".option");

    //debugging
    console.log(showtimes);
    console.log(times);

    // $.each(showtimes, (index, showtime) => {
    //     console.log(showtime.id);
    //     timeSelect.innerHTML += `
    //     <div class="option">
    //         <input type="radio" class="radio" id="date${showtime.id}" name="category" value="${showtime.id}" onclick="displaySelected(id)"/>
    //         <label for="date${showtime.id}">${showtime.dateTime}</label>
    //     </div>
    //     `;
    // });

    $.each(times, (index, date) => {
        output += `
        <div class="option">
            <input type="radio" class="radio" id="${index}" name="date" value="${index}" onclick="displaySelected(value)" />
            <label for="${index}">${index}</label>
        </div>
        `;
    });

    dateSelect.innerHTML = output;

    dateSelect.addEventListener('change', function populate_child(e) {
        timeSelect.innerHTML='';
        itm = e.target.value;
        if(itm in times) {
            for(let j = 0; j < times[itm].length; j++) {
                timeSelect.innerHTML += `
                <div class="option">
                    <input type="radio" class="radio" id="${times[itm][j].id}" name="time" value="${times[itm][j].dateTime}" onclick="displaySelected(value)"/>
                    <label for="${times[itm][j].id}">${times[itm][j].dateTime}</label>
                </div>
                `;
            }
        }
    });
}

function displaySelected(name) {
    console.log(name);
}