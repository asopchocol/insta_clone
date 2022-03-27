import Home from "../../pages/Home";
import LoginPage from "../../pages/users/Login";
import SignupPage from "../../pages/users/Signup";

const routes = [
  { path: "/", components: Home },
  { path: "/login", components: LoginPage },
  { path: "/signup", components: SignupPage },
];

export default routes;
