import { Component, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Contador from './components/Contador'

//Con clases
class App extends Component {
  constructor(){
    super();
      this.state = {
        num: 0
      }
  }
  incrementar=(event)=>{
    this.setState({
        num: num++
    })
  }
  decrementar=(event)=>{
    this.setState({
        num: num--
    })
  }
  resetear=(event)=>{
    this.setState({
        num: 0
    })
  }
  render(){
    return(
      <div>
        <Contador num = {this.state.num} incrementar = {this.incrementar} decrementar = {this.decrementar} resetear = {this.resetear}></Contador>
      </div>
    )
  }
}


//Con funciones

/*function App() {
  const[num, setNum] = useState(0)
  const incrementar = () => {
    setNum(num + 1);
  }
  const decrementar = () => {
    setNum(num - 1);
  }
  const resetear = () => {
    setNum(0);
  }

  return (
    <>
      <div>
        <Contador num = {num} incrementar = {incrementar} decrementar = {decrementar} resetear = {resetear}></Contador>
      </div>
    </>
  )
}*/

export default App
