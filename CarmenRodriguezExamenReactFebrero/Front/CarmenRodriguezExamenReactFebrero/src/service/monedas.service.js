import http from './../http-common'; 

const productService = {
  getAll: async () => {
    const response = await http.get('/products');  
    return response.data;
  },
  create: async (product) => {
    const response = await http.post('/products', product); 
    return response.data;
  },
  update: async (id, product) => {
    const response = await http.put(`/products/${id}`, product);
    return response.data;
  },
  delete: async (id) => {
    await http.delete(`/products/${id}`);
  },
};

export default productService;