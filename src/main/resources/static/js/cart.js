(function () {
    const emptyEl = document.getElementById('cart-empty');
    const tableEl = document.getElementById('cart-table');
    const rowsEl = document.getElementById('cart-rows');
    const totalEl = document.getElementById('cart-total');
    const checkoutBlock = document.getElementById('checkout-block');
    const checkoutBtn = document.getElementById('checkout-btn');
    const status = document.getElementById('checkout-status');

    function esc(s) {
        return String(s == null ? '' : s).replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
    }

    function render() {
        const items = window.Cart.read();
        if (!items.length) {
            emptyEl.hidden = false;
            tableEl.hidden = true;
            checkoutBlock.hidden = true;
            return;
        }
        emptyEl.hidden = true;
        tableEl.hidden = false;
        checkoutBlock.hidden = false;

        rowsEl.innerHTML = '';
        items.forEach(item => {
            const row = document.createElement('tr');
            const photo = item.photo || '';
            const typeLabel = item.type === 'product' ? 'Merch' : 'Ticket';
            row.innerHTML =
                "<td class='cart-item-cell'>" +
                    (photo ? "<div class='cart-thumb' style=\"background-image: url('" + esc(photo) + "')\"></div>" : "") +
                    "<div>" +
                        "<div class='cart-item-name'>" + esc(item.name) + "</div>" +
                        "<div class='cart-item-type'>" + typeLabel + "</div>" +
                    "</div>" +
                "</td>" +
                "<td>" + Number(item.price).toFixed(2) + "</td>" +
                "<td>" +
                    "<button class='qty-btn' data-id='" + item.id + "' data-delta='-1'>-</button>" +
                    "<span class='qty-val'>" + item.qty + "</span>" +
                    "<button class='qty-btn' data-id='" + item.id + "' data-delta='1'>+</button>" +
                "</td>" +
                "<td>" + (item.qty * Number(item.price)).toFixed(2) + "</td>";
            rowsEl.appendChild(row);
        });
        totalEl.textContent = window.Cart.total().toFixed(2);
    }

    document.addEventListener('click', e => {
        const qtyBtn = e.target.closest('.qty-btn');
        if (!qtyBtn) return;
        const id = qtyBtn.dataset.id;
        const delta = Number(qtyBtn.dataset.delta);
        const item = window.Cart.read().find(i => String(i.id) === String(id));
        if (item) window.Cart.setQty(id, item.qty + delta);
        render();
    });

    checkoutBtn.addEventListener('click', () => {
        const items = window.Cart.read();
        if (!items.length) return;
        // Front-end checkout: no real payment yet. Clear and confirm.
        window.Cart.clear();
        render();
        status.textContent = 'Order confirmed! Your tickets and merch are on the way.';
    });

    render();
})();
