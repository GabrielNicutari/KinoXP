$(document).ready(function() {

    //dropdown
    const selectedAll = document.querySelectorAll(".selected");

    selectedAll.forEach((selected) => {
        const optionsContainer = selected.previousElementSibling;

        const optionsList = optionsContainer.querySelectorAll(".option");

        selected.addEventListener("click", () => {
            if (optionsContainer.classList.contains("active")) {
                optionsContainer.classList.remove("active");
            } else {
                let currentActive = document.querySelector(".options-container.active");

                if (currentActive) {
                    currentActive.classList.remove("active");
                }

                optionsContainer.classList.add("active");
            }
        });

        optionsList.forEach((o) => {
            o.addEventListener("click", () => {
                selected.innerHTML = o.querySelector("label").innerHTML;
                optionsContainer.classList.remove("active");
            });
        });
    });

    //buttons
    $('.deleteButton').on('click',function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delRef').attr('href', href);
        $('#deleteModal').modal();
    });

    $('.editButton').on('click',function(event){

        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function(movie, status) {
            $('#idEdit').val(movie.id);
            $('#titleEdit').val(movie.title);
            $('#ageRequirementEdit').val(movie.ageRequirement);
            $('#durationEdit').val(movie.duration);
            $('#genreEdit').val(movie.genre);
            $('#descriptionEdit').val(movie.description);
            $('#actorsEdit').val(movie.actors);
            $('#imageEdit').val(movie.image);
            $('#coverEdit').val(movie.cover);
        });
        $('#editModal').modal();
    });


});
