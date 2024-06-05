import React, { useEffect, useState } from "react";
import { API_BASE_URL } from "../App";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import NavbarComponent from "../Components/NavbarComponent";
import BasicPagination from "../Components/BasicPagination";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import FilmCardComponent from "../Components/FilmCardComponent";
import MoviesPage from "./MoviesPage";
import { Outlet } from "react-router-dom";


const HomePage = () => {
  const navigate = useNavigate();

  useEffect(() => {
    navigate("/movies");
  }, []);

 

  return (
    <div>
      <NavbarComponent></NavbarComponent>
      <Outlet style={{ flex: 1, overflowY: "auto" }}></Outlet>


      
    </div>
  );
};

export default HomePage;
