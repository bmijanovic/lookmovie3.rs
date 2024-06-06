import React, { useEffect, useState } from "react";
import { API_BASE_URL } from "../App";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import NavbarComponent from "../Components/NavbarComponent";
import BasicPagination from "../Components/BasicPagination";
import FilmCardComponent from "../Components/FilmCardComponent";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";

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

  const handleSearchChange = (event) => {
    setSearch(event.target.value);
  };

  const handleSearchSubmit = () => {
    setPagination({ ...pagination, pageNumber: 1 }); // Reset to first page on new search
    fetchFilms();
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
      <Box display="flex" justifyContent="center" mb={2} mt={2}>
        <TextField
          label="Search Films"
          variant="outlined"
          value={search}
          onChange={handleSearchChange}
          onKeyPress={(event) => {
            if (event.key === 'Enter') {
              handleSearchSubmit();
            }
          }}
          style={{ marginRight: 8 }}
        />
        <Button variant="contained" onClick={handleSearchSubmit}>
          Search
        </Button>
      </Box>

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
          <FilmCardComponent key={film.id} film={film} />
        ))}
      </div>
    </div>
  );
};

export default MoviesPage;
