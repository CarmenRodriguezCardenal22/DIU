function Login(props) {
  const user = {
    username: "",
    email: ""
  }  

  const setUsername = (e) => {
    user.username = e.target.value;
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(user)
    props.handleLogin(user);
  }

  return (
    <section>
        <h2>Login Section</h2>
        <form onSubmit={handleSubmit}>
            <input type="text" id="username" onChange={setUsername}/>
            <button>Login</button>
        </form>
    </section>
  )
}

export default Login