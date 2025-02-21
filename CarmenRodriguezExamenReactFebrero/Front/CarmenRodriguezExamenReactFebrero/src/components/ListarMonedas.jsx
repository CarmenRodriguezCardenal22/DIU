import React, { useState, useEffect, useContext } from 'react';
import monedasService from './../service/monedas.service.js';
import './../styles/ListarMonedas.css';

function ListarMonedas() {
  const [monedas, setMonedas] = useState([]);
  const [currentMonedas, setCurrentMonedas] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);
  const [quantity, setQuantity] = useState(1);  
  //const { setProgress } = useContext(MonedasContext);

  const retrieveMonedas = async () => {
    try {
      const data = await monedasService.getAll();
      setMonedas(data);
      //setProgress(data.length);
    } catch (e) {
      console.log(e);
    }
  };

  useEffect(() => {
    retrieveMonedas();
  }, []);

  const handleDelete = async (id, event) => {
    event.preventDefault();
    try {
      await monedasService.delete(id);
      retrieveMonedas();
      setCurrentMonedas(null);
    } catch (e) {
      console.log(e);
    }
  };

  const handleQuantityChange = () => {
    setQuantity(quantity + 10);
  };

  const handleAddToCart = async () => {
    if (currentMonedas) {
        handleQuantityChange()
      if (quantity > currentMonedas.stock) {
        alert('No puedes seleccionar más cantidad de la disponible en stock.');
      } else {
        const updatedProduct = { ...currentMonedas, stock: currentMonedas.stock + 10 };
        setCurrentMonedas(updatedProduct);

        try {
          await monedasService.update(currentMonedas.id, updatedProduct);
          alert(`Añadido ${quantity} ${currentMonedas.name} al carrito.`);
        } catch (e) {
          console.log('Error al actualizar el stock en el servidor', e);
        }
      }
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center mb-4">Lista de Monedas</h2>
      <div className="row">
        <div className="col-md-6">
          <ul className="list-group">
            {monedas.length > 0 ? (
              monedas.map((product, index) => (
                <li
                  key={index}
                  className={`list-group-item d-flex justify-content-between align-items-center ${
                    index === currentIndex ? 'list-group-item-primary' : ''
                  }`}
                  onClick={() => {
                    setCurrentMonedas(product);
                    setCurrentIndex(index);
                    setQuantity(1);  
                  }}
                >
                  <span>{product.name}</span>
                </li>
              ))
            ) : (
              <p className="text-center">No hay productos disponibles</p>
            )}
          </ul>
        </div>

        {currentMonedas ? (
          <div className="col-md-6">
            <div className="border p-3 carta">
              <h4 className="text-center">Detalles de la Moneda</h4>
              <p><strong>Id:</strong> {currentMonedas.id}</p>
              <p><strong>Stock:</strong> {currentMonedas.stock}</p>
              <p><strong>Nombre:</strong> {currentMonedas.name}</p>
              <p><strong>Descripción:</strong> {currentMonedas.brand}</p>
              <p><strong>Precio:</strong> {currentMonedas.price} €</p>
              <p><strong>Activo:</strong> {currentMonedas.active}</p>
              {/* <div className="mb-3">
                <label htmlFor="quantity" className="form-label">Stock +10</label>
                <input
                  type="number"
                  id="quantity"
                  className="form-control"
                  value={10}
                  onChange={handleQuantityChange}
                  min="1"
                  max={currentMonedas.stock}  // Impide seleccionar más de lo que hay en stock
                />
              </div> */}
              <button className="btn btn-secondary" onClick={() => setCurrentMonedas(null)}>Volver a la lista</button>
              <br></br>
              <button className="btn btn-primary mt-2" onClick={handleAddToCart}>Stock 10</button>
            </div>
          </div>
        ) : (
          <div className="col-md-6">
            <div className="border p-3">
              <h4 className="text-center ¡">Selecciona una moneda para ver detalles</h4>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default ListarMonedas;