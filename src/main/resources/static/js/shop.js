const shopGrid = document.getElementById("shopGrid");

const productPhotos = {
    'Madness Black Tee': 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=800&q=80',
    'Neon Pulse Hoodie': 'https://images.unsplash.com/photo-1556821840-3a63f95609a7?w=800&q=80',
    'Basel Rave Cap': 'https://images.unsplash.com/photo-1588850561407-ed78c282e89b?w=800&q=80'
};
const productFallback = [
    'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=800&q=80',
    'https://images.unsplash.com/photo-1556821840-3a63f95609a7?w=800&q=80',
    'https://images.unsplash.com/photo-1588850561407-ed78c282e89b?w=800&q=80',
    'https://images.unsplash.com/photo-1620625515032-6ed0c1790c75?w=800&q=80',
    'https://images.unsplash.com/photo-1626785774573-4b799315345d?w=800&q=80',
    'https://images.unsplash.com/photo-1527086820793-93fcb076f4dc?w=800&q=80'
];

function pickPhoto(item, idx) {
    return (item.imageUrl) || productPhotos[item.name] || productFallback[idx % productFallback.length];
}

function escapeHtml(s) {
    return String(s == null ? '' : s).replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
}

async function loadMerchandise() {
    try {
        const response = await fetch("/api/merchandise");
        const merchandise = await response.json();
        shopGrid.innerHTML = "";

        if (!merchandise || merchandise.length === 0) {
            shopGrid.innerHTML = "<p class='section-description'>No merchandise available right now.</p>";
            return;
        }

        merchandise.forEach(function (item, idx) {
            const card = document.createElement("div");
            card.className = "event-card media-card";
            const photo = pickPhoto(item, idx);
            const stockText = item.stock > 0 ? item.stock + " in stock" : "Sold out";
            const soldOut = item.stock <= 0;

            card.innerHTML =
                "<div class='card-image' style=\"background-image: url('" + escapeHtml(photo) + "')\"></div>" +
                "<div class='card-body'>" +
                    "<p class='event-date'>" + escapeHtml(item.category || "Merchandise") + "</p>" +
                    "<h3>" + escapeHtml(item.name || "Unnamed item") + "</h3>" +
                    "<p class='event-location'>CHF " + escapeHtml(item.price) + "</p>" +
                    "<p class='event-location'>" + stockText + "</p>" +
                    "<div class='card-actions'>" +
                        "<a class='event-link secondary-btn' href='product-detail.html?id=" + item.id + "'>View Item</a>" +
                        "<button class='event-link buy-btn' " + (soldOut ? "disabled" : "") +
                            " data-id='m" + item.id + "' data-name=\"" + escapeHtml(item.name) + "\"" +
                            " data-price='" + item.price + "' data-photo=\"" + escapeHtml(photo) + "\" data-type='product'>" +
                            (soldOut ? "Sold Out" : "Add to Cart") +
                        "</button>" +
                    "</div>" +
                "</div>";

            shopGrid.appendChild(card);
        });

        shopGrid.addEventListener('click', function (e) {
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
        shopGrid.innerHTML = "<p class='section-description'>Failed to load merchandise.</p>";
    }
}

loadMerchandise();
