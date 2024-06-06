import { React, useState } from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import InfoDialog from "./DialogComponent";
import FilmInfoComponent from "./FilmInfoComponent";

const FilmCardComponent = ({ film ,clickable = true }) => {
  const [dialogOpen, setDialogOpen] = useState(false);
  const [content, setContent] = useState(null);

  const handleFilmClick = (event) => {
    if (!clickable) {
      setDialogOpen(false);
      return;
    }
    console.log("Share icon clicked");
    setContent(<FilmInfoComponent film={film} close={handleCloseDialog} />);
    setDialogOpen(true);
  };

  const handleCloseDialog = (c) => {
    setDialogOpen(false);
    if (c) {
      setContent(c);
      setDialogOpen(true);
    }
  };

  return (
    <>
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
        <CardContent
          sx={{ cursor: "pointer" }}
          onClick={() => handleFilmClick(true)}
        >
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
        </CardContent>
      </Card>
      <InfoDialog
        open={dialogOpen}
        onClose={() => handleCloseDialog()}
        title={film.name}
        content={content}
      ></InfoDialog>
    </>
  );
};

export default FilmCardComponent;
