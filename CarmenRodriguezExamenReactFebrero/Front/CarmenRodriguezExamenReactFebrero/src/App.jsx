import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { MonedasContext } from './providers/MonedasContext.jsx';
import ListarMonedas from './components/ListarMonedas.jsx';
import Conversor from './components/Conversor.jsx';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import ProgressBar from './components/ProgressBar.jsx';
import Navbar from './components/Navbar.jsx';

function App() {
  return (
    <MonedasContext>
      <Router>
        <Navbar /> 
        <div className="container mt-5 pt-4">
          <ProgressBar /> 
          <Routes>
            <Route path="/conversor" element={<Conversor />} />
            <Route path="/productos" element={<ListarMonedas />} />
            <Route path="/" element={<ListarMonedas />} />
          </Routes>
        </div>
      </Router>
    </MonedasContext>
  );
}

export default App;