const djsGrid = document.getElementById("djsGrid");

const djPhotos = [
    'https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=800&q=80',
    'https://images.unsplash.com/photo-1571266028243-e4733b1f4b1d?w=800&q=80',
    'https://images.unsplash.com/photo-1516280440614-37939bbacd81?w=800&q=80',
    'https://images.unsplash.com/photo-1574391884720-bbc049ec09ad?w=800&q=80'
];

function escHtml(s) {
    return String(s == null ? '' : s).replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
}

async function loadDjs() {
    try {
        const response = await fetch("/api/djs");
        if (!response.ok) throw new Error("Backend error: " + response.status);
        const djs = await response.json();
        if (!Array.isArray(djs)) throw new Error("Expected list");

        djsGrid.innerHTML = "";
        if (djs.length === 0) {
            djsGrid.innerHTML = "<p class='section-description'>No DJs available right now.</p>";
            return;
        }

        djs.forEach(function (dj, idx) {
            const card = document.createElement("div");
            card.className = "event-card media-card";
            const photo = dj.imageUrl || djPhotos[idx % djPhotos.length];
            const bio = dj.bio || 'No biography available.';
            const shortBio = bio.length > 120 ? bio.slice(0, 120) + '...' : bio;

            card.innerHTML =
                "<div class='card-image' style=\"background-image: url('" + escHtml(photo) + "')\"></div>" +
                "<div class='card-body'>" +
                    "<p class='event-date'>" + escHtml(dj.genre || "DJ Artist") + "</p>" +
                    "<h3>" + escHtml(dj.stageName || "Unnamed DJ") + "</h3>" +
                    "<p class='event-location'>" + escHtml(dj.country || "—") + "</p>" +
                    "<p class='event-desc'>" + escHtml(shortBio) + "</p>" +
                    "<a class='event-link' href='dj-detail.html?id=" + dj.id + "'>View DJ Profile</a>" +
                "</div>";

            djsGrid.appendChild(card);
        });

    } catch (error) {
        console.error(error);
        djsGrid.innerHTML = "<p class='section-description'>Failed to load DJs. Check backend terminal.</p>";
    }
}

loadDjs();
