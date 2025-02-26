import http from "../http-common2";

class AgendaDataService {
  getAll() {
    return http.get("/Agenda");
  }

  get(id) {
    return http.get(`/Agenda/${id}`); 
  }

  create(data) {
    console.log(data);
    return http.post("/Agenda", data);
  }

  update(id, data) {
    return http.put(`/Agenda/${id}`, data); 
  }

  delete(id) {
    return http.delete(`/Agenda/${id}`); 
  }

  deleteAll() {
    return http.delete(`/Agenda`);
  }

  findByTitle(name) {
    return http.get(`/Agenda/name/${name}`); 
  }
}

export default new AgendaDataService();
