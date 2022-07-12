$(document).ready(function() {

    var renderTemplate = Handlebars.compile($('#todo-template').html());

    function renderTodo(todo) {
        $('#todos-list').append(renderTemplate(todo));
    }

    function listTodo() {
        $('#todos-list').children().remove();
        $.get('/api/todo', function(todos) {
            todos.forEach(renderTodo);
        });
    }

    // Handling to-do submission
    $('#todo-form').submit(function(e) {
        e.preventDefault();

        var $todoText = $('#todo-text');
        $.post('/api/todo', { text: $todoText.val()}, function(data) {
            $todoText.val('');
            renderTodo(data);
        });
    });

    // Handling removing to-do
     $('#todos-list').click(function(e) {
        var $target = $(e.target);
        if ($target.is('button[class=delete]')) {
           var $parent = $target.closest('[data-todo-id]');

           $.ajax({
               url: '/api/todo/' + $parent.data('todo-id'),
               method: 'delete',
               success: function() {
                   $parent.remove();
               }
           });
        }
    });

    // On Page loaded fetch possible to-dos
    listTodo();

});
