import React, { useEffect, useState } from "react";
import { API_BASE_URL } from "../App";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import NavbarComponent from "../Components/NavbarComponent";
import BasicPagination from "../Components/BasicPagination";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import FilmCardComponent from "../Components/FilmCardComponent";


const MoviesPage = () => {
  const navigate = useNavigate();

  const [pagination, setPagination] = useState({ pageNumber: 1, pageSize: 10 });
  const [totalItems, setTotalItems] = useState(0);
  const [search, setSearch] = useState("");
  const [user, setUser] = useState(null);
  const [films, setFilms] = useState([]);

  useEffect(() => {
    axios
      .get(`${API_BASE_URL}/auth/me`, { withCredentials: true })
      .then((response) => {
        console.log(response);
        setUser(response.data);
      })
      .catch((error) => {
        console.log(error);
        navigate("/login");
      });
  }, []);

  useEffect(() => {
    fetchFilms();
  }, [user, pagination.pageNumber, pagination.pageSize]);

  const handlePageChange = (newPage) => {
    setPagination({
      ...pagination,
      pageNumber: newPage,
    });
  };

  const fetchFilms = () => {
    console.log("Page:", pagination.pageNumber);
    axios
      .get(`${API_BASE_URL}/films/`, {
        params: {
          Search: search,
          Page: pagination.pageNumber,
          PageSize: pagination.pageSize,
        },
        withCredentials: true,
      })
      .then((response) => {
        setFilms(response.data.items);
        setTotalItems(response.data.totalItems);
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
        
      <BasicPagination
        currentPage={pagination.pageNumber}
        pageSize={pagination.pageSize}
        totalItems={totalItems}
        onPageChange={handlePageChange}
      />
      <div
        style={{
          display: "flex",
          flexWrap: "wrap",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        {films.map((film) => (
          <FilmCardComponent film={film}  />
        ))}
      </div>
    </div>
  );
};

export default MoviesPage;
