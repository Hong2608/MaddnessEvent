(function () {
    const KEY = 'madness_cart';

    function read() {
        try { return JSON.parse(localStorage.getItem(KEY)) || []; } catch (e) { return []; }
    }
    function write(items) { localStorage.setItem(KEY, JSON.stringify(items)); updateBadge(); }

    function itemKey(item) {
        if (item.key) return String(item.key);
        return String(item.id) + (item.size ? '|' + item.size : '');
    }

    function add(item) {
        const items = read();
        const key = itemKey(item);
        const existing = items.find(i => itemKey(i) === key);
        if (existing) existing.qty += 1;
        else items.push(Object.assign({ qty: 1, key: key }, item));
        write(items);
    }
    function setQty(id, qty) {
        const items = read().map(i => itemKey(i) === String(id) ? Object.assign({}, i, { qty: qty }) : i).filter(i => i.qty > 0);
        write(items);
    }
    function remove(id) { write(read().filter(i => itemKey(i) !== String(id))); }
    function clear() { write([]); }
    function count() { return read().reduce((s, i) => s + i.qty, 0); }
    function total() { return read().reduce((s, i) => s + i.qty * Number(i.price || 0), 0); }

    function updateBadge() {
        const el = document.getElementById('nav-cart-count');
        if (!el) return;
        const c = count();
        el.textContent = c;
        el.hidden = c === 0;
    }

    window.Cart = { read, add, setQty, remove, clear, count, total, updateBadge };
    document.addEventListener('DOMContentLoaded', updateBadge);
})();
