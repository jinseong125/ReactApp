import logo from './logo.svg';
import './App.css';
import { Link, Routes, Route } from 'react-router-dom';
import { lazy, Suspense } from 'react';

// import Home from './pages/Home';
// import About from './pages/About';
// import Board from './pages/Board';

// lazy loading으로 컴포넌트 불러오기
const Home = lazy(() => import('./pages/Home'));
const About = lazy(() => import('./pages/About'));
const Board = lazy(() => import('./pages/Board'));

// loading 중 표시할 컴포넌트
const Loading = () => {
  return (
    <div>Loading...</div>
  );
};

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" style={{width: '100px'}}/>
        <nav>
          <Link to="/">Home</Link> | <Link to="/about">About</Link> | <Link to="/board">Board</Link>
        </nav>
      </header>
      <main>
        <Suspense fallback={Loading()}>
          <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path="/about" element={<About/>}/>
            <Route path="/board" element={<Board/>}/>
          </Routes>
        </Suspense>
      </main>
    </div>
  );
}

export default App;
