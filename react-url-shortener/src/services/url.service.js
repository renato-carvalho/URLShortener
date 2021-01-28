import http from "../http-common";

class UrlService {
  
  save(data) {
    return http.post("/save", data);
  }

  getById(originalUrl) {
    return http.get(`/getById?title=${originalUrl}`);
  }
}

export default new UrlService();
