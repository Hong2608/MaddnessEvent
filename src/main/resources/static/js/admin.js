let currentSection = "";

const apiConfig = {
    events: {
        url: "/api/events",
        title: "Events",
        fields: ["title", "date", "location", "description"]
    },
    djs: {
        url: "/api/djs",
        title: "DJs",
        fields: ["stageName", "genre", "country", "imageUrl", "bio"]
    },
    tickets: {
        url: "/api/tickets",
        title: "Tickets",
        fields: ["type", "price", "availability"]
    },
    merchandise: {
        url: "/api/merchandise",
        title: "Merchandise",
        fields: ["name", "price", "stock", "category", "imageUrl"]
    }
};

async function loadSection(section) {
    currentSection = section;
    renderForm(section);
    await loadData(section);
}

function renderForm(section) {
    const config = apiConfig[section];
    const formArea = document.getElementById("adminFormArea");

    let inputs = "";

    config.fields.forEach(function (field) {
        inputs += `
            <label>${field}</label>
            <input id="field_${field}" placeholder="${field}">
        `;
    });

    formArea.innerHTML = `
        <h3>Add / Update ${config.title}</h3>

        <input id="itemId" placeholder="ID only for update. Leave empty to add new.">

        ${inputs}

        <button onclick="saveItem()">Save</button>
        <button onclick="clearForm()">Clear</button>
    `;
}

async function loadData(section) {
    const config = apiConfig[section];
    const output = document.getElementById("adminOutput");

    try {
        const response = await fetch(config.url);
        const data = await response.json();

        output.innerHTML = "";

        if (!data || data.length === 0) {
            output.innerHTML = "<p class='section-description'>No data found.</p>";
            return;
        }

        data.forEach(function (item) {
            const card = document.createElement("div");
            card.className = "event-card";

            card.innerHTML = createCardHtml(section, item);

            output.appendChild(card);
        });

    } catch (error) {
        console.error(error);
        output.innerHTML = "<p class='section-description'>Failed to load data.</p>";
    }
}

function createCardHtml(section, item) {
    if (section === "events") {
        return `
            <p class="event-date">${item.date || ""}</p>
            <h3>${item.title || "Unnamed event"}</h3>
            <p class="event-location">${item.location || ""}</p>
            <p class="event-location">${item.description || ""}</p>
            ${adminActions(item.id)}
        `;
    }

    if (section === "djs") {
        return `
            <p class="event-date">${item.genre || ""}</p>
            <h3>${item.stageName || item.name || "Unnamed DJ"}</h3>
            <p class="event-location">${item.country || ""}</p>
            <p class="event-location">${item.bio || ""}</p>
            ${adminActions(item.id)}
        `;
    }

    if (section === "tickets") {
        return `
            <p class="event-date">${item.type || "Ticket"}</p>
            <h3>CHF ${item.price}</h3>
            <p class="event-location">${item.availability ? "Available" : "Sold out"}</p>
            ${adminActions(item.id)}
        `;
    }

    if (section === "merchandise") {
        return `
            <p class="event-date">${item.category || "Merchandise"}</p>
            <h3>${item.name || "Unnamed item"}</h3>
            <p class="event-location">CHF ${item.price}</p>
            <p class="event-location">${item.stock} in stock</p>
            ${adminActions(item.id)}
        `;
    }
}

function adminActions(id) {
    return `
        <button onclick="editItem(${id})">Edit</button>
        <button onclick="deleteItem(${id})">Delete</button>
    `;
}

async function saveItem() {
    const config = apiConfig[currentSection];
    const id = document.getElementById("itemId").value;
    const item = {};

    config.fields.forEach(function (field) {
        const value = document.getElementById("field_" + field).value;

        if (field === "price" || field === "stock") {
            item[field] = Number(value);
        } else if (field === "availability") {
            item[field] = value.toLowerCase() === "true" || value.toLowerCase() === "available";
        } else {
            item[field] = value;
        }
    });

    const method = id ? "PUT" : "POST";
    const url = id ? config.url + "/" + id : config.url;

    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(item)
        });

        if (!response.ok) {
            throw new Error("Save failed");
        }

        clearForm();
        await loadData(currentSection);

    } catch (error) {
        console.error(error);
        alert("Could not save item.");
    }
}

async function editItem(id) {
    const config = apiConfig[currentSection];

    try {
        const response = await fetch(config.url + "/" + id);
        const item = await response.json();

        document.getElementById("itemId").value = item.id;

        config.fields.forEach(function (field) {
            const input = document.getElementById("field_" + field);
            if (input) {
                input.value = item[field] ?? "";
            }
        });

    } catch (error) {
        console.error(error);
        alert("Could not load item for editing.");
    }
}

async function deleteItem(id) {
    const config = apiConfig[currentSection];

    const confirmed = confirm("Are you sure you want to delete this item?");
    if (!confirmed) {
        return;
    }

    try {
        const response = await fetch(config.url + "/" + id, {
            method: "DELETE"
        });

        if (!response.ok) {
            throw new Error("Delete failed");
        }

        await loadData(currentSection);

    } catch (error) {
        console.error(error);
        alert("Could not delete item.");
    }
}

function clearForm() {
    document.getElementById("itemId").value = "";

    const config = apiConfig[currentSection];

    config.fields.forEach(function (field) {
        const input = document.getElementById("field_" + field);
        if (input) {
            input.value = "";
        }
    });
}