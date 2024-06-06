import React, { useEffect, useState } from "react";
import { API_BASE_URL } from "../App";
import axios from "axios";
import { Card, CardContent, Typography, Grid } from "@mui/material";

const ProfilePage = () => {
    const [favouriteGenres, setFavouriteGenres] = useState({});
    const [favouriteFilms, setFavouriteFilms] = useState({});
    const [favouriteFilmRating, setFavouriteFilmRating] = useState({});
    const [favouriteDirectors, setFavouriteDirectors] = useState({});
    const [favouriteActors, setFavouriteActors] = useState({});
    const [favouriteWatchtime, setFavouriteWatchtime] = useState({});

    useEffect(() => {
        axios.get(`${API_BASE_URL}/reports/users-favourite-genres`, { withCredentials: true })
            .then((response) => {
                setFavouriteGenres(response.data);
                console.log(response);
            })
            .catch((error) => {
                console.log(error);
            });

        axios.get(`${API_BASE_URL}/reports/users-favourite-films`, { withCredentials: true })
            .then((response) => {
                setFavouriteFilms(response.data);
                console.log(response);
            })
            .catch((error) => {
                console.log(error);
            });

        axios.get(`${API_BASE_URL}/reports/users-favourite-film-rating`, { withCredentials: true })
            .then((response) => {
                setFavouriteFilmRating(response.data);
                console.log(response);
            })
            .catch((error) => {
                console.log(error);
            });

        axios.get(`${API_BASE_URL}/reports/users-favourite-directors`, { withCredentials: true })
            .then((response) => {
                setFavouriteDirectors(response.data);
                console.log(response);
            })
            .catch((error) => {
                console.log(error);
            });

        axios.get(`${API_BASE_URL}/reports/users-favourite-actors`, { withCredentials: true })
            .then((response) => {
                setFavouriteActors(response.data);
                console.log(response);
            })
            .catch((error) => {
                console.log(error);
            });

        axios.get(`${API_BASE_URL}/reports/users-favourite-watchtime`, { withCredentials: true })
            .then((response) => {
                setFavouriteWatchtime(response.data);
                console.log(response);
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);

    const findHighestValue = (data) => {
        if (!data || typeof data !== 'object' || Object.keys(data).length === 0) {
            return "No data available";
        }

        const highest = Object.entries(data).reduce((max, [key, value]) => {
            return value > max.value ? { key, value } : max;
        }, { key: "", value: -Infinity });

        return `${highest.key}: ${highest.value}`;
    };

    const renderCard = (title, data) => (
        <Card sx={{ minWidth: 275, marginBottom: 2 ,backgroundColor:"#333"}}>
            <CardContent>
                <Typography variant="h5" component="div">
                    {title}
                </Typography>
                <Typography sx={{ mb: 1.5 }} color="text.secondary">
                    {findHighestValue(data)}
                </Typography>
            </CardContent>
        </Card>
    );

    return (
        <Grid container spacing={2} sx = {{marginTop:"10px"}}>
            <Grid item xs={12} sm={4}>
                {renderCard("Favourite Genres", favouriteGenres)}
            </Grid>
            <Grid item xs={12} sm={4}>
                {renderCard("Favourite Films", favouriteFilms)}
            </Grid>
            <Grid item xs={12} sm={4}>
                {renderCard("Favourite Film Rating", favouriteFilmRating)}
            </Grid>
            <Grid item xs={12} sm={4}>
                {renderCard("Favourite Directors", favouriteDirectors)}
            </Grid>
            <Grid item xs={12} sm={4}>
                {renderCard("Favourite Actors", favouriteActors)}
            </Grid>
            <Grid item xs={12} sm={4}>
                {renderCard("Favourite Watchtime", favouriteWatchtime)}
            </Grid>
        </Grid>
    );
};

export default ProfilePage;
