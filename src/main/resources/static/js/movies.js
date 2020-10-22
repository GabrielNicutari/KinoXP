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
            <input type="radio" class="radio" id="${index}" name="date" value="${index}" onclick="displaySelected(value)"/>
            <label for="${index}">${index}</label>
        </div>
        `;
    });

    dateSelect.innerHTML = output;

    dateSelect.addEventListener('change', function populate_child(e) {
        timeSelect.innerHTML='';
        var emptyOptionList = true;
        itm = e.target.value;
        if(itm in times) {
            for(let j = 0; j < times[itm].length; j++) {
                emptyOptionList = false;  //if it gets here, that means the subarray is not empty
                timeSelect.innerHTML += `
                <div class="option">
                    <input type="radio" class="radio" id="time${times[itm][j]}" name="time" value="${times[itm][j]}" onclick="displaySelected(value)"/>
                    <label for="time${times[itm][j]}">${times[itm][j]}</label>
                </div>
                `;
            }

            if(!emptyOptionList) {
                const selectedAll = document.querySelectorAll(".selected");

                selectedAll.forEach((selected) => {
                    const optionsContainer = selected.previousElementSibling;

                    const optionsList = optionsContainer.querySelectorAll(".option");

                    const selectedS1 = document.getElementById("s1");
                    selectedS1.innerHTML = "Pick A Time";
                    timeSelect.classList.add("active");

                    optionsList.forEach((o) => {
                        o.addEventListener("click", () => {
                            selected.innerHTML = o.querySelector("label").innerHTML;
                            optionsContainer.classList.remove("active");
                        });
                    });
                });
            } else {
                const selected = document.getElementById("s1");
                selected.innerHTML = "Pick A Time";
            }
        }
    });
}

function displaySelected(name) {
    console.log(name);
}

function validateForm() {
    var valid = true;

    const selectedAll = document.querySelectorAll(".selected");

    selectedAll.forEach((selected) => {
        if(selected.innerHTML.trim() === "Pick A Time" || selected.innerHTML.trim() === "Pick A Date") {
            valid = false;
        }
    });
    if(!valid) {
        alert("Please fill all the dropdown fields below!");
    }
    return valid;
}