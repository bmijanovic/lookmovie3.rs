import React, { useEffect, useState } from "react";
import { API_BASE_URL } from "../App";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import NavbarComponent from "../Components/NavbarComponent";
import BasicPagination from "../Components/BasicPagination";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";

const HomePage = () => {
  const navigate = useNavigate();

  const [pagination, setPagination] = useState({ pageNumber: 1, pageSize: 12 });
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
      <NavbarComponent />
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
          <Card
            key={film.id}
            style={{
              margin: "10px",
              width: "200px",
              height: "300px",
              backgroundColor: "#333",
              display: "flex",
              justifyContent: "center",
            }}
          >
            <CardContent>
              <h3
                style={{
                  display: "flex",
                  justifyContent: "center",
                  alignItems: "center",
                }}
              >
                {film.name}
              </h3>
              {film.image && (
                <img
                  src={`${film.image}`}
                  alt={film.name}
                  style={{
                    width: "200px",
                    height: "230px",
                    objectFit: "cover",
                  }}
                />
              )}
              {/* <p>{film.description}</p> */} {/* Remove description */}
              {/* Add more details as needed */}
            </CardContent>
          </Card>
        ))}
      </div>
    </div>
  );
};

export default HomePage;
