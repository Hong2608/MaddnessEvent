const ticketsGrid = document.getElementById("ticketsGrid");

const eventPhotos = [
    'https://images.unsplash.com/photo-1571266028243-e4733b1f4b1d?w=1200&q=80',
    'https://images.unsplash.com/photo-1429962714451-bb934ecdc4ec?w=1200&q=80',
    'https://images.unsplash.com/photo-1459749411175-04bf5292ceea?w=1200&q=80',
    'https://images.unsplash.com/photo-1545128485-c400e7702796?w=1200&q=80',
    'https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=1200&q=80'
];

function esc(s) {
    return String(s == null ? '' : s).replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
}

async function loadTickets() {
    try {
        const response = await fetch("/api/tickets");
        const tickets = await response.json();
        ticketsGrid.innerHTML = "";

        if (!tickets || tickets.length === 0) {
            ticketsGrid.innerHTML = "<p class='section-description'>No tickets available right now.</p>";
            return;
        }

        tickets.forEach(function (ticket, idx) {
            const event = ticket.event || {};
            const card = document.createElement("div");
            card.className = "event-card media-card";
            const photo = eventPhotos[idx % eventPhotos.length];
            const soldOut = !ticket.availability;
            const desc = event.description || '';
            const shortDesc = desc.length > 120 ? desc.slice(0, 120) + '...' : desc;

            card.innerHTML =
                "<div class='card-image' style=\"background-image: url('" + esc(photo) + "')\"></div>" +
                "<div class='card-body'>" +
                    "<p class='event-date'>" + esc(event.date) + "</p>" +
                    "<h3>" + esc(event.title) + "</h3>" +
                    "<p class='event-location'>" + esc(event.location) + "</p>" +
                    (shortDesc ? "<p class='event-desc'>" + esc(shortDesc) + "</p>" : "") +
                    "<p class='event-location'>" + esc(ticket.type) + " &middot; CHF " + esc(ticket.price) + "</p>" +
                    "<div class='card-actions'>" +
                        "<a class='event-link secondary-btn' href='event-detail.html?id=" + ticket.id + "'>View Details</a>" +
                        "<button class='event-link buy-btn' " + (soldOut ? "disabled" : "") +
                            " data-id='t" + ticket.id + "' data-name=\"" + esc(event.title) + "\"" +
                            " data-price='" + ticket.price + "' data-photo=\"" + esc(photo) + "\" data-type='ticket'>" +
                            (soldOut ? "Sold Out" : "Buy Tickets") +
                        "</button>" +
                    "</div>" +
                "</div>";

            ticketsGrid.appendChild(card);
        });

        ticketsGrid.addEventListener('click', function (e) {
            const btn = e.target.closest('.buy-btn');
            if (!btn || btn.disabled) return;
            window.Cart.add({
                id: btn.dataset.id,
                name: btn.dataset.name,
                price: Number(btn.dataset.price),
                photo: btn.dataset.photo,
                type: btn.dataset.type
            });
            location.href = 'cart.html';
        });

    } catch (error) {
        console.error(error);
        ticketsGrid.innerHTML = "<p class='section-description'>Failed to load tickets.</p>";
    }
}

loadTickets();
