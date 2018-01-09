// Javascript functions
let app3, app4, app7;

$(document).ready(function(){

    app3 = new Vue({
        el: '#app-3',
        data: {
            seen: true
        }
    });

    app4 = new Vue({
        el: '#app-4',
        data: {
            todos: [
                { text: 'Learn JavaScript' },
                { text: 'Learn Vue' },
                { text: 'Build something awesome' }
            ]
        }
    });

    Vue.component('todo-item', {
        props: ['todo'],
        template: '<li><b>{{ todo.text }}</b></li>'
    });

    app7 = new Vue({
        el: '#app-7',
        data: {
            groceryList: [
                { id: 0, text: 'Vegetables' },
                { id: 1, text: 'Cheese' },
                { id: 2, text: 'Whatever else humans are supposed to eat' }
            ]
        }
    });


});

