import React from 'react'
import './calculadora.css'

function calculadora() {
  return (
    <div>
        <div className='div'>
            <button>AC</button>
            <button>+/-</button>
            <button>%</button>
            <button className='op'>/</button>
        </div>
        <div className='div'>
            <button>7</button>
            <button>8</button>
            <button>9</button>
            <button className='op'>*</button>
        </div>
        <div className='div'>
            <button>4</button>
            <button>5</button>
            <button>6</button>
            <button className='op'>-</button>
        </div>
        <div className='div'>
            <button>1</button>
            <button>2</button>
            <button>3</button>
            <button className='op'>+</button>
        </div>
        <div className='div'>
            <button>0</button>
            <button>.</button>
            <button className='op'>=</button>
        </div>
  </div>
  )
}

export default calculadora