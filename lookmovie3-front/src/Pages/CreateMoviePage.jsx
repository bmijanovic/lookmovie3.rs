import React, { useEffect, useState } from "react";
import { API_BASE_URL } from "../App";
import axios from "axios";
import BasicForm from "../Components/BasicForm";

const CreateMoviePage = () => {
  const [actors, setActors] = useState([]);
  const [directors, setDirectors] = useState([]);
  const [selectedActors, setSelectedActors] = useState();
  const [selectedDirectors, setSelectedDirectors] = useState();

  let t = [
    {
      item: "ImageDrop",
    },
    {
      item: "BasicInput",
      label: "Name",
      itemValue: "name",
    },
    {
      item: "BasicInput",
      label: "Genre",
      itemValue: "genre",
    },
    {
      item: "BasicInput",
      label: "Duration",
      itemValue: "duration",
      type: "number",
    },
    {
      item: "BasicInput",
      label: "Year",
      itemValue: "year",
      type: "number",
    },
    {
      item: "BasicInput",
      label: "Award",
      itemValue: "award",
    },
    {
      item: "BasicInput",
      label: "Description",
      itemValue: "description",
    },
    {
      item: "BasicSelect",
      label: "Actors",
      selected: selectedActors,
      collection: actors,
      valueParam: "id",
      nameParam: "name",
      nameParam2: "surname",

      itemValue: "mainActorId",

    },
    {
        item: "BasicSelect",
        label: "Directors",
        selected: selectedDirectors,
        collection: directors,
        valueParam: "id",
        nameParam: "name",
        nameParam2: "surname",
        itemValue: "directorId",
  
      },
  ];

  useEffect(() => {
    axios
      .get(`${API_BASE_URL}/films/get-actors`, { withCredentials: true })
      .then((response) => {
        console.log(response);
        setActors(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const test = async (form) => {
    axios.post(`${API_BASE_URL}/films/create-film`, form, { withCredentials: true })
    .then((response) => {
      console.log(response);
    })
    .catch((error) => {
      console.log(error);
    });

    console.log(form);
  };

  useEffect(() => {
    axios
      .get(`${API_BASE_URL}/films/get-directors`, { withCredentials: true })
      .then((response) => {
        console.log(response);
        setDirectors(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div>
      <BasicForm
        style={{ width: "70%" }}
        template={t}
        callback={test}
        buttonName={"Create Movie"}
      ></BasicForm>
    </div>
  );
};

export default CreateMoviePage;
