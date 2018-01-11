// Javascript functions
let home;
let loadedData = [
    { id: 0, name: "Nom de la salle", number: "A-456", comment: "Au fond du couloir" },
    { id: 1, name: "Nom de la salle de test", number: "A-4786", comment: "gauche" },
    { id: 2, name: "Nom de la salle de testeuh", number: "B-756", comment: "couloir" },
    { id: 3, name: "heisenedber de la salle", number: "C-14", comment: "Au fond " },
];

$(document).ready(function () {

    Vue.component('room-item', {
        props: ['room'],
        template: '<div class="room">\n' +
        '        <span class="room-name">{{room.name}}</span>\n' +
        '        <span class="room-number">{{room.number}}</span>\n' +
        '        <span class="room-comment">{{room.comment}}</span>\n' +
        '<i class="fa fa-wifi" aria-hidden="true"></i>' +

        '   </div>'
    });

    home = new Vue({
        el: '#room-list',
        data: {
            roomList: loadedData
        }
    });


});
