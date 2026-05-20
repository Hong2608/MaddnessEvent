const ticketsGrid = document.getElementById("ticketsGrid");

async function loadTickets() {
    try {
        const response = await fetch("/api/tickets");
        const tickets = await response.json();

        ticketsGrid.innerHTML = "";

        if (!tickets || tickets.length === 0) {
            ticketsGrid.innerHTML = "<p class='section-description'>No tickets available right now.</p>";
            return;
        }

        tickets.forEach(function (ticket) {
            const event = ticket.event;

            const card = document.createElement("div");
            card.className = "event-card";

            const availabilityText = ticket.availability ? "Available" : "Sold out";

            card.innerHTML =
                "<p class='event-date'>" + event.date + "</p>" +
                "<h3>" + event.title + "</h3>" +
                "<p class='event-location'>" + event.location + "</p>" +
                "<p class='event-location'>" + event.description + "</p>" +
                "<p class='event-location'>" + ticket.type + " - CHF " + ticket.price + "</p>" +
                "<p class='event-location'>" + availabilityText + "</p>" +
                "<a href='#' class='event-link'>Buy Ticket</a>";

            ticketsGrid.appendChild(card);
        });

    } catch (error) {
        console.error(error);
        ticketsGrid.innerHTML = "<p class='section-description'>Failed to load tickets.</p>";
    }
}

loadTickets();