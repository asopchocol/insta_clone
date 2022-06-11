import Home from "../../pages/Home";
import LoginPage from "../../pages/users/Login";
import SignupPage from "../../pages/users/Signup";
import ProfilePage from "../../pages/users/Profile";

const routes = [
  { path: "/", components: Home },
  { path: "/login", components: LoginPage },
  { path: "/signup", components: SignupPage },
  { path: "/Profile", components: ProfilePage },
];

export default routes;
