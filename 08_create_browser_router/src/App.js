import logo from './logo.svg';
import './App.css';
import { router } from './routes/router';

function App() {
  return (
    <div className="App">
      <h1>App</h1>
      <nav>
        <Link to="/">홈</Link> | <Link to="/about">소개</Link> | <Link to="/board">board</Link>
      </nav>
      <RouterProvider router={router} />
     </div>
  );
}

export default App;
