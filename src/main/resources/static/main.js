console.log("Hello World")

$(document).ready(function() {
    $.ajax({
        url: "/rss"
    }).then(function(data) {
        var table = $('#table-wrapper');
        $.each(data, function(i, item) {
            var tr = $('<tr>').append(
                $('<td>').text(item.title), $('<td>').text(item.pubDate)
            );
            console.log(tr);

            table.append(tr);
        });
    });
});

