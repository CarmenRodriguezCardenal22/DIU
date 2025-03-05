import http from "../http-common2";

class AgendaDataService {
  // Obtener todas las personas en la agenda
  getAll() {
    return http.get("/Agenda");
  }

  // Obtener una persona específica por su ID
  get(id) {
    return http.get(`/Agenda/${id}`);
  }

  // Crear una nueva persona en la agenda
  create(data) {
    return http.post("/Agenda", data);
  }

  // Actualizar datos de una persona en la agenda
  update(id, data) {
    return http.put(`/Agenda/${id}`, data);
  }

  // Eliminar una persona de la agenda
  delete(id) {
    return http.delete(`/Agenda/${id}`);
  }

  // Eliminar todas las personas de la agenda
  deleteAll() {
    return http.delete("/Agenda");
  }

  // Buscar personas por nombre
  findByTitle(name) {
    return http.get(`/Agenda/name/${name}`);
  }

  // Obtener tutoriales asociados a una persona
  getTutorialsByPersonId(personId) {
    return http.get(`/Tutorials/person/${personId}`);
  }
}

export default new AgendaDataService();
