import React from "react";
import { Link, useNavigate } from "react-router-dom";
import BasicButton from "./BasicButton";
import axios from "axios";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
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
            onClick={goHome}
            variant="h5"
            component="div"
            sx={{ cursor: "pointer" }}
          >
            Lookmovie3
          </Typography>
          <Link to={"movies"} sx ={{color:'white'}}>
            <Button color="inherit" sx={{ marginLeft: "10px" }}>
              Home
            </Button>
          </Link>
          <Link to={"for-you"}>
            <Button color="inherit" sx={{ marginLeft: "10px" }}>
              For You
            </Button>
          </Link>

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
