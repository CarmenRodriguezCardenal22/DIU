import http from "../http-common"; // Si se usa la misma base URL, ajusta si es diferente

class TutorialDataService {
  getByPersonId(personId) {
    return http.get(`/Tutorials/person/${personId}`);
  }
}

export default new TutorialDataService();
