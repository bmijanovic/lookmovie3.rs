import React, { useEffect, useState } from "react";
import axios from "axios";
import { Box, Typography } from "@mui/material";
import { API_BASE_URL } from "../App";
import FilmCardComponent from "../Components/FilmCardComponent";

const ForYouPage = () => {
  const [globalFilms, setGlobalFilms] = useState([]);
  const [forYouFilms, setForYouFilms] = useState([]);

  useEffect(() => {
    fetchGlobalFilms();
    fetchForYouFilms();
  }, []);

  const fetchGlobalFilms = () => {
    axios
      .get(`${API_BASE_URL}/film-recommendations/global-recommend`, {
        withCredentials: true,
      })
      .then((response) => {
        setGlobalFilms(response.data);
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const fetchForYouFilms = () => {
    axios
      .get(`${API_BASE_URL}/film-recommendations/recommend`, {
        withCredentials: true,
      })
      .then((response) => {
        setForYouFilms(response.data);
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <Typography
        variant="h4"
        gutterBottom
        sx={{
          display: "flex",
          alignItems: "center",
          justifyContent: "left",
          marginTop: "20px",
          marginLeft: "80px",
        }}
      >
        Global Films
      </Typography>
      <Box
        sx={{
          display: "flex",
          overflowX: "auto",
          gap: 1,
          alignItems: "center",
          justifyContent: "center",
        }}
      >
        {globalFilms.map((film) => (
          <Box key={film.id} sx={{ flex: "0 0 auto" }}>
            <FilmCardComponent film={film} />
          </Box>
        ))}
      </Box>

      <Typography
        variant="h4"
        gutterBottom
        style={{
          marginTop: "20px",
          display: "flex",
          alignItems: "center",
          justifyContent: "left",
          marginLeft: "80px",

        }}
      >
        For You
      </Typography>
      <Box
        sx={{
          display: "flex",
          overflowX: "auto",
          alignItems: "center",
          justifyContent: "center",
          gap: 1,
        }}
      >
        {forYouFilms.map((film) => (
          <Box key={film.id} sx={{ flex: "0 0 auto" }}>
            <FilmCardComponent film={film} />
          </Box>
        ))}
      </Box>
    </div>
  );
};

export default ForYouPage;
