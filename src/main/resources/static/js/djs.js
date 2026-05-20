const djsGrid = document.getElementById("djsGrid");

async function loadDjs() {
    try {
        const response = await fetch("/api/djs");

        if (!response.ok) {
            throw new Error("Backend error: " + response.status);
        }

        const djs = await response.json();

        if (!Array.isArray(djs)) {
            throw new Error("Expected a list of DJs, but got something else");
        }

        djsGrid.innerHTML = "";

        if (djs.length === 0) {
            djsGrid.innerHTML = "<p class='section-description'>No DJs available right now.</p>";
            return;
        }

        djs.forEach(function (dj) {
            const card = document.createElement("div");
            card.className = "event-card";

            card.innerHTML =
                "<p class='event-date'>" + (dj.genre || "DJ Artist") + "</p>" +
                "<h3>" + (dj.stageName || "Unnamed DJ") + "</h3>" +
                "<p class='event-location'>" + (dj.country || "No country added") + "</p>" +
                "<p class='event-location'>" + (dj.bio || "No description available.") + "</p>" +
                "<a href='#' class='event-link'>View Profile</a>";

            djsGrid.appendChild(card);
        });

    } catch (error) {
        console.error(error);
        djsGrid.innerHTML = "<p class='section-description'>Failed to load DJs. Check backend terminal.</p>";
    }
}

loadDjs();