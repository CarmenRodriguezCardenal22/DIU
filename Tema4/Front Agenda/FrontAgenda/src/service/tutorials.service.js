import http from "../http-common"; // Si se usa la misma base URL, ajusta si es diferente

class TutorialDataService {
  getAll() {
    return http.get("/tutorials"); // Asegúrate de que este endpoint existe en el backend
  }

  getByPersonId(dni) {
    return http.get(`/tutorials?dni=${dni}`); // Ajusta según el endpoint real
  }
}

export default new TutorialDataService();

