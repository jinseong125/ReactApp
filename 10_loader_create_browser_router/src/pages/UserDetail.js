const UserDetail = () => {
  const user = useLoaderData();
  return (
    <div>
      <h1>사용자 상세</h1>
      <p>ID: {user.id}</p>
      <p>Name: {user.name}</p>
      <p>Email: {user.email}</p>
    </div>
  );
};

export default UserDetail;