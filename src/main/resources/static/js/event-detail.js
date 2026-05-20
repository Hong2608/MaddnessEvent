(async function () {
    const wrap = document.getElementById('event-detail');
    const id = new URLSearchParams(location.search).get('id');
    if (!id) { wrap.innerHTML = "<p class='section-description'>Event not specified.</p>"; return; }

    const fallback = 'https://images.unsplash.com/photo-1571266028243-e4733b1f4b1d?w=1600&q=80';

    function esc(s) {
        return String(s == null ? '' : s).replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
    }

    try {
        const resp = await fetch('/api/tickets/' + encodeURIComponent(id));
        if (!resp.ok) { wrap.innerHTML = "<p class='section-description'>Event not found.</p>"; return; }
        const ticket = await resp.json();
        const ev = ticket.event || {};
        const photo = fallback;
        const soldOut = !ticket.availability;
        document.title = (ev.title || 'Event') + ' - MadnessEvents';

        wrap.innerHTML =
            "<div class='detail-image' style=\"background-image: url('" + photo + "')\"></div>" +
            "<div class='detail-body'>" +
                "<p class='section-tag'>" + esc(ev.date) + "</p>" +
                "<h1>" + esc(ev.title) + "</h1>" +
                "<p class='detail-meta'>📍 " + esc(ev.location) + "</p>" +
                "<p class='detail-meta'>🎫 " + esc(ticket.type) + "</p>" +
                "<p class='detail-meta'>💰 CHF " + esc(ticket.price) + " &middot; " + (soldOut ? 'Sold out' : 'Available') + "</p>" +
                "<p class='detail-description'>" + esc(ev.description || '') + "</p>" +
                "<button class='event-link buy-btn buy-large' " + (soldOut ? "disabled" : "") +
                    " data-id='t" + ticket.id + "' data-name=\"" + esc(ev.title) + "\"" +
                    " data-price='" + ticket.price + "' data-photo=\"" + photo + "\">" +
                    (soldOut ? "Sold Out" : "Buy Tickets") +
                "</button> " +
                "<a class='event-link secondary-btn' href='tickets.html'>&laquo; Back to events</a>" +
            "</div>";

        const btn = wrap.querySelector('.buy-btn');
        if (btn) btn.addEventListener('click', function () {
            if (btn.disabled) return;
            window.Cart.add({
                id: btn.dataset.id,
                name: btn.dataset.name,
                price: Number(btn.dataset.price),
                photo: btn.dataset.photo,
                type: 'ticket'
            });
            location.href = 'cart.html';
        });
    } catch (e) {
        wrap.innerHTML = "<p class='section-description'>Error loading event.</p>";
    }
})();
