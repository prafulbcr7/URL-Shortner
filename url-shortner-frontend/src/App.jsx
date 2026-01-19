import { useState } from 'react'
import './App.css'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import LandingPage from './components/LandingPage'
import AboutPage from './components/AboutPage'
import NavBar from './components/NavBar'
import Footer from './components/Footer'
import RegisterPage from './components/RegisterPage'
import LoginPage from './components/LoginPage'
import { Toaster } from 'react-hot-toast'

function App() {

  return (
    <>
      <Router>
        <NavBar />
        <Toaster position='top-center'/>
        <Routes>
          <Route path='/' element={<LandingPage/>}/>
          <Route path='/about' element={<AboutPage/>}/>
          <Route path='/signup' element={<RegisterPage/>}/>
          <Route path='/login' element={<LoginPage/>}/>
        </Routes>
        <Footer />
      </Router>
    </>
  )
}

export default App
