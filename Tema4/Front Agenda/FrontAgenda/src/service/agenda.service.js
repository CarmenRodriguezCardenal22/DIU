import http from "../http-common2";

class AgendaDataService {
  getAll() {
    return http.get("/Agenda");
  }

  get(id) {
    return http.get(`/Agenda/${dni}`);
  }

  create(data) {
    console.log(data);
    return http.post("/Agenda", data);
  }

  update(id, data) {
    return http.put(`/Agenda/${dni}`, data);
  }

  delete(id) {
    return http.delete(`/Agenda/${dni}`);
  }

  deleteAll() {
    return http.delete(`/Agenda`);
  }

 // findByTitle(title) {
 //   return http.get(`/tutorials?title=${title}`);
 // }
 findByTitle(title) {
     return http.get(`/Agenda/name/${name}`);
   }
}

export default new AgendaDataService();