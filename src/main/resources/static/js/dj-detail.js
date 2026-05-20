(async function () {
    const params = new URLSearchParams(location.search);
    const id = params.get('id');
    const nameEl = document.getElementById('dj-name');
    const genreEl = document.getElementById('dj-genre');
    const countryEl = document.getElementById('dj-country');
    const bioEl = document.getElementById('dj-bio');
    const photoEl = document.getElementById('dj-photo');
    const eventsGrid = document.getElementById('dj-events-grid');

    const fallback = 'https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=1200&q=80';
    const evPhoto = 'https://images.unsplash.com/photo-1571266028243-e4733b1f4b1d?w=800&q=80';

    function esc(s) {
        return String(s == null ? '' : s).replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
    }

    if (!id) { nameEl.textContent = 'DJ not specified'; eventsGrid.innerHTML = ''; return; }

    try {
        const [djResp, ticketsResp] = await Promise.all([
            fetch('/api/djs/' + encodeURIComponent(id)),
            fetch('/api/tickets')
        ]);

        if (!djResp.ok) { nameEl.textContent = 'DJ not found'; genreEl.textContent = ''; eventsGrid.innerHTML = ''; return; }
        const dj = await djResp.json();
        const photo = dj.imageUrl || fallback;

        nameEl.textContent = dj.stageName || 'Unnamed DJ';
        genreEl.textContent = dj.genre || '';
        countryEl.textContent = dj.country ? '📍 ' + dj.country : '';
        bioEl.textContent = dj.bio || 'No biography available.';
        photoEl.style.backgroundImage = "url('" + photo + "')";
        document.title = (dj.stageName || 'DJ') + ' - MadnessEvents';

        // Show all upcoming events (tickets) — current backend has no DJ-event link
        const tickets = await ticketsResp.json();
        if (!tickets.length) {
            eventsGrid.innerHTML = "<p class='section-description'>No upcoming events.</p>";
            return;
        }
        eventsGrid.innerHTML = '';
        tickets.slice(0, 6).forEach(function (ticket) {
            const ev = ticket.event || {};
            const card = document.createElement('div');
            card.className = 'event-card media-card';
            card.innerHTML =
                "<div class='card-image' style=\"background-image: url('" + evPhoto + "')\"></div>" +
                "<div class='card-body'>" +
                    "<p class='event-date'>" + esc(ev.date) + "</p>" +
                    "<h3>" + esc(ev.title) + "</h3>" +
                    "<p class='event-location'>" + esc(ev.location) + " &middot; CHF " + esc(ticket.price) + "</p>" +
                    "<a class='event-link secondary-btn' href='event-detail.html?id=" + ticket.id + "'>View Details</a>" +
                "</div>";
            eventsGrid.appendChild(card);
        });
    } catch (e) {
        nameEl.textContent = 'Error loading profile';
    }
})();
