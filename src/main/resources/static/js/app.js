const output = document.getElementById("output");
const buttons = document.querySelectorAll("button");

buttons[0].addEventListener("click", () => {
    fetch("/api/events")
        .then(response => response.json())
        .then(data => {
            output.innerHTML = "<h2>Events</h2><pre>" + JSON.stringify(data, null, 2) + "</pre>";
        })
        .catch(error => {
            output.innerHTML = "<p>Failed to load events.</p>";
            console.error(error);
        });
});

buttons[1].addEventListener("click", () => {
    fetch("/api/djs")
        .then(response => response.json())
        .then(data => {
            output.innerHTML = "<h2>DJs</h2><pre>" + JSON.stringify(data, null, 2) + "</pre>";
        })
        .catch(error => {
            output.innerHTML = "<p>Failed to load DJs.</p>";
            console.error(error);
        });
});

buttons[2].addEventListener("click", () => {
    fetch("/api/tickets")
        .then(response => response.json())
        .then(data => {
            output.innerHTML = "<h2>Tickets</h2><pre>" + JSON.stringify(data, null, 2) + "</pre>";
        })
        .catch(error => {
            output.innerHTML = "<p>Failed to load tickets.</p>";
            console.error(error);
        });
});

buttons[3].addEventListener("click", () => {
    fetch("/api/merchandise")
        .then(response => response.json())
        .then(data => {
            output.innerHTML = "<h2>Merchandise</h2><pre>" + JSON.stringify(data, null, 2) + "</pre>";
        })
        .catch(error => {
            output.innerHTML = "<p>Failed to load merchandise.</p>";
            console.error(error);
        });
});