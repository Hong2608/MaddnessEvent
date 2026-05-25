# MadnessEvents

<img width="1321" height="690" alt="image" src="https://github.com/user-attachments/assets/f530354d-ec5d-481f-9456-f969a18a7293" />

MadnessEvents is a dynamic and specialized organization in the planning of techno and rave events across Switzerland, for its own high energy and quick growing community. To expand its reach across young people and strengthen the involvement of its audience, MadnessEvents decided to release its own brand-new website. This platform is the main destination where customers can discover upcoming events, buy tickets, browse exclusive branded merchandise, and learn about featured DJs — all in a unified digital space that reflects the unique and vibrant identity of the brand.

The aim of the web shop is to satisfy these needs by giving MadnessEvents full control over its business and brand representation. The techno and rave industry represents a promising market opportunity, with growing demand for memorable experiences and exclusive branded merchandise. The website not only facilitates ticket sales but also offers limited edition merchandise items, captures the atmosphere of each event and performer, and presents essential information about DJs, venues, and program schedules.

#### Contents:
- [Analysis](#analysis)
  - [Project Analysis](#project-analysis)
  - [Scenario](#scenario)
  - [User Stories](#user-stories)
  - [Use Cases](#use-cases)
- [Design](#design)
  - [Corporate Identity](#corporate-identity)
  - [Color Scheme](#color-scheme)
  - [Layout](#layout)
  - [Pages](#pages)
- [Domain Design](#domain-design)
- [Business Logic](#business-logic)
- [Authentication & Security](#authentication--security)
- [Frontend Features](#frontend-features)
  - [Cart & Checkout Flow](#cart--checkout-flow)
  - [Detail Pages](#detail-pages)
  - [Footer](#footer)
  - [Admin Panel](#admin-panel)
- [Implementation](#implementation)
  - [Backend Technology](#backend-technology)
  - [Frontend Technology](#frontend-technology)
  - [Project Structure](#project-structure)
- [Execution](#execution)
  - [Run Locally](#run-locally)
  - [Run in GitHub Codespaces](#run-in-github-codespaces)
- [Project Management](#project-management)
  - [Roles](#roles)
  - [Milestones](#milestones)

## Analysis

### Project Analysis

MadnessEvents operates in a market where audience attention is strongly influenced by visual identity, speed of access to information, and ease of purchase. The website must do more than present static information — it has to support the brand commercially and communicate the event experience clearly enough that users can move from discovery to purchase without friction.

The analysis identifies three main requirements:

- Present upcoming events clearly, with enough detail for users to decide whether to attend.
- Support a commercial journey where users can buy tickets and branded merchandise in a structured and trustworthy way.
- Reflect the visual identity of MadnessEvents through a consistent techno-inspired interface.

### Scenario

MadnessEvents is a growing organizer of techno and rave events in Switzerland. As its audience expands, the organization needs a dedicated website that acts as the main entry point for customers, followers, and potential buyers. The website allows visitors to discover future events, understand the lineup and venue details, explore official merchandise, and engage with the brand in a coherent online environment.

The scenario targets young, digitally active users who expect fast access to information, visually engaging design, and smooth navigation.

### User Stories

1. As a visitor, I want to see upcoming events with date, venue, and a short description, so that I can decide which event to attend.
2. As a visitor, I want to open a specific event page to read full event details before booking.
3. As a user, I want to click **Buy Tickets** and have the ticket added to my shopping cart automatically.
4. As a visitor, I want to browse DJs and click **View DJ Profile** to see their bio, genre, country, and photo.
5. As a user, I want to browse merchandise categories so I can discover products related to MadnessEvents.
6. As a user, I want to open a product page with image, name, description and price, then add the item to my cart.
7. As a user, I want to see my cart with thumbnails, names, quantities, and a running total.
8. As a user, I want to update quantities or proceed to checkout from the cart page.
9. As an admin, I want to log in with username and password to access an admin panel.
10. As an admin, I want to add, edit, and remove events, DJs, tickets, and merchandise from the admin panel.
11. As a visitor, I want a consistent footer with brand info, directory, legal, and contact links on every page.

### Use Cases

- **UC-1** [Browse events] Visitor views all upcoming events.
- **UC-2** [View event details] Visitor opens a specific event page with full description.
- **UC-3** [Buy ticket] Visitor adds a ticket to the cart from the event card or detail page.
- **UC-4** [Browse DJs] Visitor views all DJs with their profile photo and genre.
- **UC-5** [View DJ profile] Visitor opens a DJ's dedicated profile page.
- **UC-6** [Browse merchandise] Visitor views all merchandise products.
- **UC-7** [View product details] Visitor opens a product page with image, name, description, price.
- **UC-8** [Add product to cart] Visitor adds merchandise to the cart.
- **UC-9** [Manage cart] User updates item quantities or removes items.
- **UC-10** [Checkout] User completes purchase via the Checkout button.
- **UC-11** [Login] Admin signs in with credentials.
- **UC-12** [Manage events] Admin creates, updates, deletes events.
- **UC-13** [Manage DJs] Admin creates, updates, deletes DJ profiles.
- **UC-14** [Manage tickets] Admin creates, updates, deletes tickets.
- **UC-15** [Manage merchandise] Admin creates, updates, deletes products.

## Design

### Corporate Identity

The website communicates MadnessEvents through a dark, club-inspired visual style with high-contrast neon accents that evoke the atmosphere of techno events.

### Color Scheme

- Black and very dark blue background tones for a modern nightlife identity
- Bright neon green (`#76ff7a`) accents for active navigation, highlights, and success states
- Pink (`#ff4fc3`) accent for call-to-action buttons and badges
- White and light grey text for readability

### Layout

All pages share a consistent structure:

- **Header** with logo, navigation links (Home, DJs, Tickets, Shop), cart icon with live item-count badge, and Login button
- **Hero section** with a tagline, large heading, and short description
- **Content section** with reusable image-led cards
- **Footer** with brand block, directory, legal links, contact info, and social icons (Instagram, Twitter, Facebook, Email)

### Pages

| Page | URL | Purpose |
|---|---|---|
| Home | `/index.html` | Landing page with branding |
| Tickets | `/tickets.html` | Dynamic list of upcoming events with image cards, Buy Tickets, View Details |
| Event detail | `/event-detail.html?id={id}` | Single event with large image, description, Buy Tickets |
| DJs | `/djs.html` | Dynamic list of DJs with photos and genres |
| DJ detail | `/dj-detail.html?id={id}` | DJ profile with bio, country, photo banner, upcoming events |
| Shop | `/shop.html` | Dynamic merchandise list with product images, Add to Cart, View Item |
| Product detail | `/product-detail.html?id={id}` | Single product with image, price, description, Add to Cart |
| Cart | `/cart.html` | Shopping cart with thumbnails, quantity controls, total, Checkout |
| Login | `/login.html` | Styled login form for admin |
| Admin | `/admin.html` | Admin CRUD panel for events / DJs / tickets / merchandise |
| Privacy / Terms / Cookies / Ticket T&C | `/privacy.html`, `/terms.html`, `/cookies.html`, `/ticket-tc.html` | Footer legal pages |
| Vulnerability / Press Kit | `/vulnerability.html`, `/press.html` | Footer directory pages |

## Domain Design

The domain consists of four JPA entities in `ch.fhnw.madnessevents.data.domain`:

| Entity | Fields | Relationships |
|---|---|---|
| **Event** | `id`, `title`, `date`, `location`, `description` | One-to-many with `Ticket` |
| **Dj** | `id`, `stageName`, `genre`, `country`, `imageUrl`, `bio` | — |
| **Ticket** | `id`, `type`, `price`, `availability` | Many-to-one with `Event` |
| **Merchandise** | `id`, `name`, `price`, `stock`, `category` | — |

All entities are persisted in an H2 in-memory database. The schema is auto-generated by Hibernate on startup. Seed data is inserted by `MadnessEventsApplication#initData`, providing 4 DJs, 4 events, 5 tickets, and 6 merchandise items so the public pages render with realistic content out of the box.

## Business Logic

The business layer in `ch.fhnw.madnessevents.business` contains four services: `EventService`, `DjService`, `TicketService`, `MerchandiseService`. Each is a thin wrapper over the corresponding Spring Data JPA repository providing standard CRUD plus a `findById` that throws HTTP 404 via `ResponseStatusException` if the record is missing.

**Relevant endpoints:**

| Method | Path | Description | Access |
|---|---|---|---|
| `GET` | `/api/events` | List all events | Public |
| `GET` | `/api/events/{id}` | Get one event by id | Public |
| `POST` | `/api/events` | Create an event | **Admin** |
| `PUT` | `/api/events/{id}` | Update an event | **Admin** |
| `DELETE` | `/api/events/{id}` | Delete an event | **Admin** |
| `GET` | `/api/djs` | List all DJs | Public |
| `GET` | `/api/djs/{id}` | Get one DJ | Public |
| `POST` / `PUT` / `DELETE` | `/api/djs[/{id}]` | Manage DJs | **Admin** |
| `GET` | `/api/tickets` | List all tickets with their event | Public |
| `GET` | `/api/tickets/{id}` | Get one ticket | Public |
| `POST` / `PUT` / `DELETE` | `/api/tickets[/{id}]` | Manage tickets | **Admin** |
| `GET` | `/api/merchandise` | List all merchandise | Public |
| `GET` | `/api/merchandise/{id}` | Get one product | Public |
| `POST` / `PUT` / `DELETE` | `/api/merchandise[/{id}]` | Manage merchandise | **Admin** |
| `GET` | `/hello` | Health-check endpoint | Public |

Interactive API documentation is available at `/swagger-ui.html` when the application is running.

## Authentication & Security

Security is enforced by Spring Security (`ch.fhnw.madnessevents.security.SecurityConfig`).

- **Login flow:** form-based login at `/login.html`. Submitting the form posts credentials to `/perform-login`. On success the user is redirected to `/admin.html`; on failure to `/login.html?error`.
- **Logout:** posting to `/logout` invalidates the session and returns to `/`.
- **Default credentials:** `admin` / `admin123` (in-memory user with `ROLE_ADMIN`, BCrypt-hashed).
- **Authorization rules:**
  - All public pages, all `GET /api/**`, css/js/images, swagger and h2-console are open.
  - `POST` / `PUT` / `DELETE` on any `/api/**` requires `ROLE_ADMIN`.
  - `/admin.html` and `/admin` require `ROLE_ADMIN` — anonymous users are redirected to `/login.html`.

## Frontend Features

### Cart & Checkout Flow

The cart is implemented entirely in the browser using `localStorage` so it survives page reloads without any server-side session state. The shared helper `js/cart-badge.js` exposes a `window.Cart` API (`add`, `setQty`, `remove`, `read`, `total`, `count`) and renders a live count badge in the navbar cart icon.

- Clicking **Buy Tickets** on a ticket card or event detail page adds the ticket to the cart and redirects to `/cart.html`.
- Clicking **Add to Cart** on a merchandise card or product detail page does the same for that product.
- The cart page (`cart.html`) shows each item with a thumbnail, name, type label (Ticket / Merch), unit price, quantity ± buttons, line subtotal, and running total.
- The **Checkout** button clears the cart and displays an order confirmation message.

### Detail Pages

Three dedicated detail pages provide a focused view of a single resource. All three follow the same image-banner + body layout for visual consistency.

- **`event-detail.html?id={id}`** — large event image, date, venue, ticket type, price, availability, full description, Buy Tickets button.
- **`dj-detail.html?id={id}`** — DJ photo, stage name, genre, country, biography, and a list of upcoming performances featuring that DJ.
- **`product-detail.html?id={id}`** — product image, category, name, price, stock status, description, Add to Cart button.

Since the `Event` and `Merchandise` entities do not yet carry image URLs, the JS layer applies a curated set of Unsplash dummy images (with named matches for the seeded items "Madness Black Tee", "Neon Pulse Hoodie", "Basel Rave Cap") so every card and detail page looks professional.

### Footer

Every public page ends with a site-wide footer containing:

- **Brand block** — `MADNESS`**`EVENTS`** logo with neon glow, tagline, and four social icons (Instagram, Twitter, Facebook, Email).
- **Directory** column — Event Archive, DJ Roster, Vulnerability Disclosure, Press Kit.
- **Legal** column — Privacy Policy, Terms of Service, Cookie Policy, Ticket T&C.
- **Contact** column — email address, phone number, and physical address.
- **Bottom bar** — copyright notice.

All legal and directory links resolve to dedicated stub pages so nothing 404s.

### Admin Panel

`admin.html` provides a CRUD UI for the four resources behind a single login.

- Tabbed-style buttons switch between **Events**, **DJs**, **Tickets**, **Merchandise**.
- Each section renders an add/update form (with an optional `ID` field for editing existing records) and a card-grid list of current records.
- Each card has **Edit** and **Delete** buttons wired to `PUT /api/{resource}/{id}` and `DELETE /api/{resource}/{id}`.
- A **Logout** button in the navbar ends the session.

Anonymous users navigating to `/admin.html` are redirected to the login form by Spring Security.

## Implementation

### Backend Technology

| Technology | Purpose |
|---|---|
| Java 17 | Primary programming language |
| Spring Boot 3.2.5 | Application framework |
| Spring Web (MVC) | REST controllers |
| Spring Data JPA + Hibernate | ORM and data access |
| Spring Security | Form login + role-based authorization |
| H2 Database (in-memory) | Development database |
| springdoc-openapi | Auto-generated Swagger UI |

### Frontend Technology

The frontend is plain static HTML + vanilla JavaScript + a single shared `style.css`, served directly by Spring Boot from `src/main/resources/static/`. There is no Thymeleaf, no build step, and no JavaScript framework — each page loads its data from `/api/**` via `fetch`.

| Asset | Role |
|---|---|
| `css/style.css` | Shared dark-neon theme: navbar, cards, detail layouts, cart, login, footer |
| `js/cart-badge.js` | LocalStorage cart helper + navbar badge |
| `js/tickets.js`, `djs.js`, `shop.js` | List pages — fetch and render card grids |
| `js/event-detail.js`, `dj-detail.js`, `product-detail.js` | Detail pages |
| `js/cart.js` | Cart page rendering and Checkout |
| `js/admin.js` | Admin CRUD operations |

### Project Structure

```
MaddnessEventGit/
├── pom.xml
├── mvnw, mvnw.cmd, .mvn/wrapper/           Maven wrapper
├── README.md
├── openapi.yaml
└── src/main/
    ├── java/ch/fhnw/madnessevents/
    │   ├── MadnessEventsApplication.java   Spring Boot entry point + seed data
    │   ├── business/                       EventService, DjService, TicketService, MerchandiseService
    │   ├── controller/                     EventController, DjController, TicketController, MerchandiseController, HelloController
    │   ├── data/
    │   │   ├── domain/                     Event, Dj, Ticket, Merchandise
    │   │   └── repository/                 *Repository extends JpaRepository
    │   └── security/                       SecurityConfig
    └── resources/
        ├── application.properties
        └── static/
            ├── index.html, tickets.html, djs.html, shop.html
            ├── cart.html, login.html, admin.html
            ├── event-detail.html, dj-detail.html, product-detail.html
            ├── privacy.html, terms.html, cookies.html, ticket-tc.html
            ├── vulnerability.html, press.html
            ├── css/style.css
            ├── js/
            └── images/
```

## Execution

### Run Locally

**Prerequisites:** Java 17+ (or any JDK that runs Spring Boot 3.2).

```bash
./mvnw spring-boot:run         # Linux / macOS
.\mvnw.cmd spring-boot:run     # Windows PowerShell
```

The app starts on **http://localhost:8080**.

**Key URLs once running:**

| URL | Description |
|---|---|
| `http://localhost:8080/` | Home |
| `http://localhost:8080/tickets.html` | Tickets list |
| `http://localhost:8080/djs.html` | DJs list |
| `http://localhost:8080/shop.html` | Merchandise list |
| `http://localhost:8080/cart.html` | Shopping cart |
| `http://localhost:8080/login.html` | Admin login (`admin` / `admin123`) |
| `http://localhost:8080/admin.html` | Admin CRUD panel (requires login) |
| `http://localhost:8080/api/events` | Events JSON |
| `http://localhost:8080/api/djs` | DJs JSON |
| `http://localhost:8080/api/tickets` | Tickets JSON |
| `http://localhost:8080/api/merchandise` | Merchandise JSON |
| `http://localhost:8080/swagger-ui.html` | Interactive API docs |
| `http://localhost:8080/h2-console` | H2 database console |

**H2 console credentials:**

| Field | Value |
|---|---|
| JDBC URL | `jdbc:h2:mem:madnessdb` |
| User | `sa` |
| Password | *(empty)* |

### Run in GitHub Codespaces

1. Open the repository in Codespaces (`Code → Open with Codespaces → New codespace`).
2. In the terminal:
   ```bash
   chmod +x mvnw
   ./mvnw spring-boot:run
   ```
3. Codespaces auto-forwards port 8080 — click **Open in Browser** in the popup or use the **Ports** tab.
4. If the forwarded port is Private, right-click it in the Ports tab → **Port Visibility → Public**.

## Project Management

### Roles

| Role | Contribution |
|---|---|
| Backend developer | Designed JPA entities (Event, Dj, Ticket, Merchandise), repositories, services, REST controllers, Spring Security configuration with in-memory admin user and form login. Seeded sample data via `CommandLineRunner`. |
| Frontend developer | Built every static HTML page (Home, Tickets, DJs, Shop, Cart, Detail pages, Login, Admin, Footer stubs). Implemented the shared dark-neon CSS, the localStorage cart helper with live badge, dynamic data fetching from `/api/**`, and the cart / checkout flow. |

### Milestones

| Milestone | Description |
|---|---|
| Analysis | Scenario, use cases, user stories. |
| Prototype Design | Wireframes and visual identity. |
| Domain Design | Entities and relationships. |
| Backend Implementation | JPA entities, repositories, services, REST controllers, seed data. |
| Security Implementation | Spring Security with form login and role-based authorization. |
| Frontend Implementation | Static pages, dynamic data binding, detail pages, dark-neon styling. |
| Cart & Checkout | Client-side cart with localStorage, Buy/Add buttons, cart page, checkout. |
| Admin Panel | CRUD UI for all four resources with login protection. |
| Footer & Legal | Site-wide footer, legal stub pages. |

### Maintainers

- Charuta Pande
- Devid Montecchiari

### License

Apache License, Version 2.0
