import http from "../http-common"; // Si se usa la misma base URL, ajusta si es diferente

class TutorialDataService {
  getByPersonId(dni) {
    return http.get(`/tutorials?dni=${dni}`); // Ajusta según el endpoint real
  }
}

export default new TutorialDataService();
