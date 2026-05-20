const shopGrid = document.getElementById("shopGrid");

async function loadMerchandise() {
    try {
        const response = await fetch("/api/merchandise");
        const merchandise = await response.json();

        shopGrid.innerHTML = "";

        if (!merchandise || merchandise.length === 0) {
            shopGrid.innerHTML = "<p class='section-description'>No merchandise available right now.</p>";
            return;
        }

        merchandise.forEach(function (item) {
            const card = document.createElement("div");
            card.className = "event-card";

            const stockText = item.stock > 0 ? item.stock + " in stock" : "Sold out";

            card.innerHTML =
                (item.imageUrl ? "<img class='card-image' src='" + item.imageUrl + "' alt='" + item.name + "'>" : "") +
                "<p class='event-date'>" + (item.category || "Merchandise") + "</p>" +
                "<h3>" + (item.name || "Unnamed item") + "</h3>" +
                "<p class='event-location'>CHF " + item.price + "</p>" +
                "<p class='event-location'>" + stockText + "</p>" +
                "<a href='#' class='event-link'>Buy Item</a>";

            shopGrid.appendChild(card);
        });

    } catch (error) {
        console.error(error);
        shopGrid.innerHTML = "<p class='section-description'>Failed to load merchandise.</p>";
    }
}

loadMerchandise();