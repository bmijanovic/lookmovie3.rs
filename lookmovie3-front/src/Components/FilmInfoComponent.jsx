import { Grid, Typography, Box, Button, TextField, Rating, duration } from "@mui/material";
import { PlayArrow, PlaylistAdd } from "@mui/icons-material";
import React, { useState } from "react";
import axios from "axios";
import { API_BASE_URL } from "../App";
import InfoDialog from "./DialogComponent";
import FilmCardComponent from "./FilmCardComponent";

const FilmInfoComponent = ({ film ,close }) => {
  const [rating, setRating] = useState(0);
  const [comment, setComment] = useState('');

  const [dialogOpen, setDialogOpen] = useState(false);



  const handleFilmClick = (event) => {
    console.log("Share icon clicked");
    setDialogOpen(true);
  };

  const handleCloseDialog = () => {
    setDialogOpen(false);
  };

  const handleRatingChange = (event, newRating) => {
    setRating(newRating);
  };

  const handleCommentChange = (event) => {
    setComment(event.target.value);
  };
  

  const handleSubmitRating = () => {
    let submitRating = 
    {
        rating: rating,
        filmId: film.id
    }
    axios.post(`${API_BASE_URL}/film-events/rating`, submitRating, {
        withCredentials: true,
    }).then((response) => {
        console.log(response);
        
        close(<FilmCardComponent film={response.data} clickable = {false}></FilmCardComponent>);
    }).catch((error) => {
        console.log(error);
    });
    console.log('Submitted rating:', rating);
  };

  const handleSubmitComment = () => {
    let commented = 
    {
        isPositive: true,
        filmId: film.id,
        review: comment
    }
    axios.post(`${API_BASE_URL}/film-events/review`, commented, {
        withCredentials: true,
    }).then((response) => {
        console.log(response);
        close(<FilmCardComponent film={response.data} clickable = {false}></FilmCardComponent>);

    }).catch((error) => {
        console.log(error);
    });
    console.log('Submitted comment:', comment);
  };

  const handleWatchClick = () => {
    let watched = 
    {

        filmId: film.id,
        duration: 1000
    }
    axios.post(`${API_BASE_URL}/film-events/watched`, watched, {
        withCredentials: true,
    }).then((response) => {
        console.log(response);
        close(<FilmCardComponent film={response.data} clickable = {false}></FilmCardComponent>);

    }).catch((error) => {
        console.log(error);
    });
    console.log('Watch button clicked');
  };

  const handleWishlistClick = () => {
    let wishlisted = 
    {
        filmId: film.id,
    }
    axios.post(`${API_BASE_URL}/film-events/wishlist`, wishlisted, {
        withCredentials: true,
    }).then((response) => {
        console.log(response);
        close(<FilmCardComponent film={response.data} clickable = {false}></FilmCardComponent>);

    }).catch((error) => {
        console.log(error);
    });
    console.log('Add to wishlist button clicked');
  };

  return (
    <>
      <Grid container spacing={2} padding={2}>
        <Grid item xs={12} sm={4}>
          <img
            src={`${film.image}`}
            alt={film.name}
            style={{ width: "100%", height: "auto" }}
          />
        </Grid>
        <Grid item xs={12} sm={8}>
          <Typography variant="body1">{film.description}</Typography>
          <Box mt={2}>
            <Typography variant="body2">
              <strong>Year:</strong> {film.year}
            </Typography>
            <Typography variant="body2">
              <strong>Main Actor:</strong> {film.mainActor.name + " " + film.mainActor.surname}
            </Typography>
            <Typography variant="body2">
              <strong>Director:</strong> {film.director.name + " " + film.director.surname}
            </Typography>
          </Box>
        </Grid>
        <Grid item xs={12}>
          <Box mt={4} display="flex" justifyContent="space-between" alignItems="center">
            <Typography variant="h6">Rate this film:</Typography>
            <Rating
              name="film-rating"
              value={rating}
              onChange={handleRatingChange}
            />
            <Button 
              variant="contained" 
              color="primary" 
              onClick={handleSubmitRating}
              style={{ marginLeft: '10px' }}
            >
              Rate
            </Button>
            <Button 
              variant="contained" 
              color="primary" 
              onClick={handleWatchClick}
              startIcon={<PlayArrow />}
              style={{ marginLeft: '10px' }}

            >
              Play
            </Button>
            <Button 
              variant="contained" 
              color="secondary" 
              onClick={handleWishlistClick}
              startIcon={<PlaylistAdd />}
              style={{ marginLeft: '10px' }}

            >
              Wishlist
            </Button>
          </Box>
          <Box mt={4}>
            <Typography variant="h6">Leave a comment:</Typography>
            <TextField
              fullWidth
              variant="outlined"
              multiline
              rows={4}
              value={comment}
              onChange={handleCommentChange}
            />
            <Button 
              variant="contained" 
              color="primary" 
              onClick={handleSubmitComment}
              style={{ marginTop: '10px' }}
            >
            Send
            </Button>
          </Box>
        </Grid>
        <InfoDialog
        open={dialogOpen}
        onClose={() => handleCloseDialog()}
        content={<FilmCardComponent film={film}/>}
      ></InfoDialog>
      </Grid>
    </>
  );
};

export default FilmInfoComponent;
