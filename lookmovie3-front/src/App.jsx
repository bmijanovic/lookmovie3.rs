import logo from "./logo.svg";
import LoginComponent from "./Pages/LoginPage";
import { ThemeProvider } from "@mui/material/styles";
import { themeOptions } from "./themeOptions";
import { Container } from "@mui/material";
import HomePage from "./Pages/HomePage";
import { BrowserRouter as Router } from "react-router-dom";
import { Routes, Route } from "react-router-dom";
import MoviesPage from "./Pages/MoviesPage";
import ForYouPage from "./Pages/ForYouPage";
import CreateMoviePage from "./Pages/CreateMoviePage";
import ProfilePage from "./Pages/ProfilePage";
import RegisterPage from "./Pages/RegisterPage";


export const API_BASE_URL = "http://localhost:8080/api";

function App() {
  return (
    <ThemeProvider theme={themeOptions}>
      <Router>
        <Routes>
          <Route path="/*" element={<HomePage />} >

          <Route path="movies" element={<MoviesPage />} />
          <Route path="for-you" element={<ForYouPage />} />
          <Route path="create-movie" element={<CreateMoviePage />} />
          <Route path="profile" element={<ProfilePage />} />





          </Route>
          <Route path="/login" element={<LoginComponent />} />
          <Route path="/register" element={<RegisterPage />} />

        </Routes>

        <Container sx={{ height: "100%" }}>

        </Container>
      </Router>
    </ThemeProvider>
  );
}

export default App;
