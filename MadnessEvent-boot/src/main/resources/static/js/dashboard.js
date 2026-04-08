// Update current time
function updateTime() {
    const now = new Date();
    const timeString = now.toLocaleTimeString('en-US', { 
        hour: '2-digit', 
        minute: '2-digit', 
        second: '2-digit',
        hour12: true 
    });
    document.getElementById('currentTime').textContent = timeString;
}

// Update time immediately and then every second
updateTime();
setInterval(updateTime, 1000);

// Initialize Chart.js
document.addEventListener('DOMContentLoaded', function() {
    const ctx = document.getElementById('eventsChart');
    if (ctx) {
        new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['January', 'February', 'March', 'April', 'May', 'June'],
                datasets: [{
                    label: 'Events',
                    data: [10, 15, 12, 20, 18, 22],
                    borderColor: '#007bff',
                    backgroundColor: 'rgba(0, 123, 255, 0.1)',
                    borderWidth: 3,
                    fill: true,
                    tension: 0.4,
                    pointRadius: 6,
                    pointBackgroundColor: '#007bff',
                    pointBorderColor: '#fff',
                    pointBorderWidth: 2,
                    pointHoverRadius: 8
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: true,
                plugins: {
                    legend: {
                        display: true,
                        position: 'top',
                        labels: {
                            font: {
                                size: 14,
                                weight: 600
                            },
                            padding: 20,
                            usePointStyle: true
                        }
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        max: 25,
                        ticks: {
                            stepSize: 5,
                            font: {
                                size: 12
                            }
                        },
                        grid: {
                            color: 'rgba(0, 0, 0, 0.05)',
                            drawBorder: false
                        }
                    },
                    x: {
                        ticks: {
                            font: {
                                size: 12
                            }
                        },
                        grid: {
                            display: false,
                            drawBorder: false
                        }
                    }
                }
            }
        });
    }

    // Add event listeners for interactivity
    addTableRowHover();
    addCardClickHandlers();
});

// Enhanced table row hover effect
function addTableRowHover() {
    const tableRows = document.querySelectorAll('.table tbody tr');
    tableRows.forEach(row => {
        row.addEventListener('mouseenter', function() {
            this.style.backgroundColor = 'rgba(0, 123, 255, 0.1)';
        });
        row.addEventListener('mouseleave', function() {
            this.style.backgroundColor = '';
        });
    });
}

// Card click handlers
function addCardClickHandlers() {
    const cards = document.querySelectorAll('.stat-card');
    cards.forEach(card => {
        card.addEventListener('click', function() {
            console.log('Card clicked:', this);
            // Add custom logic here if needed
        });
    });
}

// Utility function to format numbers
function formatNumber(num) {
    return num.toLocaleString('en-US', {
        minimumFractionDigits: 0,
        maximumFractionDigits: 2
    });
}

// Auto-refresh dashboard every 5 minutes (optional)
// Uncomment to enable auto-refresh
// setInterval(function() {
//     location.reload();
// }, 300000);
