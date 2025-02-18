import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import HeaderComponent from './components/HeaderComponent'
import ButtonComponent from './components/ButtonComponent'
import Login from './components/Login'
import { use } from 'react'
import MovieList from './components/MovieList'
import AnimalList from './components/AnimalList'

function App() {
  //let number = 0;
  const [number, setNumber] = useState(0);
  let myPlaceHolder = "Escribe aqui";
  const [myValue, setMyValue] = useState("");

  const [greetings, setGreetings] = useState("Bienvenidos a mi web");
  const links = {
    home: "Home",
    blog: "Blog",
    news: "News",
    contact: "Contact us"
  }

  const condition = true;

  const [user, setUser] = useState({})

  const login = (userInfo) => {
    console.log(userInfo)
    setUser(userInfo)
  }

  const [showMovies, setShowMovies] = useState(true);

  //useEffect(() => {
  //  console.log("Ejecución cada vez que se renderiza el componente raíz")
  //})
  //useEffect(() => {
  // console.log("Ejecución por cada cambio de la variable reactiva user")
  //}, [user])

  const addOne = () => {
    //number++;
    setNumber(number + 1);
    console.log(number)
  }

  const sayHello = () => {
    console.log("Hello")
  } 

  const handleChange = (e) => {
    console.log(e.target.value)
  }

  return (
    <>
      <HeaderComponent greetings = {greetings} links = {links}></HeaderComponent>
      <main className='mainContent'>
        {user.username && <h2 onClick={sayHello}>Hola {user.username}</h2>}

        <Login handleLogin={login}></Login>

        <h2 onClick={addOne}>Number: {number}</h2>

        <input value={myValue} placeholder={myPlaceHolder} type="text" onChange={handleChange}/>

        <br></br>
        <br></br>
        <ButtonComponent></ButtonComponent>

        {condition && <h2>La condición se cumple</h2>}
        {!condition && <h2>La condición no se cumple</h2>}

        {condition ? (
          <h2>La condición se cumple</h2>
        ) : (
          <h2>La condición no se cumple</h2>
        )}

        <button onClick={() => setShowMovies(!showMovies)}>Toggle Movies</button>
        {showMovies && <MovieList></MovieList>}
        <AnimalList></AnimalList>
      </main>
    </>
  )
}

export default App
