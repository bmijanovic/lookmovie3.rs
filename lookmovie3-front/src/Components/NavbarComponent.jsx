import React from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import { API_BASE_URL } from "../App";


const NavbarComponent = ({ loggedUser }) => {
  const navigate = useNavigate();

  const handleLogout = async () => {
    axios
      .post(`${API_BASE_URL}/auth/logout`, {}, { withCredentials: true })
      .then((response) => {
        console.log(response);
        navigate("/login");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const goHome = () => {
    navigate("/");
  };

  return (
    <Box sx={{ flexGrow: 1, margin: 0 }}>
      <AppBar position="static">
        <Toolbar>
          <Typography
            variant="h4"
            component="div"
          >
            Lookmovie3
          </Typography>
          
          {loggedUser && (
            <>
              <Link to="/movies" style={{ textDecoration: 'none', color: 'white' }}>
                <Button color="inherit" sx={{ marginLeft: "10px" }}>
                  Home
                </Button>
              </Link>
              <Link to="/for-you" style={{ textDecoration: 'none', color: 'white' }}>
                <Button color="inherit" sx={{ marginLeft: "10px" }}>
                  For You
                </Button>
              </Link>
              <Link to="/profile" style={{ textDecoration: 'none', color: 'white' }}>
                <Button color="inherit" sx={{ marginLeft: "10px" }}>
                  Profile
                </Button>
              </Link>
            </>
          )}

          {loggedUser.role === "ROLE_ADMIN" && (
            <>
              <Link to="/create-movie" style={{ textDecoration: 'none', color: 'white' }}>
                <Button color="inherit" sx={{ marginLeft: "10px" }}>
                  Create Movie
                </Button>
              </Link>
              
            </>
          )}
          {loggedUser != null &&(<Typography sx={{ position: "absolute", marginRight: "120px", right: 0 }}>
            {loggedUser.name}
          </Typography>)}

          <Button
            color="inherit"
            sx={{ position: "absolute", marginRight: "5px", right: 0 }}
            onClick={handleLogout}
          >
            Logout
          </Button>
        </Toolbar>
      </AppBar>
    </Box>
  );
};

export default NavbarComponent;
