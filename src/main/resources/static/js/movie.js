$(document).ready(function() {
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
            $('#imageEdit').val(movie.image);
        });
        $('#editModal').modal();
    });
});
