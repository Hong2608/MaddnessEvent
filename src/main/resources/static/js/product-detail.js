(async function () {
    const wrap = document.getElementById('product-detail');
    const id = new URLSearchParams(location.search).get('id');
    if (!id) { wrap.innerHTML = "<p class='section-description'>Product not specified.</p>"; return; }

    const knownPhotos = {
        'Madness Black Tee': 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=1600&q=80',
        'Neon Pulse Hoodie': 'https://images.unsplash.com/photo-1556821840-3a63f95609a7?w=1600&q=80',
        'Basel Rave Cap': 'https://images.unsplash.com/photo-1588850561407-ed78c282e89b?w=1600&q=80'
    };
    const fallback = 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=1600&q=80';

    function esc(s) {
        return String(s == null ? '' : s).replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
    }

    try {
        const resp = await fetch('/api/merchandise/' + encodeURIComponent(id));
        if (!resp.ok) { wrap.innerHTML = "<p class='section-description'>Product not found.</p>"; return; }
        const p = await resp.json();
        const photo = p.imageUrl || knownPhotos[p.name] || fallback;
        const soldOut = p.stock <= 0;
        document.title = (p.name || 'Product') + ' - MadnessEvents';

        wrap.innerHTML =
            "<div class='detail-image' style=\"background-image: url('" + esc(photo) + "')\"></div>" +
            "<div class='detail-body'>" +
                "<p class='section-tag'>" + esc(p.category || '') + "</p>" +
                "<h1>" + esc(p.name) + "</h1>" +
                "<p class='detail-meta'>💰 CHF " + esc(p.price) + "</p>" +
                "<p class='detail-meta'>📦 " + (soldOut ? 'Sold out' : p.stock + ' in stock') + "</p>" +
                "<p class='detail-description'>" + esc(p.description || 'Premium MadnessEvents merchandise. Limited drop, exclusive design.') + "</p>" +
                "<button class='event-link buy-btn buy-large' " + (soldOut ? "disabled" : "") +
                    " data-id='m" + p.id + "' data-name=\"" + esc(p.name) + "\"" +
                    " data-price='" + p.price + "' data-photo=\"" + esc(photo) + "\">" +
                    (soldOut ? "Sold Out" : "Add to Cart") +
                "</button> " +
                "<a class='event-link secondary-btn' href='shop.html'>&laquo; Back to shop</a>" +
            "</div>";

        const btn = wrap.querySelector('.buy-btn');
        if (btn) btn.addEventListener('click', function () {
            if (btn.disabled) return;
            window.Cart.add({
                id: btn.dataset.id,
                name: btn.dataset.name,
                price: Number(btn.dataset.price),
                photo: btn.dataset.photo,
                type: 'product'
            });
            location.href = 'cart.html';
        });
    } catch (e) {
        wrap.innerHTML = "<p class='section-description'>Error loading product.</p>";
    }
})();
