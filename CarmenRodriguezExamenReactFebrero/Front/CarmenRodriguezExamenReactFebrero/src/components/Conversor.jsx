import React, { useState, useEffect} from 'react';
import monedasService from './../service/monedas.service.js';
import { useParams, useNavigate } from 'react-router-dom';
import './../styles/Conversor.css';

function Conversor() {
    const [monedas, setMonedas] = useState([]);
    const [selectedMoneda, setSelectedMoneda] = useState(null);
    const [currentMonedas, setCurrentMonedas] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);
    const [inputValue, setInputValue] = useState("");
    const [result, setResult] = useState(0);

    const retrieveMonedas = async () => {
        try {
          const data = await monedasService.getAll();
          setMonedas(data);
          setProgress(data.length);
        } catch (e) {
          console.log(e);
        }
      };
    
      useEffect(() => {
        retrieveMonedas();
      }, []);
  
    const handleConvert = () => {
      const value = parseFloat(inputValue);
      if (!isNaN(value) && value<=selectedMoneda.stock) {
        const conversion = (value * selectedMoneda.price);
        setResult(conversion.toFixed(2));
      } else {
        alert("No hay stock disponible para convertir esa cantidad");
      }
    };
  
    return (
      <div>
        <h1>Conversor de monedas
        </h1>
  
        {/* Input para ingresar el valor a convertir */}
        <div>
          <label htmlFor="value">Cantidad&nbsp;</label>
          <input 
            type="number" 
            id="value" 
            value={inputValue} 
            onChange={(e) => setInputValue(e.target.value)} 
          />
        &nbsp;&nbsp;
        {/* Resultado */}
        <label htmlFor="value">Equivalencia:&nbsp;</label>
          <input 
            type="text" 
            id="value" 
            value={result} 
            
          />
        </div>
  
        <div style={{ display: "flex", justifyContent: "space-around" }}>
          {/* Tabla de Moneda de Origen */}
          <div>
            Moneda de origen
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
                    setSelectedMoneda(product)
                    
                  }}
                >
                  <span>{product.name}</span>
                </li>
              ))
            ) : (
              <p className="text-center">No hay productos disponibles</p>
            )}
          </ul>
            <p>Seleccionado: {selectedMoneda ? selectedMoneda.name : "Ninguno"}</p>
          </div>
  
  
        {/* Botón para convertir */}
        <div>
          <button onClick={handleConvert}>Convertir</button>
        </div>
  
        
      </div>
      </div>
    );}
  
   {
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
    /*const [id, setId] = useState('');
  const [stock, setStock] = useState(0);
  const [name, setName] = useState('');
  const [brand, setBrand] = useState('');
  const [price, setPrice] = useState(0.0);
  const [active, setActive] = useState(false);
  //const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const monedas = await monedasService.getAll();
        const moneda = monedas.find((p) => p.id.toString() === id);
        if (moneda) {
          setId(moneda.id);
          setStock(moneda.stock);
          setName(moneda.name);
          setBrand(moneda.brand);
          setPrice(moneda.price);
          setActive(moneda.active);
        } else {
          console.log(`No se encontró un producto con el ID: ${id}`);
        }
      } catch (e) {
        console.log("Error al obtener los productos:", e);
      }
    };
    fetchProduct();
  }, [id]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = { id, stock, name, brand, price, active };
    try {
      await monedasService.update(id, formData);
      alert("Producto actualizado correctamente");
      navigate('/productos');
    } catch (error) {
      console.log(error);
      alert("Error al actualizar el producto");
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Actualizar Producto</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Nombre</label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Precio</label>
          <input
            type="number"
            className="form-control"
            name="price"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
            required
            min="1"  // Asegura que el precio no sea 0 o negativo
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Stock</label>
          <input
            type="number"
            className="form-control"
            name="stock"
            value={stock}
            onChange={(e) => setStock(e.target.value)}
            required
            min="0"  // Asegura que el stock sea al menos 1
          />
        </div>
        <button type="submit" className="btn btn-primary">Actualizar</button>
      </form>
    </div>
  );*/
}

export default Conversor;