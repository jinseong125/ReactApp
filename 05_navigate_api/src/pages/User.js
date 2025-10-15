import { useParams, useNavigate } from 'react-router-dom';

const User = () => {
  const { uid } = useParams();
  const navigate = useNavigate();
  return (
    <div>
      <h1>User</h1>
      <h3>User ID: {uid}</h3>
      <button onClick={() => navigate("/")}>Home</button>
    </div>
  );
};

export default User;