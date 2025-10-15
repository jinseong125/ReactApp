import { Link, Outlet } from 'react-router-dom';

const Layout = () => {
  return (
    <div>
      <h1>Layout</h1>
      <nav>
        <Link to="board">Board</Link> | <Link to="user">User</Link>
      </nav>
      <Outlet />
    </div>
  );
};

export default Layout;