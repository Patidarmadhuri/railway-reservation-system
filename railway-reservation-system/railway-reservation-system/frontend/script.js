/*
 * Client‑side logic for the railway reservation system.
 *
 * This JavaScript file attaches event handlers to the search form and
 * dynamically populates search results.  It also defines a `book` function
 * that sends a booking request to the backend API.  Keeping this code in
 * a separate file makes it easier to maintain and avoids cluttering
 * `index.html` with inline scripts.
 */

// Listen for form submission to search for trains
const searchForm = document.getElementById('searchForm');
const resultsDiv = document.getElementById('trainResults');

searchForm.addEventListener('submit', async (e) => {
  e.preventDefault();
  const origin = document.getElementById('origin').value;
  const destination = document.getElementById('destination').value;
  const date = document.getElementById('date').value;
  resultsDiv.innerHTML = '<p>Lädt…</p>';
  try {
    // Query the backend for trains matching the search criteria
    const resp = await fetch(
      `http://localhost:8080/api/trains/search?origin=${encodeURIComponent(
        origin
      )}&destination=${encodeURIComponent(destination)}&date=${date}`
    );
    const trains = await resp.json();
    if (trains.length === 0) {
      resultsDiv.innerHTML = '<p>Keine Züge gefunden.</p>';
      return;
    }
    // Build result cards for each train
    const html = trains
      .map((t) => {
        return `
          <div class="card mb-3">
            <div class="card-body">
              <h5 class="card-title">${t.trainNumber} – ${t.origin} ➜ ${t.destination}</h5>
              <p class="card-text">Abfahrt: ${t.departureTime} | Ankunft: ${t.arrivalTime}</p>
              <p class="card-text">Verfügbare Plätze: ${t.availableSeats}</p>
              <div class="input-group mb-3">
                <input type="number" min="1" max="${t.availableSeats}" class="form-control" placeholder="Plätze" id="seats-${t.id}">
                <input type="text" class="form-control" placeholder="Name" id="name-${t.id}">
                <input type="email" class="form-control" placeholder="E‑Mail" id="email-${t.id}">
                <button class="btn btn-success" onclick="book(${t.id})">Buchen</button>
              </div>
            </div>
          </div>
        `;
      })
      .join('');
    resultsDiv.innerHTML = html;
  } catch (err) {
    console.error(err);
    resultsDiv.innerHTML = '<p>Fehler beim Laden der Züge.</p>';
  }
});

// Book seats on a specific train
async function book(trainId) {
  const seats = document.getElementById('seats-' + trainId).value;
  const name = document.getElementById('name-' + trainId).value;
  const email = document.getElementById('email-' + trainId).value;
  if (!seats || !name || !email) {
    alert('Bitte geben Sie Name, E‑Mail und die gewünschte Anzahl an Plätzen ein.');
    return;
  }
  try {
    const params = new URLSearchParams({ trainId, name, email, seats });
    const resp = await fetch(
      `http://localhost:8080/api/reservations/book?${params.toString()}`,
      { method: 'POST' }
    );
    if (resp.ok) {
      alert('Reservierung erfolgreich!');
    } else {
      alert('Reservierung fehlgeschlagen.');
    }
  } catch (err) {
    console.error(err);
    alert('Fehler beim Buchen.');
  }
}